package com.example.igclone.DataModel;

import java.util.ArrayList;

public class RepliesItem extends ListItem {

    private int mainCommentIndex;
    private ArrayList<CommentsDataModel> data;

    public RepliesItem(int mainCommentIndex, ArrayList<CommentsDataModel> data)
    {
        this.mainCommentIndex = mainCommentIndex;
        this.data = data;
    }

    public void setMainCommentIndex(int mainCommentIndex) {
        this.mainCommentIndex = mainCommentIndex;
    }

    public int getMainCommentIndex() {
        return mainCommentIndex;
    }

    public void setData(ArrayList<CommentsDataModel> data) {
        this.data = data;
    }

    public ArrayList<CommentsDataModel> getData() {
        return data;
    }

    @Override
    public int getType() {
        return 1;
    }
}
