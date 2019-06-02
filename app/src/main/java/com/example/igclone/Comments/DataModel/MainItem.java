package com.example.igclone.Comments.DataModel;

import com.example.igclone.Comments.Interfaces.Main;

public class MainItem extends ListItem implements Main {

    private long MainCommentTimestamp;

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
    public void setMainData(CommentsDataModel data)
    {
        this.data = data;
    }

    @Override
    public CommentsDataModel getMainData() {
        return data;
    }

    @Override
    public int getType() {
        return TYPE_MAIN;
    }
}
