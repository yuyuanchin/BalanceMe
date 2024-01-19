package com.bm.balanceme;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private FragmentGyms fragmentGyms;
    private FragmentParks fragmentParks;
    private FragmentYogaStudios fragmentYogaStudios;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                fragmentGyms = new FragmentGyms();
                return fragmentGyms;
            case 1:
                fragmentParks = new FragmentParks();
                return fragmentParks;
            case 2:
                fragmentYogaStudios = new FragmentYogaStudios();
                return fragmentYogaStudios;
            default:
                return new FragmentGyms();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    // Add methods to get references to the fragments
    public FragmentGyms getFragmentGyms() {
        return fragmentGyms;
    }

    public FragmentParks getFragmentParks() {
        return fragmentParks;
    }

    public FragmentYogaStudios getFragmentYogaStudios() {
        return fragmentYogaStudios;
    }
}
