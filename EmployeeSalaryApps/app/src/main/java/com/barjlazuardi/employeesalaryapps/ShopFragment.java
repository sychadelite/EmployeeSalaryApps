package com.barjlazuardi.employeesalaryapps;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.barjlazuardi.employeesalaryapps.DRVinterface.LoadMore;
import com.google.android.material.navigation.NavigationView;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ShopFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {

    public ShopFragment() {
        //Required Empty Public Constructor
    }

    private RecyclerView recyclerView;
    private StaticRecyclerViewAdapter staticRecyclerViewAdapter;

    List<DynamicRecyclerViewModel> items = new ArrayList();
    DynamicRecyclerViewAdapter dynamicRecyclerViewAdapter;

    ImageView iconDrawer;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    androidx.appcompat.widget.Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_shop, container, false);

        iconDrawer = v.findViewById(R.id.ic_nav_drawer);
        iconDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.END);
            }
        });

        // Navigation Drawer
        drawerLayout = v.findViewById(R.id.drawer_layout);
        navigationView = v.findViewById(R.id.nav_view);
        toolbar = v.findViewById(R.id.toolbar);

        // Toolbar
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        // Navigation Drawer Menu
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);


        // FOOD SHOP
        ArrayList<StaticRecyclerViewModel> item = new ArrayList<>();
        item.add(new StaticRecyclerViewModel(R.drawable.pizza, "Pizza"));
        item.add(new StaticRecyclerViewModel(R.drawable.burger, "Burger"));
        item.add(new StaticRecyclerViewModel(R.drawable.fries, "French Fries"));
        item.add(new StaticRecyclerViewModel(R.drawable.sandwich, "Sandwich"));
        item.add(new StaticRecyclerViewModel(R.drawable.friedchicken, "Fried Chicken"));
        item.add(new StaticRecyclerViewModel(R.drawable.kebab, "Kebab"));
        item.add(new StaticRecyclerViewModel(R.drawable.soup, "Soup"));

        recyclerView = v.findViewById(R.id.rv_1);
        staticRecyclerViewAdapter = new StaticRecyclerViewAdapter(item);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(staticRecyclerViewAdapter);

        items.add(new DynamicRecyclerViewModel("Burger King"));
        items.add(new DynamicRecyclerViewModel("Burger Blenger"));
        items.add(new DynamicRecyclerViewModel("Burger Huffizer"));
        items.add(new DynamicRecyclerViewModel("Burger Mozarella"));
        items.add(new DynamicRecyclerViewModel("Burger Turkey"));
        items.add(new DynamicRecyclerViewModel("Burger California"));
        items.add(new DynamicRecyclerViewModel("Burger Local Pride"));
        items.add(new DynamicRecyclerViewModel("Burger XL"));
        items.add(new DynamicRecyclerViewModel("Burger XXX"));
        items.add(new DynamicRecyclerViewModel("Burger Large"));
        items.add(new DynamicRecyclerViewModel("Burger Titanium"));
        items.add(new DynamicRecyclerViewModel("Burger Krabby"));
        items.add(new DynamicRecyclerViewModel("Burger Salmon"));
        items.add(new DynamicRecyclerViewModel("Burger Wagyu"));
        items.add(new DynamicRecyclerViewModel("Burger Straight"));
        items.add(new DynamicRecyclerViewModel("Burger Biomas"));
        items.add(new DynamicRecyclerViewModel("Burger Chicken"));
        items.add(new DynamicRecyclerViewModel("Burger Vegetarian"));

        RecyclerView drv = v.findViewById(R.id.rv_2);
        drv.setLayoutManager(new LinearLayoutManager(getActivity()));

        dynamicRecyclerViewAdapter = new DynamicRecyclerViewAdapter(drv, getActivity(), items);
        drv.setAdapter(dynamicRecyclerViewAdapter);

        dynamicRecyclerViewAdapter.setLoadMore(new LoadMore() {
            @Override
            public void onLoadMore() {
                if (items.size() <= 10) {
                    items.add(null);
                    dynamicRecyclerViewAdapter.notifyItemInserted(items.size()-1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            items.remove(items.size()-1);
                            dynamicRecyclerViewAdapter.notifyItemRemoved(items.size());

                            int index = items.size();
                            int end = index + 10;
                            for (int i = index; i<end; i++) {
                                String name = UUID.randomUUID().toString();
                                DynamicRecyclerViewModel item = new DynamicRecyclerViewModel(name);
                                items.add(item);
                            }
                            dynamicRecyclerViewAdapter.notifyDataSetChanged();
                            dynamicRecyclerViewAdapter.setLoaded();
                        }
                    },4000);
                }
//                else
//                    Toast.makeText(getActivity(),"Data Completed", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        return true;
    }

//    public void onBackPressed() {
//        if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
//            drawerLayout.closeDrawer(GravityCompat.END);
//        }
//    }

}
