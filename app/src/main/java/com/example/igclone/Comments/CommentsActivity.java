package com.example.igclone.Comments;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.igclone.R;
import com.example.igclone.Util.CommentsUtil;
import com.example.igclone.Util.SendToUserUtil;

public class CommentsActivity extends AppCompatActivity implements View.OnClickListener{
    public static String TAG = "FullScreenDialog";
    private RelativeLayout modal;
    private Toolbar modalSendToUserToolbar;
    private View modalIndicator;
    private RecyclerView modalSendToUserRecycler;
    private EditText commentInput;
    private Button postBtn;

    public static final int MAIN_COMMENT = 0;
    public static final int REPLY_COMMENT = 1;
    private static int curr_comment_state;
    private static int main_comment_index;
    private static String POST_ID;

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
        commentInput = findViewById(R.id.comment_input);
        postBtn = findViewById(R.id.post_btn);
        postBtn.setOnClickListener(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        CommentsUtil.initCommentsRecycler(recyclerView, this);

        setUpReplyBroadcastReceiver();

        isShowKeyboard(getIntent().getExtras().getInt("commentState"), getIntent().getExtras().getBoolean("showKeyboard"));
        main_comment_index = getIntent().getExtras().getInt("mainCommentIndex");

        POST_ID = getIntent().getExtras().getString("postId");
    }

    private void setUpReplyBroadcastReceiver() {
        // Register to receive reply button click.
        // We are registering an observer (replyBtnReceiver) to receive Intents
        // with actions named "reply". from RepliesItemVH or MainItemVH
        LocalBroadcastManager.getInstance(this).registerReceiver(replyBtnReceiver,
                new IntentFilter("reply"));
    }

    // Our handler for received Intent. This will be called whenever an Intent
    // with an action named "reply" is broadcasted from RepliesItemVH or MainItemVH
    private BroadcastReceiver replyBtnReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

           isShowKeyboard(intent.getExtras().getInt("commentState"), intent.getExtras().getBoolean("showKeyboard"));
           main_comment_index = intent.getExtras().getInt("mainCommentIndex");

        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.back_btn:
                onBackPressed();
                break;
            case R.id.share_btn:
                SendToUserUtil.showSendToUserModal(getSupportFragmentManager());
                break;
            case R.id.post_btn:
                if (curr_comment_state==MAIN_COMMENT)
                    CommentsUtil.updateMainComment(POST_ID, main_comment_index);
                else
                    CommentsUtil.updateReplyComment(POST_ID, main_comment_index);
        }
    }

    private void isShowKeyboard(int commentState, boolean showKeyboard)
    {
        if(showKeyboard) {
            CommentsUtil.showKeyboard(commentInput);
            curr_comment_state = commentState;
        }
    }

    @Override
    public void onBackPressed() {
            super.onBackPressed();
    }
}
