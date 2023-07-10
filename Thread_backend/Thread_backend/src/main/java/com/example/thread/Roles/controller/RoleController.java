package com.example.thread.Roles.controller;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.thread.ApiResponse.APIResponse;
import com.example.thread.Roles.model.Role;
import com.example.thread.Roles.service.RoleService;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;
    private APIResponse apiResponse;

    @PostMapping("/createNewRole")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> createNewRole(@RequestBody Role role) {
        Role newRole = roleService.createNewRole(role);
        Vector<Role> vec = new Vector<>();
        vec.add(newRole);
        apiResponse = new APIResponse();
        apiResponse.setMessage("New Role Created .");
        apiResponse.setData(vec);
        apiResponse.setStatus("success");
        apiResponse.setCount(vec.size());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getRoles")
    public ResponseEntity<?> getRoles() {
        List<Role> roles = roleService.getRoles();
        Vector<Role> vec = new Vector<>();
        vec.addAll(roles);
        apiResponse = new APIResponse();
        apiResponse.setMessage(" Role Fetched Successfully.");
        apiResponse.setData(vec);
        apiResponse.setStatus("success");
        apiResponse.setCount(vec.size());
        return ResponseEntity.ok(apiResponse);
    }
}
