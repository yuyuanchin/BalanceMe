package com.bm.balanceme;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bm.balanceme.R;
import com.bm.balanceme.RCGymAdapter;
import com.bm.balanceme.RCGymModel;

import java.util.ArrayList;

public class FragmentParks extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<RCGymModel> modelArrayList;
    private RCGymAdapter rcGymAdapter;

    private String[] title = new String[]{
            "Tasik Harapan",
            "Bukit Dumbar Park",
            "Minden Park"
    };

    private int[] image = new int[]{
            R.drawable.lr_park,
            R.drawable.central_park,
            R.drawable.harmony_gardens
    };

    private int[] ratingIcon = new int[]{
            R.drawable.rating_4_5,
            R.drawable.rating_4_5,
            R.drawable.rating_4_5
    };

    private String[] ratingText = new String[]{
            "4.5( 341)",
            "4.3 (299)",
            "4.7 (423)"
    };

    private String[] address = new String[]{
            "University Sains Malaysia, Persiaran Minden, 11800 Gelugor, Penang",
            "Jalan Jelutong, Bukit Dumbar, 11600 George Town, Pulau Pinang",
            "Minden Heights, 11700 Gelugor, Penang"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_gyms, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerGyms);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setHasFixedSize(true);

        modelArrayList = new ArrayList<>();
        rcGymAdapter = new RCGymAdapter(requireContext(), modelArrayList);
        recyclerView.setAdapter(rcGymAdapter);

        for (int i = 0; i < title.length; i++) {
            RCGymModel rcGymModel = new RCGymModel(title[i], image[i], ratingIcon[i], ratingText[i], address[i]);
            modelArrayList.add(rcGymModel);
        }
        rcGymAdapter.notifyDataSetChanged();

        return rootView;
    }
}