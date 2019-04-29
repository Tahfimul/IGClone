package com.example.igclone.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.example.igclone.R;
import com.example.igclone.Util.SendToUserUtil;

public class ModalSendToBottomSheet extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_send_to_bottom_sheet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RelativeLayout mainLayout = view.findViewById(R.id.layout_send_to_bottom_sheet);
        Toolbar toolbar = view.findViewById(R.id.toolbar);

        SendToUserUtil.setModalBehavior(mainLayout, toolbar);


        RecyclerView recyclerView = view.findViewById(R.id.friend_users);
        SendToUserUtil.initSendToUserRecycler(recyclerView, getActivity());
    }



}
