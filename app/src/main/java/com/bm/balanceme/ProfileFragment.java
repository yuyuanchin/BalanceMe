package com.bm.balanceme;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ProfileFragment extends Fragment {

    private TextView nameTextView;
    private TextView emailTextView;
    private TextView ageTextView;
    private TextView heightTextView;
    private TextView weightTextView;

    public ProfileFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        nameTextView = view.findViewById(R.id.name);
        emailTextView = view.findViewById(R.id.email);
        ageTextView = view.findViewById(R.id.age);
        heightTextView = view.findViewById(R.id.height);
        weightTextView = view.findViewById(R.id.weight);

        Button editBtn = view.findViewById(R.id.editButton);

        if (getArguments() != null) {
            String name = getArguments().getString("name");
            String email = getArguments().getString("email");
            String age = getArguments().getString("age");
            String height = getArguments().getString("height");
            String weight = getArguments().getString("weight");

            nameTextView.setText(name);
            emailTextView.setText(email);
            ageTextView.setText(age);
            heightTextView.setText(height);
            weightTextView.setText(weight);
        }

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEditProfileDialog();
            }
        });

        return view;
    }

    private void showEditProfileDialog() {
        EditProfileDialog editProfileDialog = new EditProfileDialog();

        // Set user information
        Bundle userInfo = new Bundle();
        userInfo.putString("name", nameTextView.getText().toString());
        userInfo.putString("email", emailTextView.getText().toString());
        userInfo.putString("age", ageTextView.getText().toString());
        userInfo.putString("height", heightTextView.getText().toString());
        userInfo.putString("weight", weightTextView.getText().toString());

        editProfileDialog.setArguments(userInfo);

        // Show the dialog
        editProfileDialog.show(getParentFragmentManager(), "EditProfileDialog");
    }

    public void updateProfile(String name, String email, String age, String height, String weight) {
        nameTextView.setText(name);
        emailTextView.setText(email);
        ageTextView.setText(age);
        heightTextView.setText(height);
        weightTextView.setText(weight);
    }
}