package com.example.thread.Roles.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.thread.Roles.model.Role;
import com.example.thread.Roles.repo.RoleRepo;

@Service
public class RoleService {

    @Autowired
    private RoleRepo roleRepo;

    public Role createNewRole(Role role) {
        return roleRepo.save(role);
    }

    public List<Role> getRoles() {
        return roleRepo.getRoles();
    }
}
