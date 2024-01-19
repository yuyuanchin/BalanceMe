package com.bm.balanceme.Adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bm.balanceme.Domain.WorkoutDomain;
import com.bm.balanceme.R;
import com.bm.balanceme.WorkoutDetails;

import java.util.ArrayList;
import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder> {

    private ArrayList<WorkoutDomain> workoutList;
    private ArrayList<WorkoutDomain> originalWorkoutList; // Store the original list for filtering
    private Context context;

    public WorkoutAdapter(ArrayList<WorkoutDomain> workoutList) {
        this.workoutList = workoutList;
        this.originalWorkoutList = new ArrayList<>(workoutList);
    }

    public WorkoutAdapter(ArrayList<WorkoutDomain> workoutList, Context context) {
        this.workoutList = workoutList;
        this.originalWorkoutList = new ArrayList<>(workoutList);
        this.context = context;
    }

    @NonNull
    @Override
    public WorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_workout, parent, false);
        return new WorkoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutViewHolder holder, int position) {
        WorkoutDomain workoutItem = workoutList.get(position);
        holder.bind(workoutItem);

        // Bind data to views
        holder.workoutTitle.setText(workoutItem.getWorkoutTitle());
        holder.textViewWorkoutDetails.setText(String.format("%s | %s", workoutItem.getDifficulty(), workoutItem.getDuration()));
        holder.imageViewWorkout.setImageResource(workoutItem.getWorkoutImg());

        Button viewMoreButton = holder.itemView.findViewById(R.id.ViewMoreButton);

        // Set a click listener to handle item clicks
        viewMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click
                startWorkoutDetailsActivity(workoutItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return workoutList.size();
    }

    public class WorkoutViewHolder extends RecyclerView.ViewHolder {
        private TextView workoutTitle;
        private TextView textViewWorkoutDetails;
        private ImageView imageViewWorkout;

        public WorkoutViewHolder(@NonNull View itemView) {
            super(itemView);
            workoutTitle = itemView.findViewById(R.id.textView);
            textViewWorkoutDetails = itemView.findViewById(R.id.textView2);
            imageViewWorkout = itemView.findViewById(R.id.imageView);
        }

        public void bind(final WorkoutDomain workout) {
            // Bind data to views
            workoutTitle.setText(workout.getWorkoutTitle());
            textViewWorkoutDetails.setText(String.format("%s | %s", workout.getDifficulty(), workout.getDuration()));
            imageViewWorkout.setImageResource(workout.getWorkoutImg());
        }
    }

    private void startWorkoutDetailsActivity(WorkoutDomain workoutItem) {
        // Start the WorkoutDetails activity, passing necessary data
        Intent intent = new Intent(context, WorkoutDetails.class);
        intent.putExtra("workoutTitle", workoutItem.getWorkoutTitle());
        intent.putExtra("difficulty", workoutItem.getDifficulty());
        intent.putExtra("duration", workoutItem.getDuration());
        intent.putExtra("workoutImg", workoutItem.getWorkoutImg());
        context.startActivity(intent);
    }

    public void filter(String query) {
        query = query.toLowerCase();
        workoutList.clear();

        if (query.isEmpty()) {
            workoutList.addAll(originalWorkoutList);
        } else {
            for (WorkoutDomain workout : originalWorkoutList) {
                if (workout.getWorkoutTitle().toLowerCase().contains(query)) {
                    workoutList.add(workout);
                }
            }
        }

        notifyDataSetChanged();
    }

    public void setWorkoutList(List<WorkoutDomain> workoutList) {
        this.workoutList = new ArrayList<>(workoutList);
        this.originalWorkoutList = new ArrayList<>(workoutList);
        notifyDataSetChanged();
    }

    // Method to reload the original data
    public void reloadOriginalData() {
        workoutList.clear();
        workoutList.addAll(originalWorkoutList);
        notifyDataSetChanged();
    }
}
