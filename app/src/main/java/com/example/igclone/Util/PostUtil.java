package com.example.igclone.Util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import com.example.igclone.Adapters.PhotoPostRecyclerAdapter;
import com.example.igclone.DataModel.PhotoPostDataModel;
import com.example.igclone.R;

import java.util.ArrayList;

public class PostUtil {

    public static void initProfilePhotoPostRecycler(RecyclerView recyclerView, final Context context, FragmentManager fragmentManager)
    {
        ArrayList<PhotoPostDataModel> dataset = new ArrayList<>();
        dataset.add(new PhotoPostDataModel(R.drawable.user_icon_profile, "User_name_1", R.drawable.nyc, 0, 0, 0, true, "AnotherUser", 21, "David Dobrik", "IG is cool", 5, 1515024000));
        dataset.add(new PhotoPostDataModel(R.drawable.user_icon_profile, "User_name_1", R.drawable.nyc, 0, 0, 0, true, "AnotherUser", 21, "David Dobrik", "IG is cool", 5, 1556258445));
        dataset.add(new PhotoPostDataModel(R.drawable.user_icon_profile, "User_name_1", R.drawable.nyc, 0, 0, 0, true, "AnotherUser", 21, "David Dobrik", "IG is cool", 5, 1556258445));
        dataset.add(new PhotoPostDataModel(R.drawable.user_icon_profile, "User_name_1", R.drawable.nyc, 0, 0, 0, true, "AnotherUser", 21, "David Dobrik", "IG is cool", 5, 1556258445));
        dataset.add(new PhotoPostDataModel(R.drawable.user_icon_profile, "User_name_1", R.drawable.nyc, 0, 0, 0, true, "AnotherUser", 21, "David Dobrik", "IG is cool", 5, 1556258445));
        dataset.add(new PhotoPostDataModel(R.drawable.user_icon_profile, "User_name_1", R.drawable.nyc, 0, 0, 0, true, "AnotherUser", 21, "David Dobrik", "IG is cool", 5, 1556258445));
        dataset.add(new PhotoPostDataModel(R.drawable.user_icon_profile, "User_name_1", R.drawable.nyc, 0, 0, 0, true, "AnotherUser", 21, "David Dobrik", "IG is cool", 5, 1556258445));
        dataset.add(new PhotoPostDataModel(R.drawable.user_icon_profile, "User_name_1", R.drawable.nyc, 0, 0, 0, true, "AnotherUser", 21, "David Dobrik", "IG is cool", 5, 1556258445));
        dataset.add(new PhotoPostDataModel(R.drawable.user_icon_profile, "User_name_1", R.drawable.nyc, 0, 0, 0, true, "AnotherUser", 21, "David Dobrik", "IG is cool", 5, 1556258445));
        dataset.add(new PhotoPostDataModel(R.drawable.user_icon_profile, "User_name_1", R.drawable.nyc, 0, 0, 0, true, "AnotherUser", 21, "David Dobrik", "IG is cool", 5, 1556258445));
        dataset.add(new PhotoPostDataModel(R.drawable.user_icon_profile, "User_name_1", R.drawable.nyc, 0, 0, 0, true, "AnotherUser", 21, "David Dobrik", "IG is cool", 5, 1556258445));
        dataset.add(new PhotoPostDataModel(R.drawable.user_icon_profile, "User_name_1", R.drawable.nyc, 0, 0, 0, true, "AnotherUser", 21, "David Dobrik", "IG is cool", 5, 1556258445));

        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        PhotoPostRecyclerAdapter adapter = new PhotoPostRecyclerAdapter(dataset, fragmentManager);
        recyclerView.setAdapter(adapter);
    }

}
