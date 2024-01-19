package com.bm.balanceme;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class PastChallengesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_challenges);

        // Initialize RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewPastChallenges);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Sample data for past challenges (replace this with your actual data)
        ArrayList<PastChallengeItem> pastChallengeList = generateSampleData();

        // Create and set adapter
        PastChallengesAdapter adapter = new PastChallengesAdapter(this, pastChallengeList);
        recyclerView.setAdapter(adapter);
    }

    // Sample data generation (replace this with your actual data source)
    private ArrayList<PastChallengeItem> generateSampleData() {
        ArrayList<PastChallengeItem> data = new ArrayList<>();
        data.add(new PastChallengeItem("14 Nov 2023", R.drawable.joined1,"Burn Your Calories", "Rank: 37/88"));
        data.add(new PastChallengeItem("26 Dec 2023", R.drawable.joined2, "Leg Training", "Rank 56/122"));
        data.add(new PastChallengeItem("14 Nov 2023", R.drawable.joined1,"Burn Your Calories", "Rank: 37/88"));
        data.add(new PastChallengeItem("26 Dec 2023", R.drawable.joined2, "Leg Training", "Rank 56/122"));
        data.add(new PastChallengeItem("14 Nov 2023", R.drawable.joined1,"Burn Your Calories", "Rank: 37/88"));
        data.add(new PastChallengeItem("26 Dec 2023", R.drawable.joined2, "Leg Training", "Rank 56/122"));
        // Add more items as needed

        return data;
    }

    // Handle Close button click
    public void onCloseButtonClick(View view) {
        finish(); // Close the activity
    }
}
