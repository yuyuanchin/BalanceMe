package com.bm.balanceme;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TodayChallengeActivity extends AppCompatActivity {

    private TextView timerTextView;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_challenge);

        // Find the ImageButton by ID
        ImageButton imageButton = findViewById(R.id.imageButton6);

        // Set OnClickListener for the ImageButton
        imageButton.setOnClickListener(v -> {
            // Navigate back to the previous page (finish the current activity)
            finish();
        });

        timerTextView = findViewById(R.id.timerTextView);

        // Set the countdown timer for 3 minutes (180,000 milliseconds)
        countDownTimer = new CountDownTimer(180000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Update the TextView with the remaining time
                updateTimerText(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                // Handle the timer finish event
                timerTextView.setText("Finish");
        }
        };

        // Start the countdown timer
        countDownTimer.start();
    }

    private void updateTimerText(long millisUntilFinished) {
        int seconds = (int) (millisUntilFinished / 1000);
        int minutes = seconds / 60;
        seconds = seconds % 60;

        @SuppressLint("DefaultLocale") String timerText = String.format("%02d:%02d", minutes, seconds);
        timerTextView.setText(timerText);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Cancel the countdown timer to prevent memory leaks
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
