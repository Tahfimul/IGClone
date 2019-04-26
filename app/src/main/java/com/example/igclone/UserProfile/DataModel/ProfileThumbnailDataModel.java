package com.example.igclone.UserProfile.DataModel;

public class ProfileThumbnailDataModel {

    int thumbnailSource;
    int contentTypeSource;

    public ProfileThumbnailDataModel(int thumbnailSource, int contentTypeSource)
    {
        this.thumbnailSource = thumbnailSource;
        this.contentTypeSource = contentTypeSource;
    }

    public void setThumbnailSource(int thumbnailSource) {
        this.thumbnailSource = thumbnailSource;
    }

    public int getThumbnailSource() {
        return thumbnailSource;
    }

    public void setContentTypeSource(int contentTypeSource) {
        this.contentTypeSource = contentTypeSource;
    }

    public int getContentTypeSource() {
        return contentTypeSource;
    }
}
