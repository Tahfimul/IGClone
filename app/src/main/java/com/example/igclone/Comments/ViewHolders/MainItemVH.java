package com.example.igclone.Comments.ViewHolders;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.igclone.Comments.CommentsActivity;
import com.example.igclone.Comments.DataModel.CommentsDataModel;
import com.example.igclone.Comments.DataModel.MainItem;
import com.example.igclone.Comments.Util.CommentsUtil;
import com.example.igclone.R;

public class MainItemVH extends CommentItemVH implements View.OnClickListener {

    private RelativeLayout mRelativeLayout;
    private boolean isSelected;
    private ImageView userIcn;
    private TextView txtContent;
    public ImageButton likeBtn;
    private TextView timestamp;
    public TextView likeCount;
    private TextView replyBtn;
    private CommentsDataModel data;


    public MainItemVH(@NonNull View itemView) {
        super(itemView);

        mRelativeLayout = itemView.findViewById(R.id.relativeLayout);
        userIcn = itemView.findViewById(R.id.user_icon);
        txtContent = itemView.findViewById(R.id.comment_txt);
        likeBtn = itemView.findViewById(R.id.like_btn);
        timestamp = itemView.findViewById(R.id.timestamp);;
        likeCount = itemView.findViewById(R.id.likes_count);
        replyBtn = itemView.findViewById(R.id.reply_btn);

//        registerItemSelectReqRes();
    }

    @Override
    public void bindMainItem(MainItem mainItem) {
        super.bindMainItem(mainItem);
        System.out.println("Received MainItem in MainItemVH "+mainItem.getMainCommentTimestamp());
        this.data = mainItem.getMainData();

        mRelativeLayout.setOnClickListener(this);

        if (mainItem.isSelected())
            setItemSelected();
        else
            setItemUnselected();

        userIcn.setImageResource(R.drawable.circle_border);

        int color = itemView.getContext().getResources().getColor(android.R.color.black, null);
        txtContent.setText(CommentsUtil.getCommentText(this.data.getUsername(), this.data.getComment(), color));

        likeBtn.setOnClickListener(this);

        if(this.data.isLiked())
            likeBtn.setImageResource(R.drawable.ic_liked);
        else
            likeBtn.setImageResource(R.drawable.ic_interactions);

//        System.out.println("MainItemVH timestamp "+data.getTimestamp());
//        System.out.println("MainItemVH timeAgo "+CommentsUtil.getTimeAgo(data.getTimestamp(), itemView.getContext()));
        timestamp.setText(CommentsUtil.getTimeAgo(this.data.getTimestamp(), itemView.getContext()));
        timestamp.setTextColor(color);

        likeCount.setText(this.data.getLikeCount() + " likes");
        likeCount.setTextColor(color);

        replyBtn.setOnClickListener(this);
        replyBtn.setTextColor(color);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.relativeLayout:
                System.out.println("MainItemVH selected");
//                Toast.makeText(itemView.getContext(), "Relative Layout Selected", Toast.LENGTH_SHORT).show();
                sendItemSelectReqBroadcast();
                break;
            case R.id.like_btn:
//                Toast.makeText(itemView.getContext(), "Liked", Toast.LENGTH_SHORT).show();
                if (data.isLiked()) {
                    CommentsUtil.likeBtnUnlikedInteractionAnimate(likeBtn, itemView.getContext());
                    CommentsUtil.mainCommentLikeInteraction(data.getTimestamp(), !data.isLiked(), data.getLikeCount()-1);
                }
                else
                {
                    CommentsUtil.likeBtnLikedInteractionAnimate(likeBtn, itemView.getContext());
                    CommentsUtil.mainCommentLikeInteraction(data.getTimestamp(), !data.isLiked(), data.getLikeCount()+1);
                }
                break;
            case R.id.reply_btn:
                sendReplyBroadcast();
//                Toast.makeText(itemView.getContext(), "Reply", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //Send  Main Comment Selected Broadcast Message to CommentsActivity
    private void sendItemSelectReqBroadcast() {
        Bundle bundle = new Bundle();
        bundle.putString("itemTimestamp", String.valueOf(data.getTimestamp()));
        bundle.putInt("itemType", CommentsActivity.MAIN_COMMENT);
        Intent i = new Intent();
        i.setAction("itemSelectReq");
        i.putExtras(bundle);
        LocalBroadcastManager.getInstance(itemView.getContext()).sendBroadcast(i);
    }

    //Send Reply Broadcast Message to CommentsActivity
    private void sendReplyBroadcast() {
        Bundle bundle = new Bundle();
        bundle.putString("replyContainerTimestamp", data.getTimestamp()+"R");
        bundle.putString("replyingToTimestamp", String.valueOf(data.getTimestamp()));
        bundle.putInt("replyingToSource", CommentsActivity.MAIN_COMMENT);
        Intent i = new Intent();
        i.setAction("reply");
        i.putExtras(bundle);
        LocalBroadcastManager.getInstance(itemView.getContext()).sendBroadcast(i);
    }

//    private void registerItemSelectReqRes()
//    {
//
//        // Register to receive confirmation to set Comment Item Selected or Unselected.
//        // We are registering an observer (itemSelectReqRes) to receive Intents
//        // with actions named "ItemSelectReqRes". from CommentsUtil
//        LocalBroadcastManager.getInstance(itemView.getContext()).registerReceiver(itemSelectReqResReceiver,
//                new IntentFilter("itemSelectReqRes"));
//    }
//
//    // Our handler for received Intent. This will be called whenever an Intent
//    // with an action named "ItemSelectReqRes" is broadcast from CommentsActivity
//    private BroadcastReceiver itemSelectReqResReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//
////            System.out.println("Item Select Req Res Receiver Triggered "+data.getTimestamp());
//            int itemType = intent.getExtras().getInt("itemType");
//
//            if(itemType == CommentsActivity.MAIN_COMMENT) {
//
//                boolean yes = intent.getExtras().getBoolean("res");
//                boolean del = intent.getExtras().getBoolean("del");
//                long timestamp = Long.valueOf(intent.getExtras().getString("itemTimestamp"));
//                if (yes && timestamp == data.getTimestamp()) {
//                    if (isSelected) {
//                        System.out.println("UnSelecting Item from MainItemVH "+data.getTimestamp());
//                        setItemUnselected();
//                    }
//
//                    else {
//                        System.out.println("Selecting Item from MainItemVH "+data.getTimestamp());
//                        setItemSelected();
//                    }
//
//                }
//
//                else if (!yes && del && timestamp == data.getTimestamp())
//                    setItemUnselected();
//            }
//        }
//    };

    private void setItemUnselected()
    {
        mRelativeLayout.setBackgroundResource(android.R.color.white);
        isSelected = false;
//        data.setItemSelected(isSelected);
    }

    private void setItemSelected()
    {
        System.out.println("Setting Item Selected "+data.getTimestamp());
        mRelativeLayout.setBackgroundResource(R.color.colorLightIGBlue);
        isSelected = true;
//        data.setItemSelected(isSelected);
    }
}
