package com.example.igclone.Comments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import com.example.igclone.Comments.DataModel.ListItem;
import com.example.igclone.Comments.DataModel.MainItem;
import com.example.igclone.Comments.DataModel.ReplyItem;
import com.example.igclone.R;
import com.example.igclone.Comments.Util.CommentsUtil;
import com.example.igclone.Util.SendToUserUtil;

import java.util.TreeMap;

public class CommentsActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView commentsRecycler;
    private EditText commentInput;
    private Button postBtn;
    private Toolbar mToolbar;
    private ImageButton leftBtn;
    private TextView mTitle;
    private ImageButton rightBtn;
    private RelativeLayout replyIndicator;
    private TextView replyIndicatorMsg;
    private TextView replyIndicatorDismissBtn;

    public static final int MAIN_COMMENT = 0;
    public static final int REPLY_COMMENT = 1;
    public static final int REPLY_CONTAINER = 2;
    private static int curr_comment_state;
    private static String reply_container_timestamp;
    private static String replying_to_timestamp;
    private static int replying_to_source;
    private static String POST_ID;

    private static int curr_selected_item_type;
    private static String curr_selected_Item_timestamp;
    private static String curr_selected_item_container_timestamp;
    private static boolean isItemSelected;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_comments);

        leftBtn = findViewById(R.id.left_btn);
        leftBtn.setOnClickListener(this);

        mToolbar = findViewById(R.id.toolbar);

        mTitle = findViewById(R.id.comments_title);

        rightBtn = findViewById(R.id.right_btn);
        rightBtn.setVisibility(View.INVISIBLE);

        commentInput = findViewById(R.id.comment_input);
        postBtn = findViewById(R.id.post_btn);

        POST_ID = getIntent().getExtras().getString("postId");

        commentsRecycler = findViewById(R.id.recyclerView);

        replyIndicator = findViewById(R.id.replying_indicator);
        replyIndicatorMsg = findViewById(R.id.replying_inidicator_message);
        replyIndicatorDismissBtn = findViewById(R.id.replying_indicator_dismiss_btn);

        setUpRecycler();

        registerReplyBroadcastReceiver();

        registerItemSelectReqBroadcastReceiver();

        curr_comment_state = CommentsActivity.MAIN_COMMENT;

        if(getIntent().getExtras().getBoolean("showKeyboard"))
            showKeyboard();


    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReplyBroadcastReceiver();
    }

    private void setUpRecycler() {
        CommentsUtil.initCommentsRecycler(commentsRecycler, getApplicationContext(), POST_ID);

        CommentsUtil viewModel = ViewModelProviders.of(this).get(CommentsUtil.class);

        viewModel.retrieveCommentItems(POST_ID).observe(this, new Observer<TreeMap<String, ListItem>>() {
            @Override
            public void onChanged(@Nullable TreeMap<String, ListItem> listItems) {

//                System.out.println("----------------------------Printing Predataset Comments Activity-----------------------------------------");
//                for(String key:listItems.keySet()) {
//                    System.out.println(listItems.get(key).getType());
//                    if (listItems.get(key).getType()==CommentsActivity.MAIN_COMMENT)
//                        System.out.println(((MainItem)listItems.get(key)).getMainCommentTimestamp());
//                }
//                System.out.println("-----------------------------------------------------------------------------------------");

                CommentsUtil.updateCommentsRecyclerDataset(listItems);
                replyIndicatorDismissBtn.setOnClickListener(CommentsActivity.this);
                rightBtn.setVisibility(View.VISIBLE);
                rightBtn.setOnClickListener(CommentsActivity.this);
                postBtn.setOnClickListener(CommentsActivity.this);

            }
        });
    }

    private void registerReplyBroadcastReceiver() {
        // Register to receive reply button click.
        // We are registering an observer (replyBtnReceiver) to receive Intents
        // with actions named "reply". from ReplyItemVH or MainItemVH
        LocalBroadcastManager.getInstance(this).registerReceiver(replyBtnReceiver,
                new IntentFilter("reply"));
    }

    private void registerItemSelectReqBroadcastReceiver()
    {
        // Register to receive main comment Item click.
        // We are registering an observer (mainCommentItemSelected) to receive Intents
        // with actions named "mainCommentItemSelected". from MainItemVH
        LocalBroadcastManager.getInstance(this).registerReceiver(itemSelectReqReceiver,
                new IntentFilter("itemSelectReq"));
    }

    // Our handler for received Intent. This will be called whenever an Intent
    // with an action named "reply" is broadcast from ReplyItemVH or MainItemVH
    private BroadcastReceiver replyBtnReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
           curr_comment_state = CommentsActivity.REPLY_COMMENT;
           reply_container_timestamp = intent.getExtras().getString("replyContainerTimestamp");
           replying_to_timestamp = intent.getExtras().getString("replyingToTimestamp");
           replying_to_source = intent.getExtras().getInt("replyingToSource");
           showReplyIndicator();
           showKeyboard();
        }
    };

    // Our handler for received Intent. This will be called whenever an Intent
    // with an action named "itemSelectReq" is broadcast from MainItemVH or ReplyItemVH
    private BroadcastReceiver itemSelectReqReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            int type = intent.getExtras().getInt("itemType");
            String itemTimestamp = intent.getExtras().getString("itemTimestamp");

            switch (type)
            {
                case CommentsActivity.MAIN_COMMENT:

                    if(isItemSelected)
                    {
                        if(curr_selected_item_type == CommentsActivity.MAIN_COMMENT)
                            if(curr_selected_Item_timestamp.equals(itemTimestamp))
                                setItemUnselected(type);
                        else
                            sendItemSelectReqRes(type, itemTimestamp, false);
                    }
                    else
                        setItemSelected(type, itemTimestamp, null);

                    break;

                case CommentsActivity.REPLY_COMMENT:
                    String itemContainerTimestamp = intent.getExtras().getString("itemContainerTimestamp");

                    System.out.println(itemTimestamp+" itemTimestamp received in CommentsActivity");
                    System.out.println(itemContainerTimestamp+" itemContainerTimestamp received in CommentsActivity");
                    if (isItemSelected)
                    {
                        if (curr_selected_item_type == CommentsActivity.REPLY_COMMENT) {

                            if (curr_selected_item_container_timestamp.equals(itemContainerTimestamp) && curr_selected_Item_timestamp.equals(itemTimestamp))
                                setItemUnselected(type);

                        }
                        else
                            sendItemSelectReqRes(type, itemContainerTimestamp, itemTimestamp, false);
                    }

                    else
                        setItemSelected(type, itemTimestamp, itemContainerTimestamp);

                    break;


            }
