package com.example.igclone.Comments.Util;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.igclone.Comments.Adapters.CommentsRecyclerAdapter;
import com.example.igclone.Comments.Adapters.RepliesContainerRecyclerAdapterNEW;
import com.example.igclone.Comments.CommentsActivity;
import com.example.igclone.Comments.DB.CommentsPostDB;
import com.example.igclone.Comments.Data;
import com.example.igclone.Comments.DataModel.*;
import com.example.igclone.Comments.LiveData.CommentsLiveDATA;
import com.example.igclone.R;
import com.example.igclone.Util.BoldTextClickableSpan;
import com.example.igclone.Util.NormalTextClickableSpan;

import java.util.ArrayList;
import java.util.TreeMap;

public class CommentsUtil extends ViewModel {

    private static boolean dataInitialized =false;
    private static Data data = new Data();
    private static TreeMap<String, ListItem> listITEMS = new TreeMap<>();

    private static CommentsPostDB postDB;

    private static CommentsRecyclerAdapter adapter;

    public MutableLiveData<TreeMap<String, ListItem>> retrieveCommentItems(String postId)
    {
        return new CommentsLiveDATA(postId);
    }

    public static void initCommentsRecycler(RecyclerView recyclerView, Context ctx, String postId)
    {
        System.out.println(getListItems().size()+"ListItems Size");
        if (!dataInitialized)
        {
            System.out.println("init Reycler ran");
            data.initData();
            dataInitialized = true;
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ctx);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new CommentsRecyclerAdapter();
        recyclerView.setAdapter(adapter);

        postDB = new CommentsPostDB(postId);
    }

    public static void postMainComment(String comment)
    {
        postDB.postMainComment(comment);
    }

    public static void postReplyComment(String replyContainerTimestamp, String comment)
    {
        postDB.postReplyComment(replyContainerTimestamp, comment);
    }

    public static void replyCommentLikeInteraction(String replyContainerTimestamp, long replyCommentTimestamp, boolean liked, int likeCount)
    {
        postDB.replyCommentLikeInteraction(replyContainerTimestamp, replyCommentTimestamp, liked, likeCount);
    }

    public static void mainCommentLikeInteraction(long mainCommentTimestamp, boolean liked, int likeCount)
    {
        postDB.mainCommentLikeInteraction(mainCommentTimestamp, liked, likeCount);
    }

//    public static void setMainCommentItemSelected(Context ctx, String currSelectedCommentItemTimestamp, Toolbar toolbar, ImageButton backBtn, TextView commentsTitle, ImageButton shareBtn)
//    {
//        //Get current firebaseUser Username
//        String curr_username = "username";
//
//        if (((MainItem)adapter.getDatasetListItem(currSelectedCommentItemTimestamp)).getMainData().getUsername().equals(curr_username))
//            setCurrUserCommentSelectedToolbarConfig(ctx, toolbar, backBtn, commentsTitle, shareBtn);
//        else
//            setOtherUserCommentSelectedToolbarConfig(ctx, toolbar, backBtn, commentsTitle, shareBtn);
//    }
//
//    public static void setMainCommentItemUnSelected(Context ctx, Toolbar toolbar, ImageButton backBtn, TextView commentsTitle, ImageButton shareBtn)
//    {
//        setDefaultToolbarConfig(ctx, toolbar, backBtn, commentsTitle, shareBtn);
//    }
//
//    public static void setReplyCommentItemSelected(Context ctx, String selectedReplyCommentItemContainerTimestamp, String currSelectedCommentItemTimestamp, Toolbar toolbar, ImageButton backBtn, TextView commentsTitle, ImageButton shareBtn)
//    {
//        //Get current firebaseUser Username
//        String curr_username = "username";
//
//        if(((ReplyItem)((RepliesContainerItem)adapter.getDatasetListItem(selectedReplyCommentItemContainerTimestamp)).getReplyItem(currSelectedCommentItemTimestamp)).getReplyData().getUsername().equals(curr_username))
//            setCurrUserCommentSelectedToolbarConfig(ctx, toolbar, backBtn, commentsTitle, shareBtn);
//        else
//            setOtherUserCommentSelectedToolbarConfig(ctx, toolbar, backBtn, commentsTitle, shareBtn);
//    }
//
//    public static void setReplyCommentItemUnSelected(Context ctx, Toolbar toolbar, ImageButton backBtn, TextView commentsTitle, ImageButton shareBtn)
//    {
//        setDefaultToolbarConfig(ctx, toolbar, backBtn, commentsTitle, shareBtn);
//    }

    public static void showItemSelectedToolbar(Context ctx, int itemType, String itemTimestamp, String itemContainerTimestamp, Toolbar toolbar, ImageButton leftBtn, TextView title, ImageButton rightBtn)
    {
        switch (itemType)
        {
            case CommentsActivity.MAIN_COMMENT:
                setMainItemSelectedToolbar(ctx, itemTimestamp, toolbar, leftBtn, title, rightBtn);
                break;
            case CommentsActivity.REPLY_COMMENT:
                setReplyItemSelectedToolbar(ctx, itemTimestamp, itemContainerTimestamp, toolbar, leftBtn, title, rightBtn);
                break;
        }
    }

