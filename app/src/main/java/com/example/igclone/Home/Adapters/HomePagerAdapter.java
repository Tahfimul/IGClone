package com.example.igclone.Home.Adapters;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.example.igclone.Home.Fragments.CameraFragment;
import com.example.igclone.Home.Fragments.ChatFragment;
import com.example.igclone.Home.Fragments.FeedFragment;

public class HomePagerAdapter extends FragmentStatePagerAdapter {

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        switch (i)
        {
            case 0:
                return new CameraFragment();
            case 1:
                return new FeedFragment();
            case 2:
                return new ChatFragment();
        }

        return null;

    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
