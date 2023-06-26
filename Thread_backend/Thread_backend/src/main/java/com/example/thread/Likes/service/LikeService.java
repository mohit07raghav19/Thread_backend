package com.example.thread.Likes.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.example.thread.Post.model.PostResponse;

public interface LikeService {
    public boolean userLikesPost(Authentication authentication, String postId)throws Exception;

    public boolean UnLikesPost(Authentication authentication, String postId)throws Exception;

    public void initLikes();

    public int showPostLikes(String postId);

    public List<PostResponse> showUserLikedPosts(Authentication authentication)throws Exception;

}
