package com.example.igclone.ViewHolders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.igclone.DataModel.CommentsDataModel;
import com.example.igclone.R;
import com.example.igclone.Util.CommentsUtil;

import java.util.ArrayList;

public class RepliesItemVH extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView viewRepliesBtn;
    private boolean isViewing;
    private RecyclerView recyclerView;

    public RepliesItemVH(@NonNull View itemView) {
        super(itemView);
        viewRepliesBtn = itemView.findViewById(R.id.view_replies_btn);
        isViewing = false;
        recyclerView = itemView.findViewById(R.id.recyclerView);
    }

    public void bind(long MainCommentTimestamp, int MainCommentIndex, ArrayList<CommentsDataModel> data)
    {
        recyclerView.setVisibility(View.GONE);
        CommentsUtil.initRepliesItemRecycler(recyclerView, itemView.getContext(), data, MainCommentTimestamp, MainCommentIndex);
        viewRepliesBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.view_replies_btn)
        {
            if(isViewing)
            {
                recyclerView.setVisibility(View.GONE);
                isViewing = false;
            }
            else
            {
                recyclerView.setVisibility(View.VISIBLE);
                isViewing = true;
            }
        }
    }

    public RecyclerView getRecyclerView()
    {
        return recyclerView;
    }
}
