package com.example.thread.Roles.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.thread.Roles.model.Role;

public interface RoleRepo extends JpaRepository<Role, String> {
    @Query(value = "select * from role", nativeQuery = true)
    public List<Role> getRoles();

    public Role findByRoleName(String roleName);
}
