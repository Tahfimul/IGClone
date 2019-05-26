package com.example.igclone.DB;

import android.arch.lifecycle.ViewModel;
import com.google.firebase.database.*;

public class PostDB extends ViewModel {
    DatabaseReference mCommentsRef = FirebaseDatabase.getInstance().getReference("/Comments");

    public void retrieveAllPosts()
    {

    }

    public void makePost()
    {

    }

    public void postMainComment(String postId, long timestamp, String comment)
    {
        mCommentsRef.child(postId).child("MainComments").child(String.valueOf(timestamp)).child("comment").setValue(comment);
        mCommentsRef.child(postId).child("MainComments").child(String.valueOf(timestamp)).child("likeCount").setValue(0);
        mCommentsRef.child(postId).child("MainComments").child(String.valueOf(timestamp)).child("liked").setValue(false);
        mCommentsRef.child(postId).child("MainComments").child(String.valueOf(timestamp)).child("timestamp").setValue(timestamp);
        mCommentsRef.child(postId).child("MainComments").child(String.valueOf(timestamp)).child("userIconSrc").setValue("abc");
        mCommentsRef.child(postId).child("MainComments").child(String.valueOf(timestamp)).child("username").setValue("username");
    }

    public void postReplyComment(String postId, long mainCommentTimestamp, long timestamp, String comment)
    {
        mCommentsRef.child(postId).child("ReplyComments").child(String.valueOf(mainCommentTimestamp)).child(String.valueOf(timestamp)).child("comment").setValue(comment);
        mCommentsRef.child(postId).child("ReplyComments").child(String.valueOf(mainCommentTimestamp)).child(String.valueOf(timestamp)).child("likeCount").setValue(0);
        mCommentsRef.child(postId).child("ReplyComments").child(String.valueOf(mainCommentTimestamp)).child(String.valueOf(timestamp)).child("liked").setValue(false);
        mCommentsRef.child(postId).child("ReplyComments").child(String.valueOf(mainCommentTimestamp)).child(String.valueOf(timestamp)).child("timestamp").setValue(timestamp);
        mCommentsRef.child(postId).child("ReplyComments").child(String.valueOf(mainCommentTimestamp)).child(String.valueOf(timestamp)).child("userIconSrc").setValue("abc");
        mCommentsRef.child(postId).child("ReplyComments").child(String.valueOf(mainCommentTimestamp)).child(String.valueOf(timestamp)).child("username").setValue("username");
    }

}
