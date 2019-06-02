package com.example.igclone.Comments.DataModel;

import com.example.igclone.Comments.Interfaces.RepliesContainer;
import com.example.igclone.Comments.ViewHolders.RepliesContainerVH;

import java.util.ArrayList;

public class RepliesContainerItem extends ListItem implements RepliesContainer {


    private String repliesContainerTimestamp;
    private ArrayList<ReplyItem> replyItems;
    private RepliesContainerVH repliesContainerVH;

    public RepliesContainerItem(String repliesContainerTimestamp, ArrayList<ReplyItem> replyItems)
    {
        this.repliesContainerTimestamp = repliesContainerTimestamp;
        this.replyItems = replyItems;
    }

    @Override
    public int getType() {
        return TYPE_REPLIES;
    }

    @Override
    public String getRepliesContainerTimestamp() {
        return repliesContainerTimestamp;
    }

    @Override
    public void setRepliesContainerTimestamp(String timestamp) {
        repliesContainerTimestamp = timestamp;
    }

    @Override
    public ArrayList<ReplyItem> getReplyItems() {
        return replyItems;
    }

    @Override
    public void setReplyItems(ArrayList<ReplyItem> replyItems) {
        this.replyItems = replyItems;
    }

    @Override
    public void appendReplyItem(ReplyItem replyItem) {
        replyItems.add(replyItem);
        repliesContainerVH.updateContainerRecyclerDataset(replyItem);
    }

    @Override
    public void setRepliesContainerVH(RepliesContainerVH repliesContainerVH) {
        this.repliesContainerVH = repliesContainerVH;
    }

    public ReplyItem getReplyItem(String replyItemTimestamp)
    {
        for (ReplyItem item:replyItems)
            if (item.getReplyCommentTimestamp()==Long.valueOf(replyItemTimestamp))
                return item;

        return null;
    }
}
