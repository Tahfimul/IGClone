package com.example.igclone.ViewHolders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.igclone.DataModel.UserDataModel;
import com.example.igclone.R;

public class SendToUserVH extends RecyclerView.ViewHolder {

    private ImageView userIcn;
    private TextView username;

    public SendToUserVH(@NonNull View itemView) {
        super(itemView);
        this.userIcn = itemView.findViewById(R.id.ic_user_profile);
        this.username = itemView.findViewById(R.id.username);
    }

    public void bind(UserDataModel user)
    {
        userIcn.setImageResource(user.getUserIconsrc());
        username.setText(user.getUsername());

    }
}
