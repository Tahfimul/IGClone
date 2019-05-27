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
import android.view.View;
import android.widget.*;
import com.example.igclone.Adapters.CommentsRecyclerAdapter;
import com.example.igclone.Adapters.RepliesItemAdapter;
import com.example.igclone.DataModel.CommentsDataModel;
import com.example.igclone.Comments.DataModel.ListItem;
import com.example.igclone.Comments.DataModel.MainItem;
import com.example.igclone.Comments.DataModel.RepliesItem;
import com.example.igclone.R;
import com.example.igclone.Util.CommentsUtil;
import com.example.igclone.Util.SendToUserUtil;
import com.example.igclone.ViewHolders.RepliesItemVH;

import java.util.ArrayList;
import java.util.TreeMap;

public class CommentsActivity extends AppCompatActivity implements View.OnClickListener{
    public static String TAG = "FullScreenDialog";
    private RecyclerView commentsRecycler;
    private EditText commentInput;
    private Button postBtn;
    private ImageButton shareBtn;

    public static final int MAIN_COMMENT = 0;
    public static final int REPLY_COMMENT = 1;
    private static int curr_comment_state;
    public static int main_comment_index;
    private static long main_comment_timestamp;
    private static String main_comment_comment;
    private static String POST_ID;

    //for comments Recycler
    private boolean isInitialized = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_comments);

        ImageButton backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(this);

        shareBtn = findViewById(R.id.share_btn);
        shareBtn.setVisibility(View.INVISIBLE);

        commentInput = findViewById(R.id.comment_input);
        postBtn = findViewById(R.id.post_btn);

        POST_ID = getIntent().getExtras().getString("postId");

        commentsRecycler = findViewById(R.id.recyclerView);

        setUpRecycler();

        registerReplyBroadcastReceiver();

        isShowKeyboard(getIntent().getExtras().getInt("commentState"), getIntent().getExtras().getBoolean("showKeyboard"));
        main_comment_index = getIntent().getExtras().getInt("mainCommentIndex");


    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReplyBroadcastReceiver();
    }

    private void setUpRecycler() {
        CommentsUtil viewModel = ViewModelProviders.of(this).get(CommentsUtil.class);

        viewModel.retrieveCommentItems(POST_ID).observe(this, new Observer<TreeMap<String, ListItem>>() {
            @Override
            public void onChanged(@Nullable TreeMap<String, ListItem> listItems) {

                //If CommentsRecycler has been initialized
                if(isInitialized)
                    ((CommentsRecyclerAdapter) commentsRecycler.getAdapter()).updatePreDataset(listItems);
                else
                {
                    isInitialized = true;
                    CommentsUtil.setListItems(listItems);
                    CommentsUtil.initCommentsRecycler(commentsRecycler, getApplicationContext());
                    shareBtn.setVisibility(View.VISIBLE);
                    shareBtn.setOnClickListener(CommentsActivity.this);
                    postBtn.setOnClickListener(CommentsActivity.this);
                }

            }
        });
    }

    private void registerReplyBroadcastReceiver() {
        // Register to receive reply button click.
        // We are registering an observer (replyBtnReceiver) to receive Intents
        // with actions named "reply". from RepliesItemVH or MainItemVH
        LocalBroadcastManager.getInstance(this).registerReceiver(replyBtnReceiver,
                new IntentFilter("reply"));
    }

    // Our handler for received Intent. This will be called whenever an Intent
    // with an action named "reply" is broadcast from MainItemVH
    private BroadcastReceiver replyBtnReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
           isShowKeyboard(intent.getExtras().getInt("commentState"), intent.getExtras().getBoolean("showKeyboard"));
           main_comment_timestamp = intent.getExtras().getLong("mainCommentTimestamp");
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
                main_comment_comment = commentInput.getText().toString();
                    if (curr_comment_state == MAIN_COMMENT) {
                        CommentsUtil.updateMainComment(POST_ID, CommentsUtil.getCurrentTime(), main_comment_comment);
                        ((CommentsRecyclerAdapter) commentsRecycler.getAdapter()).updatePreDataset(new MainItem(CommentsUtil.getCurrentTime(), new CommentsDataModel("abc", "username", main_comment_comment, false, CommentsUtil.getCurrentTime(), 0)));
                    } else {
                        CommentsUtil.updateReplyComment(POST_ID, main_comment_timestamp, CommentsUtil.getCurrentTime(), main_comment_comment);
                        System.out.println(main_comment_index + "Main Comment Index for  Reply");



                        System.out.println("-------------------------Printing Comments Recycler Adapter dataset from Comments Activity---------------");
                        for(int i = 0; i<((CommentsRecyclerAdapter)commentsRecycler.getAdapter()).getDataset().size(); i++)
                            System.out.println(((CommentsRecyclerAdapter)commentsRecycler.getAdapter()).getDataset().get(i));
                        System.out.println("---------------------------------------------------------------------------------------------------------");

                        System.out.println("Curr main comment timestamp "+ main_comment_timestamp + "R");
                        //If replies item for main comment does not exist
                        if(!((CommentsRecyclerAdapter)commentsRecycler.getAdapter()).getDataset().contains(main_comment_timestamp+"R"))
                        {
                            System.out.println("Comments Dataset does not have replies item in CommentsActivity");
                            ArrayList<CommentsDataModel> replies = new ArrayList<>();
                            replies.add(new CommentsDataModel("abc", "username", main_comment_comment, false, CommentsUtil.getCurrentTime(), 0));
                            ((CommentsRecyclerAdapter)commentsRecycler.getAdapter()).updatePreDataset(new RepliesItem(main_comment_timestamp, replies));

                        }
                        else {
                            System.out.println(((RepliesItem) ((CommentsRecyclerAdapter) commentsRecycler.getAdapter()).getDatasetListItem(((CommentsRecyclerAdapter) commentsRecycler.getAdapter()).getDataset().indexOf(main_comment_timestamp+"R"))).getMainCommentTimestamp()+"Printing main comment timestamp of replies item from Comments Activity");
                            ((RepliesItemAdapter) ((RepliesItemVH) ((RepliesItem) ((CommentsRecyclerAdapter) commentsRecycler.getAdapter()).getDatasetListItem(((CommentsRecyclerAdapter) commentsRecycler.getAdapter()).getDataset().indexOf(main_comment_timestamp + "R"))).getRepliesVH()).getRecyclerView().getAdapter()).updateDataset(new CommentsDataModel("abc", "username", main_comment_comment, false, CommentsUtil.getCurrentTime(), 0));
                        }
                    }
                commentInput.setText("");
                break;
        }
    }

    public void isShowKeyboard(int commentState, boolean showKeyboard)
    {
        if(showKeyboard) {
            CommentsUtil.showKeyboard(commentInput);
            curr_comment_state = commentState;
        }
    }

    private void dismissKeyboard()
    {
        CommentsUtil.dismissKeyboard(commentInput);
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
