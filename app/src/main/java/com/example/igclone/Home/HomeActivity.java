package com.example.igclone.Home;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.igclone.Home.Adapters.HomePagerAdapter;
import com.example.igclone.R;

public class HomeActivity extends AppCompatActivity {

    ViewPager viewPager;
    HomePagerAdapter homePagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewPager = findViewById(R.id.viewPager);

        setUpViewPager();

        // Register to receive messages.
        // We are registering an observer (mMessageReceiver) to receive Intents
        // with actions named "custom-event-name".
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("switch"));

    }

    private void setUpViewPager() {
        homePagerAdapter = new HomePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(homePagerAdapter);
        viewPager.setCurrentItem(1);
    }

    // Our handler for received Intents. This will be called whenever an Intent
    // with an action named "switch" is broadcasted.
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            int pos = Integer.valueOf(intent.getStringExtra("pos"));
            viewPager.setCurrentItem(pos);
        }
    };

    @Override
    public void onBackPressed() {
        if(viewPager.getCurrentItem()!=1)
            viewPager.setCurrentItem(1);
        else
            super.onBackPressed();
    }
}
