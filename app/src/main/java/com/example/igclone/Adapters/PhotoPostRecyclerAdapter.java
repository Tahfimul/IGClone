package com.example.igclone.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.*;
import android.widget.*;
import com.example.igclone.DataModel.PhotoPostDataModel;
import com.example.igclone.Fragments.MoreBtnDiag;
import com.example.igclone.PosstData.ProfilePhotoFeedData;
import com.example.igclone.R;
import com.example.igclone.Util.CommentClickableSpan;
import com.example.igclone.Util.CommentUserClickableSpan;
import com.example.igclone.Util.PostUtil;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

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
//            setPostPhotoDoubleTapListener();

            if (data.isLiked())
                likeBtn.setImageResource(R.drawable.ic_liked);
            else
                likeBtn.setImageResource(R.drawable.ic_interactions);

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

            setLatestCommentText(data);

            viewAllComments.setOnClickListener(this);

            setTimestamp(data);

        }

        private void setLatestCommentText(PhotoPostDataModel data) {
            String latestCommentText = data.getLatestCommentUsername() + " " +data.getLatestComment();
            SpannableString spannableString = new SpannableString(latestCommentText);

            int color = itemView.getContext().getResources().getColor(android.R.color.black, null);

            spannableString.setSpan(new CommentUserClickableSpan(latestCommentText, color), 0, data.getLatestCommentUsername().length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            spannableString.setSpan(new CommentClickableSpan(latestCommentText, color), data.getLatestCommentUsername().length()+1, latestCommentText.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

            latestComment.setText(spannableString);
            latestComment.setMovementMethod(LinkMovementMethod.getInstance());
        }

        private void setTimestamp(PhotoPostDataModel data)
        {
            timeStamp.setText(getTimeAgo(data.getTimeStamp(), itemView.getContext()));
        }


        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.more_btn:
                    MoreBtnDiag moreBtnDiag = new MoreBtnDiag();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    moreBtnDiag.show(ft, "ok");
                    break;
                case R.id.post_image:

                    break;

                case R.id.comment_btn:
                    break;
                case R.id.share_btn:
                    break;
                case R.id.bookmark_btn:

                    break;
                case R.id.stats_bar:
                    break;
                case R.id.latest_user_ineraction:
                    break;
                case R.id.stats_bar2:
                    break;
            }
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

        photoPostViewHolder.photoPostContainer.setOnTouchListener(new View.OnTouchListener() {
            private GestureDetector gestureDetector = new GestureDetector(photoPostViewHolder.itemView.getContext(), new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onDoubleTap(MotionEvent e) {

                    photoPostViewHolder.postPhotoInteractedIcn.setVisibility(View.VISIBLE);
                    data.setLiked(true);
                    notifyDataSetChanged();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            photoPostViewHolder.postPhotoInteractedIcn.setVisibility(View.GONE);
                        }
                    }, 1000);


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
