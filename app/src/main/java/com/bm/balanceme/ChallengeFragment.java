package com.bm.balanceme;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.util.Log;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import android.content.res.Resources;

public class ChallengeFragment extends Fragment {
    private RecyclerView recyclerView;
    private final ActivityResultLauncher<Intent> popularActivityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // Handle the result, refresh data, etc.
                    refreshData();
                }
            });
    public ChallengeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Write predefined data to the internal file during fragment creation
        writePredefinedDataToFile();
    }

    private void writePredefinedDataToFile() {
        try {
            // Open the raw resource file for reading
            InputStream inputStream = getResources().openRawResource(R.raw.records);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            // Open the internal file for writing
            FileOutputStream fileOutputStream = requireActivity().openFileOutput("records.txt", Context.MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);

            // Read data from the raw resource file and write to the internal file
            String line;
            while ((line = reader.readLine()) != null) {
                outputStreamWriter.write(line + "\n");
            }

            // Close the streams
            reader.close();
            outputStreamWriter.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("FileWrite", "Error writing to file: " + e.getMessage());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_challenge, container, false);

        // Find the CardView with the image by ID
        CardView popularCardView = view.findViewById(R.id.popularCardView);

        // Set OnClickListener for the CardView
        popularCardView.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), PopularActivity.class);
            // Start PopularActivity with the launcher
            popularActivityLauncher.launch(intent);
        });

        // Find the "View" button
        Button viewButton1 = view.findViewById(R.id.button5);

        // Set OnClickListener for the "View" button
        viewButton1.setOnClickListener(v -> {
            // Log statement to check if the button click is triggered
            Log.d("ChallengeFragment", "View button clicked");
            // Start the PastChallengesActivity when the button is clicked
            startActivity(new Intent(getActivity(), PastChallengesActivity.class));
        });

        recyclerView = view.findViewById(R.id.recyclerViewOngoing);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        // Read data from text file
        List<RecordModel> recordList = readRecordsFromTextFile();
        RecordAdapter adapter = new RecordAdapter(recordList);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private List<RecordModel> readRecordsFromTextFile() {
        List<RecordModel> recordList = new ArrayList<>();

        try {
            // Open the internal file for reading
            InputStream inputStream = getActivity().openFileInput("records.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line using the delimiter (comma in this case)
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    // Create a RecordModel and add it to the list
                    recordList.add(new RecordModel(
                            getImageResourceByName(parts[0]),
                            parts[1],
                            parts[2],
                            Integer.parseInt(parts[3])
                    ));
                }
            }

            // Close the reader
            reader.close();
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return recordList;
    }

    @SuppressLint("DiscouragedApi")
    private int getImageResourceByName(String name) {
        try {
            Resources resources = requireContext().getResources();
            return resources.getIdentifier(name, "drawable", requireContext().getPackageName());
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            return 0; // Return a default value or handle the situation accordingly
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check if the result code indicates a change
        if (resultCode == Activity.RESULT_OK) {
            // Handle the result, refresh data, etc.
            refreshData();
        }
    }

    // Refresh data in the fragment (you need to implement this method)
    private void refreshData() {
        // Implement the logic to refresh or recreate the data in the fragment
        // For example, you can re-read the records from the file and update the RecyclerView
        List<RecordModel> updatedRecordList = readRecordsFromTextFile();
        RecordAdapter adapter = new RecordAdapter(updatedRecordList);
        recyclerView.setAdapter(adapter);
    }

}
