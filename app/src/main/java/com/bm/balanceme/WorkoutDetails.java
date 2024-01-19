package com.bm.balanceme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bm.balanceme.Adaptor.ExerciseAdapter;
import com.bm.balanceme.Database.WorkoutDataSource;
import com.bm.balanceme.Domain.ExerciseDomain;

import java.util.ArrayList;
import java.util.List;

public class WorkoutDetails extends AppCompatActivity {

    private WorkoutDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workoutdetails);

        dataSource = new WorkoutDataSource(this);
        dataSource.open();

        RecyclerView recyclerView = findViewById(R.id.exerciseRecycleView);

        int workoutImg = getIntent().getIntExtra("workoutImg",R.drawable.workoutlist);
        String workoutTitle = getIntent().getStringExtra("workoutTitle");
        String difficulty = getIntent().getStringExtra("difficulty");
        String duration = getIntent().getStringExtra("duration");

        ImageView imageView5 = findViewById(R.id.imageView5);
        TextView textView4 = findViewById(R.id.textView4);
        TextView textView12 = findViewById(R.id.textView12);

        imageView5.setImageResource(workoutImg);
        textView4.setText(workoutTitle);
        textView12.setText(String.format("%s | %s",difficulty,duration));


        // Replace this with your actual list of exercises
        List<ExerciseDomain> exerciseList = new ArrayList<>();
        exerciseList.add(new ExerciseDomain(R.raw.situpvid,"Sit-up", "0:20", R.drawable.situp));
        exerciseList.add(new ExerciseDomain(R.raw.vsitupvid,"V Sit-up", "0:20", R.drawable.vsitup));
        exerciseList.add(new ExerciseDomain(R.raw.elevatedcrunchvid,"Elevated Crunches", "0:15", R.drawable.elevatedcrunch));
        exerciseList.add(new ExerciseDomain(R.raw.legspreadervid,"Leg Spreaders", "0:15", R.drawable.legspreaders));
        // Add more exercises as needed

        ImageView imageView4 = findViewById(R.id.imageView4);

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace with the code to navigate to the Workout Fragment
                navigateToWorkoutFragment();
            }
        });

        ImageView save = findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace with the code to navigate to the Workout Fragment
                toggleSaveStatus(v);
            }
        });

        ExerciseAdapter adapter = new ExerciseAdapter(exerciseList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    protected void onDestroy() {
        super.onDestroy();
        dataSource.close();
    }

    private void navigateToWorkoutFragment() {

        Intent intent = new Intent(this, MainActivity.class);
         startActivity(intent);
    }

    public void startWorkout(View view) {

        int workoutImg = getIntent().getIntExtra("workoutImg", R.drawable.workoutlist);
        String workoutTitle = getIntent().getStringExtra("workoutTitle");
        String difficulty = getIntent().getStringExtra("difficulty");
        String duration = getIntent().getStringExtra("duration");

        // Get the exercise name from the first item in the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.exerciseRecycleView);
        ExerciseAdapter adapter = (ExerciseAdapter) recyclerView.getAdapter();
        if (adapter != null && adapter.getItemCount() > 0) {
            ExerciseDomain firstExercise = adapter.getItem(0);

            if (firstExercise != null) {
                int videoName = firstExercise.getVideoName(); // Get the video name
                String exerciseName = firstExercise.getName();
                String description = firstExercise.getDescription();

                // Create an intent to start the WorkoutDisplay activity
                Intent intent = new Intent(this, WorkoutDisplay.class);

                // Pass workout title, exercise name, and video name to WorkoutDisplay activity
                intent.putExtra("workoutTitle", workoutTitle);
                intent.putExtra("exerciseName", exerciseName);
                intent.putExtra("videoName", videoName);
                intent.putExtra("description",description);
                intent.putExtra("difficulty", difficulty);
                intent.putExtra("duration",duration);
                intent.putExtra("workoutImg",workoutImg);
                // Start the WorkoutDisplay activity
                startActivity(intent);
            }
        }
    }

    public void toggleSaveStatus(View view) {
        // Get workout information
        int workoutImg = getIntent().getIntExtra("workoutImg", R.drawable.workoutlist);
        String workoutTitle = getIntent().getStringExtra("workoutTitle");
        String difficulty = getIntent().getStringExtra("difficulty");
        String duration = getIntent().getStringExtra("duration");

        // Perform save or unsave operation
        boolean isSaved = dataSource.isWorkoutSaved(workoutTitle);

        if (isSaved) {
            // Unsave the workout
            // Update the database
            long savedId = dataSource.getSavedWorkoutId(workoutTitle);

            dataSource.deleteSavedWorkout(savedId);

            // Change the image back to "drawable/save.xml"
            ImageView saveImageView = findViewById(R.id.save);
            saveImageView.setImageResource(R.drawable.saved);
        } else {
            // Save the workout
            // Update the database
            dataSource.insertSavedWorkout(workoutTitle, difficulty, duration, workoutImg);

            // Change the image to "drawable/saveddark.xml"
            ImageView saveImageView = findViewById(R.id.save);
            saveImageView.setImageResource(R.drawable.saveddark);
        }
    }

    }

