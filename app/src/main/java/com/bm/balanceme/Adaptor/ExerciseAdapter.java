package com.bm.balanceme.Adaptor;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bm.balanceme.Domain.ExerciseDomain;
import com.bm.balanceme.R;
import com.bm.balanceme.WorkoutExerciseDetails;

import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {

    private List<ExerciseDomain> exerciseList;
    private Context context;

    public ExerciseAdapter(List<ExerciseDomain> exerciseList) {
        this.exerciseList = exerciseList;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.viewholder_exercise, parent, false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        ExerciseDomain exercise = exerciseList.get(position);
        holder.bind(exercise);

        final ExerciseDomain dataItem = exerciseList.get(position);
        holder.exercisePic.setImageResource(dataItem.getImageUrl());
        holder.exerciseName.setText(dataItem.getName());

        holder.viewExerciseDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event, for example, start a new activity
                Intent intent = new Intent(context, WorkoutExerciseDetails.class);
                intent.putExtra("imageId", dataItem.getImageUrl());
                intent.putExtra("exerciseName", dataItem.getName());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }

    public ExerciseDomain getItem(int position) {
        if (position >= 0 && position < exerciseList.size()) {
            return exerciseList.get(position);
        }
        return null;
    }

    public class ExerciseViewHolder extends RecyclerView.ViewHolder {
        private ImageView exercisePic;
        private TextView exerciseName;
        private TextView exerciseDescription;
        private ImageView viewExerciseDetail;

        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            exercisePic = itemView.findViewById(R.id.exercisePic);
            exerciseName = itemView.findViewById(R.id.exerciseName);
            exerciseDescription = itemView.findViewById(R.id.exerciseDescription);
            viewExerciseDetail = itemView.findViewById(R.id.ViewExerciseDetail);
        }

        public void bind(final ExerciseDomain exercise) {
            // Bind data to views
            exerciseName.setText(exercise.getName());
            exerciseDescription.setText(exercise.getDescription());
            exercisePic.setImageResource(exercise.getImageUrl());

            // Set click listeners or perform any other binding operations
        }
    }
}
