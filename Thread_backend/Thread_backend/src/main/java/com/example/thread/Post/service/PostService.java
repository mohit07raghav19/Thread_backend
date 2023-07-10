package com.example.thread.Post.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import com.example.thread.Post.model.Post;
import com.example.thread.Post.model.PostResponse;

public interface PostService {

    public List<PostResponse> getAllPostsFeed(Authentication authentication) throws Exception;

    public List<PostResponse> getAllPostsByUser(Authentication authentication, String userName) throws Exception;

    public Post getPostById(Authentication authentication, String postId) throws Exception;

    public boolean deletePostById(Authentication authentication, String postId) throws Exception;

    public List<PostResponse> addPost(Authentication authentication, Post post, MultipartFile file) throws Exception;

    public void initPosts();

    public List<PostResponse> getAllPosts(Authentication authentication) throws Exception;
}
