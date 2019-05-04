package com.example.igclone.DB;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.example.igclone.DataModel.CommentsDataModel;
import com.example.igclone.DataModel.ListItem;
import com.example.igclone.DataModel.MainItem;
import com.example.igclone.DataModel.RepliesItem;
import com.google.firebase.database.*;

import java.util.ArrayList;

public class PostDB {
    DatabaseReference mCommentsRef = FirebaseDatabase.getInstance().getReference("/Comments");
    private static ArrayList<ListItem> commentItems = new ArrayList<>();

    public void retrieveAllPosts()
    {

    }

    public ArrayList<ListItem> retrievePostMainComments(String postId)
    {

        mCommentsRef.child(postId).child("MainComments").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                System.out.println(dataSnapshot.getChildrenCount()+"hindex");
                for(DataSnapshot mainCommentIndex:dataSnapshot.getChildren())
                {
                    int index = Integer.valueOf(mainCommentIndex.getKey());
                    commentItems.add(new MainItem(index, new CommentsDataModel(mainCommentIndex.child("userIconSrc").getValue().toString(), mainCommentIndex.child("username").getValue().toString(), mainCommentIndex.child("comment").getValue().toString(), Boolean.valueOf(mainCommentIndex.child("liked").getValue().toString()), Long.valueOf(mainCommentIndex.child("timestamp").getValue().toString()), Integer.valueOf(mainCommentIndex.child("likeCount").getValue().toString()))));
                    retrievePostReplyComments(index);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return commentItems;
    }

    public void retrievePostReplyComments(final int mainCommentIndex)
    {
        mCommentsRef.child("ReplyComments").child(String.valueOf(mainCommentIndex)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount()>0)
                {
                    ArrayList<CommentsDataModel> replyItems = new ArrayList<>();
                    for (DataSnapshot replyItem:dataSnapshot.getChildren())
                    {
                        replyItems.add(new CommentsDataModel(replyItem.child("userIconSrc").getValue().toString(), replyItem.child("username").getValue().toString(), replyItem.child("comment").getValue().toString(), Boolean.valueOf(replyItem.child("liked").getValue().toString()), Long.valueOf(replyItem.child("timestamp").getValue().toString()), Integer.valueOf(replyItem.child("likeCount").getValue().toString())));
                    }
                    commentItems.add(new RepliesItem(mainCommentIndex, replyItems));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void makePost()
    {

    }

    public void postMainComment(String postId, String comment)
    {
//        mCommentsRef.child(postId).child("MainComments").chi
    }

    public void postReplyComment(String postId, int mainCommentIndex)
    {

    }

}
