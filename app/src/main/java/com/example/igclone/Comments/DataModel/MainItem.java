package com.example.igclone.Comments.DataModel;

import com.example.igclone.DataModel.CommentsDataModel;
import com.example.igclone.ViewHolders.RepliesItemVH;

import java.util.ArrayList;

public class MainItem extends ListItem {

    private long MainCommentTimestamp;
    private int MainItemPos;

    private CommentsDataModel data;

    public MainItem(long MainItemTimestamp, CommentsDataModel data)
    {
        this.MainCommentTimestamp = MainItemTimestamp;
        this.data = data;
    }

    @Override
    public void setMainCommentTimestamp(long mainCommentTimestamp) {
        MainCommentTimestamp = mainCommentTimestamp;
    }

    @Override
    public long getMainCommentTimestamp() {
        return MainCommentTimestamp;
    }

    @Override
    public void setMainItemPos(int mainItemPos) {
        MainItemPos = mainItemPos;
    }

    @Override
    public int getMainItemPos() {
        return MainItemPos;
    }

    @Override
    public void setMainData(CommentsDataModel data)
    {
        this.data = data;
    }

    @Override
    public void setRepliesArrayData(ArrayList<CommentsDataModel> data) {

    }

    @Override
    public CommentsDataModel getMainData() {
        return data;
    }

    @Override
    public ArrayList<CommentsDataModel> getRepliesArrayData() {
        return null;
    }

    @Override
    public RepliesItemVH getRepliesVH() {
        return null;
    }

    @Override
    public void setRepliesVH(RepliesItemVH repliesVH) {

    }

    @Override
    public int getType() {
        return 0;
    }
}
