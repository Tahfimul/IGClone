package com.example.igclone.DataModel;

public class CommentsDataModel {
    private String userIcnSrc;
    private String username;
    private String comment;
    private boolean liked;
    private long timestamp;
    private int likeCount;

    public CommentsDataModel(String userIcnSrc, String username, String comment, boolean liked, long timestamp, int likeCount)
    {
        this.userIcnSrc = userIcnSrc;
        this.username = username;
        this.comment = comment;
        this.liked = liked;
        this.timestamp = timestamp;
        this.likeCount = likeCount;
    }

    public String getUserIcnSrc() {
        return userIcnSrc;
    }

    public void setUserIcnSrc(String userIcnSrc) {
        this.userIcnSrc = userIcnSrc;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public void incrementLikeCount()
    {
        likeCount++;
    }

    public void decrementLikeCount()
    {
        if (likeCount>0)
            likeCount--;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
}
