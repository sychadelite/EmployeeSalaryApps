package com.barjlazuardi.employeesalaryapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
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
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignUpActivity extends AppCompatActivity {

    private TextInputEditText etFullname, etUsername, etEmail, etPassword, etPhoneNumber, etGender, etAddress, etBirthday;
    private TextInputLayout layoutFullname, layoutUsername, layoutEmail, layoutPassword, layoutPhoneNumber, layoutGender, layoutAddress, layoutBirthday;
    private Button regButton;
    private ImageView back, info;
    private CircleImageView userProfilePic;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    Toast toast;

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
                    String user_email = etEmail.getText().toString().trim();
                    String user_password = etPassword.getText().toString().trim();

                    // Initialize Progress Dialog
                    progressDialog = new ProgressDialog(SignUpActivity.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.progress_dialog_loading_layout);
                    // Set Transparent Background
                    progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));

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
                                setToast("Registration Failed");
                            }

                        }
                    });
                };
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
                showWarningDialog();
            }
        });

    }

    private void setupUIViews(){
        // Setup Text Input Edit Text
        etFullname = (TextInputEditText) findViewById(R.id.et_fullname);
        etUsername = (TextInputEditText) findViewById(R.id.et_username);
        etEmail = (TextInputEditText) findViewById(R.id.et_email);
        etPassword = (TextInputEditText) findViewById(R.id.et_password);
        etPhoneNumber = (TextInputEditText) findViewById(R.id.et_phone);
        etGender = (TextInputEditText) findViewById(R.id.et_gender);
        etAddress = (TextInputEditText) findViewById(R.id.et_address);
        etBirthday = (TextInputEditText) findViewById(R.id.et_birth);

        // Setup Text Changed Listener for Realtime Validation
        etFullname.addTextChangedListener(new ValidationTextWatcher(etFullname));
        etUsername.addTextChangedListener(new ValidationTextWatcher(etUsername));
        etEmail.addTextChangedListener(new ValidationTextWatcher(etEmail));
        etPassword.addTextChangedListener(new ValidationTextWatcher(etPassword));
        etPhoneNumber.addTextChangedListener(new ValidationTextWatcher(etPhoneNumber));
        etGender.addTextChangedListener(new ValidationTextWatcher(etGender));
        etAddress.addTextChangedListener(new ValidationTextWatcher(etAddress));
        etBirthday.addTextChangedListener(new ValidationTextWatcher(etBirthday));

        // Setup Text Input Layout
        layoutFullname = (TextInputLayout) findViewById(R.id.layoutInputFullname);
        layoutUsername = (TextInputLayout) findViewById(R.id.layoutInputUsername);
        layoutEmail = (TextInputLayout) findViewById(R.id.layoutInputEmail);
        layoutPassword = (TextInputLayout) findViewById(R.id.layoutInputPassword);
        layoutPhoneNumber = (TextInputLayout) findViewById(R.id.layoutInputPhone);
        layoutGender = (TextInputLayout) findViewById(R.id.layoutInputGender);
        layoutAddress = (TextInputLayout) findViewById(R.id.layoutInputAddress);
        layoutBirthday = (TextInputLayout) findViewById(R.id.layoutInputBirth);

        // Setup Buttons
        regButton = (Button) findViewById(R.id.btn_reg);
        back = (ImageView) findViewById(R.id.btn_back);
        info = (ImageView) findViewById(R.id.btn_info);
    }

    private Boolean validateRegister() {
        Boolean result = true;

        // UserProfile data Realtime Firebase Database
        fullname = etFullname.getText().toString();
        username = etUsername.getText().toString();
        phone = etPhoneNumber.getText().toString();
        gen = etGender.getText().toString();
        addr = etAddress.getText().toString();
        birth = etBirthday.getText().toString();
        email = etEmail.getText().toString();

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
        if (!validateFullname() | !validateUsername() | !validateEmail() | !validatePassword() | !validatePhoneNumber() | !validateGender() | !validateAddress() | !validateBirthday()) {
            result = false;
            showErrorDialog();
        }

        return result;
    }

    // Implement Text Changed Listener
    private class ValidationTextWatcher implements TextWatcher {
        private View view;

        private ValidationTextWatcher(View view) {
            this.view = view;
        }
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }
        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.et_fullname:
                    validateFullname();
                    break;
                case R.id.et_username:
                    validateUsername();
                    break;
                case R.id.et_email:
                    validateEmail();
                    break;
                case R.id.et_password:
                    validatePassword();
                    break;
                case R.id.et_phone:
                    validatePhoneNumber();
                    break;
                case R.id.et_gender:
                    validateGender();
                    break;
                case R.id.et_address:
                    validateAddress();
                    break;
                case R.id.et_birth:
                    validateBirthday();
                    break;
            }
        }
    }

    // Implement Soft Input Focus
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private boolean validateFullname() {

        if (etFullname.getText().toString().isEmpty()) {
            layoutFullname.setErrorEnabled(false);
        }else if (etFullname.getText().toString().length() > 30) {
            layoutFullname.setError("Your name is too long");
            requestFocus(etPassword);
            return false;
        }else {
//            int color_fourth = getResources().getColor(R.color.fourth);
//            int color_white = getResources().getColor(R.color.white);
//            etFullname.setTextColor(ColorStateList.valueOf(color_white));
//            layoutFullname.setBoxBackgroundColorStateList(ColorStateList.valueOf(color_fourth));
//            layoutFullname.setHintTextColor(ColorStateList.valueOf(color_white));
//            layoutFullname.setDefaultHintTextColor(ColorStateList.valueOf(color_white));
//            layoutFullname.setStartIconTintList(ColorStateList.valueOf(color_white));
            layoutFullname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateUsername() {

        if (etUsername.getText().toString().trim().isEmpty()) {
            layoutUsername.setError("Username is required");
            requestFocus(etUsername);
            return false;
        }else if (etUsername.getText().toString().trim().length() < 6){
            layoutUsername.setError("Username is too short");
            requestFocus(etUsername);
            return false;
        }else {
            int color_green = getResources().getColor(R.color.helper_success);
            layoutUsername.setHelperText("Username is valid");
            layoutUsername.setHelperTextColor(ColorStateList.valueOf(color_green));
        }

        return true;
    }

    private boolean validateEmail() {

        if (etEmail.getText().toString().trim().isEmpty()) {
            layoutEmail.setError("Email is required");
            requestFocus(etEmail);
            return false;
        }else {
            String emailId = etEmail.getText().toString();
            Boolean  isValid = android.util.Patterns.EMAIL_ADDRESS.matcher(emailId).matches();
            if (!isValid) {
                layoutEmail.setError("Invalid Email address, ex: abc@example.com");
                requestFocus(etEmail);
                return false;
            }else {
                int color_green = getResources().getColor(R.color.helper_success);
                layoutEmail.setHelperText("Email is valid");
                layoutEmail.setHelperTextColor(ColorStateList.valueOf(color_green));
            }
        }


        return true;
    }

//    private boolean validateUsedEmail() {
//        firebaseAuth.fetchSignInMethodsForEmail(etEmail.getText().toString())
//                .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
//                    @Override
//                    public void onComplete(@NonNull @NotNull Task<SignInMethodQueryResult> task) {
//                        boolean check = !task.getResult().getSignInMethods().isEmpty();
//                        if (check) {
//                            layoutEmail.setError("Email address already used");
//                            requestFocus(etEmail);
//                        }
//
//                    }
//                });
//
//        return true;
//    }

    private boolean validatePassword() {

        if (etPassword.getText().toString().isEmpty()) {
            layoutPassword.setError("Password is required");
            requestFocus(etPassword);
            return false;
        }else if (etPassword.getText().toString().length() < 6){
            layoutPassword.setError("Password can't be less than 6 digit");
            requestFocus(etPassword);
            return false;
        }else if (etPassword.getText().toString().length() < 10){
            int color_warning = getResources().getColor(R.color.helper_warning);
            layoutPassword.setHelperText("Password is weak");
            layoutPassword.setHelperTextColor(ColorStateList.valueOf(color_warning));
        }else {
            int color_green = getResources().getColor(R.color.helper_success);
            layoutPassword.setHelperText("Password is strong");
            layoutPassword.setHelperTextColor(ColorStateList.valueOf(color_green));
        }

        return true;
    }

    private boolean validatePhoneNumber() {

        if (etPhoneNumber.getText().toString().isEmpty()) {
            layoutPhoneNumber.setErrorEnabled(false);
            layoutPhoneNumber.setEndIconDrawable(0);
        }else if (etPhoneNumber.getText().toString().length() > 20){
            layoutPhoneNumber.setError("Phone number is too long");
            requestFocus(etPhoneNumber);
            return false;
        }else {
            Drawable icon_check = getResources().getDrawable(R.drawable.ic_baseline_check_circle_24);
            layoutPhoneNumber.setEndIconDrawable(icon_check);
            layoutPhoneNumber.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateGender() {

        if (etGender.getText().toString().trim().isEmpty()) {
            layoutGender.setErrorEnabled(false);
        }else {
            layoutGender.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateAddress() {

        if (etAddress.getText().toString().isEmpty()) {
            layoutAddress.setErrorEnabled(false);
        }else {
            layoutAddress.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateBirthday() {

        if (etBirthday.getText().toString().trim().isEmpty()) {
            layoutBirthday.setErrorEnabled(false);
        }else {
            layoutBirthday.setErrorEnabled(false);
        }

        return true;
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
                        Toast.makeText(SignUpActivity.this, "Verification Email has not been sent!", Toast.LENGTH_LONG).show();
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

    // Implement Toast
    public void setToast(String message) {
        if (toast != null) {
            toast.cancel();
        }

        toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }

    // Implement Dialogs
    private void showSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(SignUpActivity.this).inflate(
                R.layout.dialog_success_layout,
                (ConstraintLayout) findViewById(R.id.dialog_container)
        );
        builder.setView(view);
        ((ImageView) view.findViewById(R.id.icon)).setImageResource((R.drawable.ic_baseline_done_24));
        ((TextView) view.findViewById(R.id.title)).setText(getResources().getString(R.string.success_title));
        ((TextView) view.findViewById(R.id.message)).setText(getResources().getString(R.string.signup_info));
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

    private void showWarningDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(SignUpActivity.this).inflate(
                R.layout.dialog_warning_layout,
                (ConstraintLayout) findViewById(R.id.dialog_container)
        );
        builder.setView(view);
        ((ImageView) view.findViewById(R.id.icon)).setImageResource((R.drawable.ic_baseline_warning_24));
        ((TextView) view.findViewById(R.id.title)).setText(getResources().getString(R.string.warning_title));
        ((TextView) view.findViewById(R.id.message)).setText(getResources().getString(R.string.data_consider_quit));
        ((Button) view.findViewById(R.id.btn_yes)).setText(getResources().getString(R.string.yes));
        ((Button) view.findViewById(R.id.btn_no)).setText(getResources().getString(R.string.no));

        final AlertDialog alertDialog = builder.create();

        view.findViewById(R.id.btn_yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                alertDialog.dismiss();
            }
        });

        view.findViewById(R.id.btn_no).setOnClickListener(new View.OnClickListener() {
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

    private void showErrorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(SignUpActivity.this).inflate(
                R.layout.dialog_error_layout,
                (ConstraintLayout) findViewById(R.id.dialog_container)
        );
        builder.setView(view);
        ((ImageView) view.findViewById(R.id.icon)).setImageResource((R.drawable.ic_baseline_error_outline_24));
        ((TextView) view.findViewById(R.id.title)).setText(getResources().getString(R.string.error_title));
        ((TextView) view.findViewById(R.id.message)).setText(getResources().getString(R.string.signup_error));
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

    public void onBackPressed() {
        showWarningDialog();
    }

}