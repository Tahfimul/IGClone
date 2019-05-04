package com.example.igclone.Adapters;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.*;
import android.widget.*;
import com.example.igclone.Comments.CommentsActivity;
import com.example.igclone.DataModel.PhotoPostDataModel;
import com.example.igclone.Fragments.MoreBtnDiag;
import com.example.igclone.Fragments.SendToUser;
import com.example.igclone.PosstData.ProfilePhotoFeedData;
import com.example.igclone.R;
import com.example.igclone.Util.*;

import java.util.ArrayList;

import static com.example.igclone.UserProfile.Util.UserProfileNavUtil.getTimeAgo;

public class PhotoPostRecyclerAdapter extends RecyclerView.Adapter<PhotoPostRecyclerAdapter.PhotoPostViewHolder> {

    ArrayList<PhotoPostDataModel> dataset;
    FragmentManager fragmentManager;

    public PhotoPostRecyclerAdapter()
    {

    }

    public PhotoPostRecyclerAdapter(ArrayList<PhotoPostDataModel> dataset, FragmentManager fragmentManager)
    {
        this.dataset = dataset;
        this.fragmentManager = fragmentManager;
    }


    public static class PhotoPostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView userIcon;
        TextView username;
        ImageButton moreBtn;
        ImageView postPhoto;
        RelativeLayout photoPostContainer;
        ImageView postPhotoInteractedIcn;
        ImageButton likeBtn;
        ImageButton commentBtn;
        ImageButton shareBtn;
        ImageButton bookmarkBtn;
        RelativeLayout statsBar;
        ImageView user1Icon;
        ImageView user2Icon;
        ImageView user3Icon;
        TextView latestInteractionUser;
        RelativeLayout statsBar2;
        TextView totalInteractionCount;
        TextView latestComment;
        TextView viewAllComments;
        TextView timeStamp;
        FragmentManager fragmentManager;
        PhotoPostDataModel data;

        public PhotoPostViewHolder(@NonNull View itemView, FragmentManager fragmentManager) {
            super(itemView);
            userIcon = itemView.findViewById(R.id.user_icon);
            username = itemView.findViewById(R.id.username);
            moreBtn = itemView.findViewById(R.id.more_btn);
            postPhoto = itemView.findViewById(R.id.post_image);
            photoPostContainer = itemView.findViewById(R.id.post_image_container);
            postPhotoInteractedIcn = itemView.findViewById(R.id.ic_interacted_post_photo);
            likeBtn = itemView.findViewById(R.id.like_btn);
            commentBtn = itemView.findViewById(R.id.comment_btn);
            shareBtn = itemView.findViewById(R.id.share_btn);
            bookmarkBtn = itemView.findViewById(R.id.bookmark_btn);
            statsBar = itemView.findViewById(R.id.stats_bar);
            user1Icon = itemView.findViewById(R.id.user_1);
            user2Icon = itemView.findViewById(R.id.user_2);
            user3Icon = itemView.findViewById(R.id.user_3);
            latestInteractionUser = itemView.findViewById(R.id.latest_user_ineraction);
            statsBar2 = itemView.findViewById(R.id.stats_bar2);
            totalInteractionCount = itemView.findViewById(R.id.interaction_count);
            latestComment= itemView.findViewById(R.id.latest_comment);
            viewAllComments = itemView.findViewById(R.id.view_all_comments);
            timeStamp = itemView.findViewById(R.id.timestamp);
            this.fragmentManager = fragmentManager;
        }

