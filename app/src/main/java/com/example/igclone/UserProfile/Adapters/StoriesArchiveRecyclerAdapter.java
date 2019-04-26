package com.example.igclone.UserProfile.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.igclone.R;
import com.example.igclone.UserProfile.DataModel.StoriesArchiveDataModel;

import java.util.ArrayList;

public class StoriesArchiveRecyclerAdapter extends RecyclerView.Adapter<StoriesArchiveRecyclerAdapter.StoriesArchiveViewHolder> {

    private ArrayList<StoriesArchiveDataModel> dataset;

    public StoriesArchiveRecyclerAdapter(ArrayList<StoriesArchiveDataModel> dataset)
    {
        this.dataset = dataset;
    }

    public static class StoriesArchiveViewHolder extends RecyclerView.ViewHolder {

        ImageView storyImage;
        TextView  storyTitle;

        public StoriesArchiveViewHolder(@NonNull View itemView) {
            super(itemView);
            storyImage = itemView.findViewById(R.id.story_image);
            storyTitle = itemView.findViewById(R.id.story_title);

        }

        void bind(StoriesArchiveDataModel dataset)
        {
            storyImage.setImageResource(dataset.getImageSource());
            storyTitle.setText(dataset.getTitle());
        }
    }

    @NonNull
    @Override
    public StoriesArchiveRecyclerAdapter.StoriesArchiveViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LinearLayout storiesLayout = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_story_read, viewGroup, false);

        StoriesArchiveViewHolder viewHolder = new StoriesArchiveViewHolder(storiesLayout);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StoriesArchiveViewHolder viewHolder, int i) {
        viewHolder.bind(dataset.get(i));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
