package com.bm.balanceme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bm.balanceme.Adaptor.WorkoutAdapter;
import com.bm.balanceme.Database.WorkoutDataSource;
import com.bm.balanceme.Domain.WorkoutDomain;

import java.util.ArrayList;
import java.util.List;

public class WorkoutSave extends AppCompatActivity {

    private RecyclerView savedWorkoutRecyclerView;
    private WorkoutAdapter savedWorkoutAdapter;
    private WorkoutDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workoutsave);

        ImageView imageView3 = findViewById(R.id.imageView3);

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace with the code to navigate to the Workout Fragment
                navigateToWorkoutFragment();
            }
        });

        savedWorkoutRecyclerView = findViewById(R.id.savedWorkoutRecycleView);
        dataSource = new WorkoutDataSource(this);
        dataSource.open();

        setupSavedWorkoutRecyclerView();
        dataSource.clearAllWorkoutData();
    }

    private void setupSavedWorkoutRecyclerView() {
        ArrayList<WorkoutDomain> savedWorkoutList = (ArrayList<WorkoutDomain>) dataSource.getAllWorkouts();

        savedWorkoutAdapter = new WorkoutAdapter(savedWorkoutList);
        savedWorkoutRecyclerView.setAdapter(savedWorkoutAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        savedWorkoutRecyclerView.setLayoutManager(layoutManager);
    }

    private void navigateToWorkoutFragment() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}