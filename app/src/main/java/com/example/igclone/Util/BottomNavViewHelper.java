package com.example.igclone.Util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.bottomnavigation.LabelVisibilityMode;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.view.Menu;
import android.view.MenuItem;
import com.example.igclone.Discovery.DiscoveryActivity;
import com.example.igclone.Home.HomeActivity;
import com.example.igclone.Interactions.InteractionsActivity;
import com.example.igclone.NewPost.NewPostActivity;
import com.example.igclone.R;
import com.example.igclone.UserProfile.UserProfileActivity;

public class BottomNavViewHelper {

    @SuppressLint("RestrictedApi")
    public static void disableShiftModeAndSetSelected(BottomNavigationView view, int pos) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        menuView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_UNLABELED);
        menuView.buildMenuView();

        Menu menu = view.getMenu();
        MenuItem menuItem = menu.getItem(pos);
        menuItem.setChecked(true);
    }

    public static void enableBottomNav(final Context context, BottomNavigationView view)
    {
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.home:
                        Intent home = new Intent(context, HomeActivity.class);
                        context.startActivity(home);
                        break;
                    case R.id.discovery:
                        Intent discovery = new Intent(context, DiscoveryActivity.class);
                        context.startActivity(discovery);
                        break;
                    case R.id.new_post:
                        Intent newPost = new Intent(context, NewPostActivity.class);
                        context.startActivity(newPost);
                        break;
                    case R.id.interactions:
                        Intent interactions = new Intent(context, InteractionsActivity.class);
                        context.startActivity(interactions);
                        break;
                    case R.id.profile:
                        Intent profile = new Intent(context, UserProfileActivity.class);
                        context.startActivity(profile);
                        break;
                }
                return false;
            }
        });
    }
}
