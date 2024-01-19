package com.bm.balanceme;

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

public class FragmentGyms extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<RCGymModel> modelArrayList;
    private RCGymAdapter rcGymAdapter;

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