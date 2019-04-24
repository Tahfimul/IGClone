package com.example.igclone.UserProfile;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.igclone.Util.BottomNavViewHelper;
import com.example.igclone.R;

public class UserProfileActivity extends AppCompatActivity{

    BottomNavigationView bottomBav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        bottomBav = findViewById(R.id.bottomNavView_bar);

        setUpBottomNav();
    }

    private void setUpBottomNav() {
        BottomNavViewHelper.enableBottomNav(this, bottomBav);
        BottomNavViewHelper.disableShiftModeAndSetSelected(bottomBav, 4);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
