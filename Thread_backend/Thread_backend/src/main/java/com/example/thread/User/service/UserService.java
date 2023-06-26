package com.example.thread.User.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import com.example.thread.User.model.User;

public interface UserService {

    public List<User> getAllUsers(Authentication authentication);

    public void initRoleAndUser();

    public User createUser(User user) throws Exception;

    public User changePassword(User user) throws Exception;

    public User updateUser(User user, MultipartFile file) throws Exception;

}
