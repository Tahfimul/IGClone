package com.example.igclone.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.igclone.R;
import com.example.igclone.Util.BottomNavViewHelper;

public class FeedFragment extends Fragment implements View.OnClickListener {

    BottomNavigationView bottomBav;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_feed, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        bottomBav = view.findViewById(R.id.bottomNavView_bar);

        ImageView camera = view.findViewById(R.id.camera);
        ImageView chat = view.findViewById(R.id.chat);

        camera.setOnClickListener(this);

        chat.setOnClickListener(this);


        setUpBottomNav();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.camera:
                sendMessage("0");
                break;
            case R.id.chat:
                sendMessage("2");
                break;
        }
    }

    // Send an Intent with an action named "custom-event-name". The Intent sent should
    // be received by the ReceiverActivity.
    private void sendMessage(String pos) {
        Log.d("sender", "Broadcasting message");
        Intent intent = new Intent("switch");
        // You can also include some extra data.
        intent.putExtra("pos", pos);
        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
    }



    private void setUpBottomNav() {
        BottomNavViewHelper.enableBottomNav(getContext(), bottomBav);
        BottomNavViewHelper.disableShiftModeAndSetSelected(bottomBav, 0);
    }
}
