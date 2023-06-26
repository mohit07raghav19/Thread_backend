package com.example.thread.Comments.model;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.example.thread.Post.model.Post;
import com.example.thread.User.model.User;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int commentId;
    @Column(columnDefinition = "LONGTEXT")
    private String commentText;
    @ManyToOne
    private User commentBy;
    @ManyToOne()
    private Post postOn;

    @Column(columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
    private LocalDateTime creationTime;

    public Comment(String commentText, User commentBy, Post postOn, LocalDateTime creationTime) {
        super();
        this.commentText = commentText;
        this.commentBy = commentBy;
        this.postOn = postOn;
        this.creationTime = creationTime;
    }

    public Comment() {
        super();
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

    public User getCommentBy() {
        return commentBy;
    }

    public void setCommentBy(User commentBy) {
        this.commentBy = commentBy;
    }

    public Post getPostOn() {
        return postOn;
    }

    public void setPostOn(Post postOn) {
        this.postOn = postOn;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

}
