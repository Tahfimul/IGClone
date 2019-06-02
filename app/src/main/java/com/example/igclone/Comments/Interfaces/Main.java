package com.example.igclone.Comments.Interfaces;

import com.example.igclone.Comments.DataModel.CommentsDataModel;

public interface Main {

    void setMainCommentTimestamp(long mainCommentTimestamp);

    long getMainCommentTimestamp();

    CommentsDataModel getMainData();

    void setMainData(CommentsDataModel mainData);
}
