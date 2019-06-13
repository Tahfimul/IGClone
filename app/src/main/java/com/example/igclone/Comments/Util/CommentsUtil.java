package com.example.igclone.Comments.Util;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
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
import com.example.igclone.Comments.Adapters.RepliesContainerRecyclerAdapter;
import com.example.igclone.Comments.CommentsActivity;
import com.example.igclone.Comments.DB.CommentsPostDB;
import com.example.igclone.Comments.DataModel.*;
import com.example.igclone.Comments.LiveData.CommentsLiveDATA;
import com.example.igclone.Fragments.OtherUserItemSelectDiag;
import com.example.igclone.R;
import com.example.igclone.Util.BoldTextClickableSpan;
import com.example.igclone.Util.NormalTextClickableSpan;
import com.example.igclone.Util.SendToUserUtil;
import com.google.android.gms.dynamic.SupportFragmentWrapper;

import java.util.ArrayList;
import java.util.TreeMap;

public class CommentsUtil extends ViewModel {

    private static CommentsPostDB postDB;

    private static CommentsRecyclerAdapter adapter;

    //Comment Item Selected Vars
    private static int curr_selected_item_type;
    private static String curr_selected_Item_timestamp;
    private static String curr_selected_item_container_timestamp;
    private static boolean isItemSelected;
    private static final int CURR_USER_ITEM_SELECTED = 0;
    private static final int OTHER_USER_ITEM_SELECTED = 1;
    private static Activity ctx;
    private static FragmentManager fm;
    private static Toolbar mToolbar;
    private static ImageView mLeftBtn;
    private static TextView mTitle;
    private static ImageView mRightBtn;

    public MutableLiveData<TreeMap<String, ListItem>> retrieveCommentItems(String postId)
    {
        return new CommentsLiveDATA(postId);
    }

