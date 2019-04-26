package com.example.igclone.UserProfile.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.example.igclone.R;
import com.example.igclone.UserProfile.DataModel.ProfileThumbnailDataModel;

import java.util.ArrayList;

public class ProfileThumbnailRecyclerAdapter extends RecyclerView.Adapter<ProfileThumbnailRecyclerAdapter.ProfileThumbnailViewHolder> {

    private ArrayList<ProfileThumbnailDataModel> dataset;


    public class ProfileThumbnailViewHolder extends RecyclerView.ViewHolder {

        ImageView thumbnail;
        ImageView contentIndicator;

        public ProfileThumbnailViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.thumbnail_image);
            contentIndicator = itemView.findViewById(R.id.content_indicator);
        }

        void bind(ProfileThumbnailDataModel data)
        {
            thumbnail.setImageResource(data.getThumbnailSource());
            contentIndicator.setImageResource(data.getContentTypeSource());
        }
    }

    public ProfileThumbnailRecyclerAdapter(ArrayList<ProfileThumbnailDataModel> dataset)
    {
        this.dataset = dataset;
    }

    @NonNull
    @Override
    public ProfileThumbnailViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View thumbnail = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_thumbnail, viewGroup, false);
        ProfileThumbnailViewHolder viewHolder = new ProfileThumbnailViewHolder(thumbnail);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileThumbnailViewHolder profileThumbnailViewHolder, int i) {

        profileThumbnailViewHolder.bind(dataset.get(i));

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
