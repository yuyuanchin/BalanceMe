package com.bm.balanceme;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RecommendFragment extends Fragment {

    private MaterialButtonToggleGroup toggleButton;
    private ViewPager2 viewPager2;
    private ViewPagerAdapter vpAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);

        toggleButton = view.findViewById(R.id.toggleButton);
        viewPager2 = view.findViewById(R.id.viewpager);
        vpAdapter = new ViewPagerAdapter((FragmentActivity) requireContext());
        viewPager2.setAdapter(vpAdapter);

        toggleButton.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                if (isChecked) {
                    if (checkedId == R.id.button1) {
                        viewPager2.setCurrentItem(0);
                    } else if (checkedId == R.id.button2) {
                        viewPager2.setCurrentItem(1);
                    } else if (checkedId == R.id.button3) {
                        viewPager2.setCurrentItem(2);
                    }
                }
            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        toggleButton.check(R.id.button1);
                        break;
                    case 1:
                        toggleButton.check(R.id.button2);
                        break;
                    case 2:
                        toggleButton.check(R.id.button3);
                        break;
                }
            }
        });

        FloatingActionButton fabButton = view.findViewById(R.id.fabButton);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), FavouriteActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}