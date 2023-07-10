package com.example.thread.Post.service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

// import com.example.thread.Connections.repo.ConnectionRepo;
import com.example.thread.Post.model.Post;
import com.example.thread.Post.model.PostResponse;
import com.example.thread.Post.repo.PostRepo;
import com.example.thread.Roles.repo.RoleRepo;
import com.example.thread.User.model.User;
import com.example.thread.User.repo.UserRepo;

@Service
public class PostServiceImpl implements PostService {

    // Please Create a folder("MyImages") in public dir of React App.
    // And Add the Full Path here

    // public final static String FOLDER_PATH =
    // "E:\\webdproject\\Thread_backend\\Thread_frontend\\frontend\\public\\MyImages\\";
    public final static String FOLDER_PATH = "/Users/mohitraghav/Desktop/THREAD_MOHIT/Thread_repo/Thread_frontend/frontend/public/MyImages/";

    @Autowired
    private PostRepo postRepo;
    @Autowired
    UserRepo userRepo;

    // @Autowired
    // private ConnectionRepo conRepo;
    @Autowired
    RoleRepo roleRepo;

    public PostServiceImpl() {
        super();
    }

    public void initPosts() {
        if (postRepo.findAll().size() == 0) {
            User user0 = userRepo.findByUserName("Manish_Rawat");
            Post post0 = new Post("post0", user0,
                    "\u091C\u092F \u0936\u094D\u0930\u0940 \u0930\u093E\u092E\u0936\u094D\u0930\u0926\u094D\u0927\u093E, \u092C\u0932\u093F\u0926\u093E\u0928, \u092D\u093E\u0935 \u0914\u0930 \u092C\u0902\u0927\u0928 \u0907\u0928\u0915\u093E \u0906\u0926\u0930 \u0930\u093E\u092E \u092D\u0915\u094D\u0924 \u0915\u0930 \u092A\u093E\u0924\u0947 \u0939\u0948 ,\n"
                            + //
                            "\n" + //
                            "\u0935\u0930\u0928\u093E \u0906\u091C\u0915\u0932 \u0915\u0947 \u092A\u095D\u0947 \u0932\u093F\u0916\u0947 \u0930\u093E\u092E \u0930\u093E\u092E \u092C\u094B\u0932\u0928\u0947 \u092E\u0947\u0902 \u0936\u0930\u092E\u093E\u0924\u0947 \u0939\u0948\u0964\u0964\n"
                            + //
                            "\n" + //
                            "\u091C\u092F \u0936\u094D\u0930\u0940 \u0930\u093E\u092E",
                    "post0.jpg");
            post0.setCreationTime(LocalDateTime.of(2023, 07, 9, 01, 00, 00));

            User user1 = userRepo.findByUserName("karthik_bannu");
            Post post1 = new Post("post1", user1,
                    "Squid Games \n\n Hundreds of cash-strapped contestants accept an invitation to compete in children's games for a tempting prize, but the stakes are deadly.",
                    "post1.jpg");
            post1.setCreationTime(LocalDateTime.of(2023, 07, 8, 12, 59, 57));

            User user2 = userRepo.findByUserName("Slumber_420");
            Post post2 = new Post("post2", user2,
                    "Yes, I am a criminal. My crime is that of curiosity. My crime is that of outsmarting you, something that you will never forgive me for.\n I am a hacker, and this is my manifesto. You may stop this individual, but you can't stop us all… after all, we're all alike.",
                    "post2.jpeg");

            post2.setCreationTime(LocalDateTime.of(2023, 06, 20, 00, 00, 01));

            User user3 = userRepo.findByUserName("money_heist");
            Post post3 = new Post("post3", user3,
                    "First time is always so special, unique. But the last time is beyond comparison. It is priceless. But people don't normally know it is their last time.\n\nIn this world, everything is governed by balance. There's what you stand to gain and what you stand to lose. And when you think you've got nothing to lose, you become overconfident.",
                    "post3.jpg");
            post3.setCreationTime(LocalDateTime.of(2023, 07, 8, 23, 59, 57));

            User user4 = userRepo.findByUserName("asur_official");
            Post post4 = new Post("post4", user4,
                    "Limitless Darkness Is The Ultimate Truth, Attachment Is Suffering,Kindness Is Cruelty And The End Itself Is The Beginning.",
                    "post4.jpg");
            post4.setCreationTime(LocalDateTime.of(2023, 05, 20, 12, 12, 12));

            User user5 = userRepo.findByUserName("marvel_official");
            Post post5 = new Post("post5", user5,
                    "At Some Point, We All Have to Choose, Between What the World Wants You to Be, and Who You Are.",
                    "post5.png");
            post5.setCreationTime(LocalDateTime.of(2021, 01, 01, 01, 00, 00));

            User user6 = userRepo.findByUserName("Mohit_lcs32");
            Post post6 = new Post("post6", user6,
                    "Welcome All,Here is our Project Thread, a social media platform. ",
                    "logo.jpg");
            post6.setCreationTime(LocalDateTime.now());

            User user7 = userRepo.findByUserName("iiitl_official");
            Post post7 = new Post("post7", user7,
                    "IIIT Lucknow invites you to our Family.",
                    "postIIITL.jpg");
            post7.setCreationTime(LocalDateTime.of(2017, 01, 01, 01, 00, 00));
            User user8 = userRepo.findByUserName("axios_iiitl");
            Post post8 = new Post("post8", user8,
                    "We Axios , Technical Society of IIITL will always be ready to help you . Hoping to see you soon.",
                    "post_axios.jpg");
            post8.setCreationTime(LocalDateTime.of(2019, 01, 01, 01, 00, 00));

            User user9 = userRepo.findByUserName("Harshit_lcs21");
            Post post9 = new Post("post9", user9,
                    "This is only Text Post. ",
                    null);
            post9.setCreationTime(LocalDateTime.of(2023, 03, 01, 01, 00, 00));

            User user10 = userRepo.findByUserName("Umesh_lcs52");
            Post post10 = new Post("post10", user10,
                    "This is only Text Post. How are you guys?  ",
                    null);
            post10.setCreationTime(LocalDateTime.of(2022, 05, 01, 01, 00, 00));

            User user11 = userRepo.findByUserName("Sunil_lcs48");
            Post post11 = new Post("post11", user11,
                    "Carry out a random act of kindness, with no expectation of reward, safe in the knowledge that one day someone might do the same for you.",
                    null);
            post11.setCreationTime(LocalDateTime.of(2023, 06, 19, 01, 00, 00));
            Post post12 = new Post("post12", user9,
                    "\u0926\u094B\u0938\u094D\u0924 \u0939\u0948 \u092E\u0947\u0930\u093E \u092C\u0939\u093E\u0930\u094B\u0902 \u091C\u0948\u0938\u093E, \u0926\u093F\u0932 \u0939\u0948 \u0909\u0938\u0915\u093E \u0926\u093F\u0932\u0926\u093E\u0930\u094B\u0902 \u091C\u0948\u0938\u093E, \u092C\u0939\u094B\u0924 \u0926\u094B\u0938\u094D\u0924 \u0928\u0939\u0940 \u0930\u0916\u0924\u0947 \u0939\u092E \u092E\u0917\u0930, \u092E\u0947\u0930\u093E \u090F\u0915 \u0939\u0940 \u0926\u094B\u0938\u094D\u0924 \u0939\u0948 \u0939\u091C\u093E\u0930\u094B\u0902 \u091C\u0948\u0938\u093E\u0964",
                    "15.jpg");
            post12.setCreationTime(LocalDateTime.of(2022, 12, 02, 12, 00, 00));
            Post post13 = new Post("post13", user9,
                    null,
                    "3.jpg");
            post13.setCreationTime(LocalDateTime.of(2023, 03, 02, 12, 00, 00));

            postRepo.save(post0);
            postRepo.save(post1);
            postRepo.save(post2);
            postRepo.save(post3);
            postRepo.save(post4);
            postRepo.save(post5);
            postRepo.save(post6);
            postRepo.save(post7);
            postRepo.save(post8);
            postRepo.save(post9);
            postRepo.save(post10);
            postRepo.save(post11);
            postRepo.save(post12);
            postRepo.save(post13);
        }

    }

