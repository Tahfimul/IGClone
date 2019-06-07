package com.example.igclone.Comments.ViewHolders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.example.igclone.Comments.Adapters.RepliesContainerRecyclerAdapter;
import com.example.igclone.Comments.DataModel.ReplyItem;
import com.example.igclone.R;
import com.example.igclone.Comments.Util.CommentsUtil;

import java.util.ArrayList;

public class RepliesContainerVH extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView viewRepliesBtn;
    private boolean isViewing;
    private RecyclerView recyclerView;
    private RepliesContainerRecyclerAdapter adapter;

    public RepliesContainerVH(@NonNull View itemView) {
        super(itemView);
        viewRepliesBtn = itemView.findViewById(R.id.view_replies_btn);
        isViewing = false;
        recyclerView = itemView.findViewById(R.id.recyclerView);
    }

    public void bind(ArrayList<ReplyItem> data)
    {
        recyclerView.setVisibility(View.GONE);
        CommentsUtil.initRepliesItemRecycler(recyclerView, itemView.getContext(), data);
        adapter = (RepliesContainerRecyclerAdapter) recyclerView.getAdapter();
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

    public void updateContainerRecyclerDataset(ReplyItem replyItem)
    {
        adapter.updateDataset(replyItem);
    }

    public RecyclerView getRecyclerView()
    {
        return recyclerView;
    }
}