//            Toast.makeText(CommentsActivity.this, "Main Comment Item Selected", Toast.LENGTH_SHORT).show();
//            selected_reply_comment_item_container_timestamp = " ";
//
//            if(!isCommentItemSelected) {
//                curr_selected_comment_Item_timestamp = intent.getExtras().getString("mainCommentItemTimestamp");
//                isCommentItemSelected = true;
//                CommentsUtil.setMainCommentItemSelected(CommentsActivity.this, curr_selected_comment_Item_timestamp, mToolbar, backBtn, mCommentsTitle, shareBtn);
//
//                Bundle bundle = new Bundle();
//                bundle.putBoolean("mainCommentItemSelectionConfirmed", true);
//                Intent i = new Intent();
//                i.setAction("mainCommentItemSelectionConfirmation");
//                i.putExtras(bundle);
//                LocalBroadcastManager.getInstance(CommentsActivity.this).sendBroadcast(i);
//
//            }
//            if(curr_selected_comment_Item_timestamp.equals(intent.getExtras().getString("mainCommentItemTimestamp")))
//            {
//                isCommentItemSelected = false;
//                CommentsUtil.setMainCommentItemUnSelected(CommentsActivity.this, mToolbar, backBtn, mCommentsTitle, shareBtn);
//                curr_selected_comment_Item_timestamp = " ";
//
//                Bundle bundle = new Bundle();
//                bundle.putBoolean("mainCommentItemSelectionConfirmed", true);
//                Intent i = new Intent();
//                i.setAction("mainCommentItemSelectionConfirmation");
//                i.putExtras(bundle);
//                LocalBroadcastManager.getInstance(CommentsActivity.this).sendBroadcast(i);
//            }
        }
    };

    private void setItemUnselected(int itemType)
    {
        switch (itemType)
        {
            case CommentsActivity.MAIN_COMMENT:
                isItemSelected = false;
                CommentsUtil.showDefaultToolbar(this,mToolbar, leftBtn, mTitle, rightBtn);
                sendItemSelectReqRes(itemType, curr_selected_Item_timestamp, true);
                curr_selected_Item_timestamp = " ";
                break;

            case CommentsActivity.REPLY_COMMENT:
                isItemSelected = false;
                CommentsUtil.showDefaultToolbar(this, mToolbar, leftBtn, mTitle, rightBtn);
                sendItemSelectReqRes(itemType, curr_selected_item_container_timestamp, curr_selected_Item_timestamp, true);
                curr_selected_item_container_timestamp = " ";
                curr_selected_Item_timestamp = " ";
                break;
        }
    }

    private void setItemSelected(int itemType, String itemTimestamp, String itemContainerTimestamp)
    {
        switch (itemType)
        {
            case CommentsActivity.MAIN_COMMENT:
                curr_selected_item_type = itemType;
                isItemSelected = true;
                curr_selected_Item_timestamp = itemTimestamp;
                CommentsUtil.showItemSelectedToolbar(this, itemType, curr_selected_Item_timestamp, null, mToolbar, leftBtn, mTitle, rightBtn);
                sendItemSelectReqRes(itemType, curr_selected_Item_timestamp, true);
                break;

            case CommentsActivity.REPLY_COMMENT:
                curr_selected_item_type = itemType;
                isItemSelected = true;
                curr_selected_item_container_timestamp = itemContainerTimestamp;
                curr_selected_Item_timestamp = itemTimestamp;
                CommentsUtil.showItemSelectedToolbar(this, itemType, curr_selected_Item_timestamp, curr_selected_item_container_timestamp, mToolbar, leftBtn, mTitle, rightBtn);
                sendItemSelectReqRes(itemType, curr_selected_item_container_timestamp, curr_selected_Item_timestamp, true);
                break;
        }
    }

    private void sendItemSelectReqRes(int type, String itemTimestamp, boolean res)
    {
        System.out.println("Res from CommentsActivity "+res );
        System.out.println("Main Item Timestamp from CommentsActivity"+itemTimestamp);
        Bundle b = new Bundle();
        b.putInt("itemType", type);
        b.putString("itemTimestamp", itemTimestamp);
        b.putBoolean("res", res);

        Intent i = new Intent();
        i.setAction("itemSelectReqRes");
        i.putExtras(b);

        LocalBroadcastManager.getInstance(this).sendBroadcast(i);
    }

    private void sendItemSelectReqRes(int type, String itemContainerTimestamp, String itemTimestamp, boolean res)
    {
        System.out.println(res + " res from sendItemSelectReqRes() in CommentsActivity");
        System.out.println(itemContainerTimestamp + " item Container Timestamp from sendItemSelectReqRes() in CommentsActivity");
        System.out.println(itemTimestamp + " item Timestamp from sendItemSelectReqRes() in CommentsActivity");
        Bundle b = new Bundle();
        b.putInt("itemType", type);
        b.putString("itemContainerTimestamp", itemContainerTimestamp);
        b.putString("itemTimestamp", itemTimestamp);
        b.putBoolean("res", res);

        Intent i = new Intent();
        i.setAction("itemSelectReqRes");
        i.putExtras(b);

        LocalBroadcastManager.getInstance(this).sendBroadcast(i);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.left_btn:
                onBackPressed();
                break;
            case R.id.right_btn:
                SendToUserUtil.showSendToUserModal(getSupportFragmentManager());
                break;
            case R.id.post_btn:
                 String comment = commentInput.getText().toString();
                if (!comment.isEmpty())
                {
                    switch (curr_comment_state)
                    {
                        case CommentsActivity.MAIN_COMMENT:
                            System.out.println("Posting Main COmment");
                            CommentsUtil.postMainComment(comment);
                            break;
                        case CommentsActivity.REPLY_COMMENT:
                            System.out.println("Posting Reply Comment");
                            CommentsUtil.postReplyComment(reply_container_timestamp, comment);
                            break;
                    }
                }
                commentInput.setText("");
                break;
            case R.id.replying_indicator_dismiss_btn:
                curr_comment_state = CommentsActivity.MAIN_COMMENT;
                dismissReplyIndicator();
                break;
        }
    }

    private void showKeyboard()
    {
        CommentsUtil.showKeyboard(commentInput);
    }

    private void dismissKeyboard()
    {
        CommentsUtil.dismissKeyboard(commentInput);
    }

    private void showReplyIndicator()
    {
        switch(replying_to_source)
        {
            case CommentsActivity.MAIN_COMMENT:
                replyIndicatorMsg.setText("Replying to "+CommentsUtil.getReplyingToUsernameFromMain(replying_to_timestamp));
                break;
            case CommentsActivity.REPLY_CONTAINER:
                replyIndicatorMsg.setText("Replying to "+CommentsUtil.getReplyingToUsernameFromReplyContainer(reply_container_timestamp, replying_to_timestamp));
                break;
        }
        replyIndicator.setVisibility(View.VISIBLE);

        Animation slide_up = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.anim_slide_up);

        replyIndicator.startAnimation(slide_up);
    }

    private void dismissReplyIndicator()
    {
        Animation slide_down = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.anim_slide_down);

        replyIndicator.startAnimation(slide_down);

        replyIndicator.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed() {
            finish();
            super.onBackPressed();
    }


    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(replyBtnReceiver);
    }
}
