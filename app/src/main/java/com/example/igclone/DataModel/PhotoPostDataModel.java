package com.example.igclone.DataModel;

public class PhotoPostDataModel {

    private int userIconSrc;
    private String username;
    private int photoSrc;
    private int user1IconSrc;
    private int user2IconSrc;
    private int user3IconSrc;
    private boolean liked;
    private String latestUserLike;
    private int userLikeCount;
    private String latestCommentUsername;
    private String latestComment;
    private int commentCount;
    private long timeStamp;

    public PhotoPostDataModel(int userIconSrc, String username, int photoSrc, int user1IconSrc, int user2IconSrc, int user3IconSrc, boolean liked, String latestUserLike, int userLikeCount, String latestCommentUsername, String latestComment, int commentCount, long timeStamp)
    {
        this.userIconSrc = userIconSrc;
        this.username = username;
        this.photoSrc = photoSrc;
        this.user1IconSrc = user1IconSrc;
        this.user2IconSrc = user2IconSrc;
        this.user3IconSrc = user3IconSrc;
        this.liked = liked;
        this.latestUserLike = latestUserLike;
        this.userLikeCount = userLikeCount;
        this.latestComment = latestComment;
        this.latestCommentUsername = latestCommentUsername;
        this.commentCount = commentCount;
        this.timeStamp = timeStamp;
    }

    public void setUserIconSrc(int userIconSrc) {
        this.userIconSrc = userIconSrc;
    }

    public int getUserIconSrc() {
        return userIconSrc;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPhotoSrc(int photoSrc) {
        this.photoSrc = photoSrc;
    }

    public int getPhotoSrc() {
        return photoSrc;
    }

    public void setUser1IconSrc(int user1IconSrc) {
        this.user1IconSrc = user1IconSrc;
    }

    public int getUser1IconSrc() {
        return user1IconSrc;
    }

    public void setUser2IconSrc(int user2IconSrc) {
        this.user2IconSrc = user2IconSrc;
    }

    public int getUser2IconSrc() {
        return user2IconSrc;
    }

    public void setUser3IconSrc(int user3IconSrc) {
        this.user3IconSrc = user3IconSrc;
    }

    public int getUser3IconSrc() {
        return user3IconSrc;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLatestUserLike(String latestUserLike) {
        this.latestUserLike = latestUserLike;
    }

    public String getLatestUserLike() {
        return latestUserLike;
    }

    public void setUserLikeCount(int userLikeCount) {
        this.userLikeCount = userLikeCount;
    }

    public int getUserLikeCount() {
        return userLikeCount;
    }

    public void setLatestCommentUsername(String latestCommentUsername) {
        this.latestCommentUsername = latestCommentUsername;
    }

    public String getLatestCommentUsername() {
        return latestCommentUsername;
    }

    public void setLatestComment(String latestComment) {
        this.latestComment = latestComment;
    }

    public String getLatestComment() {
        return latestComment;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public long getTimeStamp() {
        return timeStamp;
    }
}
