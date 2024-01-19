package com.bm.balanceme;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bm.balanceme.R;
import com.bm.balanceme.RCFavouriteAdapter;
import com.bm.balanceme.RCGymModel;

import java.util.ArrayList;

public class FavouriteActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<RCGymModel> modelArrayList;
    private RCFavouriteAdapter rcFavouriteAdapter;
    private ImageButton backButton;

    private String[] title = new String[]{
            "Azman Hashim USM Sports Arena",
            "Seven Star Gym Gelugor",
            "Orient Fitness"
    };

    private int[] image = new int[]{
            R.drawable.iron_fusion_fitness,
            R.drawable.epic_body_forge,
            R.drawable.powerpulse_gym
    };

    private int[] ratingIcon = new int[]{
            R.drawable.rating_4_5,
            R.drawable.rating_4_5,
            R.drawable.rating_4_5
    };

    private String[] ratingText = new String[]{
            "4.5 (341)",
            "4.3 (299)",
            "4.7 (423)"
    };

    private String[] address = new String[]{
            "20, Jalan Stadium, Taman Pekaka, 11700 Gelugor, Pulau Pinang",
            "10J, Jalan Sultan Azlan Shah, Minden Heights, 11700 Gelugor, Pulau Pinang",
            "1, Lengkok Nipah, Taman Lip Sin, 11900 Bayan Lepas, Pulau Pinang"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        recyclerView = findViewById(R.id.recyclerFavourites);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        modelArrayList = new ArrayList<>();
        rcFavouriteAdapter = new RCFavouriteAdapter(this, modelArrayList);
        recyclerView.setAdapter(rcFavouriteAdapter);

        // Initialize and set OnClickListener for the ImageButton
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Finish the current activity (equivalent to going back)
            }
        });

        for (int i = 0; i < title.length; i++) {
            RCGymModel rcGymModel = new RCGymModel(title[i], image[i], ratingIcon[i], ratingText[i], address[i]);
            modelArrayList.add(rcGymModel);
        }
        rcFavouriteAdapter.notifyDataSetChanged();
    }
}