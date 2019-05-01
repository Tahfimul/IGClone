package com.example.igclone.Home.Fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.example.igclone.Fragments.SendToUser;
import com.example.igclone.R;
import com.example.igclone.Util.BottomNavViewHelper;
import com.example.igclone.Util.PostUtil;
import com.example.igclone.Util.SendToUserUtil;

public class FeedFragment extends Fragment implements View.OnClickListener {

    BottomNavigationView bottomBav;
    RelativeLayout modal;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home_feed, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        bottomBav = view.findViewById(R.id.bottomNavView_bar);

        ImageView camera = view.findViewById(R.id.camera);
        ImageView chat = view.findViewById(R.id.chat);

        camera.setOnClickListener(this);

        chat.setOnClickListener(this);

        modal = view.findViewById(R.id.layout_send_to_bottom_sheet);
        Toolbar modalToolbar = view.findViewById(R.id.modalToolbar);
        View modalIndicator = view.findViewById(R.id.modalIndicator);

        // Register to receive messages.
        // We are registering an observer (mSendToUserBottomSheetMessageReceiver) to receive Intents
        // with actions named "sendToUser".
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mSendToUserBottomSheetMessageReceiver,
                new IntentFilter("sendToUser"));

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        PostUtil.initHomePhotoPostRecycler(recyclerView, getContext(), getFragmentManager());


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
    // be received by the HomeActivity.
    private void sendMessage(String pos) {
        Log.d("sender", "Broadcasting message");
        Intent intent = new Intent("switch");
        // You can also include some extra data.
        intent.putExtra("pos", pos);
        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
    }

    // Our handler for received SendToUserBottomSheet intent. This will be called whenever an Intent
    // with an action named "sendToUser" is broadcasted from PhotoPostRecycler.
    private BroadcastReceiver mSendToUserBottomSheetMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
//            SendToUserUtil.showSendToUserModal(modal);
            SendToUser sendToUser = new SendToUser();
            sendToUser.show(getFragmentManager().beginTransaction(), "sendToUser");
        }
    };




    private void setUpBottomNav() {
        BottomNavViewHelper.enableBottomNav(getContext(), bottomBav, getActivity());
        BottomNavViewHelper.disableShiftModeAndSetSelected(bottomBav, 0);
    }
}
