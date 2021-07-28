package com.barjlazuardi.employeesalaryapps;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import org.jetbrains.annotations.NotNull;

public class MessageAdapterFragment extends FragmentStateAdapter {
    public MessageAdapterFragment(@NonNull @NotNull FragmentManager fragmentManager, @NonNull @NotNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 1 :
                return new RoomsFragment();
            case 2 :
                return new StatusFragment();
        }

        return new ChatsFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
