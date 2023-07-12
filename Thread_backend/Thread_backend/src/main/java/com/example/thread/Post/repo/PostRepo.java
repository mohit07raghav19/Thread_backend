package com.example.thread.Post.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.thread.Post.model.Post;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, String> {
        Post findByPostId(String postId);

        @Query(value = "select * from post where post_id like :post_id% ", nativeQuery = true)
        List<Post> findListByPostId(@Param("post_id") String postId);

        @Query(value = "select * from post where user_user_name like %:userName% "
                        + "ORDER BY creation_time DESC", nativeQuery = true)
        List<Post> findByUser(@Param("userName") String userName);

        @Query(value = "select * from post where user_user_name in "
                        + "(select c.connection_to from connections c where c.connector like :loggedUserName )"
                        + "ORDER BY creation_time DESC", nativeQuery = true)
        List<Post> findPostOfConnections(@Param("loggedUserName") String loggedUserName);

        @Query(value = "select post_id from post ", nativeQuery = true)
        List<String> findAllPostsAdmin1();

        @Query(value = "SELECT * "
                        + "FROM post "
                        + "WHERE post_id IN"
                        + "("
                        + "SELECT post_id FROM likes WHERE  user_name like :userName "
                        + ") "
                        + "ORDER BY creation_time DESC", nativeQuery = true)
        List<Post> PostsLikedByUser(@Param("userName") String userName);

}
