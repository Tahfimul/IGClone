package com.example.igclone.Comments.ViewHolders;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.*;
import com.example.igclone.Comments.CommentsActivity;
import com.example.igclone.Comments.DataModel.ReplyItem;
import com.example.igclone.Comments.Util.CommentsUtil;
import com.example.igclone.R;

public class ReplyItemVH extends RecyclerView.ViewHolder implements View.OnClickListener{

    private RelativeLayout mRelativeLayout;
    private boolean isSelected;
    private ImageView userIcn;
    private TextView txtContent;
    public ImageButton likeBtn;
    private TextView timestamp;
    public TextView likeCount;
    private TextView replyBtn;
    private ReplyItem data;

    public ReplyItemVH(@NonNull View itemView) {
        super(itemView);

        mRelativeLayout = itemView.findViewById(R.id.relativeLayout);
        userIcn = itemView.findViewById(R.id.user_icon);
        txtContent = itemView.findViewById(R.id.comment_txt);
        likeBtn = itemView.findViewById(R.id.like_btn);
        timestamp = itemView.findViewById(R.id.timestamp);;
        likeCount = itemView.findViewById(R.id.likes_count);
        replyBtn = itemView.findViewById(R.id.reply_btn);

        registerItemSelectReqRes();
    }

    public void bind(ReplyItem data)
    {
        this.data = data;

        mRelativeLayout.setOnClickListener(this);

        userIcn.setImageResource(R.drawable.circle_border);

        int color = itemView.getContext().getResources().getColor(android.R.color.black, null);
        txtContent.setText(CommentsUtil.getCommentText(data.getReplyData().getUsername(), data.getReplyData().getComment(), color));

        likeBtn.setOnClickListener(this);

        if(data.getReplyData().isLiked())
            likeBtn.setImageResource(R.drawable.ic_liked);
        else
            likeBtn.setImageResource(R.drawable.ic_interactions);


        timestamp.setText(CommentsUtil.getTimeAgo(data.getReplyData().getTimestamp(), itemView.getContext()));
        timestamp.setTextColor(color);

        likeCount.setText(data.getReplyData().getLikeCount() + " likes");
        likeCount.setTextColor(color);

        replyBtn.setOnClickListener(this);
        replyBtn.setTextColor(color);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.relativeLayout:
                sendItemSelectReq();
                break;

            case R.id.like_btn:
                Toast.makeText(itemView.getContext(), "Liked", Toast.LENGTH_SHORT).show();
                if (data.getReplyData().isLiked()) {
                    CommentsUtil.likeBtnUnlikedInteractionAnimate(likeBtn, itemView.getContext());

                    CommentsUtil.replyCommentLikeInteraction(data.getReplyContainerTimestamp(), data.getReplyCommentTimestamp(), !data.getReplyData().isLiked(), data.getReplyData().getLikeCount()-1);

                }
                else
                {
                    CommentsUtil.likeBtnLikedInteractionAnimate(likeBtn, itemView.getContext());

                    CommentsUtil.replyCommentLikeInteraction(data.getReplyContainerTimestamp(), data.getReplyCommentTimestamp(), !data.getReplyData().isLiked(), data.getReplyData().getLikeCount()+1);

                }
                break;
            case R.id.reply_btn:
//                System.out.println(data.getReplyContainerTimestamp()+" Reply Comment Main Timestamp ReplyItemVH");
                sendReplyBroadcast();
//                Toast.makeText(itemView.getContext(), "Reply | Timestamp" + data.getReplyContainerTimestamp(), Toast.LENGTH_SHORT).show();
                break;

        }

    }

    private void sendItemSelectReq()
    {
        int itemType = CommentsActivity.REPLY_COMMENT;
        String itemContainerTimestamp = data.getReplyContainerTimestamp();
        String itemTimestamp = String.valueOf(data.getReplyCommentTimestamp());

        Bundle b = new Bundle();
        b.putInt("itemType", itemType);
        b.putString("itemContainerTimestamp", itemContainerTimestamp);
        b.putString("itemTimestamp", itemTimestamp);

        Intent i = new Intent();
        i.setAction("itemSelectReq");
        i.putExtras(b);

        LocalBroadcastManager.getInstance(itemView.getContext()).sendBroadcast(i);
    }

    //Send Reply Broadcast Message to CommentsActivity
    private void sendReplyBroadcast() {
        Bundle bundle = new Bundle();
//        System.out.println(data.getReplyContainerTimestamp()+" Reply Comment Main Timestamp ReplyItemVH");
        bundle.putString("replyContainerTimestamp", data.getReplyContainerTimestamp());
        bundle.putString("replyingToTimestamp", String.valueOf(data.getReplyCommentTimestamp()));
        bundle.putInt("replyingToSource", CommentsActivity.REPLY_CONTAINER);

        Intent i = new Intent();
        i.setAction("reply");
        i.putExtras(bundle);

        LocalBroadcastManager.getInstance(itemView.getContext()).sendBroadcast(i);
    }

    private void registerItemSelectReqRes()
    {
        // Register to receive confirmation to set Comment Item Selected or Unselected.
        // We are registering an observer (mainCommentItemSelectionConfirmation) to receive Intents
        // with actions named "itemSelectReqRes". from CommentsActivity
        LocalBroadcastManager.getInstance(itemView.getContext()).registerReceiver(itemSelectReqRes,
                new IntentFilter("itemSelectReqRes"));
    }

    // Our handler for received Intent. This will be called whenever an Intent
    // with an action named "itemSelectReqRes" is broadcast from CommentsActivity
    private BroadcastReceiver itemSelectReqRes = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            int itemType = intent.getExtras().getInt("itemType");

            if (itemType==CommentsActivity.REPLY_COMMENT) {

                boolean yes = intent.getExtras().getBoolean("res");
                String itemContainerTimestamp = intent.getExtras().getString("itemContainerTimestamp");
                String itemTimestamp = intent.getExtras().getString("itemTimestamp");


//                System.out.println("res in ReplyItemVH "+ yes);
//                System.out.println("itemType received in ReplyItemVH "+itemType);
//                System.out.println("itemContainerTimestamp received in ReplyItemVH "+itemContainerTimestamp+" Equality "+itemContainerTimestamp.equals(data.getReplyContainerTimestamp()));
//                System.out.println("itemTimestamp received in ReplyItemVH "+itemTimestamp+" Equality "+itemTimestamp.equals(String.valueOf(data.getReplyCommentTimestamp())));

                if (yes && itemContainerTimestamp.equals(data.getReplyContainerTimestamp()) && itemTimestamp.equals(String.valueOf(data.getReplyCommentTimestamp()))) {

                    if (isSelected) {
                        setItemUnselected();
                    } else {
                        setItemSelected();
                    }
                }

            }
        }
    };

    private void setItemUnselected()
    {
//        System.out.println("Setting itemUnselected in replyItemVH");
        mRelativeLayout.setBackgroundResource(android.R.color.white);
        isSelected = false;
    }

    private void setItemSelected()
    {
//        System.out.println("Setting itemSelected in replyItemVH");
        mRelativeLayout.setBackgroundResource(R.color.colorLightIGBlue);
        isSelected = true;
    }
}
