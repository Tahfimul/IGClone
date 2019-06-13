package com.example.igclone.Comments.ViewHolders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.example.igclone.Comments.DataModel.MainItem;
import com.example.igclone.Comments.DataModel.RepliesContainerItem;
import com.example.igclone.Comments.DataModel.ReplyItem;

public class CommentItemVH extends RecyclerView.ViewHolder {

    public CommentItemVH(@NonNull View itemView) {
        super(itemView);
    }

    public void bindMainItem(MainItem mainItem){}

    public void bindReplyContainerItem(RepliesContainerItem replyItem){}

}
