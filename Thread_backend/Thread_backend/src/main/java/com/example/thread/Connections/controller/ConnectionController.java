package com.example.thread.Connections.controller;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.thread.ApiResponse.APIResponse;
import com.example.thread.Connections.service.ConnectionService;
import com.example.thread.User.model.UserResponse;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/")
public class ConnectionController {

    private APIResponse apiResponse;
    @Autowired
    private ConnectionService connectionService;

    @PostMapping(path = "follow/user/{userName}", produces = "application/json")
    public ResponseEntity<?> addConnection(
            Authentication authentication,
            @PathVariable("userName") String userName) {
        apiResponse = new APIResponse();
        Vector<UserResponse> vec = new Vector<>();
        try {
            if (connectionService.connectWithUser(authentication, userName)) {
                List<UserResponse> l = connectionService.getAllConnections(authentication);
                vec.addAll(l);
                apiResponse.setStatus("success");
                apiResponse.setMessage("Connection Added!");
                apiResponse.setData(vec);
                apiResponse.setCount(vec.size());
            } else {
                List<UserResponse> l = connectionService.getAllConnections(authentication);
                vec.addAll(l);
                apiResponse.setStatus("fail");
                apiResponse.setMessage("Connection Not Added ");
                apiResponse.setData(vec);
                apiResponse.setCount(vec.size());

            }

        } catch (Exception e) {
            apiResponse.setStatus("fail");
            apiResponse.setMessage(e.getMessage());
            apiResponse.setData(null);
            apiResponse.setCount(0);

        }

        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping(path = "unfollow/user/{userName}", produces = "application/json")
    public ResponseEntity<?> deleteConnection(
            Authentication authentication,
            @PathVariable("userName") String userName) {
        apiResponse = new APIResponse();
        Vector<UserResponse> vec = new Vector<>();
        try {
            if (connectionService.UnconnectWithUser(authentication, userName)) {
                List<UserResponse> l = connectionService.getAllConnections(authentication);
                vec.addAll(l);
                apiResponse.setMessage("Connection Removed!");
                apiResponse.setStatus("success");

                apiResponse.setData(vec);
                apiResponse.setCount(vec.size());
            } else {
                List<UserResponse> l = connectionService.getAllConnections(authentication);
                vec.addAll(l);
                apiResponse.setStatus("fail");
                apiResponse.setMessage("Error - Connection Already Removed or user not authenticated.");
                apiResponse.setData(vec);
                apiResponse.setCount(vec.size());

            }
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus("fail");
            apiResponse.setData(null);
            apiResponse.setCount(0);
        }

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/connections", produces = "application/json")
    public ResponseEntity<?> getConnections(Authentication authentication) {
        apiResponse = new APIResponse();
        List<UserResponse> c;
        try {
            c = this.connectionService.getAllConnections(authentication);
            if (c != null) {
                Vector<UserResponse> vec = new Vector<>();
                vec.addAll(c);
                apiResponse.setStatus("success");
                apiResponse.setMessage("Fetched All Connections");
                apiResponse.setCount(vec.size());
                apiResponse.setData(vec);
            } else {
                apiResponse.setStatus("success");
                apiResponse.setMessage("No Connections");
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

    @GetMapping(value = "/nonconnections", produces = "application/json")
    public ResponseEntity<?> getNonConnections(Authentication authentication) {
        apiResponse = new APIResponse();
        List<UserResponse> c;
        try {
            c = this.connectionService.getAllNonConnections(authentication);
            if (c != null) {
                Vector<UserResponse> vec = new Vector<>();
                vec.addAll(c);
                apiResponse.setStatus("success");
                apiResponse.setMessage("Fetched All NonConnections");
                apiResponse.setCount(vec.size());
                apiResponse.setData(vec);
            } else {
                apiResponse.setStatus("success");
                apiResponse.setMessage("No NonConnections");
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

}
