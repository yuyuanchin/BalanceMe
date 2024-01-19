package com.bm.balanceme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

public class WorkoutDisplay extends AppCompatActivity {

    private VideoView videoView;
    private Button restartButton;
    private Button pauseButton;
    private TextView countdownTextView;
    private CountDownTimer countDownTimer;

    private void updateCountdownText(long millisUntilFinished) {
        long seconds = millisUntilFinished / 1000;
        long minutes = seconds / 60;
        seconds = seconds % 60;

        // Format the time as "mm:ss" and set it to the TextView
        String timeString = String.format("%02d:%02d", minutes, seconds);
        countdownTextView.setText(timeString);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workoutdisplay);

        Intent intent = getIntent();
        String workoutTitle = intent.getStringExtra("workoutTitle");
        String exerciseName = intent.getStringExtra("exerciseName");
        String description = intent.getStringExtra("description");
        int videoName = intent.getIntExtra("videoName", -1);
        String difficulty = intent.getStringExtra("difficulty");
        String duration = intent.getStringExtra("duration");
        int workoutImg = intent.getIntExtra("workoutImg", -1);

        // Use the data as needed (e.g., set text to TextViews)
        TextView workoutTitleTextView = findViewById(R.id.workoutTitle);
        TextView exerciseTitleTextView = findViewById(R.id.exerciseTitle);

        workoutTitleTextView.setText(workoutTitle);
        exerciseTitleTextView.setText(exerciseName);

        countdownTextView = findViewById(R.id.countdown); // Replace with the actual ID of your TextView

        long initialTimeInMillis = parseTimeString(description);

        countDownTimer = new CountDownTimer(initialTimeInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateCountdownText(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                // Handle countdown finish (e.g., display a message)
                countdownTextView.setText("Done!");
            }
        };

        // Set up VideoView
        videoView = findViewById(R.id.videoView);

        // Sample video file names (replace with your actual video files)
        int videoResource = videoName;

        // Set the video source
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + videoResource));

        // Start playing the video
        videoView.start();

        // Start the countdown timer
        countDownTimer.start();

        // Set up buttons
        restartButton = findViewById(R.id.restartbutton);
        pauseButton = findViewById(R.id.pausebutton);

        // Set click listeners for buttons
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Restart the video
                videoView.seekTo(0);
                videoView.start();
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Pause or resume the video
                if (videoView.isPlaying()) {
                    videoView.pause();
                    pauseButton.setText("Continue");
                } else {
                    videoView.start();
                    pauseButton.setText("Pause");
                }
            }
        });

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                // Start playing the video once it's prepared
                mediaPlayer.setLooping(true);
                videoView.start();
            }
        });

        ImageView imageView6 = findViewById(R.id.imageView7);

        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToWorkoutDetails(workoutTitle, difficulty, duration, workoutImg);
            }
        });
    }

    private void navigateToWorkoutDetails(String workoutTitle, String difficulty, String duration, int workoutImg) {

        Intent intent = new Intent(this, WorkoutDetails.class);
        intent.putExtra("workoutTitle", workoutTitle);
        intent.putExtra("difficulty", difficulty);
        intent.putExtra("duration", duration);
        intent.putExtra("workoutImg", workoutImg);
        startActivity(intent);
    }

    private long parseTimeString(String timeString) {
        String[] timeComponents = timeString.split(":");
        int minutes = Integer.parseInt(timeComponents[0]);
        int seconds = Integer.parseInt(timeComponents[1]);
        return (minutes * 60 + seconds) * 1000; // Convert to milliseconds
    }

    private void startCountdown(long initialTimeInMillis) {
        countDownTimer = new CountDownTimer(initialTimeInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                runOnUiThread(() -> updateCountdownText(millisUntilFinished));
            }

            @Override
            public void onFinish() {
                runOnUiThread(() -> {
                    updateCountdownText(0); // Update to show 00:00 when finished
                    // Handle countdown finish (e.g., display a message)
                    countdownTextView.setText("Done!");
                });
            }
        };
    }
}
