package com.example.thread.Post.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.thread.ApiResponse.APIResponse;
import com.example.thread.Likes.repo.LikeRepo;
import com.example.thread.Post.model.Post;
import com.example.thread.Post.model.PostResponse;
import com.example.thread.Post.repo.PostRepo;
import com.example.thread.Post.service.PostService;
import com.example.thread.User.model.User;
import com.example.thread.User.repo.UserRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private LikeRepo likeRepo;

    private APIResponse apiResponse;

    @GetMapping("posts/feed")
    public ResponseEntity<?> getAllConnectionsPosts(Authentication authentication) {
        apiResponse = new APIResponse();
        List<PostResponse> posts;
        Vector<PostResponse> vec = new Vector<>();
        try {
            User LoggedInUser = userRepo.findByUserName(authentication.getName());
            String loggedUserName = LoggedInUser.getUserName();
            posts = this.postService.getAllPostsFeed(authentication);
            for (PostResponse p : posts) {
                String post_id = p.getPostId();
                if (likeRepo.isLikedByUser(loggedUserName, post_id) == null)
                    p.setIsliked(0);
                else
                    p.setIsliked(1);
            }
            vec.addAll(posts);
            apiResponse.setMessage("All Posts fetched Successfully");
            apiResponse.setData(vec);
            apiResponse.setStatus("success");
            apiResponse.setCount(vec.size());
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setData(null);
            apiResponse.setStatus("fail");
            apiResponse.setCount(0);
        }
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("all/posts/")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> getAllPosts(Authentication authentication) {
        apiResponse = new APIResponse();
        List<PostResponse> posts = new ArrayList<>();
        Vector<PostResponse> vec = new Vector<>();
        try {
            User LoggedInUser = userRepo.findByUserName(authentication.getName());
            String loggedUserName = LoggedInUser.getUserName();
            List<Post> allposts = this.postRepo.findAll();
            for (Post post : allposts) {
                posts.add(new PostResponse(post));
            }
            for (PostResponse p : posts) {
                String post_id = p.getPostId();
                if (likeRepo.isLikedByUser(loggedUserName, post_id) == null)
                    p.setIsliked(0);
                else
                    p.setIsliked(1);
            }
            vec.addAll(posts);
            apiResponse.setMessage("All Posts fetched Successfully");
            apiResponse.setData(vec);
            apiResponse.setStatus("success");
            apiResponse.setCount(vec.size());
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setData(null);
            apiResponse.setStatus("fail");
            apiResponse.setCount(0);
        }
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("posts/user/{userName}")
    public ResponseEntity<?> getAllPostsByUser(Authentication authentication, @PathVariable String userName) {
        apiResponse = new APIResponse();
        List<PostResponse> posts;
        Vector<PostResponse> vec = new Vector<>();
        try {
            posts = this.postService.getAllPostsByUser(authentication, userName);
            User LoggedInUser = userRepo.findByUserName(authentication.getName());
            String loggedUserName = LoggedInUser.getUserName();
            for (PostResponse p : posts) {
                String post_id = p.getPostId();
                if (likeRepo.isLikedByUser(loggedUserName, post_id) == null)
                    p.setIsliked(0);
                else
                    p.setIsliked(1);
            }
            vec.addAll(posts);
            apiResponse.setMessage("All Posts of a User fetched Successfully");
            apiResponse.setData(vec);
            apiResponse.setStatus("success");
            apiResponse.setCount(vec.size());
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setData(null);
            apiResponse.setStatus("fail");
            apiResponse.setCount(0);
        }
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("posts/{postId}")
    public ResponseEntity<?> getPostById(Authentication authentication, @PathVariable String postId) {
        Post post;
        try {
            post = this.postService.getPostById(authentication, postId);
            if (post != null) {
                apiResponse = new APIResponse();
                PostResponse pResp = new PostResponse(post);
                User LoggedInUser = userRepo.findByUserName(authentication.getName());
                String loggedUserName = LoggedInUser.getUserName();
                String post_id = pResp.getPostId();
                if (likeRepo.isLikedByUser(loggedUserName, post_id) == null)
                    pResp.setIsliked(0);
                else
                    pResp.setIsliked(1);
                Vector<PostResponse> vec = new Vector<>();

                vec.add(pResp);
                apiResponse.setMessage("Post fetched Successfully");
                apiResponse.setData(vec);
                apiResponse.setStatus("success");
                apiResponse.setCount(vec.size());
            } else {
                apiResponse = new APIResponse();
                apiResponse.setMessage("Post cannot be fetched as you are not the creator of post");
                apiResponse.setData(null);
                apiResponse.setStatus("fail");
                apiResponse.setCount(0);
            }
        } catch (

        Exception e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setData(null);
            apiResponse.setStatus("fail");
            apiResponse.setCount(0);

        }
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("posts/{postId}")
    public ResponseEntity<?> deletePostById(Authentication authentication, @PathVariable String postId) {
        boolean isDeleted;
        apiResponse = new APIResponse();
        Vector<String> vec = new Vector<>();
        try {
            isDeleted = this.postService.deletePostById(authentication, postId);
            if (isDeleted) {
                apiResponse.setMessage("Post with ID " + postId + " has been deleted.");
                apiResponse.setData(vec);
                apiResponse.setStatus("success");
                apiResponse.setCount(vec.size());

            } else {
                apiResponse.setMessage("You Cant delete Post with ID " + postId + ".");
                apiResponse.setData(null);
                apiResponse.setStatus("fail");
                apiResponse.setCount(0);
            }
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setData(null);
            apiResponse.setStatus("fail");
            apiResponse.setCount(0);
        }
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("posts/")
    public ResponseEntity<?> addPost(Authentication authentication, @RequestParam("post") String post,
            @RequestParam(value = "file", required = false) MultipartFile file) {
        apiResponse = new APIResponse();
        Vector<PostResponse> vec = new Vector<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            User LoggedInUser = userRepo.findByUserName(authentication.getName());
            if (LoggedInUser == null) {
                throw new Exception("user not authorized");
            }
            String loggedUserName = LoggedInUser.getUserName();
            Post post2 = objectMapper.readValue(post, Post.class);
            List<PostResponse> posts = this.postService.addPost(authentication, post2, file);
            for (PostResponse p : posts) {
                String post_id = p.getPostId();
                if (likeRepo.isLikedByUser(loggedUserName, post_id) == null)
                    p.setIsliked(0);
                else
                    p.setIsliked(1);
            }
            vec.addAll(posts);
            apiResponse.setMessage("Post Added Successfully");
            apiResponse.setData(vec);
            apiResponse.setStatus("success");
            apiResponse.setCount(vec.size());
        } catch (Exception e) {
            User LoggedInUser = userRepo.findByUserName(authentication.getName());
            if (LoggedInUser != null) {
                try {
                    List<Post> posts = this.postRepo.findAll(Sort.by(Sort.Direction.DESC, "creationTime"));
                    Iterator<Post> itr = posts.iterator();
                    // ! Get only Users Post
                    while (itr.hasNext()) {
                        String name = itr.next().getUser().getUserName();

                        if (!name.equals(LoggedInUser.getUserName())) {
                            itr.remove();
                        }
                    }
                    List<PostResponse> postResponse = new ArrayList<PostResponse>();
                    for (Post p : posts) {
                        postResponse.add(new PostResponse(p));
                    }
                    String loggedUserName = LoggedInUser.getUserName();
                    for (PostResponse p : postResponse) {
                        String post_id = p.getPostId();
                        if (likeRepo.isLikedByUser(loggedUserName, post_id) == null)
                            p.setIsliked(0);
                        else
                            p.setIsliked(1);
                    }
                    apiResponse.setMessage("Post Not Added due to Error " + e.getMessage());
                    apiResponse.setData(vec);
                    apiResponse.setStatus("fail");
                    apiResponse.setCount(vec.size());
                } catch (Exception e1) {
                    apiResponse.setMessage("Post Not Added due to Error " + e1.getMessage());
                    apiResponse.setData(vec);
                    apiResponse.setStatus("fail");
                    apiResponse.setCount(vec.size());
                }
            }

        }

        return ResponseEntity.ok(apiResponse);
    }
}