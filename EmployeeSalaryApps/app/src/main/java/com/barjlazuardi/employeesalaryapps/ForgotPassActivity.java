package com.barjlazuardi.employeesalaryapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassActivity extends AppCompatActivity {

    private TextInputEditText userEmail;
    private Button resetButton;
    private ImageView back;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail = userEmail.getText().toString().trim();

                if(useremail.equals("")) {
                    Toast.makeText(ForgotPassActivity.this, "Please enter your registered email", Toast.LENGTH_SHORT).show();
                }else {
                    progressDialog.setMessage("Verifying Your Email...");
                    progressDialog.show();
                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                progressDialog.dismiss();
                                Toast.makeText(ForgotPassActivity.this, "Password reset email has been sent!", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(ForgotPassActivity.this, LoginActivity.class));
                            }else {
                                progressDialog.dismiss();
                                Toast.makeText(ForgotPassActivity.this, "Email is invalid", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgotPassActivity.this, LoginActivity.class));
            }
        });

    }

    private void setupUIViews(){
        userEmail = (TextInputEditText) findViewById(R.id.inputEmail);
        resetButton = (Button) findViewById(R.id.btn_reset);
        back = (ImageView) findViewById(R.id.btn_back);
    }

    public void onBackPressed() {
        startActivity(new Intent(ForgotPassActivity.this, LoginActivity.class));
    }

}