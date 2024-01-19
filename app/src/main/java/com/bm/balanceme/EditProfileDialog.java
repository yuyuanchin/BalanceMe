package com.bm.balanceme;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfileDialog extends DialogFragment {

    private OnProfileUpdatedListener onProfileUpdatedListener;
    private EditText editName, editEmail, editAge, editHeight, editWeight;
    private Button saveBtn;
    private String nameUser, emailUser, ageUser, heightUser, weightUser;
    private DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.dialog_edit_profile, container, false);

        reference = FirebaseDatabase.getInstance().getReference("users");

        editName = view.findViewById(R.id.editName);
        editEmail = view.findViewById(R.id.editEmail);
        editAge = view.findViewById(R.id.editAge);
        editHeight = view.findViewById(R.id.editHeight);
        editWeight = view.findViewById(R.id.editWeight);
        saveBtn = view.findViewById(R.id.saveBtn);

        showData();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isNameChanged = isNameChanged();
                boolean isEmailChanged = isEmailChanged();
                boolean isAgeChanged = isAgeChanged();
                boolean isHeightChanged = isHeightChanged();
                boolean isWeightChanged = isWeightChanged();

                if (isNameChanged || isEmailChanged || isAgeChanged || isHeightChanged || isWeightChanged) {
                    Toast.makeText(requireContext(), "Saved", Toast.LENGTH_SHORT).show();

                    // Notify the listener only once with the updated information
                    if (onProfileUpdatedListener != null) {
                        onProfileUpdatedListener.onProfileUpdated(
                                nameUser, emailUser, ageUser, heightUser, weightUser
                        );
                    }
                } else {
                    Toast.makeText(requireContext(), "No Changes Found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    public void setUserInfo(String name, String email, String age, String height, String weight) {
        Bundle args = new Bundle();
        args.putString("name", name);
        args.putString("email", email);
        args.putString("age", age);
        args.putString("height", height);
        args.putString("weight", weight);
        setArguments(args);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            onProfileUpdatedListener = (OnProfileUpdatedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnProfileUpdatedListener");
        }
    }

    private boolean isNameChanged() {
        if(!nameUser.equals(editName.getText().toString())) {
            reference.child(emailUser).child("name").setValue(editName.getText().toString());
            nameUser = editName.getText().toString();
            if (onProfileUpdatedListener != null) {
                onProfileUpdatedListener.onProfileUpdated(nameUser, emailUser, ageUser, heightUser, weightUser);
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean isEmailChanged() {
        if (!emailUser.equals(editEmail.getText().toString().trim())) {
            reference.child(emailUser).child("email").setValue(editEmail.getText().toString().trim());
            emailUser = editEmail.getText().toString().trim();
            if (onProfileUpdatedListener != null) {
                onProfileUpdatedListener.onProfileUpdated(nameUser, emailUser, ageUser, heightUser, weightUser);
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean isAgeChanged() {
        if (!ageUser.equals(editAge.getText().toString())) {
            reference.child(emailUser).child("age").setValue(editAge.getText().toString());
            ageUser = editAge.getText().toString();
            if (onProfileUpdatedListener != null) {
                onProfileUpdatedListener.onProfileUpdated(nameUser, emailUser, ageUser, heightUser, weightUser);
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean isHeightChanged() {
        String heightInput = editHeight.getText().toString().trim();
        if (!heightUser.equals(heightInput)) {
            // Extract numeric value without units
            String numericHeight = heightInput.replaceAll("[^\\d.]", "");
            reference.child(emailUser).child("height").setValue(numericHeight);
            heightUser = heightInput; // Update with units
            if (onProfileUpdatedListener != null) {
                onProfileUpdatedListener.onProfileUpdated(nameUser, emailUser, ageUser, heightUser, weightUser);
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean isWeightChanged() {
        String weightInput = editWeight.getText().toString().trim();
        if (!weightUser.equals(weightInput)) {
            // Extract numeric value without units
            String numericWeight = weightInput.replaceAll("[^\\d.]", "");
            reference.child(emailUser).child("weight").setValue(numericWeight);
            weightUser = weightInput; // Update with units
            if (onProfileUpdatedListener != null) {
                onProfileUpdatedListener.onProfileUpdated(nameUser, emailUser, ageUser, heightUser, weightUser);
            }
            return true;
        } else {
            return false;
        }
    }

    private void showData() {
        Bundle bundle = getArguments();

        if (bundle != null) {
            nameUser = bundle.getString("name", "");
            emailUser = bundle.getString("email", "").replace(".", "_");
            ageUser = bundle.getString("age", "");
            heightUser = bundle.getString("height", "");
            weightUser = bundle.getString("weight", "");

            editName.setText(nameUser);
            editEmail.setText(emailUser);
            editAge.setText(ageUser);
            editHeight.setText(heightUser);
            editWeight.setText(weightUser);
        }
    }

    public interface OnProfileUpdatedListener {
        void onProfileUpdated(String name, String email, String age, String height, String weight);
    }

}