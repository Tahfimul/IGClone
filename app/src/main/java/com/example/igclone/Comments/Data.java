package com.example.igclone.Comments;

import com.example.igclone.DataModel.CommentsDataModel;
import com.example.igclone.R;

import java.util.ArrayList;

public class Data {

    private static ArrayList<CommentsDataModel> main = new ArrayList<>();
    private static ArrayList<CommentsDataModel> replies = new ArrayList<>();

    public void initData()
    {
//        replies.add(new CommentsDataModel(R.drawable.circle_border, "Testing", "hello", true, 1556502740, 0));
//        replies.add(new CommentsDataModel(R.drawable.circle_border, "Testing", "hello", true, 1556502740, 1));
//        replies.add(new CommentsDataModel(R.drawable.circle_border, "Testing", "hello", true, 1556502740, 2));
//        replies.add(new CommentsDataModel(R.drawable.circle_border, "Testing", "hello", true, 1556502740, 3));
//        replies.add(new CommentsDataModel(R.drawable.circle_border, "Testing", "hello", true, 1556502740, 4));
//        replies.add(new CommentsDataModel(R.drawable.circle_border, "Testing", "hello", true, 1556502740, 5));
//        replies.add(new CommentsDataModel(R.drawable.circle_border, "Testing", "hello", true, 1556502740, 6));
//        replies.add(new CommentsDataModel(R.drawable.circle_border, "Testing", "hello", true, 1556502740, 7));
//        replies.add(new CommentsDataModel(R.drawable.circle_border, "Testing", "hello", true, 1556502740, 8));
//        replies.add(new CommentsDataModel(R.drawable.circle_border, "Testing", "hello", true, 1556502740, 9));
//        replies.add(new CommentsDataModel(R.drawable.circle_border, "Testing", "hello", true, 1556502740, 10));
//        replies.add(new CommentsDataModel(R.drawable.circle_border, "Testing", "hello", true, 1556502740, 11));
//
//        main.add(new CommentsDataModel(R.drawable.circle_border, "Testing", "hello", true, 1556502740, 0));
//        main.add(new CommentsDataModel(R.drawable.circle_border, "Testing", "hello", true, 1556502740, 1));
    }

    public ArrayList<CommentsDataModel> getRepliesData()
    {
        return replies;
    }

    public void setRepliesData(CommentsDataModel data)
    {
        System.out.println(data);
        replies.set(replies.indexOf(data), data);

    }

    public ArrayList<CommentsDataModel> getMainData() {
        return main;
    }

    public void setMainData(CommentsDataModel data) {
        Data.main.set(main.indexOf(data), data);
    }
}
