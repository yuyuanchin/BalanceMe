package com.bm.balanceme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class WorkoutExerciseDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workoutexercisedetails);

        int imageId = getIntent().getIntExtra("imageId", R.drawable.ic_launcher_foreground); // Provide a default image ID
        String exerciseName = getIntent().getStringExtra("exerciseName");

        // Update the ImageView and TextView with the received data
        ImageView imageView8 = findViewById(R.id.imageView8);
        TextView textView3 = findViewById(R.id.textView3);

        imageView8.setImageResource(imageId);
        textView3.setText(exerciseName);

        ImageView imageView6 = findViewById(R.id.imageView6);

        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace with the code to navigate to the Workout Fragment
                navigateToWorkoutDetails();
            }
        });

    }

    private void navigateToWorkoutDetails() {

        Intent intent = new Intent(this, WorkoutDetails.class);
        startActivity(intent);
    }

    private void navigateToWorkoutDisplay() {

        Intent intent = new Intent(this, WorkoutDisplay.class);
        startActivity(intent);
    }
}