        void bind(PhotoPostDataModel data)
        {
            this.data = data;
            userIcon.setImageResource(data.getUserIconSrc());
            username.setText(data.getUsername());
            moreBtn.setOnClickListener(this);
            postPhoto.setImageResource(data.getPhotoSrc());

            if (data.isLiked())
                PostUtil.likeBtnLikedInteractionAnimate(likeBtn, itemView.getContext());
            else
                PostUtil.likeBtnUnlikedInteractionAnimate(likeBtn, itemView.getContext());

            if (data.isBookmarked())
                PostUtil.unbookmarkedInteractionAnimate(bookmarkBtn, itemView.getContext());
            else
                PostUtil.bookmarkedInteractionAnimate(bookmarkBtn, itemView.getContext());

            likeBtn.setOnClickListener(this);

            commentBtn.setOnClickListener(this);
            shareBtn.setOnClickListener(this);
            bookmarkBtn.setOnClickListener(this);


            statsBar.setOnClickListener(this);

            if(data.getUser1IconSrc()!=0)
                user1Icon.setImageResource(data.getUser1IconSrc());
            if (data.getUser2IconSrc()!=0)
                user1Icon.setImageResource(data.getUser2IconSrc());
            if(data.getUser3IconSrc()!=0)
                user3Icon.setImageResource(data.getUser3IconSrc());

            latestInteractionUser.setText(data.getLatestCommentUsername());
            statsBar2.setOnClickListener(this);
            totalInteractionCount.setText(String.valueOf(data.getUserLikeCount()));

            int color = itemView.getContext().getResources().getColor(android.R.color.black, null);

            latestComment.setText(PostUtil.getCaptionText(data.getLatestCommentUsername(), data.getLatestComment(), color));
            latestComment.setMovementMethod(LinkMovementMethod.getInstance());

            viewAllComments.setOnClickListener(this);

            timeStamp.setText(getTimeAgo(data.getTimeStamp(), itemView.getContext()));

        }


        @Override
        public void onClick(View v) {
            int viewId = v.getId();
            switch (viewId)
            {
                case R.id.more_btn:
                    MoreBtnDiag moreBtnDiag = new MoreBtnDiag();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    moreBtnDiag.show(ft, "ok");
                    break;
                case R.id.post_image:
                    break;
                case R.id.share_btn:
                    sendSendToUserTriggerMessage();
                    break;
                case R.id.stats_bar:
                    break;
                case R.id.latest_user_ineraction:
                    break;
                case R.id.stats_bar2:
                    break;
            }
            if(viewId==R.id.comment_btn)
                showComments(true);
            if(viewId==R.id.view_all_comments)
                showComments(false);
        }

        private void sendSendToUserTriggerMessage() {
            SendToUser sendToUser = new SendToUser();
            sendToUser.show(fragmentManager, "sendToUser");
        }

        private void showComments(boolean showKeyboard)
        {
            Bundle bundle = new Bundle();
            bundle.putBoolean("showKeyboard", showKeyboard);
            bundle.putInt("postSource", CommentsActivity.MAIN_COMMENT);
            bundle.putString("postId", data.getPostId());

            Intent commentsIntent = new Intent(itemView.getContext(), CommentsActivity.class);
            commentsIntent.putExtras(bundle);
            itemView.getContext().startActivity(commentsIntent);
        }
    }

    public void updateDataset(PhotoPostDataModel data, int pos)
    {
        System.out.println(data.getLatestComment());
        dataset.set(pos, data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PhotoPostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View photoPostView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_photo_post, viewGroup, false);
        PhotoPostViewHolder viewHolder = new PhotoPostViewHolder(photoPostView, fragmentManager);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final PhotoPostViewHolder photoPostViewHolder, int i) {

        final ProfilePhotoFeedData dataset = new ProfilePhotoFeedData();
        final PhotoPostDataModel data = this.dataset.get(i);
        photoPostViewHolder.bind(data);

        photoPostViewHolder.likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (data.isLiked())
                {
                    data.setLiked(false);
                }
                else
                {
                    data.setLiked(true);
                }
                dataset.initData();
                dataset.updateData(data);
                notifyDataSetChanged();
            }
        });

        photoPostViewHolder.bookmarkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(data.isBookmarked())
                    data.setBookmarked(false);
                else
                    data.setBookmarked(true);

                dataset.initData();
                dataset.updateData(data);
                notifyDataSetChanged();
            }
        });

        photoPostViewHolder.photoPostContainer.setOnTouchListener(new View.OnTouchListener() {
            private GestureDetector gestureDetector = new GestureDetector(photoPostViewHolder.itemView.getContext(), new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onDoubleTap(MotionEvent e) {

                    photoPostViewHolder.postPhotoInteractedIcn.setVisibility(View.VISIBLE);
                    data.setLiked(true);
                    notifyDataSetChanged();

                    PostUtil.photoInteractionAnimateUp(photoPostViewHolder.postPhotoInteractedIcn, photoPostViewHolder.itemView.getContext());

                    data.setLiked(true);
                    dataset.initData();
                    dataset.updateData(data);
                    notifyDataSetChanged();


                    return super.onDoubleTap(e);
                }
            });
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }


}
