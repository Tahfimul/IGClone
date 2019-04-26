package com.example.igclone.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.igclone.DataModel.PhotoPostDataModel;
import com.example.igclone.R;

import java.util.ArrayList;

public class PhotoPostRecyclerAdapter extends RecyclerView.Adapter<PhotoPostRecyclerAdapter.PhotoPostViewHolder> {

    ArrayList<PhotoPostDataModel> dataset;

    public PhotoPostRecyclerAdapter(ArrayList<PhotoPostDataModel> dataset)
    {
        this.dataset = dataset;
    }


    public class PhotoPostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView userIcon;
        TextView username;
        ImageButton moreBtn;
        ImageView postPhoto;
        ImageButton likeBtn;
        ImageButton commentBtn;
        ImageButton shareBtn;
        ImageButton bookmarkBtn;
        ImageView user1Icon;
        ImageView user2Icon;
        ImageView user3Icon;
        TextView latestInteractionUser;
        TextView totalInteractionCount;
        TextView latestComment;
        TextView viewAllComments;
        TextView timeStamp;

        public PhotoPostViewHolder(@NonNull View itemView) {
            super(itemView);
            userIcon = itemView.findViewById(R.id.user_icon);
            username = itemView.findViewById(R.id.username);
            moreBtn = itemView.findViewById(R.id.more_btn);
            postPhoto = itemView.findViewById(R.id.post_image);
            likeBtn = itemView.findViewById(R.id.like_btn);
            commentBtn = itemView.findViewById(R.id.comment_btn);
            shareBtn = itemView.findViewById(R.id.share_btn);
            bookmarkBtn = itemView.findViewById(R.id.bookmark_btn);
            user1Icon = itemView.findViewById(R.id.user_1);
            user2Icon = itemView.findViewById(R.id.user_2);
            user3Icon = itemView.findViewById(R.id.user_3);
            latestInteractionUser = itemView.findViewById(R.id.latest_user_ineraction);
            totalInteractionCount = itemView.findViewById(R.id.interaction_count);
            latestComment= itemView.findViewById(R.id.latest_comment);
            viewAllComments = itemView.findViewById(R.id.view_all_comments);
            timeStamp = itemView.findViewById(R.id.timestamp);
        }

        void bind(PhotoPostDataModel data)
        {
            userIcon.setImageResource(data.getUserIconSrc());
            username.setText(data.getUsername());
            moreBtn.setOnClickListener(this);
            postPhoto.setImageResource(data.getPhotoSrc());

            if (data.isLiked())
                likeBtn.setImageResource(R.drawable.ic_liked);
            else
                likeBtn.setImageResource(R.drawable.ic_interactions);

            commentBtn.setOnClickListener(this);
            shareBtn.setOnClickListener(this);
            bookmarkBtn.setOnClickListener(this);

            if(data.getUser1IconSrc()!=0)
                user1Icon.setImageResource(data.getUser1IconSrc());
            if (data.getUser2IconSrc()!=0)
                user1Icon.setImageResource(data.getUser2IconSrc());
            if(data.getUser3IconSrc()!=0)
                user3Icon.setImageResource(data.getUser3IconSrc());

            latestInteractionUser.setText(data.getLatestCommentUsername());
            totalInteractionCount.setText(String.valueOf(data.getUserLikeCount()));

            setLatestCommentText(data);

            viewAllComments.setOnClickListener(this);

            setTimestamp(data);

        }

        private void setLatestCommentText(PhotoPostDataModel data) {
            String latestCommentText = data.getLatestCommentUsername() + " " +data.getLatestComment();
            SpannableString spannableString = new SpannableString(latestCommentText);
            spannableString.setSpan(new StyleSpan(Typeface.BOLD), 0, data.getLatestCommentUsername().length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            latestComment.setText(spannableString);
        }

        private void setTimestamp(PhotoPostDataModel data)
        {
            timeStamp.setText(getTimeAgo(data.getTimeStamp(), itemView.getContext()));
        }


        private String getTimeAgo(long time, Context ctx) {

            final int SECOND_MILLIS = 1000;
            final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
            final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
            final int DAY_MILLIS = 24 * HOUR_MILLIS;

            if (time < 1000000000000L) {
                // if timestamp given in seconds, convert to millis
                time *= 1000;
            }

            long now = getCurrentTime();
            if (time > now || time <= 0) {

                System.out.println(now+" time returning"+time);
                return null;
            }

            // TODO: localize
            final long diff = now - time;
            System.out.println(diff+" time diff");
            if (diff < MINUTE_MILLIS) {
                return "Just now";
            } else if (diff < 2 * MINUTE_MILLIS) {
                return "a minute ago";
            } else if (diff < 50 * MINUTE_MILLIS) {
                return diff / MINUTE_MILLIS + " minutes ago";
            } else if (diff < 90 * MINUTE_MILLIS) {
                return "an hour ago";
            } else if (diff < 24 * HOUR_MILLIS) {
                return diff / HOUR_MILLIS + " hours ago";
            } else if (diff < 48 * HOUR_MILLIS) {
                return "yesterday";
            } else {
                return new java.util.Date(time) + "";
            }
        }

        private long getCurrentTime()
        {
            return System.currentTimeMillis();
        }

        @Override
        public void onClick(View v) {

        }
    }

    @NonNull
    @Override
    public PhotoPostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View photoPostView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_photo_post, viewGroup, false);
        PhotoPostViewHolder viewHolder = new PhotoPostViewHolder(photoPostView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoPostViewHolder photoPostViewHolder, int i) {
        photoPostViewHolder.bind(dataset.get(i));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }


}
