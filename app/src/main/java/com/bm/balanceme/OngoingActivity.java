package com.bm.balanceme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Calendar;

public class OngoingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ongoing);

        ImageButton backButton = findViewById(R.id.imageButton2);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close the current activity and go back
                finish();
            }
        });

        // Find the "Start Today Challenge" button
        Button startChallengeButton = findViewById(R.id.button2);

        // Set OnClickListener for the button
        startChallengeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the TodayChallengeActivity when the button is clicked
                startActivity(new Intent(OngoingActivity.this, TodayChallengeActivity.class));
            }
        });

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2023);
        calendar.set(Calendar.MONTH, 11);  // Note: Months in Calendar are 0-indexed (0 = January, 11 = December)
        calendar.set(Calendar.DAY_OF_MONTH, 23);

        long millis = calendar.getTimeInMillis();

        CalendarView calendarView = findViewById(R.id.calendarView2);
        calendarView.setDate(millis);

        // Find the TextView in your layout
        TextView textView = findViewById(R.id.textView11);

        // Get the string from resources
        String description = getString(R.string.ongoing_desc);

        // Create a SpannableString
        SpannableString spannableString = new SpannableString(description);

        // Find the position of "Read More" in the string
        int readMoreStart = description.indexOf("Read More");

        // Create a ClickableSpan for "Read More"
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                // Do nothing on click
            }
        };

        // Set the ClickableSpan for "Read More"
        spannableString.setSpan(clickableSpan, readMoreStart, readMoreStart + "Read More".length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Change the color of "Read More"
        spannableString.setSpan(new android.text.style.ForegroundColorSpan(Color.BLUE), readMoreStart, readMoreStart + "Read More".length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Set the SpannableString to the TextView
        textView.setText(spannableString);

        // Make the TextView clickable
        textView.setMovementMethod(null);
    }
}