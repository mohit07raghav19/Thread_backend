package com.example.thread.Likes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.thread.Likes.model.Likes;
import com.example.thread.Likes.repo.LikeRepo;
import com.example.thread.Post.model.Post;
import com.example.thread.Post.model.PostResponse;
import com.example.thread.Post.repo.PostRepo;
import com.example.thread.User.model.User;
import com.example.thread.User.repo.UserRepo;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    private LikeRepo likeRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PostRepo postRepo;

    public void initLikes() {
        if (likeRepo.findAll().size() == 0) {
            User user0 = userRepo.findByUserName("Manish_Rawat");
            User user1 = userRepo.findByUserName("karthik_bannu");
            User user2 = userRepo.findByUserName("Slumber_420");
            User user3 = userRepo.findByUserName("money_heist");
            User user4 = userRepo.findByUserName("asur_official");
            User user5 = userRepo.findByUserName("marvel_official");
            User user7 = userRepo.findByUserName("Yash_lcs57");
            User user8 = userRepo.findByUserName("Mohit_lcs32");
            User user9 = userRepo.findByUserName("Manjeet_lcs29");
            User user10 = userRepo.findByUserName("Harshit_lcs21");
            User user11 = userRepo.findByUserName("Sunil_lcs48");
            User user12 = userRepo.findByUserName("Umesh_lcs52");
            User user14 = userRepo.findByUserName("axios_iiitl");

            Post post0 = postRepo.findByPostId("post0");
            Post post1 = postRepo.findByPostId("post1");
            Post post2 = postRepo.findByPostId("post2");
            Post post4 = postRepo.findByPostId("post4");
            Post post7 = postRepo.findByPostId("post7");
            Post post10 = postRepo.findByPostId("post10");
            Post post11 = postRepo.findByPostId("post11");
            // post0
            likeRepo.save(new Likes(user0, post0));

            likeRepo.save(new Likes(user1, post0));

            likeRepo.save(new Likes(user2, post0));

            likeRepo.save(new Likes(user3, post0));
            likeRepo.save(new Likes(user4, post0));
            likeRepo.save(new Likes(user5, post0));
            likeRepo.save(new Likes(user12, post0));
            likeRepo.save(new Likes(user7, post0));
            likeRepo.save(new Likes(user8, post0));
            likeRepo.save(new Likes(user9, post0));
            likeRepo.save(new Likes(user10, post0));
            likeRepo.save(new Likes(user11, post0));
            likeRepo.save(new Likes(user12, post0));

            // post1

            likeRepo.save(new Likes(user0, post1));
            likeRepo.save(new Likes(user10, post1));
            likeRepo.save(new Likes(user3, post1));

            // post2

            likeRepo.save(new Likes(user3, post2));
            likeRepo.save(new Likes(user14, post2));
            likeRepo.save(new Likes(user12, post2));
            likeRepo.save(new Likes(user4, post2));

            likeRepo.save(new Likes(user3, post4));

            likeRepo.save(new Likes(user3, post7));
            likeRepo.save(new Likes(user2, post10));

            likeRepo.save(new Likes(user1, post11));
            likeRepo.save(new Likes(user8, post11));
        }

    }

    @Override
    public boolean userLikesPost(Authentication authentication, String postId) throws Exception {
        User LoggedInUser = userRepo.findByUserName(authentication.getName());
        if (LoggedInUser == null) {
            throw new Exception("user not authorized");
        }
        Post post = postRepo.findByPostId(postId);
        if (post == null) {
            return false;
        }

        Likes l = likeRepo.isLikedByUser(LoggedInUser.getUserName(), postId);

        if (l != null) {
            System.out.println("user already liked the post.");
            return false;
        } else {
            Likes like = new Likes();
            like.setUserLiked(LoggedInUser);
            like.setPostLiked(post);
            likeRepo.save(like);
            return true;
        }

    }

    @Override
    public boolean UnLikesPost(Authentication authentication, String postId) throws Exception {
        User LoggedInUser = userRepo.findByUserName(authentication.getName());
        if (LoggedInUser == null) {
            throw new Exception("user not authorized");
        }
        Post post = postRepo.findByPostId(postId);
        if (post == null) {
            return false;
        }
        Likes l = likeRepo.isLikedByUser(LoggedInUser.getUserName(), postId);
        if (l == null) {
            return false;
        } else {
            likeRepo.UnLikedByUserOnPost(LoggedInUser.getUserName(), postId);
            return true;
        }

    }

    @Override
    public int showPostLikes(String postId) {
        return likeRepo.LikesOnPostByPostId(postId);
    }

    @Override
    public List<PostResponse> showUserLikedPosts(Authentication authentication) throws Exception {
        User LoggedInUser = userRepo.findByUserName(authentication.getName());
        if (LoggedInUser == null) {
            throw new Exception("user not authorized");
        }
        String userName = LoggedInUser.getUserName();
        List<Post> posts = postRepo.PostsLikedByUser(userName);
        if (posts == null) {
            return null;
        }
        List<PostResponse> pResp = new ArrayList<>();
        for (Post post : posts) {
            pResp.add(new PostResponse(post));
        }
        return pResp;
    }
}
