package com.example.igclone.ViewHolders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.igclone.DataModel.CommentsDataModel;
import com.example.igclone.R;
import com.example.igclone.Util.CommentsUtil;
import com.example.igclone.Util.PostUtil;

public class MainItemVH extends RecyclerView.ViewHolder implements View.OnClickListener{

    private ImageView userIcn;
    private TextView txtContent;
    private ImageButton likeBtn;
    private TextView timestamp;
    private TextView likeCount;
    private TextView replyBtn;

    public MainItemVH(@NonNull View itemView) {
        super(itemView);

        userIcn = itemView.findViewById(R.id.user_icon);
        txtContent = itemView.findViewById(R.id.comment_txt);
        likeBtn = itemView.findViewById(R.id.like_btn);
        timestamp = itemView.findViewById(R.id.timestamp);;
        likeCount = itemView.findViewById(R.id.likes_count);
        replyBtn = itemView.findViewById(R.id.reply_btn);
    }

    public void bind(CommentsDataModel data)
    {
        userIcn.setImageResource(data.getUserIcnSrc());

        int color = itemView.getContext().getResources().getColor(android.R.color.black, null);
        txtContent.setText(CommentsUtil.getCommentText(data.getUsername(), data.getComment(), color));

        likeBtn.setOnClickListener(this);

        timestamp.setText(PostUtil.getTimeAgo(data.getTimestamp(), itemView.getContext()));
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
                break;
            case R.id.reply_btn:
                Toast.makeText(itemView.getContext(), "Reply", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