    @Override
    public List<PostResponse> getAllPostsFeed(Authentication authentication) throws Exception {
        User LoggedInUser = userRepo.findByUserName(authentication.getName());
        if (LoggedInUser == null) {
            throw new Exception("user not authorized");
        }
        // ! Get All Posts except Logged in User
        // ! --> Connections post
        // * Also left --> Not to include Liked posts

        List<Post> posts = this.postRepo.findPostOfConnections(LoggedInUser.getUserName());

        List<PostResponse> postResponse = new ArrayList<PostResponse>();
        for (Post post : posts) {
            postResponse.add(new PostResponse(post));
        }
        return postResponse;
    }

    @Override
    public List<String> getAllPosts(Authentication authentication) throws Exception {
        User LoggedInUser = userRepo.findByUserName(authentication.getName());
        if (LoggedInUser == null) {
            throw new Exception("user not authorized");
        }
        // Pageable pageable = PageRequest.of(0, 1);
        List<String> posts = this.postRepo.findAllPostsAdmin1();

        // Page<Post> posts = this.postRepo.findAll(pageable);
        // List<Post> allRoles = posts.getContent();

        // List<PostResponse> postResponse = new ArrayList<PostResponse>();
        // for (Post post : posts) {
        // post.getUser().setLiked(null);
        // post.getUser().setConnector(null);
        // postResponse.add(new PostResponse(post));
        // }
        return posts;
    }

