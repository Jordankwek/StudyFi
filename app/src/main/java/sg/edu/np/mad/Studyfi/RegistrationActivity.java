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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    TextView signInTxt;
    EditText regName, regEmail, regPassword, regcPassword;
    Button signUpBtn;
    private FirebaseAuth auth;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().hide();

        auth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://studyfi-19a30-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference databaseReference = database.getReference();


        signInTxt = findViewById(R.id.signInTxt);
        regName = findViewById(R.id.nameReg);
        regEmail = findViewById(R.id.emailReg);
        regPassword = findViewById(R.id.passwordReg);
        regcPassword = findViewById(R.id.cPasswordReg);
        signUpBtn = findViewById(R.id.signUpBtn);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = regName.getText().toString();
                String email = regEmail.getText().toString();
                String password = regPassword.getText().toString();
                String cPassword = regcPassword.getText().toString();


                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(email) ||
                TextUtils.isEmpty(password) || TextUtils.isEmpty(cPassword))
                {
                    Toast.makeText(RegistrationActivity.this, "Enter Valid Input", Toast.LENGTH_SHORT).show();
                }
                else if (!email.matches(emailPattern))
                {
                    regEmail.setError("Enter Valid Email");
                    Toast.makeText(RegistrationActivity.this, "Enter Valid Email", Toast.LENGTH_SHORT).show();
                }
                else if (!password.equals(cPassword))
                {
                    Toast.makeText(RegistrationActivity.this, "Password does not match", Toast.LENGTH_SHORT).show();
                }
                else if (password.length()<6)
                {
                    Toast.makeText(RegistrationActivity.this, "Enter 6 Character Password", Toast.LENGTH_SHORT);
                }
                else {
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d("pass", "pass");
                                DatabaseReference reference = database.getReference().child("Users").child(auth.getUid());
                                User user = new User();
                                user.uid = auth.getUid();
                                user.email = email;
                                user.name = name;
                                addDatatoFirebase(user.uid, email, name);
                                Intent intent = new Intent(RegistrationActivity.this, MessageActivity.class);
                                startActivity(intent);
                                //FirebaseUser currentUser = auth.getCurrentUser();
                            } else {
                                Toast.makeText(RegistrationActivity.this, "Error creating new user!", Toast.LENGTH_SHORT);
                            }
                        }
                    });
                }
            }
            private void addDatatoFirebase(String uid, String name, String email)
            {
                User user = new User();
                user.setUid(uid);
                user.setName(name);
                user.setEmail(email);
                databaseReference.child("Users").child(uid).setValue(user);
            }
        });

        signInTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }


}