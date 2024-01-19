package com.bm.balanceme;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteDiaryActivity extends AppCompatActivity {
    private EditText editTextEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.writediary);

        editTextEntry = findViewById(R.id.editTextText);

        Button saveButton = findViewById(R.id.button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDiaryEntry();
            }
        });

        ImageButton backButton = findViewById(R.id.imageButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the current activity and return to the previous page
            }
        });


    }

    private void saveDiaryEntry() {
        String entryText = editTextEntry.getText().toString();

        // Get the current date and time
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateAndTime = sdf.format(new Date());

        try {
            // Save the entry with the current date and time to a file
            String fileName = "diary_entries.txt";
            FileOutputStream fileOutputStream = openFileOutput(fileName, MODE_APPEND);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.write("Date: " + currentDateAndTime + "\n");
            outputStreamWriter.write(entryText + "\n\n");
            outputStreamWriter.close();

            // Optionally, you can provide feedback to the user (e.g., a Toast message)
            // Toast.makeText(this, "Entry saved successfully", Toast.LENGTH_SHORT).show();

            // Clear the EditText after saving
            editTextEntry.setText("");
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception, for example, by showing an error message to the user
        }
    }
}
