package com.example.igclone.UserProfile;

import android.content.Context;
import android.support.design.widget.TabLayout;
import com.example.igclone.R;

public class Util {

    private static int tabHighlightedIconIds[] = {R.drawable.ic_thumbnail_profile_highlighted, R.drawable.ic_feed_profile_highlighted, R.drawable.ic_tagged_profile_highlighted};
    private static int tabIconIds[] = {R.drawable.ic_thumbnail_profile, R.drawable.ic_feed_profile, R.drawable.ic_tagged_profile};

    public static void initProfileNav(Context context, final TabLayout tabLayout)
    {
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_thumbnail_profile_highlighted));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_feed_profile));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_tagged_profile));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                setTabHighlighted(tabLayout.getSelectedTabPosition(), tabLayout);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private static void setTabHighlighted(int selectedTabPosition, TabLayout tabLayout) {

        for(int tabPos=0; tabPos<tabLayout.getTabCount(); tabPos++)
        {
            if(tabPos==selectedTabPosition)
                tabLayout.getTabAt(tabPos).setIcon(tabHighlightedIconIds[tabPos]);
            else
                tabLayout.getTabAt(tabPos).setIcon(tabIconIds[tabPos]);
        }
    }
}
