package com.example.thread.Comments.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class CommentResponse {
    private int commentId;
    private String commentText;

    private String userName;
    private String userFullName;
    private String profileImage;
    private String postId;

    private String creationTime;

    public CommentResponse() {
        super();
    }

    public CommentResponse(Comment comment) {
        this.commentId = comment.getCommentId();
        this.commentText = comment.getCommentText();
        this.userName = comment.getCommentBy().getUserName();
        this.userFullName = comment.getCommentBy().getUserFullName();
        this.profileImage = comment.getCommentBy().getProfileImage();
        this.postId = comment.getPostOn().getPostId();
        LocalDateTime now = LocalDateTime.now();
        Duration time = Duration.between(comment.getCreationTime(), now);
        if (time.toDays() > 365) {
            this.creationTime = time.toDays() / 365 + " years ago";
        } else if (time.toDays() > 31) {
            this.creationTime = time.toDays() / 31 + " months ago";
        } else if (time.toDays() > 0) {
            this.creationTime = time.toDays() % 31 + " days ago";
        } else if (time.toHours() > 0) {
            this.creationTime = time.toHours() + " hrs ago";
        } else if (time.toMinutes() > 0) {
            this.creationTime = time.toMinutes() + " min ago";
        } else if (time.toSeconds() > 10) {
            this.creationTime = time.toSeconds() + " sec ago";
        } else {
            this.creationTime = " Just Now";
        }
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

}
