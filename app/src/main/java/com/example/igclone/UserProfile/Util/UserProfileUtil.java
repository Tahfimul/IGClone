package com.example.igclone.UserProfile.Util;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.igclone.R;
import com.example.igclone.UserProfile.Adapters.StoriesArchiveRecyclerAdapter;
import com.example.igclone.UserProfile.DataModel.StoriesArchiveDataModel;
import com.example.igclone.UserProfile.Fragments.ProfileFeedFragment;
import com.example.igclone.UserProfile.Fragments.ProfileTaggedFragment;
import com.example.igclone.UserProfile.Fragments.ProfileThumbnailFragment;

import java.util.ArrayList;

public class UserProfileUtil {

    //tab layout vars
    private static int tabHighlightedIconIds[] = {R.drawable.ic_thumbnail_profile_highlighted, R.drawable.ic_feed_profile_highlighted, R.drawable.ic_tagged_profile_highlighted};
    private static int tabIconIds[] = {R.drawable.ic_thumbnail_profile, R.drawable.ic_feed_profile, R.drawable.ic_tagged_profile};

    public static void initStoriesArchiveRecycler(Context context, RecyclerView recyclerView)
    {
        ArrayList<StoriesArchiveDataModel> dataset = new ArrayList<>();
        dataset.add(new StoriesArchiveDataModel(R.drawable.ic_plus, "Add Story"));
        dataset.add(new StoriesArchiveDataModel(R.drawable.ic_plus, "Add Story"));
        dataset.add(new StoriesArchiveDataModel(R.drawable.ic_plus, "Add Story"));
        dataset.add(new StoriesArchiveDataModel(R.drawable.ic_plus, "Add Story"));
        dataset.add(new StoriesArchiveDataModel(R.drawable.ic_plus, "Add Story"));
        dataset.add(new StoriesArchiveDataModel(R.drawable.ic_plus, "Add Story"));
        dataset.add(new StoriesArchiveDataModel(R.drawable.ic_plus, "Add Story"));
        dataset.add(new StoriesArchiveDataModel(R.drawable.ic_plus, "Add Story"));
        dataset.add(new StoriesArchiveDataModel(R.drawable.ic_plus, "Add Story"));
        dataset.add(new StoriesArchiveDataModel(R.drawable.ic_plus, "Add Story"));
        dataset.add(new StoriesArchiveDataModel(R.drawable.ic_plus, "Add Story"));

        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter adapter = new StoriesArchiveRecyclerAdapter(dataset);
        recyclerView.setAdapter(adapter);

    }

    public static void initProfileNav(Context context, final TabLayout tabLayout, final FragmentManager fragmentManager)
    {

        setDefaultConfiguration(tabLayout, fragmentManager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                setTabHighlighted(tabLayout.getSelectedTabPosition(), tabLayout);
                setFragmentLayout(fragmentManager, tabLayout.getSelectedTabPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private static void setDefaultConfiguration(TabLayout tabLayout, FragmentManager fragmentManager)
    {
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_thumbnail_profile_highlighted));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_feed_profile));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_tagged_profile));

        setFragmentLayout(fragmentManager, 0);
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

    public static void setFragmentLayout(FragmentManager fragmentManager, int pos)
    {
        Fragment newFragment = null;

        switch(pos)
        {
            case 0:
                newFragment = new ProfileThumbnailFragment();
                break;
            case 1:
                newFragment = new ProfileFeedFragment();
                break;
            case 2:
                newFragment = new ProfileTaggedFragment();
                break;
        }
        // Create new transaction
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }
}