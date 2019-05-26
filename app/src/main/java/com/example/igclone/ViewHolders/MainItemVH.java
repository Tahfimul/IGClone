package com.example.igclone.ViewHolders;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.igclone.Comments.CommentsActivity;
import com.example.igclone.DataModel.CommentsDataModel;
import com.example.igclone.R;
import com.example.igclone.Util.CommentsUtil;
import com.example.igclone.Util.PostUtil;

public class MainItemVH extends RecyclerView.ViewHolder implements View.OnClickListener{

    private ImageView userIcn;
    private TextView txtContent;
    public ImageButton likeBtn;
    private TextView timestamp;
    public TextView likeCount;
    private TextView replyBtn;
    private CommentsDataModel data;
    private long MainCommentTimestamp;
    private int MainCommentIndex;

    public MainItemVH(@NonNull View itemView) {
        super(itemView);

        userIcn = itemView.findViewById(R.id.user_icon);
        txtContent = itemView.findViewById(R.id.comment_txt);
        likeBtn = itemView.findViewById(R.id.like_btn);
        timestamp = itemView.findViewById(R.id.timestamp);;
        likeCount = itemView.findViewById(R.id.likes_count);
        replyBtn = itemView.findViewById(R.id.reply_btn);
    }

    public void bind(long MainCommentTimestamp, int MainCommentIndex, CommentsDataModel data)
    {
        this.data = data;
        this.MainCommentTimestamp = MainCommentTimestamp;
        this.MainCommentIndex = MainCommentIndex;

        userIcn.setImageResource(R.drawable.circle_border);

        int color = itemView.getContext().getResources().getColor(android.R.color.black, null);
        txtContent.setText(CommentsUtil.getCommentText(data.getUsername(), data.getComment(), color));

        likeBtn.setOnClickListener(this);

        if(data.isLiked())
            likeBtn.setImageResource(R.drawable.ic_liked);
        else
            likeBtn.setImageResource(R.drawable.ic_interactions);


        timestamp.setText(CommentsUtil.getTimeAgo(data.getTimestamp(), itemView.getContext()));
        timestamp.setTextColor(color);

        likeCount.setText(data.getLikeCount() + " likes");
        likeCount.setTextColor(color);

        replyBtn.setOnClickListener(this);
        replyBtn.setTextColor(color);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.like_btn:
                Toast.makeText(itemView.getContext(), "Liked", Toast.LENGTH_SHORT).show();
//                if (data.isLiked()) {
//                    CommentsUtil.likeBtnUnlikedInteractionAnimate(likeBtn, itemView.getContext());
//                    data.setLiked(false);
//                }
//                else
//                {
//                    CommentsUtil.likeBtnLikedInteractionAnimate(likeBtn, itemView.getContext());
//                    data.setLiked(true);
//                }
                break;
            case R.id.reply_btn:
                sendReplyBroadcast();
                Toast.makeText(itemView.getContext(), "Reply", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    //Send Reply Broadcast Message to CommentsActivity
    private void sendReplyBroadcast() {
        Bundle bundle = new Bundle();
        bundle.putInt("commentState", CommentsActivity.REPLY_COMMENT);
        bundle.putBoolean("showKeyboard", true);
        bundle.putLong("mainCommentTimestamp", data.getTimestamp());
        bundle.putInt("mainCommentIndex", MainCommentIndex);
        Intent i = new Intent();
        i.setAction("reply");
        i.putExtras(bundle);
        LocalBroadcastManager.getInstance(itemView.getContext()).sendBroadcast(i);
    }

}
