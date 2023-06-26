package com.example.thread.Likes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.thread.ApiResponse.APIResponse;
import com.example.thread.Likes.service.LikeService;
import com.example.thread.Post.model.PostResponse;

import java.util.List;
import java.util.Vector;

@RestController
@RequestMapping("/")
public class LikesController {

    @Autowired
    private LikeService likeService;

    private APIResponse apiResponse;

    @PostMapping(value = "like/{postId}", produces = "application/json")
    public ResponseEntity<?> likePost(Authentication authentication, @PathVariable String postId) {
        apiResponse = new APIResponse();
        boolean f;
        try {
            f = likeService.userLikesPost(authentication, postId);
            if (f) {
                Vector<Integer> vec = new Vector<>();
                vec.add(likeService.showPostLikes(postId));
                apiResponse.setCount(vec.size());
                apiResponse.setStatus("success");
                apiResponse.setMessage("Liked the Post");
                apiResponse.setData(vec);
            } else {
                Vector<Integer> vec = new Vector<>();
                vec.add(likeService.showPostLikes(postId));
                apiResponse.setCount(vec.size());
                apiResponse.setStatus("fail");
                apiResponse.setMessage("Could not like Post");
                apiResponse.setData(vec);
            }
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setData(null);
            apiResponse.setStatus("fail");
            apiResponse.setCount(0);
        }
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping(value = "unlike/{postId}", produces = "application/json")
    public ResponseEntity<?> UnLikePost(Authentication authentication, @PathVariable String postId) {
        apiResponse = new APIResponse();
        boolean f;
        try {
            f = likeService.UnLikesPost(authentication, postId);
            if (f) {
                Vector<Integer> vec = new Vector<>();
                vec.add(likeService.showPostLikes(postId));
                apiResponse.setCount(vec.size());
                apiResponse.setStatus("success");
                apiResponse.setMessage("UnLiked the Post");
                apiResponse.setData(vec);
            } else {
                Vector<Integer> vec = new Vector<>();
                vec.add(likeService.showPostLikes(postId));
                apiResponse.setCount(vec.size());
                apiResponse.setStatus("fail");
                apiResponse.setMessage("Could not unlike Post");
                apiResponse.setData(vec);
            }
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setData(null);
            apiResponse.setStatus("fail");
            apiResponse.setCount(0);
        }

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "likes/post/{postId}", produces = "application/json")
    public ResponseEntity<?> LikesOnPost(@PathVariable String postId) {
        apiResponse = new APIResponse();
        int likes = likeService.showPostLikes(postId);
        Vector<Integer> vec = new Vector<>();
        vec.add(likes);
        apiResponse.setCount(vec.size());
        apiResponse.setStatus("success");
        apiResponse.setMessage("Number of Post likes.");
        apiResponse.setData(vec);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "likes/user", produces = "application/json")
    public ResponseEntity<?> UserLikedPost(Authentication authentication) {
        apiResponse = new APIResponse();
        List<PostResponse> posts;
        Vector<PostResponse> vec = new Vector<>();
        try {
            posts = likeService.showUserLikedPosts(authentication);
            vec.addAll(posts);
            apiResponse.setCount(vec.size());
            apiResponse.setStatus("success");
            apiResponse.setMessage("Posts liked By User Returned");
            apiResponse.setData(vec);
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setData(null);
            apiResponse.setStatus("fail");
            apiResponse.setCount(0);
        }

        return ResponseEntity.ok(apiResponse);
    }
}
