package com.example.igclone.Util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.example.igclone.Adapters.SendToRecyclerAdapter;
import com.example.igclone.DataModel.UserDataModel;
import com.example.igclone.Fragments.SendToUser;
import com.example.igclone.R;

import java.util.ArrayList;

public class SendToUserUtil {

    public static void initSendToUserRecycler(RecyclerView recyclerView, Context ctx)
    {

        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ctx);
        recyclerView.setLayoutManager(layoutManager);

        SendToRecyclerAdapter adapter = new SendToRecyclerAdapter(getData());
        recyclerView.setAdapter(adapter);
    }

    private static ArrayList<UserDataModel> getData()
    {
        ArrayList<UserDataModel> data = new ArrayList<>();
        data.add(new UserDataModel(R.drawable.circle_border, "Username"));
        data.add(new UserDataModel(R.drawable.circle_border, "Username"));
        data.add(new UserDataModel(R.drawable.circle_border, "Username"));
        data.add(new UserDataModel(R.drawable.circle_border, "Username"));
        data.add(new UserDataModel(R.drawable.circle_border, "Username"));
        data.add(new UserDataModel(R.drawable.circle_border, "Username"));
        data.add(new UserDataModel(R.drawable.circle_border, "Username"));
        data.add(new UserDataModel(R.drawable.circle_border, "Username"));
        data.add(new UserDataModel(R.drawable.circle_border, "Username"));

        return data;
    }

    public static void showSendToUserModal(FragmentManager fm)
    {
        SendToUser sendToUser = new SendToUser();
        sendToUser.show(fm.beginTransaction(), "sendToUser");

    }

    public static void hideSendToUserModal(RelativeLayout modal)
    {
         BottomSheetBehavior behavior = BottomSheetBehavior.from(modal);

        behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }



    public static void setModalBehavior( final BottomSheetDialog modal, final Toolbar modalToolbar, final View modalIndicator)
    {
//        modalToolbar.setVisibility(View.GONE);
        modal.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                FrameLayout frameLayout = modal.findViewById(android.support.design.R.id.design_bottom_sheet);
                BottomSheetBehavior behavior = BottomSheetBehavior.from(frameLayout);
                behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                    @Override
                    public void onStateChanged(@NonNull View view, int newState) {
                        switch (newState)
                        {
                            case BottomSheetBehavior.STATE_HIDDEN:
                                modalToolbar.setVisibility(View.INVISIBLE);
                                break;
                            case BottomSheetBehavior.STATE_EXPANDED: {
                                modalIndicator.setVisibility(View.GONE);
                                modalToolbar.setVisibility(View.VISIBLE);
                            }
                            break;
                            case BottomSheetBehavior.STATE_HALF_EXPANDED:
                            {
                                modalToolbar.setVisibility(View.GONE);
                                modalIndicator.setVisibility(View.VISIBLE);
                            }
                            case BottomSheetBehavior.STATE_COLLAPSED: {
                                modalToolbar.setVisibility(View.GONE);
                                modalToolbar.setVisibility(View.INVISIBLE);
                            }
                            break;
                            case BottomSheetBehavior.STATE_DRAGGING: {
                                modalToolbar.setVisibility(View.GONE);
                                modalIndicator.setVisibility(View.VISIBLE);
                            }
                            break;
                            case BottomSheetBehavior.STATE_SETTLING:
                                modalToolbar.setVisibility(View.GONE);
                                break;
                        }
                    }

                    @Override
                    public void onSlide(@NonNull View view, float v) {

                    }
                });
            }
        });
    }

    public static int getModalBehaviorState(RelativeLayout modal)
    {
        BottomSheetBehavior behavior = BottomSheetBehavior.from(modal);

        return behavior.getState();
    }
}
