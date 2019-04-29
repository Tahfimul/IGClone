package com.example.igclone.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.igclone.DataModel.UserDataModel;
import com.example.igclone.R;
import com.example.igclone.ViewHolders.SendToUserVH;

import java.util.ArrayList;

public class SendToRecyclerAdapter extends RecyclerView.Adapter {

    private ArrayList<UserDataModel> users;

    public SendToRecyclerAdapter(ArrayList<UserDataModel> users)
    {
        this.users = users;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_send_to_user, viewGroup, false);
        SendToUserVH vh = new SendToUserVH(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        SendToUserVH vh = (SendToUserVH) viewHolder;
        vh.bind(users.get(i));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
