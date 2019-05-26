package com.example.igclone.Comments.DataModel;

import com.example.igclone.DataModel.CommentsDataModel;
import com.example.igclone.ViewHolders.RepliesItemVH;

import java.util.ArrayList;

public class RepliesItem extends ListItem {

    private long mainCommentTimestamp;
    private int MainItemPos;
    private ArrayList<CommentsDataModel> data;
    private RepliesItemVH repliesVH;

    public RepliesItem(long mainCommentTimestamp, ArrayList<CommentsDataModel> data)
    {
        this.mainCommentTimestamp = mainCommentTimestamp;
        this.data = data;
    }

    @Override
    public void setMainCommentTimestamp(long mainCommentTimestamp) {
        this.mainCommentTimestamp = mainCommentTimestamp;
    }

    @Override
    public long getMainCommentTimestamp() {
        return mainCommentTimestamp;
    }

    @Override
    public int getMainItemPos() {
        return MainItemPos;
    }

    @Override
    public void setMainData(CommentsDataModel data) {

    }

    @Override
    public void setMainItemPos(int mainItemPos) {
        MainItemPos = mainItemPos;
    }

    @Override
    public void setRepliesArrayData(ArrayList<CommentsDataModel> data) {
        this.data = data;
    }

    @Override
    public CommentsDataModel getMainData() {
        return null;
    }


    @Override
    public ArrayList<CommentsDataModel> getRepliesArrayData() {
        return data;
    }

    @Override
    public RepliesItemVH getRepliesVH() {
        return repliesVH;
    }

    @Override
    public void setRepliesVH(RepliesItemVH repliesViewHolder) {
        repliesVH = repliesViewHolder;
    }

    @Override
    public int getType() {
        return 1;
    }
}
