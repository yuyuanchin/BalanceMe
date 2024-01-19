package com.bm.balanceme;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {

    private List<RecordModel> recordList;

    public RecordAdapter(List<RecordModel> recordList) {
        this.recordList = recordList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.record_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecordModel record = recordList.get(position);

        holder.imageView.setImageResource(record.getImageResourceId());
        holder.titleTextView.setText(record.getTitle());
        holder.descriptionTextView.setText(record.getDescription());

        // Set the progress for the ProgressBar
        holder.progressBar.setProgress(record.getProgress());

        // Set OnClickListener for the ImageButton
        holder.imageButton.setOnClickListener(v -> {
            // Handle the click event for the ImageButton
            // You can implement this behavior based on your requirements
            Context context = v.getContext();
            Intent intent = new Intent(context, OngoingActivity.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;
        TextView descriptionTextView;
        ProgressBar progressBar;
        ImageButton imageButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            progressBar = itemView.findViewById(R.id.progressBar);
            imageButton = itemView.findViewById(R.id.imageButton);
        }
    }
}
