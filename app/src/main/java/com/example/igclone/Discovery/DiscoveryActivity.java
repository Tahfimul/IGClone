package com.example.igclone.Discovery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import com.example.igclone.Util.BottomNavViewHelper;
import com.example.igclone.R;

public class DiscoveryActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discovery);

        bottomNav = findViewById(R.id.bottomNavView_bar);

        setUpBottomNav();
    }

    private void setUpBottomNav() {
        BottomNavViewHelper.enableBottomNav(this, bottomNav);
        BottomNavViewHelper.disableShiftModeAndSetSelected(bottomNav, 1);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
