package com.example.igclone.Comments.LiveData;


import android.app.ActivityManager;
import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.example.igclone.Comments.DataModel.*;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

public class CommentsLiveDATA extends MutableLiveData<TreeMap<String, ListItem>> {

    private DatabaseReference mCommentsRef;
    private DatabaseReference mMainCommentsRef;
    private DatabaseReference mReplyCommentsRef;

    private static TreeMap<String, ListItem> commentItems = new TreeMap<>();

    private String postId;

    public CommentsLiveDATA(String postId)
    {

        mCommentsRef = FirebaseDatabase.getInstance().getReference("/Comments/"+postId);
        mMainCommentsRef = mCommentsRef.child("MainComments");
        mReplyCommentsRef = mCommentsRef.child("ReplyComments");


        this.postId = postId;
    }

    @Override
    protected void onActive() {
        super.onActive();

        mCommentsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println("Main Comments Children Count"+dataSnapshot.getChildrenCount());
                if (dataSnapshot.getChildrenCount()>0) {
                    if (commentItems.containsKey("null"))
                        commentItems.remove("null");
                    System.out.println("Retrieving Comments");
                    commentItems.clear();
                    retrieveMainComments();
                    retrieveReplyContainers();
                }
                else {
                    commentItems.put("null", new NullItem());
                    setValue(commentItems);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mCommentsRef.keepSynced(true);

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
                    commentItems.put(String.valueOf(timestamp), mainItem);
                    setValue(commentItems);
//                    checkHasReplies(mainItem);
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

                if (commentItems.containsKey(mainCommentTimestamp.getKey()+"R"))
                    commentItems.remove(mainCommentTimestamp.getKey()+"R");

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

//    private void checkHasReplies(final MainItem mainItem)
//    {
//        mReplyCommentsRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if(dataSnapshot.hasChild(mainItem.getMainCommentTimestamp()+"R")) {
//                    System.out.println("Reply Items Exist "+Arrays.asList(commentItems).toString());
//                    retrievePostReplyComments(mainItem);
//                }
//                else {
//                    commentItems.put(String.valueOf(mainItem.getMainCommentTimestamp()), mainItem);
//                    System.out.println("Setting Main Item To commentItems");
//                    setValue(commentItems);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
////        mReplyCommentsRef.keepSynced(true);
//    }

    private void retrieveReplyContainers()
    {
        mReplyCommentsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot replyContainer, @Nullable String s) {
                ArrayList<ReplyItem> replyItems = new ArrayList<>();
                for (DataSnapshot replyItem:replyContainer.getChildren())
                {
                    replyItems.add(new ReplyItem(replyContainer.getKey() , Long.valueOf(replyItem.getKey()), new CommentsDataModel(replyItem.child("userIconSrc").getValue().toString(), replyItem.child("username").getValue().toString(), replyItem.child("comment").getValue().toString(), Boolean.valueOf(replyItem.child("liked").getValue().toString()), Long.valueOf(replyItem.child("timestamp").getValue().toString()), Integer.valueOf(replyItem.child("likeCount").getValue().toString()))));

                }

                RepliesContainerItem repliesContainerItem = new RepliesContainerItem(replyContainer.getKey(), replyItems);
                commentItems.put(replyContainer.getKey(), repliesContainerItem);

                setValue(commentItems);

//                listenForChangesInReplyItems(replyContainer.getKey());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot replyContainerTimestamp) {
                commentItems.remove(replyContainerTimestamp.getKey());
                setValue(commentItems);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mReplyCommentsRef.keepSynced(true);
    }

    private void listenForChangesInReplyItems(final String replyContainerTimestamp)
    {

        mReplyCommentsRef.child(replyContainerTimestamp).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot ReplyItem, @Nullable String s) {
                ReplyItem replyItem = new ReplyItem(replyContainerTimestamp, Long.valueOf(ReplyItem.getKey()), new CommentsDataModel(ReplyItem.child("userIconSrc").getValue().toString(), ReplyItem.child("username").getValue().toString(), ReplyItem.child("comment").getValue().toString(), Boolean.valueOf(ReplyItem.child("liked").getValue().toString()), Long.valueOf(ReplyItem.child("timestamp").getValue().toString()), Integer.valueOf(ReplyItem.child("likeCount").getValue().toString())));
                if (commentItems.containsKey(replyContainerTimestamp)) {
                    RepliesContainerItem repliesContainerItem = (RepliesContainerItem) commentItems.get(replyContainerTimestamp);

                    if (!repliesContainerItem.getReplyItems().contains(replyItem)) {
                        repliesContainerItem.appendReplyItem(replyItem);

                        setValue(commentItems);
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot ReplyItem) {

                ReplyItem replyItem = new ReplyItem(replyContainerTimestamp, Long.valueOf(ReplyItem.getKey()), new CommentsDataModel(ReplyItem.child("userIconSrc").getValue().toString(), ReplyItem.child("username").getValue().toString(), ReplyItem.child("comment").getValue().toString(), Boolean.valueOf(ReplyItem.child("liked").getValue().toString()), Long.valueOf(ReplyItem.child("timestamp").getValue().toString()), Integer.valueOf(ReplyItem.child("likeCount").getValue().toString())));

                if (commentItems.containsKey(replyContainerTimestamp)) {
                    RepliesContainerItem repliesContainerItem = (RepliesContainerItem) commentItems.get(replyContainerTimestamp);

                    repliesContainerItem.removeReplyItem(replyItem);

                    setValue(commentItems);
                }

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mReplyCommentsRef.keepSynced(true);
    }



//    private void retrievePostReplyComments(final MainItem mainItem)
//    {
////        System.out.println(commentItems.size()+" comment Items size in CommentLiveDATA");
//        mReplyCommentsRef.child(mainItem.getMainCommentTimestamp()+"R").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                ArrayList<ReplyItem> replyItems = new ArrayList<>();
////                System.out.println(dataSnapshot.getKey()+" Main Comment Reply Container Timestamp");
//                for (DataSnapshot replyItem:dataSnapshot.getChildren())
//                {
//                    replyItems.add(new ReplyItem(dataSnapshot.getKey(), Long.valueOf(replyItem.getKey()), new CommentsDataModel(replyItem.child("userIconSrc").getValue().toString(), replyItem.child("username").getValue().toString(), replyItem.child("comment").getValue().toString(), Boolean.valueOf(replyItem.child("liked").getValue().toString()), Long.valueOf(replyItem.child("timestamp").getValue().toString()), Integer.valueOf(replyItem.child("likeCount").getValue().toString()))));
//                }
//
////                System.out.println(" CommentsLiveDataR "+dataSnapshot.getKey());
//
//                commentItems.put(String.valueOf(mainItem.getMainCommentTimestamp()), mainItem);
//                RepliesContainerItem repliesContainerItem = new RepliesContainerItem(dataSnapshot.getKey(), replyItems);
//                commentItems.put(dataSnapshot.getKey(), repliesContainerItem);
//
////                System.out.println("----------------------------Printing Data Comments LiveData-----------------------------------------");
////                for(String key:commentItems.keySet()) {
////                    if(commentItems.get(key).getType()== CommentsActivity.MAIN_COMMENT)
////                        System.out.println(((MainItem)commentItems.get(key)).getMainCommentTimestamp());
////                    else
////                        System.out.println(((RepliesContainerItem)commentItems.get(key)).getRepliesContainerTimestamp());
////                }
////                System.out.println("----------------------------------------------------------------------------------------------------");
//
//                setValue(commentItems);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
}
