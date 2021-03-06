package com.example.igclone.PosstData;

import com.example.igclone.DataModel.PhotoPostDataModel;
import com.example.igclone.R;

import java.util.ArrayList;

public class ProfilePhotoFeedData {
    private static ArrayList<PhotoPostDataModel> profilePhotoFeedDataset = new ArrayList<>();

    public void initData()
    {
        profilePhotoFeedDataset.add(new PhotoPostDataModel(R.drawable.user_icon_profile, "User_name_1",R.drawable.nyc, 0, 0, 0, true, false, "AnotherUser", 21, "David Dobrik", "IG is cool", 5, 1515024000));
        profilePhotoFeedDataset.add(new PhotoPostDataModel(R.drawable.user_icon_profile, "User_name_1", R.drawable.nyc, 0, 0, 0, true, false, "AnotherUser", 21, "David Dobrik", "IG is cool", 5, 1556258445));
        profilePhotoFeedDataset.add(new PhotoPostDataModel(R.drawable.user_icon_profile, "User_name_1", R.drawable.nyc, 0, 0, 0, true, false, "AnotherUser", 21, "David Dobrik", "IG is cool", 5, 1556258445));
        profilePhotoFeedDataset.add(new PhotoPostDataModel(R.drawable.user_icon_profile, "User_name_1", R.drawable.nyc, 0, 0, 0, true, false, "AnotherUser", 21, "David Dobrik", "IG is cool", 5, 1556258445));
        profilePhotoFeedDataset.add(new PhotoPostDataModel(R.drawable.user_icon_profile, "User_name_1", R.drawable.nyc, 0, 0, 0, true, false,"AnotherUser", 21, "David Dobrik", "IG is cool", 5, 1556258445));
        profilePhotoFeedDataset.add(new PhotoPostDataModel(R.drawable.user_icon_profile, "User_name_1", R.drawable.nyc, 0, 0, 0, true, false, "AnotherUser", 21, "David Dobrik", "IG is cool", 5, 1556258445));
        profilePhotoFeedDataset.add(new PhotoPostDataModel(R.drawable.user_icon_profile, "User_name_1", R.drawable.nyc, 0, 0, 0, true, false,"AnotherUser", 21, "David Dobrik", "IG is cool", 5, 1556258445));
        profilePhotoFeedDataset.add(new PhotoPostDataModel(R.drawable.user_icon_profile, "User_name_1", R.drawable.nyc, 0, 0, 0, true, false,"AnotherUser", 21, "David Dobrik", "IG is cool", 5, 1556258445));
        profilePhotoFeedDataset.add(new PhotoPostDataModel(R.drawable.user_icon_profile, "User_name_1", R.drawable.nyc, 0, 0, 0, true, false,"AnotherUser", 21, "David Dobrik", "IG is cool", 5, 1556258445));
        profilePhotoFeedDataset.add(new PhotoPostDataModel(R.drawable.user_icon_profile, "User_name_1", R.drawable.nyc, 0, 0, 0, true, false,"AnotherUser", 21, "David Dobrik", "IG is cool", 5, 1556258445));
        profilePhotoFeedDataset.add(new PhotoPostDataModel(R.drawable.user_icon_profile, "User_name_1", R.drawable.nyc, 0, 0, 0, true, false,"AnotherUser", 21, "David Dobrik", "IG is cool", 5, 1556258445));
        profilePhotoFeedDataset.add(new PhotoPostDataModel(R.drawable.user_icon_profile, "User_name_1", R.drawable.nyc, 0, 0, 0, true, false,"AnotherUser", 21, "David Dobrik", "IG is cool", 5, 1556258445));
    }

    public void updateData(PhotoPostDataModel data)
    {
       profilePhotoFeedDataset.set(profilePhotoFeedDataset.indexOf(data), data);
    }

    public ArrayList<PhotoPostDataModel> getProfilePhotoFeedDataset()
    {
        return profilePhotoFeedDataset;
    }
}
