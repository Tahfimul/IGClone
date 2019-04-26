package com.example.igclone.UserProfile.Util;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.igclone.R;
import com.example.igclone.UserProfile.Adapters.ProfileThumbnailRecyclerAdapter;
import com.example.igclone.UserProfile.DataModel.ProfileThumbnailDataModel;

import java.util.ArrayList;

public class UserProfileNavUtil {

    public static void initThumbnailRecycler(RecyclerView recyclerView, Context context)
    {
        ArrayList<ProfileThumbnailDataModel> dataset = new ArrayList<>();
        dataset.add(new ProfileThumbnailDataModel(R.drawable.thumbnail_drawable, R.drawable.ic_collection));
        dataset.add(new ProfileThumbnailDataModel(R.drawable.thumbnail_drawable, R.drawable.ic_collection));
        dataset.add(new ProfileThumbnailDataModel(R.drawable.thumbnail_drawable, R.drawable.ic_collection));
        dataset.add(new ProfileThumbnailDataModel(R.drawable.thumbnail_drawable, R.drawable.ic_collection));
        dataset.add(new ProfileThumbnailDataModel(R.drawable.thumbnail_drawable, R.drawable.ic_collection));
        dataset.add(new ProfileThumbnailDataModel(R.drawable.thumbnail_drawable, R.drawable.ic_collection));
        dataset.add(new ProfileThumbnailDataModel(R.drawable.thumbnail_drawable, R.drawable.ic_collection));
        dataset.add(new ProfileThumbnailDataModel(R.drawable.thumbnail_drawable, R.drawable.ic_collection));
        dataset.add(new ProfileThumbnailDataModel(R.drawable.thumbnail_drawable, R.drawable.ic_collection));
        dataset.add(new ProfileThumbnailDataModel(R.drawable.thumbnail_drawable, R.drawable.ic_collection));
        dataset.add(new ProfileThumbnailDataModel(R.drawable.thumbnail_drawable, R.drawable.ic_collection));
        dataset.add(new ProfileThumbnailDataModel(R.drawable.thumbnail_drawable, R.drawable.ic_collection));
        dataset.add(new ProfileThumbnailDataModel(R.drawable.thumbnail_drawable, R.drawable.ic_collection));
        dataset.add(new ProfileThumbnailDataModel(R.drawable.thumbnail_drawable, R.drawable.ic_collection));
        dataset.add(new ProfileThumbnailDataModel(R.drawable.thumbnail_drawable, R.drawable.ic_collection));

        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context,3);
        recyclerView.setLayoutManager(layoutManager);

        ProfileThumbnailRecyclerAdapter adapter = new ProfileThumbnailRecyclerAdapter(dataset);
        recyclerView.setAdapter(adapter);
    }

    public static void initTaggedRecycler(RecyclerView recyclerView, Context context)
    {
        ArrayList<ProfileThumbnailDataModel> dataset = new ArrayList<>();
        dataset.add(new ProfileThumbnailDataModel(R.drawable.thumbnail_drawable, R.drawable.ic_collection));
        dataset.add(new ProfileThumbnailDataModel(R.drawable.thumbnail_drawable, R.drawable.ic_collection));
        dataset.add(new ProfileThumbnailDataModel(R.drawable.thumbnail_drawable, R.drawable.ic_collection));
        dataset.add(new ProfileThumbnailDataModel(R.drawable.thumbnail_drawable, R.drawable.ic_collection));
        dataset.add(new ProfileThumbnailDataModel(R.drawable.thumbnail_drawable, R.drawable.ic_collection));
        dataset.add(new ProfileThumbnailDataModel(R.drawable.thumbnail_drawable, R.drawable.ic_collection));

        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context,3);
        recyclerView.setLayoutManager(layoutManager);

        ProfileThumbnailRecyclerAdapter adapter = new ProfileThumbnailRecyclerAdapter(dataset);
        recyclerView.setAdapter(adapter);
    }

    //Photo post recycler

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
