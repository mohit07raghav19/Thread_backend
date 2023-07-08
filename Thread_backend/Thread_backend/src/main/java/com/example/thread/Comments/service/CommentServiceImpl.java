package com.example.thread.Comments.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.thread.Comments.model.Comment;
import com.example.thread.Comments.model.CommentResponse;
import com.example.thread.Comments.repo.CommentRepo;
import com.example.thread.Post.model.Post;
import com.example.thread.Post.repo.PostRepo;
import com.example.thread.User.model.User;
import com.example.thread.User.repo.UserRepo;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private CommentService commentService;

    @Override
    public void initComment() {

        if (commentRepo.findAll().size() == 0) {
            User user1 = userRepo.findByUserName("karthik_bannu");

            User user3 = userRepo.findByUserName("money_heist");
            User user4 = userRepo.findByUserName("asur_official");
            User user5 = userRepo.findByUserName("marvel_official");
            User user10 = userRepo.findByUserName("Umesh_lcs52");

            Post post0 = postRepo.findByPostId("post0");
            Post post2 = postRepo.findByPostId("post2");

            Post post5 = postRepo.findByPostId("post5");
            Post post10 = postRepo.findByPostId("post10");

            Comment c1 = new Comment("\u091C\u092F \u0936\u094D\u0930\u0940 \u0930\u093E\u092E", user1, post0, null);
            c1.setCreationTime(LocalDateTime.of(2023, 07, 9, 03, 00, 00));
            Comment c2 = new Comment("Jay Shree Raam", user10, post0, null);
            c2.setCreationTime(LocalDateTime.of(2023, 07, 9, 05, 00, 00));
            Comment c3 = new Comment("\u091C\u092F \u0936\u094D\u0930\u0940 \u0930\u093E\u092E", user3, post0, null);
            c3.setCreationTime(LocalDateTime.now());

            Comment c4 = new Comment("Well Bhai", user3, post10, null);
            c4.setCreationTime(LocalDateTime.now());
            Comment c5 = new Comment("Good Bro", user5, post10, null);
            c5.setCreationTime(LocalDateTime.now());

            Comment c6 = new Comment("Ya..well said", user10, post2, null);
            c6.setCreationTime(LocalDateTime.now());
            Comment c7 = new Comment("Shi kaha bhai", user4, post5, null);
            c7.setCreationTime(LocalDateTime.of(2023, 07, 9, 05, 00, 00));

            commentRepo.save(c1);
            commentRepo.save(c2);
            commentRepo.save(c3);
            commentRepo.save(c4);
            commentRepo.save(c5);
            commentRepo.save(c6);
            commentRepo.save(c7);
        }

    }

    @Override
    public List<CommentResponse> userMakesNewCommentAtPost(Authentication authentication, String Post_id,
            Comment comment)
            throws Exception {
        User LoggedInUser = userRepo.findByUserName(authentication.getName());
        if (LoggedInUser == null) {
            throw new Exception("user not authorized");
        }
        Post post = postRepo.findByPostId(Post_id);
        if (post == null) {
            throw new Exception("Post not found!");
        }
        if (comment == null) {
            throw new Exception("Comment not found!");
        }
        comment.setPostOn(post);
        comment.setCommentBy(LoggedInUser);
        comment.setCreationTime(LocalDateTime.now());
        comment = commentRepo.save(comment);
        List<CommentResponse> cResp = commentService.showPostComments(authentication,
                comment.getPostOn().getPostId());

        return cResp;
    }

    @Override
    public List<CommentResponse> deleteComment(Authentication authentication, Integer comment_id) throws Exception {
        User LoggedInUser = userRepo.findByUserName(authentication.getName());
        if (LoggedInUser == null) {
            throw new Exception("user not authorized");
        }
        Comment commentToDelete = commentRepo.findById(comment_id).orElse(null);
        if (commentToDelete == null) {
            throw new Exception("Comment not found");
        }
        if (commentToDelete.getCommentBy() != LoggedInUser) {
            throw new Exception("Not authorized to delete this comment");
        }

        commentRepo.delete(commentToDelete);
        List<CommentResponse> cResp = commentService.showPostComments(authentication,
                commentToDelete.getPostOn().getPostId());

        return cResp;
    }

    @Override
    public List<CommentResponse> showPostComments(Authentication authentication, String postId) throws Exception {
        User LoggedInUser = userRepo.findByUserName(authentication.getName());
        if (LoggedInUser == null) {
            throw new Exception("user not authorized");
        }
        Post commented_post = postRepo.findByPostId(postId);
        if (commented_post == null) {
            throw new Exception("Post not found!");
        }
        List<Comment> comments = commentRepo.findByPostId(commented_post);
        Vector<CommentResponse> vec = new Vector<>();
        for (Comment comment : comments) {
            vec.add(new CommentResponse(comment));
        }
        List<CommentResponse> cResp = new ArrayList<>();
        cResp.addAll(vec);
        return cResp;
    }

    @Override
    public CommentResponse getCommentById(Authentication authentication, int commentId) throws Exception {
        User LoggedInUser = userRepo.findByUserName(authentication.getName());
        if (LoggedInUser == null) {
            throw new Exception("user not authorized");
        }
        Comment c = commentRepo.findById(commentId);
        if (c == null) {
            throw new Exception("Comment not found");
        }
        if (c.getCommentBy() != LoggedInUser) {
            throw new Exception("Not authorized to fetch this comment");
        }
        CommentResponse commentedResponse = new CommentResponse(c);
        return commentedResponse;
    }

}
