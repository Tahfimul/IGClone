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


}
