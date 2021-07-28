package com.barjlazuardi.employeesalaryapps;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.Arrays;

public class ExploreFragment extends Fragment {

    public ExploreFragment() {
        //Required Empty Public Constructor
    }

    RecyclerView reelsRecycler, stakeholdersRecycler, mostViewedRecycler, categoriesRecycler;
    RecyclerView.Adapter adapter;
    GradientDrawable gradient1, gradient2, gradient3, gradient4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_explore, container, false);

        //Hooks
        reelsRecycler = v.findViewById(R.id.reels_recycler);
        reelsRecycler();

        stakeholdersRecycler = v.findViewById(R.id.stakeholders_recycler);
        stakeholdersRecycler();

        mostViewedRecycler = v.findViewById(R.id.most_viewed_recycler);
        mostViewedRecycler();

        categoriesRecycler = v.findViewById(R.id.categories_recycler);
        categoriesRecycler();

        return v;

    }

    private void reelsRecycler() {

        reelsRecycler.setHasFixedSize(true);
        reelsRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        ArrayList<ReelsHelperClass> reelsLocations = new ArrayList<>();

        reelsLocations.add(new ReelsHelperClass(R.drawable.ic_baseline_add_fourth_24, "Your Story"));
        reelsLocations.add(new ReelsHelperClass(R.drawable.barj, "barjlazuardi"));
        reelsLocations.add(new ReelsHelperClass(R.drawable.barj, "sahlun31"));
        reelsLocations.add(new ReelsHelperClass(R.drawable.barj, "fadhilarya"));
        reelsLocations.add(new ReelsHelperClass(R.drawable.barj, "rafinabil"));
        reelsLocations.add(new ReelsHelperClass(R.drawable.barj, "adamcanray"));
        reelsLocations.add(new ReelsHelperClass(R.drawable.barj, "idhamfaidzi"));

        adapter = new ReelsAdapter(reelsLocations);
        reelsRecycler.setAdapter(adapter);

    }

    private void stakeholdersRecycler() {

        stakeholdersRecycler.setHasFixedSize(true);
        stakeholdersRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));

        ArrayList<StakeholdersHelperClass> stakeholdersLocations = new ArrayList<>();

        stakeholdersLocations.add(new StakeholdersHelperClass(R.drawable.barj, 4f, "barjlazuardi", "Hello, my name is Barj, My hobbies are playing football, music, games and so many more"));
        stakeholdersLocations.add(new StakeholdersHelperClass(R.drawable.barj, 3.5f, "sahlun31", "Hello, my name is Sahal, My hobbies are playing football, music, games and so many more"));
        stakeholdersLocations.add(new StakeholdersHelperClass(R.drawable.barj, 5f, "fadhilarya", "'Hello, my name is Fadil, My hobbies are playing football, music, games and so many more"));
        stakeholdersLocations.add(new StakeholdersHelperClass(R.drawable.barj, 2f, "rafinabil", "Hello, my name is Abi, My hobbies are playing football, music, games and so many more"));

        adapter = new StakeholdersAdapter(stakeholdersLocations);
        stakeholdersRecycler.setAdapter(adapter);

    }

    private void mostViewedRecycler() {

        mostViewedRecycler.setHasFixedSize(true);
        mostViewedRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        ArrayList<MostViewedHelperClass> mostViewedLocations = new ArrayList<>();

        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.narcos, 4f, "Ramon Arellano Felix"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.narcos, 4.5f, "Edenrobe"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.narcos, 3f, "Krazy-8"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.narcos, 3.7f, "Walrus"));

        adapter = new MostViewedAdapter(mostViewedLocations);
        mostViewedRecycler.setAdapter(adapter);

    }

    private void categoriesRecycler() {

        //All Gradients
        gradient2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffd4cbe5, 0xffd4cbe5});
        gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xff7adccf, 0xff7adccf});
        gradient3 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xfff7c59f, 0xFFf7c59f});
        gradient4 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffb8d7f5, 0xffb8d7f5});


        ArrayList<CategoriesHelperClass> categoriesHelperClasses = new ArrayList<>();

        categoriesHelperClasses.add(new CategoriesHelperClass(R.drawable.pegawais, "Education", gradient1));
        categoriesHelperClasses.add(new CategoriesHelperClass(R.drawable.pegawais, "HOSPITAL", gradient2));
        categoriesHelperClasses.add(new CategoriesHelperClass(R.drawable.pegawais, "Restaurant", gradient3));
        categoriesHelperClasses.add(new CategoriesHelperClass(R.drawable.pegawais, "Shopping", gradient4));
        categoriesHelperClasses.add(new CategoriesHelperClass(R.drawable.pegawais, "Transport", gradient2));


        categoriesRecycler.setHasFixedSize(true);
        adapter = new CategoriesAdapter(categoriesHelperClasses);
        categoriesRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        categoriesRecycler.setAdapter(adapter);

    }

}
