package com.example.igclone.Comments.DB;

import com.example.igclone.Comments.Util.CommentsUtil;
import com.example.igclone.Comments.DataModel.CommentsDataModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CommentsPostDB {

    private DatabaseReference mCommentsRef = FirebaseDatabase.getInstance().getReference("/Comments");

    public CommentsPostDB(String postId)
    {
        mCommentsRef = mCommentsRef.child(postId);
    }


    public void retrieveAllPosts()
    {

    }

    public void makePost()
    {

    }

    public void postMainComment(String comment)
    {
        DatabaseReference mMainCommentsRef = mCommentsRef.child("MainComments").child(String.valueOf(CommentsUtil.getCurrentTime()));
        mMainCommentsRef.setValue(new CommentsDataModel("abc", "username", comment, false,  CommentsUtil.getCurrentTime(), 0));
    }

    public void postReplyComment(String replyContainerTimestamp, String comment)
    {
        DatabaseReference mReplyCommentsRef = mCommentsRef.child("ReplyComments").child(replyContainerTimestamp).child(String.valueOf(CommentsUtil.getCurrentTime()));
        mReplyCommentsRef.setValue(new CommentsDataModel("abc", "username", comment, false,  CommentsUtil.getCurrentTime(), 0));
    }

    public void replyCommentLikeInteraction(String replyContainerTimestamp, long replyCommentTimestamp, boolean liked, int likeCount)
    {
        DatabaseReference mReplyCommentsRef = mCommentsRef.child("ReplyComments").child(replyContainerTimestamp).child(String.valueOf(replyCommentTimestamp));
        mReplyCommentsRef.child("liked").setValue(liked);
        mReplyCommentsRef.child("likeCount").setValue(likeCount);
    }

    public void mainCommentLikeInteraction(long mainCommentTimestamp, boolean liked, int likeCount)
    {
        DatabaseReference mMainCommentsRef = mCommentsRef.child("MainComments").child(String.valueOf(mainCommentTimestamp));
        mMainCommentsRef.child("liked").setValue(liked);
        mMainCommentsRef.child("likeCount").setValue(likeCount);
    }

    public void mainCommentSelected(String itemTimestamp, boolean selected)
    {
        mCommentsRef.child("MainComments").child(itemTimestamp).child("itemSelected").setValue(selected);
    }

    public void replyCommentSelected(String itemContainerTimestamp, String itemTimestamp, boolean selected)
    {
        mCommentsRef.child("ReplyComments").child(itemContainerTimestamp).child(itemTimestamp).child("itemSelected").setValue(selected);
    }

    public void removeMainComment(String itemTimestamp)
    {
        mCommentsRef.child("MainComments").child(itemTimestamp).removeValue();
        mCommentsRef.child("ReplyComments").child(itemTimestamp+"R").removeValue();
    }

    public void removeReplyComment(String itemContainerTimestamp, String itemTimestamp)
    {
        mCommentsRef.child("ReplyComments").child(itemContainerTimestamp).child(itemTimestamp).removeValue();
    }

}
