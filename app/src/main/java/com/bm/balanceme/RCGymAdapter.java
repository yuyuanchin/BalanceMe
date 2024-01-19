package com.bm.balanceme;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bm.balanceme.R;
import com.bm.balanceme.RCGymModel;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RCGymAdapter extends RecyclerView.Adapter<RCGymAdapter.RCGymViewHolder> {

    Context context;

    public RCGymAdapter(Context context, ArrayList<RCGymModel> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    ArrayList<RCGymModel> modelArrayList;

    @NonNull
    @Override
    public RCGymViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rc_gym_item, parent, false);
        return new RCGymViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RCGymViewHolder holder, int position) {
        RCGymModel rcGymModel = modelArrayList.get(position);
        holder.title.setText(rcGymModel.title);
        holder.image.setImageResource(rcGymModel.image);
        holder.rating.setText(rcGymModel.ratingText);
        holder.address.setText(rcGymModel.address);

        Drawable ratingDrawable = ContextCompat.getDrawable(holder.itemView.getContext(), rcGymModel.ratingIcon);
        if (ratingDrawable != null) {
            // Adjust the padding as needed
            ratingDrawable.setBounds(0, 0, ratingDrawable.getIntrinsicWidth(), ratingDrawable.getIntrinsicHeight());
            holder.rating.setCompoundDrawablesWithIntrinsicBounds(ratingDrawable, null, null, null);
        }

        holder.navigateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String depart = "Universiti Sains Malaysia";
                String destination = rcGymModel.getTitle();

                Uri uri = Uri.parse("https://www.google.com/maps/dir/" + depart + "/" + destination);

                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.setPackage("com.google.android.apps.maps");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public static class RCGymViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView rating;
        TextView address;
        Button navigateBtn;

        public RCGymViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            rating = itemView.findViewById(R.id.rating);
            address = itemView.findViewById(R.id.address);
            navigateBtn = itemView.findViewById(R.id.navigate);
        }
    }
}
