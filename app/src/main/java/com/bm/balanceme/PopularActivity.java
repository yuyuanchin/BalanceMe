package com.bm.balanceme;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PopularActivity extends AppCompatActivity {

    private void setActivityResult() {
        setResult(Activity.RESULT_OK);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewPopular);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Read data from text file
        List<PopularItem> popularItemList = readPopularItemsFromTextFile();

        // Create an instance of PopularAdapter and set the OnJoinClickListener
        PopularAdapter adapter = new PopularAdapter(this, popularItemList);
        adapter.setOnJoinClickListener(position -> {
            // Implement the logic for handling the join button click
            // You can perform any additional actions here if needed
            // For now, the logic is kept empty
        });

        recyclerView.setAdapter(adapter);
    }

    private List<PopularItem> readPopularItemsFromTextFile() {
        List<PopularItem> popularItemList = new ArrayList<>();

        try {
            // Open the text file from res/raw
            InputStream inputStream = getResources().openRawResource(R.raw.popular_items);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line using the delimiter (comma in this case)
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    // Create a PopularItem and add it to the list
                    popularItemList.add(new PopularItem(
                            getImageResourceByName(parts[0]),
                            parts[1],
                            parts[2],
                            parts[3]
                    ));
                }
            }

            // Close the reader
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return popularItemList;
    }

    // Get image resource ID by name
    private int getImageResourceByName(String name) {
        Resources resources = getResources();
        return resources.getIdentifier(name, "drawable", getPackageName());
    }

    // Handle Close button click
    public void onCloseButtonClick(View view) {
        setActivityResult();
    }
}
