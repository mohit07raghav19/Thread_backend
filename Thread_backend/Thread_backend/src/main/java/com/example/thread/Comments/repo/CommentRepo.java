package com.example.thread.Comments.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.thread.Comments.model.Comment;
import com.example.thread.Post.model.Post;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
    @Query(value = "SELECT * "
            + "FROM comment "
            + "WHERE post_on_post_id IN"
            + "("
            + "SELECT post_id FROM post WHERE post_id = ?1"
            + ") "
            + "ORDER BY creation_time DESC", nativeQuery = true)
    List<Comment> findByPostId(Post post);

    Comment findById(int commentId);
}
