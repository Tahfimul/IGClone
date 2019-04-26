package com.example.igclone.UserProfile.DataModel;

public class StoriesArchiveDataModel {

    int imageSource;
    String title;

    public StoriesArchiveDataModel(int imageSource, String title)
    {
        this.imageSource = imageSource;
        this.title = title;
    }

    public void setImageSource(int imageSource) {
        this.imageSource = imageSource;
    }

    public int getImageSource() {
        return imageSource;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
