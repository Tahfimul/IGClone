package com.example.igclone.Comments.Interfaces;

import com.example.igclone.Comments.DataModel.ReplyItem;
import com.example.igclone.Comments.ViewHolders.RepliesContainerVH;

import java.util.ArrayList;

public interface RepliesContainer {

    String getRepliesContainerTimestamp();

    void setRepliesContainerTimestamp(String timestamp);

    ArrayList<ReplyItem> getReplyItems();

    void setReplyItems(ArrayList<ReplyItem> replyItems);

    void appendReplyItem(ReplyItem replyItem);

    void setRepliesContainerVH(RepliesContainerVH vh);

    void removeReplyItem(ReplyItem item);

}
