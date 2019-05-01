package com.example.igclone.Util;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.TypedValue;
import android.widget.ImageView;
import com.example.igclone.Adapters.CommentsRecyclerAdapter;
import com.example.igclone.Adapters.RepliesItemAdapter;
import com.example.igclone.Comments.Data;
import com.example.igclone.DataModel.CommentsDataModel;
import com.example.igclone.DataModel.ListItem;
import com.example.igclone.DataModel.MainItem;
import com.example.igclone.DataModel.RepliesItem;
import com.example.igclone.R;

import java.util.ArrayList;

public class CommentsUtil {

    private static boolean dataInitialized =false;
    private static Data data = new Data();

    public static void initCommentsRecycler(RecyclerView recyclerView, Context ctx)
    {
        if (!dataInitialized)
        {
            System.out.println("init Reycler ran");
            data.initData();
            dataInitialized = true;
        }
        ArrayList<ListItem> data = new ArrayList<>();

        data.add(new MainItem(CommentsUtil.data.getMainData().get(0)));

        data.add(new RepliesItem(getRepliesData()));

        data.add(new MainItem(CommentsUtil.data.getMainData().get(1)));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ctx);
        recyclerView.setLayoutManager(layoutManager);

        CommentsRecyclerAdapter adapter = new CommentsRecyclerAdapter(data);
        recyclerView.setAdapter(adapter);
    }

    public static void initRepliesItemRecycler(RecyclerView recyclerView, Context ctx, ArrayList<CommentsDataModel> data)
    {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ctx);
        recyclerView.setLayoutManager(layoutManager);

        RepliesItemAdapter adapter = new RepliesItemAdapter(data);
        recyclerView.setAdapter(adapter);
    }

    public static void setMainData(CommentsDataModel data)
    {
        CommentsUtil.data.setMainData(data);
    }

    public static ArrayList<CommentsDataModel> getRepliesData()
    {

        return data.getRepliesData();
    }

    public static void setRepliesData(CommentsDataModel repliesData)
    {
        data.setRepliesData(repliesData);
    }

    public static SpannableString getCommentText(String username, String comment, int color) {
        String latestCommentText = username + " " + comment;
        SpannableString spannableString = new SpannableString(latestCommentText);

        spannableString.setSpan(new BoldTextClickableSpan(latestCommentText, color), 0, username.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        spannableString.setSpan(new NormalTextClickableSpan(latestCommentText, color), username.length()+1, latestCommentText.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        return spannableString;
    }

    public static void likeBtnLikedInteractionAnimate(final ImageView likeBtn, final Context context)
    {

        ObjectAnimator scaleX1 = ObjectAnimator.ofFloat(likeBtn, "scaleX", 0f);
        scaleX1.setDuration(context.getResources().getInteger(android.R.integer.config_shortAnimTime));
        ObjectAnimator scaleY1 = ObjectAnimator.ofFloat(likeBtn, "scaleY", 0f);
        scaleY1.setDuration(context.getResources().getInteger(android.R.integer.config_shortAnimTime));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleX1, scaleY1);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                likeBtn.setImageResource(R.drawable.ic_liked);
                ObjectAnimator scaleX1 = ObjectAnimator.ofFloat(likeBtn, "scaleX", 1.525f);
                scaleX1.setDuration(context.getResources().getInteger(android.R.integer.config_shortAnimTime));
                ObjectAnimator scaleY1 = ObjectAnimator.ofFloat(likeBtn, "scaleY", 1.525f);
                scaleY1.setDuration(context.getResources().getInteger(android.R.integer.config_shortAnimTime));
                ObjectAnimator translationZ1 = ObjectAnimator.ofFloat(likeBtn, "translationZ", TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 4,
                        context.getResources().getDisplayMetrics()) );
                translationZ1.setDuration(context.getResources().getInteger(android.R.integer.config_shortAnimTime));
                ObjectAnimator scaleX2 = ObjectAnimator.ofFloat(likeBtn, "scaleX", 1.0f);
                scaleX2.setDuration(context.getResources().getInteger(android.R.integer.config_shortAnimTime));
                scaleX2.setStartDelay(context.getResources().getInteger(android.R.integer.config_shortAnimTime));
                ObjectAnimator scaleY2 = ObjectAnimator.ofFloat(likeBtn, "scaleY", 1.0f);
                scaleY2.setDuration(context.getResources().getInteger(android.R.integer.config_shortAnimTime));
                scaleY2.setStartDelay(context.getResources().getInteger(android.R.integer.config_shortAnimTime));
                ObjectAnimator translationZ2 = ObjectAnimator.ofFloat(likeBtn, "translationZ", TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 0,
                        likeBtn.getResources().getDisplayMetrics()));
                translationZ2.setDuration(likeBtn.getResources().getInteger(android.R.integer.config_shortAnimTime));
                translationZ2.setStartDelay(likeBtn.getResources().getInteger(android.R.integer.config_shortAnimTime));

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(scaleX1, scaleY1, translationZ1, scaleX2, scaleY2, translationZ2);
                animatorSet.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.start();
    }

    public static void likeBtnUnlikedInteractionAnimate(final ImageView likeBtn, final Context context)
    {

        ObjectAnimator scaleX1 = ObjectAnimator.ofFloat(likeBtn, "scaleX", 0f);
        scaleX1.setDuration(context.getResources().getInteger(android.R.integer.config_shortAnimTime));
        ObjectAnimator scaleY1 = ObjectAnimator.ofFloat(likeBtn, "scaleY", 0f);
        scaleY1.setDuration(context.getResources().getInteger(android.R.integer.config_shortAnimTime));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleX1, scaleY1);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                likeBtn.setImageResource(R.drawable.ic_interactions);
                ObjectAnimator scaleX1 = ObjectAnimator.ofFloat(likeBtn, "scaleX", 1.525f);
                scaleX1.setDuration(context.getResources().getInteger(android.R.integer.config_shortAnimTime));
                ObjectAnimator scaleY1 = ObjectAnimator.ofFloat(likeBtn, "scaleY", 1.525f);
                scaleY1.setDuration(context.getResources().getInteger(android.R.integer.config_shortAnimTime));
                ObjectAnimator translationZ1 = ObjectAnimator.ofFloat(likeBtn, "translationZ", TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 4,
                        context.getResources().getDisplayMetrics()) );
                translationZ1.setDuration(context.getResources().getInteger(android.R.integer.config_shortAnimTime));
                ObjectAnimator scaleX2 = ObjectAnimator.ofFloat(likeBtn, "scaleX", 1.0f);
                scaleX2.setDuration(context.getResources().getInteger(android.R.integer.config_shortAnimTime));
                scaleX2.setStartDelay(context.getResources().getInteger(android.R.integer.config_shortAnimTime));
                ObjectAnimator scaleY2 = ObjectAnimator.ofFloat(likeBtn, "scaleY", 1.0f);
                scaleY2.setDuration(context.getResources().getInteger(android.R.integer.config_shortAnimTime));
                scaleY2.setStartDelay(context.getResources().getInteger(android.R.integer.config_shortAnimTime));
                ObjectAnimator translationZ2 = ObjectAnimator.ofFloat(likeBtn, "translationZ", TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 0,
                        likeBtn.getResources().getDisplayMetrics()));
                translationZ2.setDuration(likeBtn.getResources().getInteger(android.R.integer.config_shortAnimTime));
                translationZ2.setStartDelay(likeBtn.getResources().getInteger(android.R.integer.config_shortAnimTime));

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(scaleX1, scaleY1, translationZ1, scaleX2, scaleY2, translationZ2);
                animatorSet.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.start();
    }

    public static String getTimeAgo(long time, Context ctx) {

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
            System.out.println(new java.util.Date(time).toString().substring(0, 11) );
            return new java.util.Date(time).toString().substring(0, 11) + "";
        }
    }

    private static long getCurrentTime()
    {
        return System.currentTimeMillis();
    }
}