    private static void setMainItemSelectedToolbar(Context ctx, String itemTimestamp, Toolbar toolbar, ImageButton leftBtn, TextView title, ImageButton rightBtn)
    {
        MainItem mainItem = (MainItem) adapter.getDatasetListItem(itemTimestamp);

        String itemUsername = mainItem.getMainData().getUsername();

        String dummyUsername = "username";

        if(itemUsername.equals(dummyUsername))
            setCurrUserItemSelectedToolbarConfig(ctx, toolbar, leftBtn, title, rightBtn);
        else
            setOtherUserCommentSelectedToolbarConfig(ctx, toolbar, leftBtn, title, rightBtn);
    }

    private static void setReplyItemSelectedToolbar(Context ctx, String itemTimestamp, String itemContainerTimestamp, Toolbar toolbar, ImageButton leftBtn, TextView title, ImageButton rightBtn)
    {
        RepliesContainerItem replyContainerItem = (RepliesContainerItem) adapter.getDatasetListItem(itemContainerTimestamp);

        ReplyItem replyItem = replyContainerItem.getReplyItem(itemTimestamp);

        String itemUsername = replyItem.getReplyData().getUsername();

        String dummyUsername = "username";

        if(itemUsername.equals(dummyUsername))
            setCurrUserItemSelectedToolbarConfig(ctx, toolbar, leftBtn, title, rightBtn);
        else
            setOtherUserCommentSelectedToolbarConfig(ctx, toolbar, leftBtn, title, rightBtn);
    }

    private static void setCurrUserItemSelectedToolbarConfig(Context ctx, Toolbar toolbar, ImageButton backBtn, TextView commentsTitle, ImageButton shareBtn)
    {
        Toast.makeText(ctx, "Curr User Comment Selected Toolbar Config", Toast.LENGTH_SHORT).show();
        toolbar.setBackgroundResource(R.color.colorIGBlue);
        backBtn.setImageResource(R.drawable.ic_close_white);
        Toast.makeText(ctx, commentsTitle.getText(), Toast.LENGTH_SHORT).show();
        commentsTitle.setText("1 Selected");
        commentsTitle.setTextColor(ctx.getResources().getColor(android.R.color.white, null));
        shareBtn.setImageResource(R.drawable.ic_delete_white);
    }

    private static void setOtherUserCommentSelectedToolbarConfig(Context ctx, Toolbar toolbar, ImageButton backBtn, TextView commentsTitle, ImageButton shareBtn)
    {
        toolbar.setBackgroundResource(R.color.colorIGBlue);
        backBtn.setImageResource(R.drawable.ic_close_white);
        commentsTitle.setText("1 Selected");
        commentsTitle.setTextColor(ctx.getResources().getColor(android.R.color.white, null));
        shareBtn.setImageResource(R.drawable.ic_info_white);
    }

    public static void showDefaultToolbar(Context ctx, Toolbar toolbar, ImageButton backBtn, TextView commentsTitle, ImageButton shareBtn)
    {
        toolbar.setBackgroundResource(android.R.color.white);
        backBtn.setImageResource(R.drawable.ic_back);
        commentsTitle.setText("Comments");
        commentsTitle.setTextColor(ctx.getResources().getColor(android.R.color.black, null));
        shareBtn.setImageResource(R.drawable.ic_share);
    }

    public static void updateCommentsRecyclerDataset(TreeMap<String, ListItem> items)
    {
        adapter.updatePreDataset(items);
    }

    public static String getReplyingToUsernameFromMain(String replyingToTimestamp)
    {
        return ((MainItem)adapter.getDatasetListItem(replyingToTimestamp)).getMainData().getUsername();
    }

    public static String getReplyingToUsernameFromReplyContainer(String replyContainerTimestamp, String replyingToTimestamp)
    {
        return ((RepliesContainerItem)adapter.getDatasetListItem(replyContainerTimestamp)).getReplyItem(replyingToTimestamp).getReplyData().getUsername();
    }

    public static void showKeyboard(View view)
    {
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    public static void dismissKeyboard(View view)
    {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    public static void initRepliesItemRecycler(RecyclerView recyclerView, Context ctx, ArrayList<ReplyItem> data)
    {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ctx);
        recyclerView.setLayoutManager(layoutManager);

        RepliesContainerRecyclerAdapterNEW adapter = new RepliesContainerRecyclerAdapterNEW(data);
        recyclerView.setAdapter(adapter);
    }

    public static void setListItems(TreeMap<String, ListItem> listItems) {
        CommentsUtil.listITEMS= listItems;
    }

    public static TreeMap<String, ListItem> getListItems() {
        return listITEMS;
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

        long now = getCurrentTime()*1000L;
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
            System.out.println(" time calculated " );
            return new java.util.Date(time).toString().substring(0, 11) + "";
        }
    }

    public static long getCurrentTime()
    {
        return System.currentTimeMillis()/1000L;
    }
}
