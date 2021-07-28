package com.barjlazuardi.employeesalaryapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
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

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private Animation fromleft, fromright;
    private TextInputLayout layoutEmail, layoutPassword;
    private TextInputEditText userEmail, userPassword;
    private TextView topText, warning, forget, signUp;
    private Button login;
    private int counter = 5;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    Toast toast;

    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupUIViews();

        // Animation
        topText.setAnimation(fromleft);
        lottieAnimationView.setAnimation(fromright);

        warning.setText("No of attempts remaining: 5");

        // Setup Firebase
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        // Auto Login If there is a user
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null){
            finish();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }

        // Input
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    // Sign In to apps
                    String email = userEmail.getText().toString().trim();
                    String password = userPassword.getText().toString();

                    // Initialize Progress Dialog
                    progressDialog = new ProgressDialog(LoginActivity.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.progress_dialog_login_layout);
                    progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));

                    firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                progressDialog.dismiss();
                                checkEmailVerification();
                            }else {
                                setToast("Email or Password is Wrong");
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

    private void setupUIViews(){

        // Setup Animation
        fromright = AnimationUtils.loadAnimation(this,R.anim.fromright);
        fromleft = AnimationUtils.loadAnimation(this,R.anim.fromleft);
        lottieAnimationView = findViewById(R.id.lottie);

        layoutEmail = (TextInputLayout) findViewById(R.id.layoutInputEmail);
        layoutPassword = (TextInputLayout) findViewById(R.id.layoutInputPassword);

        topText = (TextView) findViewById(R.id.topText);
        userEmail = (TextInputEditText) findViewById(R.id.inputEmail);
        userPassword = (TextInputEditText) findViewById(R.id.inputPassword);
        warning = (TextView) findViewById(R.id.warning);
        login = (Button) findViewById(R.id.btnLogin);
        forget = (TextView) findViewById(R.id.forgot_password);
        signUp = (TextView) findViewById(R.id.sign_up);

    }

    private Boolean validate() {
        Boolean result = false;

        String email = userEmail.getText().toString();
        String password = userPassword.getText().toString();

        if(email.isEmpty() || password.isEmpty()) {
            setToast("Please enter all of the details");
        }else {

            result = true;
        }

        return result;
    }

    private void checkEmailVerification() {
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();

        if(emailflag) {
            finish();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }else {
            setToast("Verify your email");
            firebaseAuth.signOut();
        }
    }

    // Implement Toast
    public void setToast(String message) {
        if (toast != null) {
            toast.cancel();
        }

        toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void onBackPressed() {
        finishAffinity();
    }

}