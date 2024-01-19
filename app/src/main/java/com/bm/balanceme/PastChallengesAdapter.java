package com.bm.balanceme;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class PastChallengesAdapter extends RecyclerView.Adapter<PastChallengesAdapter.ViewHolder> {

    private ArrayList<PastChallengeItem> pastChallengeList;
    private Context context; // Add a Context variable

    public PastChallengesAdapter(Context context, ArrayList<PastChallengeItem> pastChallengeList) {
        this.context = context;
        this.pastChallengeList = pastChallengeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_past_challenge, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PastChallengeItem pastChallengeItem = pastChallengeList.get(position);

        holder.dateTextView.setText(pastChallengeItem.getDate());
        holder.imageView.setImageResource(pastChallengeItem.getImageResourceId());
        holder.titleTextView.setText(pastChallengeItem.getTitle());
        holder.descriptionTextView.setText(pastChallengeItem.getDescription());

        // Set OnClickListener for the item
        holder.itemView.setOnClickListener(v -> {
            // Create an Intent to start the PastChallengeActivity
            Intent intent = new Intent(context, PastChallengesActivity.class);
            // Pass data to the intent
            intent.putExtra("date", pastChallengeItem.getDate());
            intent.putExtra("imageResourceId", pastChallengeItem.getImageResourceId());
            intent.putExtra("title", pastChallengeItem.getTitle());
            intent.putExtra("description", pastChallengeItem.getDescription());
            // Start the activity
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return pastChallengeList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView;
        ImageView imageView;
        TextView titleTextView;
        TextView descriptionTextView;

        ViewHolder(View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            imageView = itemView.findViewById(R.id.imageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
        }
    }
}
