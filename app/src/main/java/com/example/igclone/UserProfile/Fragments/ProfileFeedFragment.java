package com.example.igclone.UserProfile.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.igclone.R;
import com.example.igclone.Util.PostUtil;

public class ProfileFeedFragment extends Fragment {

    RecyclerView photoPostRecycler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feed_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        photoPostRecycler = view.findViewById(R.id.recyclerView);

        setUpPhotoPostRecycler();

    }

    private void setUpPhotoPostRecycler() {
        System.out.println("init photo post recycler");
        PostUtil.initProfilePhotoPostRecycler(photoPostRecycler, getContext(), getFragmentManager());
    }
}
