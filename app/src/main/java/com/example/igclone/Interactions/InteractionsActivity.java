package com.example.igclone.Interactions;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.igclone.Interactions.Adapters.InteractionsActivityAdapter;
import com.example.igclone.Util.BottomNavViewHelper;
import com.example.igclone.R;

public class InteractionsActivity extends AppCompatActivity{

    BottomNavigationView bottomNav;
    ViewPager viewPager;
    InteractionsActivityAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interactions);

        bottomNav = findViewById(R.id.bottomNavView_bar);

        viewPager = findViewById(R.id.viewPager);

        setUpBottomNav();

        setUpViewPager();

    }

    private void setUpBottomNav() {
        BottomNavViewHelper.enableBottomNav(this, bottomNav, this);
        BottomNavViewHelper.disableShiftModeAndSetSelected(bottomNav, 3);
    }

    private void setUpViewPager()
    {
        adapter = new InteractionsActivityAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
