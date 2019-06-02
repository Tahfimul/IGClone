package com.example.igclone.Comments.DataModel;

public abstract class ListItem {

    public static final int TYPE_MAIN =0;
    public static final int TYPE_REPLIES = 1;
    public static final int TYPE_REPLY = 2;

    public abstract int getType();
}
