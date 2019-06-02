package com.example.igclone.Comments.LiveData;


import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import com.example.igclone.Comments.DataModel.CommentsDataModel;
import com.example.igclone.Comments.DataModel.ListItem;
import com.example.igclone.Comments.DataModel.MainItem;
import com.google.firebase.database.*;

import java.util.ArrayList;

public class CommentsLiveData extends MutableLiveData<ArrayList<ListItem>> {
    DatabaseReference mCommentsRef;
    private static ArrayList<ListItem> commentItems = new ArrayList<>();
//    private static TreeMap<Long, ListItem> commentItems = new TreeMap<>();

    String postId;

    public CommentsLiveData(String postId)
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
                for(DataSnapshot mainCommentTimestamp:dataSnapshot.getChildren())
                {
                    long timestamp = Long.valueOf(mainCommentTimestamp.getKey());
                    System.out.println("Item Timestamp: "+timestamp);
                    MainItem mainItem = new MainItem(timestamp, new CommentsDataModel(mainCommentTimestamp.child("userIconSrc").getValue().toString(), mainCommentTimestamp.child("username").getValue().toString(), mainCommentTimestamp.child("comment").getValue().toString(), Boolean.valueOf(mainCommentTimestamp.child("liked").getValue().toString()), Long.valueOf(mainCommentTimestamp.child("timestamp").getValue().toString()), Integer.valueOf(mainCommentTimestamp.child("likeCount").getValue().toString())));
//                    retrievePostReplyComments(mainItem, (int) dataSnapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

//    private void retrievePostReplyComments(final MainItem mainItem, final int itemCount)
//    {
//        mCommentsRef.child("ReplyComments").child(String.valueOf(mainItem.getMainCommentTimestamp())).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                if (commentItems.size()>itemCount)
//                    commentItems.clear();
//
//                if (dataSnapshot.getChildrenCount()>0)
//                {
//                    ArrayList<CommentsDataModel> replyItems = new ArrayList<>();
//                    for (DataSnapshot replyItem:dataSnapshot.getChildren())
//                    {
//                        replyItems.add(new CommentsDataModel(replyItem.child("userIconSrc").getValue().toString(), replyItem.child("username").getValue().toString(), replyItem.child("comment").getValue().toString(), Boolean.valueOf(replyItem.child("liked").getValue().toString()), Long.valueOf(replyItem.child("timestamp").getValue().toString()), Integer.valueOf(replyItem.child("likeCount").getValue().toString())));
//                    }
//                    commentItems.add(mainItem);
//                    commentItems.get(commentItems.indexOf(mainItem)).setMainItemPos(commentItems.indexOf(mainItem));
//                    ReplyItem replyItem = new ReplyItem(mainItem.getMainCommentTimestamp(), replyItems);
//                    replyItem.setMainItemPos(commentItems.size());
//                    commentItems.add(replyItem);
////                    commentItems.put(mainItem.getMainCommentTimestamp(), mainItem);
////                    commentItems.put(mainItem.getMainCommentTimestamp()+1, new ReplyItem(mainItem.getMainCommentTimestamp(), replyItems));
//                }
//                else
//                {
//                    commentItems.add(mainItem);
//                    mainItem.setMainItemPos(commentItems.indexOf(mainItem));
//                }
//
//                if(commentItems.size()==itemCount)
//                {
////                    for(Long timestamp:commentItems.keySet())
////                    {
////                        if (commentItems.get(timestamp).getType()==CommentsActivity.MAIN_COMMENT)
////                            System.out.println("Printing Data " +timestamp+" , Type Main");
////                        else
////                            System.out.println("Printing Data " +timestamp+" , Type Reply");
////                    }
//                    setValue(commentItems);
//                }
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
