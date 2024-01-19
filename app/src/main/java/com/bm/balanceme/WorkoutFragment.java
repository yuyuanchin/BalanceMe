package com.bm.balanceme;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;

import com.bm.balanceme.Adaptor.CategoryAdaptor;
import com.bm.balanceme.Adaptor.WorkoutAdapter;
import com.bm.balanceme.Domain.CategoryDomain;
import com.bm.balanceme.Domain.WorkoutDomain;

import java.util.ArrayList;

public class WorkoutFragment extends Fragment {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCategoryList;
    private RecyclerView workoutRecyclerView;
    private WorkoutAdapter workoutAdapter;
    private ArrayList<WorkoutDomain> workoutList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_workout, container, false);

        recyclerViewCategoryList = view.findViewById(R.id.recyclerView);
        recyclerViewCategory();

        // Find and initialize the workout RecyclerView
        workoutRecyclerView = view.findViewById(R.id.workoutRecycleView);
        // Set up the workout RecyclerView
        setupWorkoutRecyclerView();

        // Find the search EditText
        EditText searchEditText = view.findViewById(R.id.editTextText);
        // Set up the search functionality
        setupSearch(searchEditText);

        Button viewButton = view.findViewById(R.id.viewbutton);

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the WorkoutSave activity
                Intent intent = new Intent(requireActivity(), WorkoutSave.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> category = new ArrayList<>();
        category.add(new CategoryDomain("All","allicon"));
        category.add(new CategoryDomain("Shoulder","shoulder"));
        category.add(new CategoryDomain("Abs","abs"));
        category.add(new CategoryDomain("Chest","chest"));
        category.add(new CategoryDomain("Biceps","biceps"));
        category.add(new CategoryDomain("Calves","calves"));
        category.add(new CategoryDomain("Hamstring","hamstring"));
        category.add(new CategoryDomain("Back","backmuscle"));

        adapter = new CategoryAdaptor(category);
        recyclerViewCategoryList.setAdapter(adapter);
    }

    private void setupWorkoutRecyclerView() {
        // Initialize the workout list with sample data (replace with your data)
        workoutList = new ArrayList<>();
        workoutList.add(new WorkoutDomain("Full Body Workout", "Beginner", "32 mins", R.drawable.fullbody));
        workoutList.add(new WorkoutDomain("Abs Workout", "Beginner", "20 mins", R.drawable.absworkout));
        workoutList.add(new WorkoutDomain("Lower Body Workout", "Advanced", "45 mins", R.drawable.lowerbody));

        // Create and set up the adapter
        workoutAdapter = new WorkoutAdapter(workoutList, requireContext());
        workoutRecyclerView.setAdapter(workoutAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        workoutRecyclerView.setLayoutManager(layoutManager);
    }

    // Inside the setupSearch method
    private void setupSearch(EditText searchEditText) {
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                // Filter the workout list based on the search query
                workoutAdapter.filter(editable.toString());

                // Check if the search query is empty
                if (editable.toString().isEmpty()) {
                    // If empty, reload the original data
                    workoutAdapter.reloadOriginalData();
                }
            }
        });
    }

    public void onViewButtonClick(View view) {
        // Start the TargetActivity
        Intent intent = new Intent(getActivity(), WorkoutSave.class);
        startActivity(intent);
    }
}