package com.example.igclone.Util;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import com.example.igclone.Adapters.PhotoPostRecyclerAdapter;
import com.example.igclone.DataModel.PhotoPostDataModel;
import com.example.igclone.PosstData.ProfilePhotoFeedData;
import com.example.igclone.R;

import java.util.ArrayList;

public class PostUtil {

    static boolean profilePhotoPostRecyclerInitalized = false;

    public static void initProfilePhotoPostRecycler(RecyclerView recyclerView, final Context context, FragmentManager fragmentManager)
    {
        ProfilePhotoFeedData data = new ProfilePhotoFeedData();

        if (!profilePhotoPostRecyclerInitalized)
        {
            data.initData();
            profilePhotoPostRecyclerInitalized = true;
        }

        ArrayList<PhotoPostDataModel> dataset = data.getProfilePhotoFeedDataset();

        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        PhotoPostRecyclerAdapter adapter = new PhotoPostRecyclerAdapter(dataset, fragmentManager);
        recyclerView.setAdapter(adapter);
    }

}
