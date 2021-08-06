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

public class LoginActivity extends AppCompatActivity {

    TextView signUpText;
    //User email and password
    EditText loginEmail, loginPassword;
    //Sign in button
    Button signInBtn;
    //Firebase auth
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        //Initialize firebase auth
        auth = FirebaseAuth.getInstance();
        //Button if user needs to register
        signUpText = findViewById(R.id.signUpTxt);
        //Get email input
        loginEmail = findViewById(R.id.emailLogin);
        //Get password input
        loginPassword = findViewById(R.id.passwordLogin);
        //Sign in button
        signInBtn = findViewById(R.id.signInBtn);
        //Email validation pattern
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        //When user clicks on sign in button
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Retrieve text from input and convert to string
                String email = loginEmail.getText().toString();
                String password = loginPassword.getText().toString();

                //Validations for empty email and password fields
                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
                {
                    Toast.makeText(LoginActivity.this,"Enter Valid Info", Toast.LENGTH_SHORT).show();
                }

                //Validation for proper email pattern
                else if(!email.matches(emailPattern))
                {
                    loginEmail.setError("Invalid Email");
                    Toast.makeText(LoginActivity.this,"Invalid Email", Toast.LENGTH_SHORT).show();
                }

                //Validation for password length
                else if(password.length()<6)
                {
                    loginPassword.setError("Invalid Password");
                    Toast.makeText(LoginActivity.this,"Invalid Password", Toast.LENGTH_SHORT).show();

                }

                //If everything passes validation, sign user in
                else {
                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(LoginActivity.this, MessageActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(LoginActivity.this, "Login Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

        //If user does not have an account
        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
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