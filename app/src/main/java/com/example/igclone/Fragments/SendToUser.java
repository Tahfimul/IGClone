package com.example.igclone.Fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.example.igclone.R;
import com.example.igclone.Util.SendToUserUtil;

public class SendToUser extends BottomSheetDialogFragment{

    @Override
    public void show(FragmentManager manager, String tag) {
        try{

            FragmentTransaction ft = manager.beginTransaction();
            ft.add(this, tag);
            ft.commitAllowingStateLoss();

        }
        catch (IllegalStateException e)
        {
            Log.e("SendToUser", e.getMessage());
        }
    }

    private Toolbar modalToolbar;
    private View modalIndicator;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_send_to_user_bottom_sheet_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        modalToolbar = view.findViewById(R.id.modalToolbar);
        modalIndicator = view.findViewById(R.id.modalIndicator);
        RecyclerView recyclerView = view.findViewById(R.id.friend_users);

        setModalBehavior();
        SendToUserUtil.initSendToUserRecycler(recyclerView, getContext());


    }

    private void setModalBehavior() {
        final BottomSheetDialog diag = (BottomSheetDialog) getDialog();
        diag.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                FrameLayout frameLayout = diag.findViewById(android.support.design.R.id.design_bottom_sheet);
                BottomSheetBehavior behavior = BottomSheetBehavior.from(frameLayout);
                behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                    @Override
                    public void onStateChanged(@NonNull View view, int newState) {
                        switch (newState)
                        {
                            case BottomSheetBehavior.STATE_HIDDEN:
                                dismiss();
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


}
