package com.example.igclone.Comments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;
import butterknife.BindView;
import com.example.igclone.R;
import com.example.igclone.Util.CommentsUtil;
import com.example.igclone.Util.SendToUserUtil;

public class CommentsActivity extends AppCompatActivity implements View.OnClickListener{
    public static String TAG = "FullScreenDialog";
    private RelativeLayout modal;
    private Toolbar modalSendToUserToolbar;
    private View modalIndicator;
    private RecyclerView modalSendToUserRecycler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_comments);

        ImageButton backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(this);

        ImageButton shareBtn = findViewById(R.id.share_btn);
        shareBtn.setOnClickListener(this);

        modal = findViewById(R.id.layout_send_to_bottom_sheet);
        modalSendToUserToolbar = findViewById(R.id.modalToolbar);
        modalIndicator = findViewById(R.id.modalIndicator);
        modalSendToUserRecycler = findViewById(R.id.friend_users);

        SendToUserUtil.setModalBehavior(modal, modalSendToUserToolbar, modalIndicator);
        SendToUserUtil.hideSendToUserModal(modal);


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        CommentsUtil.initCommentsRecycler(recyclerView, this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.back_btn:
                onBackPressed();
                break;
            case R.id.share_btn:
                SendToUserUtil.showSendToUserModal(modal);
                SendToUserUtil.initSendToUserRecycler(modalSendToUserRecycler, this);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        int sendToUserModalState = SendToUserUtil.getModalBehaviorState(modal);
        if(sendToUserModalState==BottomSheetBehavior.STATE_EXPANDED || sendToUserModalState == BottomSheetBehavior.STATE_HALF_EXPANDED)
        {
            SendToUserUtil.hideSendToUserModal(modal);
        }
        else
            super.onBackPressed();
    }
}
