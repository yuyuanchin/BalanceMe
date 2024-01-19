package com.bm.balanceme;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class TipsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tips, container, false);

        Button writediaryButton = rootView.findViewById(R.id.button5);
        writediaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWriteDiaryActivity();
            }
        });

        Button checkButton = rootView.findViewById(R.id.button2);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiaryHistoryActivity();
            }
        });

        Button viewTipsButton = rootView.findViewById(R.id.button4);
        viewTipsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openViewTipsActivity();
            }
        });

        Button viewTips2Button = rootView.findViewById(R.id.button6);
        viewTips2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openViewTips2Activity();
            }
        });

        Button viewTips3Button = rootView.findViewById(R.id.button7);
        viewTips3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openViewTips3Activity();
            }
        });

        return rootView;
    }

    private void openWriteDiaryActivity() {
        Intent intent = new Intent(requireActivity(), WriteDiaryActivity.class);
        startActivity(intent);
    }

    private void openDiaryHistoryActivity() {
        Intent intent = new Intent(requireActivity(), DiaryHistoryActivity.class);
        startActivity(intent);
    }

    private void openViewTipsActivity() {
        Intent intent = new Intent(requireActivity(), ViewTipsActivity.class);
        startActivity(intent);
    }

    private void openViewTips2Activity() {
        Intent intent = new Intent(requireActivity(), ViewTips2Activity.class);
        startActivity(intent);
    }

    private void openViewTips3Activity() {
        Intent intent = new Intent(requireActivity(), ViewTips3Activity.class);
        startActivity(intent);
    }
}