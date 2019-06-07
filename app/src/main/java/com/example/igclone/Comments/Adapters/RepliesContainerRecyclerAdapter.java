package com.example.igclone.Comments.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.igclone.Comments.DataModel.ReplyItem;
import com.example.igclone.Comments.ViewHolders.ReplyItemVH;
import com.example.igclone.R;

import java.util.ArrayList;

public class RepliesContainerRecyclerAdapter extends RecyclerView.Adapter{

    private ArrayList<ReplyItem> dataset;

    public RepliesContainerRecyclerAdapter(ArrayList<ReplyItem> replyItems)
    {
        dataset = replyItems;
    }

    public void updateDataset(ReplyItem data)
    {
        dataset.add(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View comment = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_comment, viewGroup, false);
        ReplyItemVH vh = new ReplyItemVH(comment);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
        final ReplyItemVH vh = (ReplyItemVH) viewHolder;
        final ReplyItem data = dataset.get(i);
        vh.bind(data);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
