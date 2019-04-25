package com.example.igclone.Interactions.Adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.example.igclone.Interactions.Fragments.FollowingFragment;
import com.example.igclone.Interactions.Fragments.YouFragment;

public class InteractionsActivityAdapter extends FragmentStatePagerAdapter {

    public InteractionsActivityAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i)
        {
            case 0:
                return new FollowingFragment();
            case 1:
                return new YouFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position)
        {
            case 0:
                return "FOLLOWING";
            case 1:
                return "YOU";
        }

        return "";
    }
}
