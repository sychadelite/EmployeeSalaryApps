package com.barjlazuardi.employeesalaryapps;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        //Required Empty Public Constructor
    }

    private CircleImageView profilePic;
    private TextView profileUsername, profileName, profileEmail, profileAddress, profilePhone, profileGender, profileBirthday;
    private ImageView addContent;
    private Button btnOut, btnEditProfile;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    Toast toast;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        firebaseAuth = FirebaseAuth.getInstance();

        profilePic = v.findViewById(R.id.profile_pic);
        profileUsername = v.findViewById(R.id.username);
        profileName = v.findViewById(R.id.text_fullname);
        profileEmail = v.findViewById(R.id.text_email);
        profileAddress = v.findViewById(R.id.text_address);
        profilePhone = v.findViewById(R.id.text_phone);
        profileGender = v.findViewById(R.id.text_gender);
        profileBirthday = v.findViewById(R.id.text_birthday);

        btnOut = (Button) v.findViewById(R.id.btn_out);
        btnEditProfile = (Button) v.findViewById(R.id.btn_edit);

        addContent = (ImageView) v.findViewById(R.id.ic_add);

        profileUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetProfile();
            }
        });

        btnOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutWarningDialog();
            }
        });

        return v;

    }

    private void showBottomSheetProfile() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity(), R.style.BottomSheetDialogTheme);

        View bottomSheetView = LayoutInflater.from(getContext()).inflate(R.layout.bottom_sheet_stuff_layout,
                (LinearLayout) getActivity().findViewById(R.id.bottom_sheet_container));

        bottomSheetView.findViewById(R.id.bottom_sheet_indicator).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });

        bottomSheetView.findViewById(R.id.btn_review).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MainActivity.class));
                bottomSheetDialog.dismiss();
            }
        });

        bottomSheetView.findViewById(R.id.btn_fav).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setToast("Loved");
            }
        });

        bottomSheetView.findViewById(R.id.btn_cart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setToast("Added to cart");
            }
        });

        bottomSheetView.findViewById(R.id.btn_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setToast("Shared");
            }
        });

        bottomSheetView.findViewById(R.id.btn_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setToast("Request has been sent !");
                bottomSheetDialog.dismiss();
            }
        });

        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }

    private void showLogoutWarningDialog() {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(getContext(), R.style.AlertDialogTheme);
        View view = LayoutInflater.from(getActivity()).inflate(
                R.layout.dialog_warning_layout,
                (ConstraintLayout) getActivity().findViewById(R.id.dialog_container)
        );
        builder.setView(view);
        ((ImageView) view.findViewById(R.id.icon)).setImageResource((R.drawable.ic_baseline_warning_24));
        ((TextView) view.findViewById(R.id.title)).setText(getResources().getString(R.string.warning_title));
        ((TextView) view.findViewById(R.id.message)).setText(getResources().getString(R.string.logout_warning));
        ((Button) view.findViewById(R.id.btn_yes)).setText(getResources().getString(R.string.yes));
        ((Button) view.findViewById(R.id.btn_no)).setText(getResources().getString(R.string.no));

        final androidx.appcompat.app.AlertDialog alertDialog = builder.create();

        view.findViewById(R.id.btn_yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Jika user memilih YES maka akan finish/selesai --> Keluar dari aplikasi
                firebaseAuth.signOut();
                getActivity().finish();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                alertDialog.dismiss();
            }
        });

        view.findViewById(R.id.btn_no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Jika user memilih NO maka akan kembali ke activity
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

        toast = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }

}