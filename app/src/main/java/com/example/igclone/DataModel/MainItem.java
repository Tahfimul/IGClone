package com.example.igclone.DataModel;

public class MainItem extends ListItem {

    private int MainCommentIndex;

    private CommentsDataModel data;

    public MainItem(int MainItemIndex, CommentsDataModel data)
    {
        this.MainCommentIndex = MainItemIndex;
        this.data = data;
    }

    public void setMainCommentIndex(int mainCommentIndex) {
        MainCommentIndex = mainCommentIndex;
    }

    public int getMainCommentIndex() {
        return MainCommentIndex;
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
