package com.bm.balanceme;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.res.Resources;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.PopularViewHolder> {

    private OnJoinClickListener joinClickListener;
    private Context context;
    private List<PopularItem> popularItemList;

    // Interface to handle join button click events
    public interface OnJoinClickListener {
        void onJoinClick(int position);
    }

    public void setOnJoinClickListener(OnJoinClickListener listener) {
        this.joinClickListener = listener;
    }

    // Constructor to accept the Context and List<PopularItem>
    public PopularAdapter(Context context, List<PopularItem> popularItemList) {
        this.context = context;
        this.popularItemList = popularItemList;
    }

    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popular, parent, false);
        return new PopularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, int position) {
        PopularItem item = popularItemList.get(position);

        holder.imageView.setImageResource(item.getImageResourceId());
        holder.titleTextView.setText(item.getTitle());
        holder.quantityTextView.setText(item.getQuantity());
        holder.descriptionTextView.setText(item.getDescription());

        // Set OnClickListener for the "Expand" button
        holder.expandButton.setOnClickListener(v -> {
            // Handle expand event here
        });

        // Set OnClickListener for the "Join" button
        holder.joinButton.setOnClickListener(v -> {
            // Handle join event here
            if (joinClickListener != null) {
                joinClickListener.onJoinClick(position);

                // Display a toast message when the join button is clicked
                Toast.makeText(v.getContext(), "Joined and Saved to Ongoing List", Toast.LENGTH_SHORT).show();

                // Write to the records.txt file
//                clearFileContent();
                writeToFile(item);

            }
        });
    }

    private void writeToFile(PopularItem item) {
        try {
            String imageFileName = getImageFileNameFromResourceId(item.getImageResourceId());

            // Open the "records.txt" file for writing
            FileOutputStream fileOutputStream = context.openFileOutput("records.txt", Context.MODE_APPEND);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);

            // Format the data and write to the file
            String data = String.format("%s,%s,%s,%s\n", imageFileName, item.getTitle(), item.getQuantity(), "0");
            outputStreamWriter.write(data);

            // Close the streams
            outputStreamWriter.close();
            fileOutputStream.close();

            // Log the file path
            String filePath = context.getFilesDir().getPath() + "/records.txt";
            Log.d("FilePath", "File Path: " + filePath);

            // Read and log the content of the file
            readAndLogFileContent();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("FileWrite", "Error writing to file: " + e.getMessage());
        }
    }
    private void clearFileContent() {
        try {
            // Open the "records.txt" file for writing (overwrite mode)
            FileOutputStream fileOutputStream = context.openFileOutput("records.txt", Context.MODE_PRIVATE);
            fileOutputStream.close();
            Log.d("ClearFile", "File content cleared.");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("ClearFile", "Error clearing file content: " + e.getMessage());
        }
    }
    private void readAndLogFileContent() {
        try {
            // Open the "records.txt" file for reading
            FileInputStream fileInputStream = context.openFileInput("records.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));

            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }

            // Log the file content
            Log.d("FileContent", "File Content:\n" + content.toString());

            // Close the reader
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("FileRead", "Error reading from file: " + e.getMessage());
        }
    }

    private String getImageFileNameFromResourceId(int resourceId) {
        try {
            Resources resources = context.getResources();
            return resources.getResourceEntryName(resourceId);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            return ""; // Return a default value or handle the situation accordingly
        }
    }

    @Override
    public int getItemCount() {
        return popularItemList.size();
    }

    public static class PopularViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView titleTextView;
        TextView quantityTextView;
        TextView descriptionTextView;
        ImageButton expandButton;
        Button joinButton;

        public PopularViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            quantityTextView = itemView.findViewById(R.id.quantityTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            expandButton = itemView.findViewById(R.id.imageButton);
            joinButton = itemView.findViewById(R.id.button);
        }
    }
}
