package com.example.thread.User.controller;

import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.thread.ApiResponse.APIResponse;
import com.example.thread.Comments.service.CommentService;
import com.example.thread.Connections.service.ConnectionService;
import com.example.thread.ContactUs.service.ContactUsQueryService;
import com.example.thread.Likes.service.LikeService;
import com.example.thread.Post.service.PostService;
import com.example.thread.TeamAboutUs.service.AboutUsTeamService;
import com.example.thread.User.model.User;
import com.example.thread.User.repo.UserRepo;
import com.example.thread.User.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.annotation.PostConstruct;

@RestController
public class UserController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private LikeService likeService;
    @Autowired
    private PostService postService;
    @Autowired
    private ContactUsQueryService contactUsQueryService;
    @Autowired
    private ConnectionService connectionService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private AboutUsTeamService aboutService;

    private APIResponse apiResponse;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
        postService.initPosts();
        connectionService.initConnections();
        likeService.initLikes();
        contactUsQueryService.initQuery();
        commentService.initComment();
        aboutService.initTeam();
    }

    @PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> register(@RequestBody User user) {
        User newUser;
        try {
            Vector<User> vec = new Vector<>();
            newUser = userService.createUser(user);
            vec.add(newUser);
            apiResponse = new APIResponse();
            apiResponse.setMessage("User created!");
            apiResponse.setStatus("success");
            apiResponse.setCount(1);
            apiResponse.setData(vec);
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setData(null);
            apiResponse.setStatus("fail");
            apiResponse.setCount(0);
        }

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(path = "/user/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateUser(Authentication authentication,
            @RequestParam("user") String user,
            @RequestParam("file") MultipartFile file) {
        User LoggedInUser = userRepo.findByUserName(authentication.getName());
        apiResponse = new APIResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        User updatedUser;

        try {
            User user2 = objectMapper.readValue(user, User.class);
            String userName = user2.getUserName();
            String email = user2.getEmail();
            if (email.equals(LoggedInUser.getEmail()) &&
                    userName.equals(LoggedInUser.getUserName())) {
                Vector<User> vec = new Vector<>();
                updatedUser = userService.updateUser(user2, file);

                vec.add(updatedUser);
                apiResponse.setMessage("User updated Successfully !");
                apiResponse.setStatus("success");
                apiResponse.setCount(1);
                apiResponse.setData(vec);
            } else {
                apiResponse.setMessage("Error !! Email and UserName Can't be changed. ");
                apiResponse.setStatus("fail");
                apiResponse.setCount(0);
                apiResponse.setData(null);
            }
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus("fail");
            apiResponse.setCount(0);
            apiResponse.setData(null);
        }

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/get/user/{email}/{securityq}", produces = "application/json")
    public ResponseEntity<?> getUserByDetails(@PathVariable("email") String email,
            @PathVariable("securityq") String securityq) {
        apiResponse = new APIResponse();
        User getUser;
        try {
            Vector<User> vec = new Vector<>();
            getUser = userRepo.findByUserDetails(email, securityq);
            if (getUser != null) {
                getUser.setConnector(null);
                vec.add(getUser);
                apiResponse.setMessage("User Exists with details !");
                apiResponse.setStatus("success");
                apiResponse.setCount(1);
                apiResponse.setData(vec);
            } else {
                apiResponse.setMessage("User Doesn't Exists with details !");
                apiResponse.setStatus("success");
                apiResponse.setCount(0);
                apiResponse.setData(null);
            }
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setData(null);
            apiResponse.setStatus("fail");
            apiResponse.setCount(0);
        }
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(path = "/changepassword", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> changePassword(@RequestBody User user) {
        User updatedUser;
        apiResponse = new APIResponse();
        try {
            Vector<User> vec = new Vector<>();
            updatedUser = userService.changePassword(user);
            updatedUser.setConnector(null);
            vec.add(updatedUser);
            apiResponse.setMessage("Password Changed!");
            apiResponse.setStatus("success");
            apiResponse.setCount(1);
            apiResponse.setData(vec);
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setData(null);
            apiResponse.setStatus("fail");
            apiResponse.setCount(0);
        }

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping({ "/forAdmin" })
    @PreAuthorize("hasAuthority('Admin')")
    public String forAdmin() {
        return "This URL is only accessible to the admin";
    }
}
