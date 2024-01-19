package com.bm.balanceme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    EditText loginEmail, loginPassword;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmail = findViewById(R.id.email);
        loginPassword = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateEmail() || !validatePassword()) {

                } else {
                    checkUser();
                }
            }
        });
    }

    public Boolean validateEmail() {
        String val = loginEmail.getText().toString();
        if (val.isEmpty()) {
            loginEmail.setError("Email cannot be empty");
            return false;
        } else {
            loginEmail.setError(null);
            return true;
        }
    }

    public Boolean validatePassword() {
        String val = loginPassword.getText().toString();
        if (val.isEmpty()) {
            loginPassword.setError("Password cannot be empty");
            return false;
        } else {
            loginPassword.setError(null);
            return true;
        }
    }

    public void checkUser() {
        String userEmail = loginEmail.getText().toString().trim();
        String modifiedEmail = userEmail.replace(".", "_");
        String userPassword = loginPassword.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("email").equalTo(modifiedEmail);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    loginEmail.setError(null);

                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                        String passwordFromDb = userSnapshot.child("password").getValue(String.class);

                        if (passwordFromDb.equals(userPassword)) {
                            loginEmail.setError(null);

                            String nameFromDb = userSnapshot.child("name").getValue(String.class);
                            String ageFromDb = userSnapshot.child("age").getValue(String.class);
                            String heightFromDb = userSnapshot.child("height").getValue(String.class);
                            String weightFromDb = userSnapshot.child("weight").getValue(String.class);

                            String formattedHeight = heightFromDb + " cm";
                            String formattedWeight = weightFromDb + " kg";

                            Intent intent = new Intent(Login.this, MainActivity.class);

                            intent.putExtra("name", nameFromDb);
                            intent.putExtra("email", modifiedEmail);
                            intent.putExtra("age", ageFromDb);
                            intent.putExtra("height", formattedHeight);
                            intent.putExtra("weight", formattedWeight);
                            startActivity(intent);
                            return; // exit the loop once a matching user is found
                        }
                    }

                    // If the loop completes without finding a matching user/password
                    loginPassword.setError("Invalid Credentials");
                    loginPassword.requestFocus();
                } else {
                    Log.d("Login", "User does not exist");
                    loginEmail.setError("User does not exist");
                    loginEmail.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Database error: " + error.getMessage());
            }
        });
    }

}