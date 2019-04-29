package com.example.igclone.Util;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import butterknife.ButterKnife;
import com.example.igclone.Adapters.SendToRecyclerAdapter;
import com.example.igclone.DataModel.UserDataModel;
import com.example.igclone.Fragments.ModalSendToBottomSheet;
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

    public static void showSendToUserModal(Activity ctx)
    {
        RelativeLayout modal = ctx.findViewById(R.id.layout_send_to_bottom_sheet);
        Toolbar toolbar = ctx.findViewById(R.id.toolbar);


        setModalBehavior(modal, toolbar);

    }



    public static void setModalBehavior(RelativeLayout modal, final Toolbar toolbar)
    {
        BottomSheetBehavior behavior = BottomSheetBehavior.from(modal);


        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED: {
                        toolbar.setVisibility(View.VISIBLE);
                    }
                    break;
                    case BottomSheetBehavior.STATE_COLLAPSED: {
                        toolbar.setVisibility(View.GONE);
                    }
                    break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });
    }
}
