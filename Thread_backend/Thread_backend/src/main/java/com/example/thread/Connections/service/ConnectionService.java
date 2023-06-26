package com.example.thread.Connections.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.example.thread.User.model.UserResponse;

public interface ConnectionService {
    public boolean connectWithUser(Authentication authentication, String userName)throws Exception ;

    public boolean UnconnectWithUser(Authentication authentication, String userName)throws Exception ;

    public void initConnections();
    public List<UserResponse> getAllConnections(Authentication authentication)throws Exception ;

    public List<UserResponse> getAllNonConnections(Authentication authentication)throws Exception ;
}
