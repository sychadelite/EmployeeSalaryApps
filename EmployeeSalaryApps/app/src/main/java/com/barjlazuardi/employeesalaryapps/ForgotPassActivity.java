package com.barjlazuardi.employeesalaryapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassActivity extends AppCompatActivity {

    private TextInputEditText userEmail;
    private Button resetButton;
    private ImageView back, info;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    Toast toast;

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
                    setToast("Please enter your registered email");
                }else {
                    progressDialog = new ProgressDialog(ForgotPassActivity.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.progress_dialog_reset_password_layout);
                    progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));

                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                progressDialog.dismiss();
                                setToast("Password reset email has been sent !");
                                finish();
                                startActivity(new Intent(ForgotPassActivity.this, LoginActivity.class));
                            }else {
                                progressDialog.dismiss();
                                setToast("Email is invalid");
                            }
                        }
                    });
                }
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSuccessDialog();
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
        info = (ImageView) findViewById(R.id.btn_info);
    }

    private void showSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPassActivity.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(ForgotPassActivity.this).inflate(
                R.layout.dialog_success_layout,
                (ConstraintLayout) findViewById(R.id.dialog_container)
        );
        builder.setView(view);
        ((ImageView) view.findViewById(R.id.icon)).setImageResource((R.drawable.ic_baseline_done_24));
        ((TextView) view.findViewById(R.id.title)).setText(getResources().getString(R.string.success_title));
        ((TextView) view.findViewById(R.id.message)).setText(getResources().getString(R.string.forgot_pass_info));
        ((Button) view.findViewById(R.id.btn_action)).setText(getResources().getString(R.string.okay));

        final AlertDialog alertDialog = builder.create();

        view.findViewById(R.id.btn_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        alertDialog.show();
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
        startActivity(new Intent(ForgotPassActivity.this, LoginActivity.class));
    }

}