package com.example.igclone.DataModel;

public class MainItem extends ListItem {

    private CommentsDataModel data;

    public MainItem(CommentsDataModel data)
    {
        this.data = data;
    }

    public void setData(CommentsDataModel data)
    {
        this.data = data;
    }

    public CommentsDataModel getData() {
        return data;
    }

    @Override
    public int getType() {
        return 0;
    }
}
