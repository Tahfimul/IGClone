package com.example.igclone.Comments.ViewHolders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.example.igclone.Comments.Adapters.RepliesContainerRecyclerAdapter;
import com.example.igclone.Comments.DataModel.RepliesContainerItem;
import com.example.igclone.Comments.DataModel.ReplyItem;
import com.example.igclone.Comments.Util.CommentsUtil;
import com.example.igclone.R;

public class RepliesContainerVH extends CommentItemVH implements View.OnClickListener{

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

    @Override
    public void bindReplyContainerItem(RepliesContainerItem replyContainerItem) {
        super.bindReplyContainerItem(replyContainerItem);

        replyContainerItem.setRepliesContainerVH(this);

        System.out.println("Received replyItem in replyContainerItem "+replyContainerItem.getRepliesContainerTimestamp());
        recyclerView.setVisibility(View.GONE);
        CommentsUtil.initRepliesItemRecycler(recyclerView, itemView.getContext(), replyContainerItem.getReplyItems());
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
