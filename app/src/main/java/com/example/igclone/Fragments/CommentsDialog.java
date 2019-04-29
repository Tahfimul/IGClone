package com.example.igclone.Fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.Toolbar;
import com.example.igclone.R;
import com.example.igclone.Util.CommentsUtil;
import com.example.igclone.Util.SendToUserUtil;

public class CommentsDialog extends DialogFragment implements View.OnClickListener{
    public static String TAG = "FullScreenDialog";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.layout_comments, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageButton backBtn = view.findViewById(R.id.back_btn);
        backBtn.setOnClickListener(this);

        ImageButton shareBtn = view.findViewById(R.id.share_btn);
        shareBtn.setOnClickListener(this);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        CommentsUtil.initCommentsRecycler(recyclerView, view.getContext());
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.back_btn:
                dismiss();
                break;
            case R.id.share_btn:
                ModalSendToBottomSheet cmntBtnDiag = new ModalSendToBottomSheet();
                FragmentTransaction ft1 = getFragmentManager().beginTransaction();
                cmntBtnDiag.show(ft1, "ok");
                Toast.makeText(getContext(), "Share", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
