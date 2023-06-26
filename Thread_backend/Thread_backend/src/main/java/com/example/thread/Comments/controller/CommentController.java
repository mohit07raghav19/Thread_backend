package com.example.thread.Comments.controller;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.thread.ApiResponse.APIResponse;
import com.example.thread.Comments.model.Comment;
import com.example.thread.Comments.model.CommentResponse;
import com.example.thread.Comments.service.CommentService;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    private APIResponse apiResponse;

    @PostMapping(path = "/post/{postId}/comment", produces = "application/json")
    public ResponseEntity<?> userMakesCommentAtTweet(
            @RequestBody Comment comment,
            Authentication authentication,
            @PathVariable("postId") String postId) {
        Vector<CommentResponse> vec = new Vector<>();
        List<CommentResponse> savedComment;
        apiResponse = new APIResponse();
        try {
            savedComment = commentService.userMakesNewCommentAtPost(authentication, postId, comment);
            vec.addAll(savedComment);
            apiResponse.setData(vec);
            apiResponse.setMessage("Comment created");
            apiResponse.setStatus("success");
            apiResponse.setCount(vec.size());
        } catch (Exception e) {
            apiResponse.setData(vec);
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus("fail");
            apiResponse.setCount(vec.size());
        }
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping(path = "/post/{postId}/comment", produces = "application/json")
    public ResponseEntity<?> showPostComments(Authentication authentication, @PathVariable("postId") String postId) {

        List<CommentResponse> post_comments;
        Vector<CommentResponse> vec = new Vector<>();
        try {
            post_comments = commentService.showPostComments(authentication, postId);
            apiResponse = new APIResponse();
            vec.addAll(post_comments);
            apiResponse.setStatus("success");
            apiResponse.setCount(vec.size());
            apiResponse.setData(vec);
            apiResponse.setMessage("post comments fetched");
        } catch (Exception e) {
            apiResponse = new APIResponse();
            apiResponse.setStatus("fail");
            apiResponse.setCount(0);
            apiResponse.setData(null);
            apiResponse.setMessage(e.getMessage());
        }
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping(path = "/comment/{comment_id}", produces = "application/json")
    public ResponseEntity<?> deleteComment(
            Authentication authentication,
            @PathVariable("comment_id") int comment_id) {
        apiResponse = new APIResponse();
        Vector<CommentResponse> vec = new Vector<>();
        try {
            List<CommentResponse> commentLeft = commentService.deleteComment(authentication, comment_id);
            vec.addAll(commentLeft);
            apiResponse.setData(vec);
            apiResponse.setMessage("Comment deleted");
            apiResponse.setStatus("success");
            apiResponse.setCount(vec.size());
        } catch (Exception e) {
            apiResponse.setData(vec);
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus("fail");
            apiResponse.setCount(vec.size());
        }

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(path = "/comment/{comment_id}", produces = "application/json")
    public ResponseEntity<?> getComment(
            Authentication authentication,
            @PathVariable("comment_id") int comment_id) {
        CommentResponse c;
        apiResponse = new APIResponse();
        Vector<CommentResponse> vec = new Vector<>();
        try {
            c = commentService.getCommentById(authentication, comment_id);
            vec.add(c);
            apiResponse.setData(vec);
            apiResponse.setMessage("Comment fetched");
            apiResponse.setStatus("success");
            apiResponse.setCount(vec.size());
        } catch (Exception e) {
            apiResponse.setData(vec);
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus("fail");
            apiResponse.setCount(vec.size());
        }

        return ResponseEntity.ok(apiResponse);
    }
}
