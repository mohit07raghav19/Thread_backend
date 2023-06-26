package com.example.thread.Comments.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.example.thread.Comments.model.Comment;
import com.example.thread.Comments.model.CommentResponse;

public interface CommentService {
    public List<CommentResponse> userMakesNewCommentAtPost(Authentication authentication, String Post_id, Comment comment)
            throws Exception;

    public void initComment();
    public  List<CommentResponse> deleteComment(Authentication authentication, Integer comment_id) throws Exception;

    public CommentResponse getCommentById(Authentication authentication,int CommentId) throws Exception;

    public List<CommentResponse> showPostComments(Authentication authentication,String postId)throws Exception;
}
