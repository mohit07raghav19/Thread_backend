package com.example.thread.Auth.controller;

import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.thread.ApiResponse.APIResponse;
import com.example.thread.Auth.model.JwtRequest;
import com.example.thread.Auth.model.JwtResponse;
import com.example.thread.Auth.service.JwtService;

@RestController
public class JwtController {

    @Autowired
    private JwtService jwtService;
    private APIResponse apiResponse;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createJwtToken(@RequestBody JwtRequest jwtRequest) {
        apiResponse = new APIResponse();
        try {
            JwtResponse res = jwtService.createJwtToken(jwtRequest);
            Vector<JwtResponse> vec = new Vector<>();
            vec.add(res);
            apiResponse.setMessage("Authenticated Successfully");
            apiResponse.setData(vec);
            apiResponse.setStatus("success");
            apiResponse.setCount(vec.size());
        } catch (Exception e) {
            e.printStackTrace();
            Vector<String> vec = new Vector<>();
            apiResponse.setMessage("Authenticated Error. Try Again With Correct Credentials");
            apiResponse.setData(vec);
            apiResponse.setStatus("fail");
            apiResponse.setCount(vec.size());
        }
        return ResponseEntity.ok(apiResponse);
    }
}
