package com.barjlazuardi.employeesalaryapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private ImageView imageViewPerson;
    private Animation fromleft, fromright;
    private TextInputEditText userEmail, userPassword;
    private TextView topText, warning, forget, signUp;
    private Button login;
    private int counter = 5;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Setup Animation
        fromright = AnimationUtils.loadAnimation(this,R.anim.fromright);
        fromleft = AnimationUtils.loadAnimation(this,R.anim.fromleft);
        // Setup Header
        imageViewPerson = (ImageView) findViewById(R.id.imageViewPerson);
        topText = (TextView) findViewById(R.id.topText);

        userEmail = (TextInputEditText) findViewById(R.id.inputEmail);
        userPassword = (TextInputEditText) findViewById(R.id.inputPassword);
        warning = (TextView) findViewById(R.id.warning);
        login = (Button) findViewById(R.id.btnLogin);
        forget = (TextView) findViewById(R.id.forgot_password);
        signUp = (TextView) findViewById(R.id.sign_up);

        warning.setText("No of attempts remaining: 5");

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null){
            finish();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }

        // Animation
        topText.setAnimation(fromleft);
        imageViewPerson.setAnimation(fromright);

        // Input
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    // Sign In to apps
                    String email = userEmail.getText().toString().trim();
                    String password = userPassword.getText().toString().trim();

                    progressDialog.setMessage("Please Wait, We're Working On It...");
                    progressDialog.show();

                    firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                progressDialog.dismiss();
                                checkEmailVerification();
                            }else {
                                Toast.makeText(LoginActivity.this, "Email or Password is Wrong", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                counter--;

                                warning.setText("Number of attempts remaining: " + counter);

                                if(counter == 0) {
                                    login.setEnabled(false);
                                }
                            }
                        }
                    });
                }
            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_forget = new Intent(LoginActivity.this, ForgotPassActivity.class);
                startActivity(intent_forget);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_signup = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent_signup);
            }
        });

    }

    private Boolean validate() {
        Boolean result = false;

        String email = userEmail.getText().toString();
        String password = userPassword.getText().toString();

        if(email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }else {
            result = true;
        }

        return result;
    }

    public void onBackPressed() {
        finishAffinity();
    }

    private void checkEmailVerification() {
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();

        if(emailflag) {
            finish();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }else {
            Toast.makeText(this, "Verify your email", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }

}