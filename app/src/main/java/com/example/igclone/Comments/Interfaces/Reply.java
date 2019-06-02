package com.example.igclone.Comments.Interfaces;

import com.example.igclone.Comments.DataModel.CommentsDataModel;

public interface Reply {

    void setReplyCommentTimestamp(long timestamp);

    long getReplyCommentTimestamp();

    CommentsDataModel getReplyData();

    void setReplyData(CommentsDataModel replyData);
}
