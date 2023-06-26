package com.example.thread.Likes.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.thread.Likes.model.Likes;

public interface LikeRepo extends JpaRepository<Likes, Integer> {
        @Query(value = "SELECT Count(*) "
                        + "FROM likes "
                        + "WHERE post_id IN"
                        + "("
                        + "SELECT post_id FROM post WHERE post_id like  :postId "
                        + ") ", nativeQuery = true)
        int LikesOnPostByPostId(@Param("postId") String postId);

        @Query(value = "SELECT * "
                        + "FROM likes "
                        + "WHERE post_id = "
                        + " ?2 and user_name =?1 ", nativeQuery = true)
        Likes isLikedByUser(@Param("userName") String userName, @Param("postId") String postId);

        @Query(value = "insert into likes(post_id,user_name) values(?1,?2)", nativeQuery = true)
        void LikedByUserOnPost(String postId, String userName);

        @Modifying
        @Transactional
        @Query(value = "delete FROM likes "
                        + "WHERE post_id like "
                        + " :postId and user_name like :userName", nativeQuery = true)
        void UnLikedByUserOnPost(@Param("userName") String userName, @Param("postId") String postId);

}
