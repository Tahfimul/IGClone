package com.example.igclone.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.igclone.DataModel.ListItem;
import com.example.igclone.DataModel.MainItem;
import com.example.igclone.DataModel.RepliesItem;
import com.example.igclone.R;
import com.example.igclone.ViewHolders.MainItemVH;
import com.example.igclone.ViewHolders.RepliesItemVH;

import java.util.ArrayList;

public class CommentsRecyclerAdapter extends RecyclerView.Adapter {

    private ArrayList<ListItem> dataset;

    public CommentsRecyclerAdapter(ArrayList<ListItem> dataset)
    {
        this.dataset = dataset;
    }



    @Override
    public int getItemViewType(int position) {
        return dataset.get(position).getType();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        int type = getItemViewType(i);
        if(type == ListItem.TYPE_MAIN)
        {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_comment, viewGroup, false);
            return new MainItemVH(itemView);
        }
        if(type == ListItem.TYPE_REPLIES)
        {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_replies, viewGroup, false);
            return new RepliesItemVH(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int type = getItemViewType(i);
        if (type == ListItem.TYPE_MAIN) {
            MainItem mainItem = (MainItem) dataset.get(i);
            MainItemVH  mainItemVH = (MainItemVH) viewHolder;
            mainItemVH.bind(mainItem.getData());
        }
        if (type == ListItem.TYPE_REPLIES){
            RepliesItem repliesItem = (RepliesItem) dataset.get(i);
            RepliesItemVH  repliesItemVH = (RepliesItemVH) viewHolder;
            repliesItemVH.bind(repliesItem.getData());
        }
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
