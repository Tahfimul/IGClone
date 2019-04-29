package com.example.igclone.DataModel;

import java.util.ArrayList;

public class RepliesItem extends ListItem {

    private ArrayList<CommentsDataModel> data;

    public RepliesItem(ArrayList<CommentsDataModel> data)
    {
        this.data = data;
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
