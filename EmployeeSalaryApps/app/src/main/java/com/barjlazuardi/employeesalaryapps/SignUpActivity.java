package com.barjlazuardi.employeesalaryapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignUpActivity extends AppCompatActivity {

    private TextInputEditText fullName, userName, userEmail, userPassword, phoneNumber, gender, address, birthday;
    private TextInputLayout layoutFullname, layoutUsername, layoutEmail, layoutPassword, layoutPhoneNumber, layoutGender, layoutAddress, layoutBirthday;
    private Button regButton;
    private ImageView back;
    private CircleImageView userProfilePic;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    String fullname, username, email, phone, gen, addr, birth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateRegister()) {
                    // Upload data to the firebase
                    String user_email = userEmail.getText().toString().trim();
                    String user_password = userPassword.getText().toString().trim();

                    progressDialog.setMessage("Sending Your Data...");
                    progressDialog.show();

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                progressDialog.dismiss();
                                // If you want to get verification first on sign up process
                                sendEmailVerification();

                                // If you don't want to get verification first on sign up process
                                //sendUserData();
                                //Toast.makeText(SignUpActivity.this, "Successfully Registered, Upload Complete!", Toast.LENGTH_LONG).show();
                                //finish();
                                //startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                            }else {
                                progressDialog.dismiss();
                                Toast.makeText(SignUpActivity.this,"Registration Failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                };
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(SignUpActivity.this);
                alertDialogBuilder.setMessage("The data will not be saved, Are you sure ?");

                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                    }
                });

                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

                android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

    }

    private void setupUIViews(){
        fullName = (TextInputEditText) findViewById(R.id.et_fullname);
        userName = (TextInputEditText) findViewById(R.id.et_username);
        userEmail = (TextInputEditText) findViewById(R.id.et_email);
        userPassword = (TextInputEditText) findViewById(R.id.et_password);
        phoneNumber = (TextInputEditText) findViewById(R.id.et_phone);
        gender = (TextInputEditText) findViewById(R.id.et_gender);
        address = (TextInputEditText) findViewById(R.id.et_address);
        birthday = (TextInputEditText) findViewById(R.id.et_birth);

        layoutFullname = (TextInputLayout) findViewById(R.id.layoutInputFullname);
        layoutUsername = (TextInputLayout) findViewById(R.id.layoutInputUsername);
        layoutEmail = (TextInputLayout) findViewById(R.id.layoutInputEmail);
        layoutPassword = (TextInputLayout) findViewById(R.id.layoutInputPassword);
        layoutPhoneNumber = (TextInputLayout) findViewById(R.id.layoutInputPhone);
        layoutGender = (TextInputLayout) findViewById(R.id.layoutInputGender);
        layoutAddress = (TextInputLayout) findViewById(R.id.layoutInputAddress);
        layoutBirthday = (TextInputLayout) findViewById(R.id.layoutInputBirth);

        regButton = (Button) findViewById(R.id.btn_reg);
        back = (ImageView) findViewById(R.id.btn_back);
    }

    private Boolean validateRegister() {
        Boolean result = true;

        fullname = fullName.getText().toString();
        username = userName.getText().toString();
        phone = phoneNumber.getText().toString();
        gen = gender.getText().toString();
        addr = address.getText().toString();
        birth = birthday.getText().toString();
        email = userEmail.getText().toString();

        //String fullname = fullName.getText().toString();
        //String username = userName.getText().toString();
        //String email = userEmail.getText().toString();
        //String password = userPassword.getText().toString();
        //String phone = phoneNumber.getText().toString();
        //String gen = gender.getText().toString();
        //String addr = address.getText().toString();
        //String birth = birthday.getText().toString();

        //String fullName = layoutFullname.getEditText().getText().toString().trim();
        //String userName = layoutUsername.getEditText().getText().toString().trim();
        //String emailInput = layoutEmail.getEditText().getText().toString().trim();
        //String passwordInput = layoutPassword.getEditText().getText().toString().trim();
        //String phoneNumberInput = layoutPhoneNumber.getEditText().getText().toString().trim();
        //String genderInput = layoutGender.getEditText().getText().toString().trim();
        //String addressInput = layoutAddress.getEditText().getText().toString().trim();
        //String birthdayInput = layoutBirthday.getEditText().getText().toString().trim();

        // "|" all validation, "||" one-by-one validation
        if(!validateFullname() | !validateUsername() | !validateEmail() | !validatePassword() | !validatePhoneNumber() | !validateGender() | !validateAddress() | !validateBirthday()) {
            result = false;
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }else {

        }

        return result;
    }

    private boolean validateFullname() {
        String fullName = layoutFullname.getEditText().getText().toString().trim();

        if (fullName.isEmpty()) {
            layoutFullname.setError("Field can't be empty");
            return false;
        }else if (fullName.length() > 30) {
            layoutFullname.setError("Your name is too long");
            return false;
        }else {
            layoutFullname.setError(null);
            return true;
        }
    }

    private boolean validateUsername() {
        String userName = layoutUsername.getEditText().getText().toString().trim();

        if (userName.isEmpty()) {
            layoutUsername.setError("Field can't be empty");
            return false;
        }else if (userName.length() < 6){
            layoutUsername.setError("Username is too short");
            return false;
        }else {
            layoutUsername.setError(null);
            return true;
        }
    }

    private boolean validateEmail() {
        String emailInput = layoutEmail.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()) {
            layoutEmail.setError("Field can't be empty");
            return false;
        }else {
            layoutEmail.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = layoutPassword.getEditText().getText().toString().trim();

        if (passwordInput.isEmpty()) {
            layoutPassword.setError("Field can't be empty");
            return false;
        }else if (passwordInput.length() < 6){
            layoutPassword.setError("Password is too easy");
            return false;
        }else {
            layoutPassword.setEndIconActivated(true);
            layoutPassword.setError(null);
            return true;
        }
    }

    private boolean validatePhoneNumber() {
        String phoneNumberInput = layoutPhoneNumber.getEditText().getText().toString().trim();

        if (phoneNumberInput.isEmpty()) {
            layoutPhoneNumber.setError("Field can't be empty");
            return false;
        }else if (phoneNumberInput.length() > 20){
            layoutPhoneNumber.setError("Phone number is too long");
            return false;
        }else {
            layoutPhoneNumber.setError(null);
            return true;
        }
    }

    private boolean validateGender() {
        String genderInput = layoutGender.getEditText().getText().toString().trim();

        if (genderInput.isEmpty()) {
            layoutGender.setError("Field can't be empty");
            return false;
        }else {
            layoutGender.setError(null);
            return true;
        }
    }

    private boolean validateAddress() {
        String addressInput = layoutAddress.getEditText().getText().toString().trim();

        if (addressInput.isEmpty()) {
            layoutAddress.setError("Field can't be empty");
            return false;
        }else {
            layoutAddress.setError(null);
            return true;
        }
    }

    private boolean validateBirthday() {
        String birthdayInput = layoutBirthday.getEditText().getText().toString().trim();

        if (birthdayInput.isEmpty()) {
            layoutBirthday.setError("Field can't be empty");
            return false;
        }else {
            layoutBirthday.setError(null);
            return true;
        }
    }

    //public void confirmInput(View v) {
        //if (!validateFullname() | !validateEmail() | !validateUsername() | !validatePassword() | !validatePhoneNumber() | !validateGender() | !validateAddress() | !validateBirthday()) {
            //return;
        //}

        //String input = "Fullname: " + layoutFullname.getEditText().getText().toString();
        //input += "Email: " + layoutEmail.getEditText().getText().toString();
        //input += "\n";
        //input += "Username: " + layoutUsername.getEditText().getText().toString();
        //input += "\n";
        //input += "Password: " + layoutUsername.getEditText().getText().toString();
        //input += "\n";
        //input += "Phone Number: " + layoutPhoneNumber.getEditText().getText().toString();
        //input += "\n";
        //input += "Gender: " + layoutGender.getEditText().getText().toString();
        //input += "\n";
        //input += "Phone Address: " + layoutAddress.getEditText().getText().toString();
        //input += "\n";
        //input += "Phone Birthday: " + layoutBirthday.getEditText().getText().toString();

        //Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    //}

    private void sendEmailVerification() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser != null) {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()) {
                        sendUserData();
                        Toast.makeText(SignUpActivity.this, "Successfully Registered, Please check your Email for Verification!", Toast.LENGTH_LONG).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                    }else {
                        Toast.makeText(SignUpActivity.this, "Verification Email has not been sent!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendUserData() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
        UserProfile userProfile = new UserProfile(addr, birth, email, fullname, gen, phone, username);
        myRef.setValue(userProfile);
    }

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setMessage("The data will not be saved, Are you sure ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Jika user memilih YES maka akan finish/selesai --> Keluar dari aplikasi
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));;
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Jika user memilih NO maka akan kembali ke activity
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

}