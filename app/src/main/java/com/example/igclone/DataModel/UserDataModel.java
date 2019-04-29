package com.example.igclone.DataModel;

public class UserDataModel  {

    private int userIconsrc;
    private String username;

    public UserDataModel(int userIconsrc, String username)
    {
        this.userIconsrc = userIconsrc;
        this.username = username;
    }

    public void setUserIconsrc(int userIconsrc) {
        this.userIconsrc = userIconsrc;
    }

    public int getUserIconsrc() {
        return userIconsrc;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
