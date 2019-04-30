package com.example.igclone.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.igclone.DataModel.CommentsDataModel;
import com.example.igclone.DataModel.MainItem;
import com.example.igclone.R;
import com.example.igclone.Util.CommentsUtil;
import com.example.igclone.ViewHolders.MainItemVH;

import java.util.ArrayList;

public class RepliesItemAdapter extends RecyclerView.Adapter {

    private ArrayList<CommentsDataModel> dataset;

    public RepliesItemAdapter(ArrayList<CommentsDataModel> dataset)
    {
        this.dataset = dataset;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View comment = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_comment, viewGroup, false);
        MainItemVH vh = new MainItemVH(comment);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        final MainItemVH vh = (MainItemVH) viewHolder;
        final CommentsDataModel data = dataset.get(i);
        vh.bind(dataset.get(i));
        vh.likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(data.isLiked())
                {
                    CommentsUtil.likeBtnUnlikedInteractionAnimate(vh.likeBtn, vh.itemView.getContext());
                    data.setLiked(false);
                }
                else {
                    CommentsUtil.likeBtnLikedInteractionAnimate(vh.likeBtn, vh.itemView.getContext());
                    data.setLiked(true);
                }

                notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
