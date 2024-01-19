package com.bm.balanceme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.bm.balanceme.Adaptor.CategoryAdaptor;
import com.bm.balanceme.Domain.CategoryDomain;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements EditProfileDialog.OnProfileUpdatedListener {

    BottomNavigationView bottomNavigationView;
    WorkoutFragment workoutFragment = new WorkoutFragment();
    ChallengeFragment challengeFragment = new ChallengeFragment();
    TipsFragment tipsFragment = new TipsFragment();
    RecommendFragment recommendFragment = new RecommendFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,workoutFragment).commit();

        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            String email = intent.getStringExtra("email");
            String age = intent.getStringExtra("age");
            String height = intent.getStringExtra("height");
            String weight = intent.getStringExtra("weight");

            bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem item) {
                    if (item.getItemId() == R.id.workout) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, workoutFragment).commit();
                        return true;
                    } else if (item.getItemId() == R.id.challenge) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, challengeFragment).commit();
                        return true;
                    } else if (item.getItemId() == R.id.recommend) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, recommendFragment).commit();
                        return true;
                    } else if (item.getItemId() == R.id.tips) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, tipsFragment).commit();
                        return true;
                    } else if (item.getItemId() == R.id.profile) {
                        Bundle bundle = new Bundle();
                        if (updatedName != null) {
                            bundle.putString("name", updatedName);
                            bundle.putString("email", updatedEmail);
                            bundle.putString("age", updatedAge);
                            bundle.putString("height", updatedHeight);
                            bundle.putString("weight", updatedWeight);
                            updatedName = null; // Reset the updated information
                        } else {
                            // If no updated information, use the original information
                            bundle.putString("name", name);
                            bundle.putString("email", email);
                            bundle.putString("age", age);
                            bundle.putString("height", height);
                            bundle.putString("weight", weight);
                        }
                        profileFragment.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragment).commit();
                        return true;
                    }
                    return false;
                }
            });
        }
    }

    private String updatedName;
    private String updatedEmail;
    private String updatedAge;
    private String updatedHeight;
    private String updatedWeight;

    @Override
    public void onProfileUpdated(String name, String email, String age, String height, String weight) {
        updatedName = name;
        updatedEmail = email;
        updatedAge = age;
        updatedHeight = height;
        updatedWeight = weight;

        // Update the ProfileFragment with the new information
        ProfileFragment profileFragment = (ProfileFragment) getSupportFragmentManager().findFragmentById(R.id.container);
        if (profileFragment != null) {
            profileFragment.updateProfile(updatedName, updatedEmail, updatedAge, updatedHeight, updatedWeight);
        }
    }
}