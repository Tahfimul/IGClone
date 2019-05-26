package com.example.igclone.LiveData;


import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import com.example.igclone.DataModel.CommentsDataModel;
import com.example.igclone.Comments.DataModel.ListItem;
import com.example.igclone.Comments.DataModel.MainItem;
import com.example.igclone.Comments.DataModel.RepliesItem;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class CommentsLiveDATA extends MutableLiveData<TreeMap<String, ListItem>> {
    DatabaseReference mCommentsRef;
    private static TreeMap<String, ListItem> commentItems = new TreeMap<>();

    private String postId;

    private static int itemCount;


    public CommentsLiveDATA(String postId)
    {
        mCommentsRef = FirebaseDatabase.getInstance().getReference("/Comments/"+postId);
        this.postId = postId;
    }

    @Override
    protected void onActive() {
        super.onActive();

        System.out.println("COMMENTS LIVE DATA ACTIVE");

        retrieveMainComments();

    }

    private void retrieveMainComments() {

        mCommentsRef.child("MainComments").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println(dataSnapshot.getChildrenCount()+"Main Comments Children count");
                itemCount = (int) dataSnapshot.getChildrenCount();
                for(DataSnapshot mainCommentTimestamp:dataSnapshot.getChildren())
                {
                    long timestamp = Long.valueOf(mainCommentTimestamp.getKey());
                    System.out.println("Item Timestamp: "+timestamp);
                    MainItem mainItem = new MainItem(timestamp, new CommentsDataModel(mainCommentTimestamp.child("userIconSrc").getValue().toString(), mainCommentTimestamp.child("username").getValue().toString(), mainCommentTimestamp.child("comment").getValue().toString(), Boolean.valueOf(mainCommentTimestamp.child("liked").getValue().toString()), Long.valueOf(mainCommentTimestamp.child("timestamp").getValue().toString()), Integer.valueOf(mainCommentTimestamp.child("likeCount").getValue().toString())));

                    retrievePostReplyComments(mainItem);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mCommentsRef.keepSynced(true);
    }

    private void retrievePostReplyComments(final MainItem mainItem)
    {
        System.out.println(commentItems.size()+" comment Items size in CommentLiveDATA");
        mCommentsRef.child("ReplyComments").child(String.valueOf(mainItem.getMainCommentTimestamp())).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                itemCount+=(int) dataSnapshot.getChildrenCount();

//                if (commentItems.size()>itemCount)
//                    commentItems.clear();

                if (dataSnapshot.getChildrenCount()>0)
                {
                    ArrayList<CommentsDataModel> replyItems = new ArrayList<>();
                    for (DataSnapshot replyItem:dataSnapshot.getChildren())
                    {
                        itemCount++;
                        replyItems.add(new CommentsDataModel(replyItem.child("userIconSrc").getValue().toString(), replyItem.child("username").getValue().toString(), replyItem.child("comment").getValue().toString(), Boolean.valueOf(replyItem.child("liked").getValue().toString()), Long.valueOf(replyItem.child("timestamp").getValue().toString()), Integer.valueOf(replyItem.child("likeCount").getValue().toString())));
                    }
                    commentItems.put(String.valueOf(mainItem.getMainCommentTimestamp()), mainItem);
//                    commentItems.get(commentItems.indexOf(mainItem)).setMainItemPos(commentItems.indexOf(mainItem));
                    RepliesItem repliesItem = new RepliesItem(mainItem.getMainCommentTimestamp(), replyItems);
                    repliesItem.setMainItemPos(commentItems.size());
                    commentItems.put(mainItem.getMainCommentTimestamp()+"R", repliesItem);
//                    commentItems.put(mainItem.getMainCommentTimestamp(), mainItem);
//                    commentItems.put(mainItem.getMainCommentTimestamp()+1, new RepliesItem(mainItem.getMainCommentTimestamp(), replyItems));
                }
                else
                {
                    commentItems.put(String.valueOf(mainItem.getMainCommentTimestamp()), mainItem);
//                    mainItem.setMainItemPos(commentItems.indexOf(mainItem));
                }

                setValue(commentItems);
                if(commentItems.size()==itemCount)
                {
                    System.out.println(itemCount+"Item Count in CommentsLiveDATA");
//                    for(Long timestamp:commentItems.keySet())
//                    {
//                        if (commentItems.get(timestamp).getType()==CommentsActivity.MAIN_COMMENT)
//                            System.out.println("Printing Data " +timestamp+" , Type Main");
//                        else
//                            System.out.println("Printing Data " +timestamp+" , Type Reply");
//                    }
                    setValue(commentItems);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
