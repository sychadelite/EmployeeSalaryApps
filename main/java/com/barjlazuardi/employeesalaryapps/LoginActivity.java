package com.barjlazuardi.employeesalaryapps;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    TextView topText;
    Animation fromleft;

    ImageView imageViewPerson;
    Animation fromright;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Animation
        topText = (TextView) findViewById(R.id.topText);
        fromleft = AnimationUtils.loadAnimation(this,R.anim.fromleft);
        topText.setAnimation(fromleft);

        imageViewPerson = (ImageView) findViewById(R.id.imageViewPerson);
        fromright = AnimationUtils.loadAnimation(this,R.anim.fromright);
        imageViewPerson.setAnimation(fromright);


        Button btn_login = (Button) findViewById(R.id.btnLogin);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_login = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent_login);
            }
        });

        Button forget = (Button) findViewById(R.id.forgot_password);
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_forget = new Intent(LoginActivity.this, ForgotPassActivity.class);
                startActivity(intent_forget);
            }
        });

        Button signup = (Button) findViewById(R.id.sign_up);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_signup = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent_signup);
            }
        });

    }

    public void onBackPressed() {
        startActivity(new Intent(LoginActivity.this, IntroductoryActivity.class));
    }

}