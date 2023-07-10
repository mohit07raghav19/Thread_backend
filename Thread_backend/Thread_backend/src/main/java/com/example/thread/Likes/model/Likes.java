package com.example.thread.Likes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.thread.Post.model.Post;
import com.example.thread.User.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int likeId;
    @ManyToOne()

    @JoinColumn(name = "userName")
    private User userLiked;
    @ManyToOne()

    @JoinColumn(name = "postId")
    private Post postLiked;

    public int getLikeId() {
        return likeId;
    }

    public void setLikeId(int likeId) {
        this.likeId = likeId;
    }

    public User getUserLiked() {
        return userLiked;
    }

    public void setUserLiked(User userLiked) {
        this.userLiked = userLiked;
    }

    public Post getPostLiked() {
        return postLiked;
    }

    public void setPostLiked(Post postLiked) {
        this.postLiked = postLiked;
    }

    public Likes() {
        super();
    }

    public Likes(int likeId, User userLiked, Post postLiked) {
        this.likeId = likeId;
        this.userLiked = userLiked;
        this.postLiked = postLiked;
    }

    public Likes(User userLiked, Post postLiked) {
        this.userLiked = userLiked;
        this.postLiked = postLiked;
    }

}