    @Override
    public List<PostResponse> getAllPostsByUser(Authentication authentication, String userName) throws Exception {
        User LoggedInUser = userRepo.findByUserName(authentication.getName());
        if (LoggedInUser == null) {
            throw new Exception("user not authorized");
        }
        List<Post> posts = this.postRepo.findByUser(userName);

        List<PostResponse> postResponse = new ArrayList<PostResponse>();
        for (Post post : posts) {
            postResponse.add(new PostResponse(post));
        }
        return postResponse;
    }

    @Override
    public Post getPostById(Authentication authentication, String postId) throws Exception {
        User LoggedInUser = userRepo.findByUserName(authentication.getName());
        if (LoggedInUser == null) {
            throw new Exception("user not authorized");
        }
        // Post post = this.postRepo.findByPostId(postId);
        Post post = this.postRepo.findByPostId(postId);

        if ((LoggedInUser.getUserName().equals(post.getUser().getUserName())
                || LoggedInUser.getRole().contains(roleRepo.findByRoleName("Admin"))))
            return post;
        else
            return null;
    }

    @Override
    public boolean deletePostById(Authentication authentication, String postId) throws Exception {
        User LoggedInUser = userRepo.findByUserName(authentication.getName());
        if (LoggedInUser == null) {
            throw new Exception("user not authorized");
        }
        Post post = this.postRepo.findByPostId(postId);
        if (post == null) {
            return false;
        }
        if (LoggedInUser.getUserName().equals(post.getUser().getUserName())
                || LoggedInUser.getRole().contains(roleRepo.findByRoleName("Admin"))) {
            this.postRepo.delete(post);
            return true;
        }
        return false;
    }

    @Override
    public List<PostResponse> addPost(Authentication authentication, Post post, MultipartFile file) throws Exception {
        User LoggedInUser = userRepo.findByUserName(authentication.getName());
        if (LoggedInUser == null) {
            throw new Exception("user not authorized");
        }
        String image = null;
        if (file != null) {
            image = file.getOriginalFilename();
        }
        String desc = post.getDescription();

        if (desc == null)
            post.setDescription(null);
        if (desc == null && (image == null))
            throw new Exception("image and desc both can't be null");
        if (image != null) {
            String fileNameWithoutExtension = image.substring(0, image.lastIndexOf('.'));
            String fileExtension = image.substring(image.lastIndexOf("."));
            String postImage = fileNameWithoutExtension + "___" + PostServiceImpl.getAlphaNumericString(5)
                    + fileExtension;
            // * save file to folder
            file.transferTo(new File(PostServiceImpl.FOLDER_PATH + postImage));
            post.setPostImage(postImage);
        }
        post.setUser(LoggedInUser);

        LocalDateTime creationTime = LocalDateTime.now();
        post.setCreationTime(creationTime);

        post.setPostId(post.getUser().getUserName() + "___" + PostServiceImpl.getAlphaNumericString(5));
        this.postRepo.save(post);

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

        return postResponse;

    }

    // can be synchronized
    public static String getAlphaNumericString(int n) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index = (int) (AlphaNumericString.length()
                    * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }
}