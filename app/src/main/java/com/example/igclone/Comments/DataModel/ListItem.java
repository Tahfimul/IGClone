package com.example.igclone.Comments.DataModel;

import com.example.igclone.DataModel.CommentsDataModel;
import com.example.igclone.ViewHolders.RepliesItemVH;

import java.util.ArrayList;

public abstract class ListItem {

    public static int TYPE_MAIN =0;
    public static int TYPE_REPLIES = 1;

    public abstract void setMainCommentTimestamp(long mainCommentTimestamp);

    public abstract long getMainCommentTimestamp();

    public abstract void setMainItemPos(int mainItemPos);

    public abstract int getMainItemPos();

    public abstract void setMainData(CommentsDataModel data);

    public abstract void setRepliesArrayData(ArrayList<CommentsDataModel> data);

    public abstract CommentsDataModel getMainData();

    public abstract ArrayList<CommentsDataModel> getRepliesArrayData();

    public abstract RepliesItemVH getRepliesVH();

    public abstract void setRepliesVH(RepliesItemVH repliesVH);

    public abstract int getType();
}
