package com.example.igclone.Interactions;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.igclone.Util.BottomNavViewHelper;
import com.example.igclone.R;

public class InteractionsActivity extends AppCompatActivity{

    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interactions);

        bottomNav = findViewById(R.id.bottomNavView_bar);

        setUpBottomNav();

    }

    private void setUpBottomNav() {
        BottomNavViewHelper.enableBottomNav(this, bottomNav);
        BottomNavViewHelper.disableShiftModeAndSetSelected(bottomNav, 3);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
