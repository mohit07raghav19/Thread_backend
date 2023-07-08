package com.example.thread.Post.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.example.thread.Comments.model.Comment;
import com.example.thread.Comments.model.CommentResponse;

import java.time.Duration;

public class PostResponse {
    private String postId;
    private String userName;
    private String userFullName;
    private String profileImage;
    private String description;
    private String postImage;
    private String creationTime;
    private int likes;
    private int isliked;

    private List<CommentResponse> comments;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public PostResponse(Post post) {
        this.postId = post.getPostId();
        this.userName = post.getUser().getUserName();
        this.userFullName = post.getUser().getUserFullName();
        this.profileImage = post.getUser().getProfileImage();
        this.description = post.getDescription();
        this.postImage = post.getPostImage();
        this.likes = post.getLikes() == null ? 0 : post.getLikes().size();

        List<Comment> c = post.getComments() == null ? null : post.getComments();
        if (c != null) {
            Vector<CommentResponse> vec = new Vector<>();
            for (Comment comment : c) {
                vec.add(new CommentResponse(comment));
            }
            List<CommentResponse> cResp = new ArrayList<>();
            cResp.addAll(vec);
            this.comments = cResp;
        } else
            this.comments = null;
        // this.comments = comments;
        LocalDateTime now = LocalDateTime.now();
        Duration time = Duration.between(post.getCreationTime(), now);
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

    public PostResponse() {
        super();
    }

    public List<CommentResponse> getComments() {
        return comments;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setComments(List<CommentResponse> comments) {
        this.comments = comments;
    }

    public int getIsliked() {
        return isliked;
    }

    public void setIsliked(int isliked) {
        this.isliked = isliked;
    }

}
