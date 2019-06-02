package com.example.igclone.Comments.DataModel;

import com.example.igclone.Comments.Interfaces.Reply;

public class ReplyItem extends ListItem implements Reply {

    private String replyContainerTimestamp;
    private long replyCommentTimestamp;

    private CommentsDataModel data;

    public ReplyItem(String replyContainerTimestamp, long replyCommentTimestamp, CommentsDataModel data)
    {
        this.replyContainerTimestamp = replyContainerTimestamp;
        this.replyCommentTimestamp = replyCommentTimestamp;
        this.data = data;
    }

    @Override
    public int getType() {
        return TYPE_REPLY;
    }

    public void setMainCommentTimestamp(String replyContainerTimestamp) {
        this.replyContainerTimestamp = replyContainerTimestamp;
    }

    public String getReplyContainerTimestamp() {
        return replyContainerTimestamp;
    }

    @Override
    public void setReplyCommentTimestamp(long timestamp) {
        replyCommentTimestamp = timestamp;
    }

    @Override
    public long getReplyCommentTimestamp() {
        return replyCommentTimestamp;
    }

    @Override
    public CommentsDataModel getReplyData() {
        return data;
    }

    @Override
    public void setReplyData(CommentsDataModel replyData) {
        data = replyData;
    }
}
