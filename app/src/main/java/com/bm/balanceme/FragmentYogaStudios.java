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

public class FragmentYogaStudios extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<RCGymModel> modelArrayList;
    private RCGymAdapter rcGymAdapter;

    private String[] title = new String[]{
            "Unwind Studio",
            "Q Yoga Wellness Studio",
            "Arutjothee Yoga Academy"
    };

    private int[] image = new int[]{
            R.drawable.yoga1,
            R.drawable.yoga2,
            R.drawable.yoga3
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
            "236A, Jalan Jelutong, Taman Ara, 11600 George Town, Pulau Pinang",
            "76-3, Farlim Square, Jln Pisang Berangan, 11500 Ayer Itam, Pulau Pinang",
            "3-1, Desa Mutiara, 13, Lorong Delima 20, Taman Island Glades, 11700 Gelugor, Penang"
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