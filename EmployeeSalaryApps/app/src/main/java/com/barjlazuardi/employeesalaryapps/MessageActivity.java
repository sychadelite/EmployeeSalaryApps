package com.barjlazuardi.employeesalaryapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class MessageActivity extends AppCompatActivity {

    ImageView back;
    TextView profileUsername;
    Toast toast;

    TabLayout tabLayout;
    ViewPager2 pager2;
    MessageAdapterFragment fragmentMessageAdapter;

    private FirebaseAuth firebaseAuth;

    private GestureDetectorCompat gestureDetectorCompat;
    float x1,x2,y1,y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        profileUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetProfile();
            }
        });

        // Tab Layout with View Pager2 Adapter
        FragmentManager fm = getSupportFragmentManager();
        fragmentMessageAdapter = new MessageAdapterFragment(fm, getLifecycle());
        pager2.setAdapter(fragmentMessageAdapter);

        tabLayout.addTab(tabLayout.newTab().setText("Chats"));
        tabLayout.addTab(tabLayout.newTab().setText("Rooms"));
        tabLayout.addTab(tabLayout.newTab().setText("Status"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

//        // Gesture Detector
//        gestureDetectorCompat = new GestureDetectorCompat(this, new GestureListener());

    }

    private void setupUIViews() {

        back = (ImageView) findViewById(R.id.btn_back);
        profileUsername = (TextView) findViewById(R.id.username);

        tabLayout = findViewById(R.id.tab_layout);
        pager2 = findViewById(R.id.view_pager2);

    }

    private void showBottomSheetProfile() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MessageActivity.this, R.style.BottomSheetDialogTheme);

        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet_profile_layout,
                (LinearLayout) findViewById(R.id.bottom_sheet_container));

        bottomSheetView.findViewById(R.id.bottom_sheet_indicator).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });

        bottomSheetView.findViewById(R.id.user_account).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setToast("Long Clicked");
                return false;
            }
        });

        bottomSheetView.findViewById(R.id.user_add_account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                MessageActivity.this.finish();
                startActivity(new Intent(MessageActivity.this, LoginActivity.class));
            }
        });

        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }

    // Implement Toast
    public void setToast(String message) {
        if (toast != null) {
            toast.cancel();
        }

        toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }

    // Implement onBackPressed
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

//    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
//
//        @Override
//        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//            setToast("Fling");
//            return super.onFling(e1, e2, velocityX, velocityY);
//        }
//
//        @Override
//        public boolean onDoubleTap(MotionEvent e) {
//            setToast("Double Tap");
//            return super.onDoubleTap(e);
//        }
//
//        @Override
//        public boolean onSingleTapConfirmed(MotionEvent e) {
//            setToast("Single Tap Confirmed");
//            return super.onSingleTapConfirmed(e);
//        }
//    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        gestureDetectorCompat.onTouchEvent(event);
//        return super.onTouchEvent(event);
//    }

    //    public boolean onTouchEvent(MotionEvent motionEvent) {
//        switch (motionEvent.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                x1 = motionEvent.getX();
//                y1 = motionEvent.getY();
//                break;
//            case MotionEvent.ACTION_UP:
//                x2 = motionEvent.getX();
//                y2 = motionEvent.getY();
//                if (x1 < x2) {
//                    // Swipe To Right
//                    finish();
//                } else if (x1 > x2) {
//                    // Swipe To Left
//                } else if (y1 < y2) {
//                    // Swipe To Top
//                } else if (y1 > y2) {
//                    // Swipe To Bottom
//                }
//                break;
//        }
//        return false;
//    }

}