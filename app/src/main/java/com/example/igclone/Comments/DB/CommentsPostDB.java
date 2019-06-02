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
//        mMainCommentsRef.child("comment").setValue(comment);
//        mMainCommentsRef.child("likeCount").setValue(0);
//        mMainCommentsRef.child("liked").setValue(false);
//        mMainCommentsRef.child("timestamp").setValue(CommentsUtil.getCurrentTime());
//        mMainCommentsRef.child("userIconSrc").setValue("abc");
//        mMainCommentsRef.child("username").setValue("username");
    }

    public void postReplyComment(String replyContainerTimestamp, String comment)
    {
        DatabaseReference mReplyCommentsRef = mCommentsRef.child("ReplyComments").child(replyContainerTimestamp).child(String.valueOf(CommentsUtil.getCurrentTime()));
        mReplyCommentsRef.child("comment").setValue(comment);
        mReplyCommentsRef.child("likeCount").setValue(0);
        mReplyCommentsRef.child("liked").setValue(false);
        mReplyCommentsRef.child("timestamp").setValue(CommentsUtil.getCurrentTime());
        mReplyCommentsRef.child("userIconSrc").setValue("abc");
        mReplyCommentsRef.child("username").setValue("username");
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
}
