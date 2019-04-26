package com.example.igclone.UserProfile;

import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import com.example.igclone.UserProfile.Fragments.ProfileThumbnailFragment;
import com.example.igclone.UserProfile.Util.UserProfileUtil;
import com.example.igclone.Util.BottomNavViewHelper;
import com.example.igclone.R;

public class UserProfileActivity extends AppCompatActivity{

    RecyclerView storiesArchiveRecycler;
    TabLayout profileNav;
    ViewPager profileNavViewPager;
    BottomNavigationView bottomBav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        storiesArchiveRecycler = findViewById(R.id.stories_archive_recycler);
        profileNav = findViewById(R.id.tabLayout);
        profileNavViewPager = findViewById(R.id.viewPager);
        bottomBav = findViewById(R.id.bottomNavView_bar);

        setUpStoriesArchiveRecycler();
        setUpTabLayout();
        setUpBottomNav();
    }

    private void setUpStoriesArchiveRecycler() {
        UserProfileUtil.initStoriesArchiveRecycler(this, storiesArchiveRecycler);
    }

    private void setUpTabLayout() {
        UserProfileUtil.initProfileNav(this, profileNav, getSupportFragmentManager());
    }

    private void setUpBottomNav() {
        BottomNavViewHelper.enableBottomNav(this, bottomBav, this);
        BottomNavViewHelper.disableShiftModeAndSetSelected(bottomBav, 4);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
