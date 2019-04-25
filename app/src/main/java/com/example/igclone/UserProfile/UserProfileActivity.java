package com.example.igclone.UserProfile;

import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.igclone.Util.BottomNavViewHelper;
import com.example.igclone.R;

public class UserProfileActivity extends AppCompatActivity{

    BottomNavigationView bottomBav;
    
    TabLayout profileNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        profileNav = findViewById(R.id.tabLayout);
        bottomBav = findViewById(R.id.bottomNavView_bar);

        setUpTabLayout();
        setUpBottomNav();
    }

    private void setUpTabLayout() {
        Util.initProfileNav(this, profileNav);
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
