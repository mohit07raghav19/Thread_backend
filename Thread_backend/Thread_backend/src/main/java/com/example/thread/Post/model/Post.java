package com.example.thread.Post.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import com.example.thread.Comments.model.Comment;
import com.example.thread.Likes.model.Likes;
import com.example.thread.User.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "post")
public class Post {
    @Id
    private String postId;

    @OneToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private User user;

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    private String postImage;

    @Column(columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
    private LocalDateTime creationTime;

    @OneToMany(mappedBy = "postOn", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    List<Comment> comments;

    @OneToMany(mappedBy = "postLiked", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    List<Likes> likes;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
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

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public Post(String postId, User user, String description,
            String postImage, LocalDateTime creationTime) {
        this.postId = postId;
        this.user = user;
        this.description = description;
        this.postImage = postImage;
        this.creationTime = creationTime;
    }

    public Post(User user, String description, String postImage) {
        this.user = user;
        this.description = description;
        this.postImage = postImage;
    }

    public Post(String postId, User user, String description, String postImage) {
        this.postId = postId;
        this.user = user;
        this.description = description;
        this.postImage = postImage;
    }

    public Post() {
        super();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Likes> getLikes() {
        return likes;
    }

    public void setLikes(List<Likes> likes) {
        this.likes = likes;
    }

}
