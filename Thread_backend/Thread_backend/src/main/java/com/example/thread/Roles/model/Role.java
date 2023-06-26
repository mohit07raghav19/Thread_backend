package com.example.thread.Roles.model;

import javax.persistence.*;

@Entity
public class Role {

    @Id
    private String roleName;
    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String roleDescription;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }
}
