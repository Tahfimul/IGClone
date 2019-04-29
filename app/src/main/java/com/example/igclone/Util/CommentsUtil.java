package com.example.igclone.Util;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import com.example.igclone.Adapters.CommentsRecyclerAdapter;
import com.example.igclone.Adapters.RepliesItemAdapter;
import com.example.igclone.DataModel.CommentsDataModel;
import com.example.igclone.DataModel.ListItem;
import com.example.igclone.DataModel.MainItem;
import com.example.igclone.DataModel.RepliesItem;
import com.example.igclone.R;

import java.util.ArrayList;

public class CommentsUtil {

    public static void initCommentsRecycler(RecyclerView recyclerView, Context ctx)
    {
        ArrayList<ListItem> data = new ArrayList<>();

        data.add(new MainItem(new CommentsDataModel(R.drawable.circle_border, "Testing", "hello", true, 1556502740, 20)));

        ArrayList<CommentsDataModel> replies = new ArrayList<>();
        replies.add(new CommentsDataModel(R.drawable.circle_border, "Testing", "hello", true, 1556502740, 20));
        data.add(new RepliesItem(replies));
        data.add(new RepliesItem(replies));

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

    public static SpannableString getCommentText(String username, String comment, int color) {
        String latestCommentText = username + " " + comment;
        SpannableString spannableString = new SpannableString(latestCommentText);

        spannableString.setSpan(new BoldTextClickableSpan(latestCommentText, color), 0, username.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        spannableString.setSpan(new NormalTextClickableSpan(latestCommentText, color), username.length()+1, latestCommentText.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        return spannableString;
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
            return new java.util.Date(time) + "";
        }
    }

    private static long getCurrentTime()
    {
        return System.currentTimeMillis();
    }
}
