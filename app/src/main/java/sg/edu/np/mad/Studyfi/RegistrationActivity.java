package sg.edu.np.mad.Studyfi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    //Sign in text if user has an account
    TextView signInTxt;
    //Registration info
    EditText regName, regEmail, regPassword, regcPassword;
    //Sign up button
    Button signUpBtn;
    //Initialize auth
    private FirebaseAuth auth;
    //email pattern for validation
    String emailPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    //Check if user email exist
    Boolean userExist = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().hide();

        //Initialize firebase auth
        auth = FirebaseAuth.getInstance();
        //Accessing database
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://studyfi-19a30-default-rtdb.asia-southeast1.firebasedatabase.app/");

        //Reference to database data
        DatabaseReference databaseReference = database.getReference();

        //Getting from layout page
        signInTxt = findViewById(R.id.signInTxt);
        regName = findViewById(R.id.nameReg);
        regEmail = findViewById(R.id.emailReg);
        regPassword = findViewById(R.id.passwordReg);
        regcPassword = findViewById(R.id.cPasswordReg);
        signUpBtn = findViewById(R.id.signUpBtn);

        //When user clicks on sign up button
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Retrieving user registration input
                String name = regName.getText().toString().trim();
                String email = regEmail.getText().toString().trim().toLowerCase();
                String password = regPassword.getText().toString().trim();
                String cPassword = regcPassword.getText().toString().trim();

                //Checks whether email exist already
                auth.fetchSignInMethodsForEmail(email).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                        boolean isNewUser = task.getResult().getSignInMethods().isEmpty();
                        if (isNewUser){
                            userExist = false;
                        }
                        else{
                            userExist = true;
                        }
                    }
                });

                //Validation for empty name, email, password and confirm password
                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(email) ||
                TextUtils.isEmpty(password) || TextUtils.isEmpty(cPassword))
                {
                    Toast.makeText(RegistrationActivity.this, "Enter Valid Input", Toast.LENGTH_SHORT).show();
                }
                //Validation for email matching pattern
                else if (!email.matches(emailPattern))
                {
                    regEmail.setError("Enter Valid Email");
                    Toast.makeText(RegistrationActivity.this, "Enter Valid Email", Toast.LENGTH_SHORT).show();
                }
                //Validation for confirm password and password if they are the same
                else if (!password.equals(cPassword))
                {
                    Toast.makeText(RegistrationActivity.this, "Password does not match", Toast.LENGTH_SHORT).show();
                }
                //Validation for password length less than 6
                else if (password.length()<6 || cPassword.length()<6 || (cPassword.length()<6 && password.length()<6))
                {
                    Toast.makeText(RegistrationActivity.this, "Enter 6 Character Password", Toast.LENGTH_SHORT).show();
                }

                //Validation for same email registration
                else if (userExist == true)
                {
                    Toast.makeText(RegistrationActivity.this, "Email already exist", Toast.LENGTH_SHORT).show();
                }

                //If input passes validation
                else {
                    //Addition of new user to firebase
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                DatabaseReference reference = database.getReference().child("Users").child(auth.getUid());
                                //Creating new user object
                                User user = new User();
                                user.uid = auth.getUid();
                                user.email = email;
                                user.name = name;
                                //add user to firebase
                                addDatatoFirebase(user.uid, name, email);

                                //Goes to message activity after user created
                                Intent intent = new Intent(RegistrationActivity.this, MessageActivity.class);
                                startActivity(intent);
                                //FirebaseUser currentUser = auth.getCurrentUser();
                            }
                            //If creation of user fails
                            else {
                                Toast.makeText(RegistrationActivity.this, "Error creating new user!", Toast.LENGTH_SHORT);
                            }
                        }
                    });
                }
            }

            //Add user to firebase
            private void addDatatoFirebase(String uid, String name, String email)
            {
                User user = new User();
                user.setUid(uid);
                user.setName(name);
                user.setEmail(email);
                databaseReference.child("Users").child(uid).setValue(user);
            }
        });

        //If user already has an account
        signInTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    //Prevent activity from stacking
    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }




}