    public static void initCommentsRecycler(RecyclerView recyclerView, String postId)
    {
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
        System.out.println(replyContainerTimestamp+" ");
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

    public static void setActivityComponents(Activity ctx, FragmentManager fm)
    {
        CommentsUtil.ctx = ctx;
        CommentsUtil.fm = fm;

    }
    public static void setToolbarComponents(Toolbar toolbar, ImageView leftBtn, TextView title, ImageView rightBtn)
    {
//        System.out.println(isItemSelected+" isItemSelected in CommentsUtil "+getCurrentTime());
//        System.out.println(curr_selected_item_type+ " curr selected type in CommentsUtil "+getCurrentTime());
//        System.out.println(curr_selected_item_container_timestamp + " curr selected item container timestamp in CommentsUtil "+getCurrentTime());
//        System.out.println(curr_selected_Item_timestamp + " curr selected item timestamp in CommentsUtil "+getCurrentTime());

        mToolbar = toolbar;
        mLeftBtn = leftBtn;
        mTitle = title;
        mRightBtn = rightBtn;
    }

    public static void updateToolbar(int type, String itemTimestamp, String itemContainerTimestamp)
    {
        System.out.println(type+ " type received in CommentsUtil "+getCurrentTime());
        switch (type)
        {
            case CommentsActivity.MAIN_COMMENT:

                System.out.println(isItemSelected+" isItemSelected in CommentsUtil "+getCurrentTime());
                System.out.println(curr_selected_Item_timestamp + "currSelectedItemTimestamp in CommentsUtil"+getCurrentTime());
                if(isItemSelected)
                {
                    if(curr_selected_item_type == CommentsActivity.MAIN_COMMENT)
                        if(curr_selected_Item_timestamp.equals(itemTimestamp))
                            setItemUnselected(type);
                        else
                            sendItemSelectReqRes(type, itemTimestamp, false);
                }
                else
                    setItemSelected(type, itemTimestamp, null);

                break;

            case CommentsActivity.REPLY_COMMENT:

//                    System.out.println(itemTimestamp+" itemTimestamp received in CommentsActivity");
//                    System.out.println(itemContainerTimestamp+" itemContainerTimestamp received in CommentsActivity");
                if (isItemSelected)
                {
                    if (curr_selected_item_type == CommentsActivity.REPLY_COMMENT) {

                        if (curr_selected_item_container_timestamp.equals(itemContainerTimestamp) && curr_selected_Item_timestamp.equals(itemTimestamp))
                            setItemUnselected(type);

                    }
                    else
                        sendItemSelectReqRes(type, itemContainerTimestamp, itemTimestamp, false);
                }

                else
                    setItemSelected(type, itemTimestamp, itemContainerTimestamp);

                break;


        }
    }

    private static void setItemUnselected(int itemType)
    {
        switch (itemType)
        {
            case CommentsActivity.MAIN_COMMENT:
                isItemSelected = false;
                showDefaultToolbar();
//                postDB.mainCommentSelected(curr_selected_Item_timestamp, false);
                sendItemSelectReqRes(itemType, curr_selected_Item_timestamp, true);
                adapter.setMainItemUnselected();
//                ((MainItem) adapter.getDatasetListItem(curr_selected_Item_timestamp)).setSelected(false);
                curr_selected_Item_timestamp = " ";
                break;

            case CommentsActivity.REPLY_COMMENT:
                isItemSelected = false;
                showDefaultToolbar();
//                postDB.replyCommentSelected(curr_selected_item_container_timestamp, curr_selected_Item_timestamp, false);
                sendItemSelectReqRes(itemType, curr_selected_item_container_timestamp, curr_selected_Item_timestamp, true);
                adapter.setReplyItemUnselected();
                curr_selected_item_container_timestamp = " ";
                curr_selected_Item_timestamp = " ";
                break;
        }
    }

    private static void setItemSelected(int itemType, String itemTimestamp, String itemContainerTimestamp)
    {
        switch (itemType)
        {
            case CommentsActivity.MAIN_COMMENT:
                curr_selected_item_type = itemType;
                isItemSelected = true;
                curr_selected_Item_timestamp = itemTimestamp;
                showItemSelectedToolbar(itemType);
//                postDB.mainCommentSelected(curr_selected_Item_timestamp, true);
                sendItemSelectReqRes(itemType, curr_selected_Item_timestamp, true);
//                ((MainItem) adapter.getDatasetListItem(curr_selected_Item_timestamp)).setSelected(true);
                adapter.setMainItemSelected(curr_selected_Item_timestamp);
                break;

            case CommentsActivity.REPLY_COMMENT:
                curr_selected_item_type = itemType;
                isItemSelected = true;
                curr_selected_item_container_timestamp = itemContainerTimestamp;
                curr_selected_Item_timestamp = itemTimestamp;
                showItemSelectedToolbar(itemType);
//                postDB.replyCommentSelected(curr_selected_item_container_timestamp, curr_selected_Item_timestamp, true);
                sendItemSelectReqRes(itemType, curr_selected_item_container_timestamp, curr_selected_Item_timestamp, true);
                adapter.setReplyItemSelected(curr_selected_item_container_timestamp, curr_selected_Item_timestamp);
                break;
        }
    }

    public static void showItemSelectedToolbar(int itemType)
    {
        switch (itemType)
        {
            case CommentsActivity.MAIN_COMMENT:
                setMainItemSelectedToolbar();
                break;
            case CommentsActivity.REPLY_COMMENT:
                setReplyItemSelectedToolbar();
                break;
        }
    }

    private static void sendItemSelectReqRes(int type, String itemTimestamp, boolean res)
    {
        System.out.println("Res from CommentsUtil "+res );
        System.out.println("Main Item Timestamp from CommentsUtil"+itemTimestamp);
        Bundle b = new Bundle();
        b.putInt("itemType", type);
        b.putString("itemTimestamp", itemTimestamp);
        b.putBoolean("res", res);

        Intent i = new Intent();
        i.setAction("itemSelectReqRes");
        i.putExtras(b);

        LocalBroadcastManager.getInstance(ctx).sendBroadcast(i);
    }

    private static void sendItemSelectReqRes(int type, String itemContainerTimestamp, String itemTimestamp, boolean res)
    {
//        System.out.println(res + " res from sendItemSelectReqRes() in CommentsActivity");
//        System.out.println(itemContainerTimestamp + " item Container Timestamp from sendItemSelectReqRes() in CommentsActivity");
//        System.out.println(itemTimestamp + " item Timestamp from sendItemSelectReqRes() in CommentsActivity");
        Bundle b = new Bundle();
        b.putInt("itemType", type);
        b.putString("itemContainerTimestamp", itemContainerTimestamp);
        b.putString("itemTimestamp", itemTimestamp);
        b.putBoolean("res", res);

        Intent i = new Intent();
        i.setAction("itemSelectReqRes");
        i.putExtras(b);

        LocalBroadcastManager.getInstance(ctx).sendBroadcast(i);
    }

    private static void setMainItemSelectedToolbar()
    {
        MainItem mainItem = (MainItem) adapter.getDatasetListItem(curr_selected_Item_timestamp);

        String itemUsername = mainItem.getMainData().getUsername();

        String dummyUsername = "username";

        if(itemUsername.equals(dummyUsername))
            setCurrUserItemSelectedToolbarConfig();
        else
            setOtherUserCommentSelectedToolbarConfig();
    }

    private static void setReplyItemSelectedToolbar()
    {
        RepliesContainerItem replyContainerItem = (RepliesContainerItem) adapter.getDatasetListItem(curr_selected_item_container_timestamp);

        ReplyItem replyItem = replyContainerItem.getReplyItem(curr_selected_Item_timestamp);

        String itemUsername = replyItem.getReplyData().getUsername();

        String dummyUsername = "username";

        if(itemUsername.equals(dummyUsername))
            setCurrUserItemSelectedToolbarConfig();
        else
            setOtherUserCommentSelectedToolbarConfig();
    }

    private static void setCurrUserItemSelectedToolbarConfig()
    {
        mToolbar.setBackgroundResource(R.color.colorIGBlue);
        mLeftBtn.setImageResource(R.drawable.ic_close_white);
        mTitle.setText("1 Selected");
        mTitle.setTextColor(ctx.getResources().getColor(android.R.color.white, null));
        mRightBtn.setImageResource(R.drawable.ic_delete_white);

        setItemSelectedBtnListeners(CURR_USER_ITEM_SELECTED);
    }

    private static void setOtherUserCommentSelectedToolbarConfig()
    {
        mToolbar.setBackgroundResource(R.color.colorIGBlue);
        mLeftBtn.setImageResource(R.drawable.ic_close_white);
        mTitle.setText("1 Selected");
        mTitle.setTextColor(ctx.getResources().getColor(android.R.color.white, null));
        mRightBtn.setImageResource(R.drawable.ic_info_white);

        setItemSelectedBtnListeners(OTHER_USER_ITEM_SELECTED);
    }

    private static void setItemSelectedBtnListeners(final int type) {

        mLeftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDefaultToolbar();

                if(curr_selected_item_type == CommentsActivity.MAIN_COMMENT)
                {
                    adapter.setMainItemUnselected();
                }
                else if(curr_selected_item_type == CommentsActivity.REPLY_COMMENT)
                {
                    adapter.setReplyItemUnselected();
                }

                resetToolbarSelectedFields();
            }
        });

        mRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (type)
                {
                    case CommentsUtil.CURR_USER_ITEM_SELECTED:
                        if(curr_selected_item_type == CommentsActivity.MAIN_COMMENT)
                        {
//                            sendUnregisterItemSelectReqRes();
                            adapter.setMainItemUnselected();
                            postDB.removeMainComment(curr_selected_Item_timestamp);
                        }
                        else if(curr_selected_item_type == CommentsActivity.REPLY_COMMENT)
                        {
                            adapter.setReplyItemUnselected();
                            postDB.removeReplyComment(curr_selected_item_container_timestamp, curr_selected_Item_timestamp);
                        }
                        showDefaultToolbar();
                        resetToolbarSelectedFields();
                        break;

                    case CommentsUtil.OTHER_USER_ITEM_SELECTED:
                        OtherUserItemSelectDiag diag = new OtherUserItemSelectDiag();
                        diag.show(fm, "otherUserDiag");
                        break;
                }
            }
        });

    }

    private static void sendUnregisterItemSelectReqRes()
    {
        Bundle b = new Bundle();
        b.putBoolean("res", false);
        b.putBoolean("del", true);
        b.putInt("itemType", curr_selected_item_type);
        b.putString("itemContainerTimestamp", curr_selected_item_container_timestamp);
        b.putString("itemTimestamp", curr_selected_Item_timestamp);

        Intent i = new Intent();
        i.setAction("itemSelectReqRes");
        i.putExtras(b);

        LocalBroadcastManager.getInstance(ctx).sendBroadcast(i);
    }

    public static void showDefaultToolbar()
    {

//        //Unhighlight selected main comment item or reply comment item
//        if (isItemSelected) {
//
//            switch (curr_selected_item_type) {
//                case CommentsActivity.MAIN_COMMENT:
//                    sendItemSelectReqRes(curr_selected_item_type, curr_selected_Item_timestamp, true);
//                    break;
//                case CommentsActivity.REPLY_COMMENT:
//                    sendItemSelectReqRes(curr_selected_item_type, curr_selected_item_container_timestamp, curr_selected_Item_timestamp, true);
//                    break;
//            }
//        }

        mToolbar.setBackgroundResource(android.R.color.white);
        mLeftBtn.setImageResource(R.drawable.ic_back);
        mTitle.setText("Comments");
        mTitle.setTextColor(ctx.getResources().getColor(android.R.color.black, null));
        mRightBtn.setImageResource(R.drawable.ic_share);

        setDefaultBtnListeners();
    }

    private static void setDefaultBtnListeners()
    {
        mLeftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ctx.finish();
            }
        });

        mRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendToUserUtil.showSendToUserModal(fm);
            }
        });
    }

    private static void resetToolbarSelectedFields()
    {
        isItemSelected = false;
        curr_selected_item_type = -1;
        curr_selected_Item_timestamp = " ";
        curr_selected_item_container_timestamp = " ";
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

        RepliesContainerRecyclerAdapter adapter = new RepliesContainerRecyclerAdapter(data);
        recyclerView.setAdapter(adapter);
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
