package com.example.igclone.DataModel;

public abstract class ListItem {

    public static int TYPE_MAIN =0;
    public static int TYPE_REPLIES = 1;

    public abstract int getType();
}
