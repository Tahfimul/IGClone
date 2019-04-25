package com.example.igclone.NewPost.Adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.example.igclone.NewPost.Fragments.GalleryFragment;
import com.example.igclone.NewPost.Fragments.PhotoFragment;
import com.example.igclone.NewPost.Fragments.VideoFragment;

public class NewPostActivityAdapter extends FragmentStatePagerAdapter {


    public NewPostActivityAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i)
        {
            case 0:
                return new GalleryFragment();
            case 1:
                return new PhotoFragment();
            case 2:
                return new VideoFragment();

        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position)
        {
            case 0:
                return "GALLERY";
            case 1:
                return "PHOTO";
            case 2:
                return "VIDEO";
        }

        return " ";
    }
}
