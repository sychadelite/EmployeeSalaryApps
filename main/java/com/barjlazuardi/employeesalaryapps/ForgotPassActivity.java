package com.barjlazuardi.employeesalaryapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ForgotPassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
    }

    public void onBackPressed() {
        startActivity(new Intent(ForgotPassActivity.this, LoginActivity.class));
    }

}