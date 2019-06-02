package com.example.igclone.Comments.LiveData;


import android.app.ActivityManager;
import android.arch.lifecycle.MutableLiveData;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.example.igclone.Comments.DataModel.RepliesContainerItem;
import com.example.igclone.Comments.DataModel.ReplyItem;
import com.example.igclone.Comments.DataModel.CommentsDataModel;
import com.example.igclone.Comments.DataModel.ListItem;
import com.example.igclone.Comments.DataModel.MainItem;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

public class CommentsLiveDATA extends MutableLiveData<TreeMap<String, ListItem>> {

    private DatabaseReference mMainCommentsRef;
    private DatabaseReference mReplyCommentsRef;

    private static TreeMap<String, ListItem> commentItems = new TreeMap<>();

    private String postId;

    public CommentsLiveDATA(String postId)
    {

        mMainCommentsRef = FirebaseDatabase.getInstance().getReference("/Comments/"+postId+"/MainComments");
        mReplyCommentsRef = FirebaseDatabase.getInstance().getReference("/Comments/"+postId+"/ReplyComments");


        this.postId = postId;
    }

    @Override
    protected void onActive() {
        super.onActive();
        retrieveMainComments();

    }

    private void retrieveMainComments() {

        mMainCommentsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot mainCommentTimestamp, @Nullable String s) {
//                System.out.println(mainCommentTimestamp.child("username").getValue().toString());
                if(mainCommentTimestamp.getChildrenCount()==6)
                {
                    long timestamp = Long.valueOf(mainCommentTimestamp.getKey());
                    System.out.println("Child Count Met"+timestamp);
                    MainItem mainItem = new MainItem(timestamp, new CommentsDataModel(mainCommentTimestamp.child("userIconSrc").getValue().toString(), mainCommentTimestamp.child("username").getValue().toString(), mainCommentTimestamp.child("comment").getValue().toString(), Boolean.valueOf(mainCommentTimestamp.child("liked").getValue().toString()), Long.valueOf(mainCommentTimestamp.child("timestamp").getValue().toString()), Integer.valueOf(mainCommentTimestamp.child("likeCount").getValue().toString())));

                    checkHasReplies(mainItem);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot mainCommentTimestamp, @Nullable String s) {
                if(mainCommentTimestamp.getChildrenCount()==6)
                {
                    long timestamp = Long.valueOf(mainCommentTimestamp.getKey());
//                    System.out.println("Child Count Met"+timestamp);
                    MainItem mainItem = new MainItem(timestamp, new CommentsDataModel(mainCommentTimestamp.child("userIconSrc").getValue().toString(), mainCommentTimestamp.child("username").getValue().toString(), mainCommentTimestamp.child("comment").getValue().toString(), Boolean.valueOf(mainCommentTimestamp.child("liked").getValue().toString()), Long.valueOf(mainCommentTimestamp.child("timestamp").getValue().toString()), Integer.valueOf(mainCommentTimestamp.child("likeCount").getValue().toString())));

                    commentItems.put(String.valueOf(timestamp), mainItem);
                    setValue(commentItems);

                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot mainCommentTimestamp) {

                System.out.println(mainCommentTimestamp.getKey()+"Timestamp");
                commentItems.remove(mainCommentTimestamp.getKey());

                System.out.println(Arrays.asList(commentItems.keySet()).toString());

                setValue(commentItems);

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//        mMainCommentsRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                System.out.println(dataSnapshot.getChildrenCount()+"Main Comments Children count");
//                for(DataSnapshot mainCommentTimestamp:dataSnapshot.getChildren())
//                {
//                    if(mainCommentTimestamp.getChildrenCount()==6)
//                    {
//                        long timestamp = Long.valueOf(mainCommentTimestamp.getKey());
//                        MainItem mainItem = new MainItem(timestamp, new CommentsDataModel(mainCommentTimestamp.child("userIconSrc").getValue().toString(), mainCommentTimestamp.child("username").getValue().toString(), mainCommentTimestamp.child("comment").getValue().toString(), Boolean.valueOf(mainCommentTimestamp.child("liked").getValue().toString()), Long.valueOf(mainCommentTimestamp.child("timestamp").getValue().toString()), Integer.valueOf(mainCommentTimestamp.child("likeCount").getValue().toString())));
//
//                        checkHasReplies(mainItem);
//                    }
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        mMainCommentsRef.keepSynced(true);
    }

    private void checkHasReplies(final MainItem mainItem)
    {
        mReplyCommentsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(mainItem.getMainCommentTimestamp()+"R"))
                    retrievePostReplyComments(mainItem);
                else {
                    commentItems.put(String.valueOf(mainItem.getMainCommentTimestamp()), mainItem);
                    setValue(commentItems);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mReplyCommentsRef.keepSynced(true);
    }

    private void retrievePostReplyComments(final MainItem mainItem)
    {
//        System.out.println(commentItems.size()+" comment Items size in CommentLiveDATA");
        mReplyCommentsRef.child(mainItem.getMainCommentTimestamp()+"R").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ArrayList<ReplyItem> replyItems = new ArrayList<>();
//                System.out.println(dataSnapshot.getKey()+" Main Comment Reply Container Timestamp");
                for (DataSnapshot replyItem:dataSnapshot.getChildren())
                {
                    replyItems.add(new ReplyItem(dataSnapshot.getKey(), Long.valueOf(replyItem.getKey()), new CommentsDataModel(replyItem.child("userIconSrc").getValue().toString(), replyItem.child("username").getValue().toString(), replyItem.child("comment").getValue().toString(), Boolean.valueOf(replyItem.child("liked").getValue().toString()), Long.valueOf(replyItem.child("timestamp").getValue().toString()), Integer.valueOf(replyItem.child("likeCount").getValue().toString()))));
                }

//                System.out.println(" CommentsLiveDataR "+dataSnapshot.getKey());

                commentItems.put(String.valueOf(mainItem.getMainCommentTimestamp()), mainItem);
                RepliesContainerItem repliesContainerItem = new RepliesContainerItem(dataSnapshot.getKey(), replyItems);
                commentItems.put(dataSnapshot.getKey(), repliesContainerItem);

//                System.out.println("----------------------------Printing Data Comments LiveData-----------------------------------------");
//                for(String key:commentItems.keySet()) {
//                    if(commentItems.get(key).getType()== CommentsActivity.MAIN_COMMENT)
//                        System.out.println(((MainItem)commentItems.get(key)).getMainCommentTimestamp());
//                    else
//                        System.out.println(((RepliesContainerItem)commentItems.get(key)).getRepliesContainerTimestamp());
//                }
//                System.out.println("----------------------------------------------------------------------------------------------------");

                setValue(commentItems);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
