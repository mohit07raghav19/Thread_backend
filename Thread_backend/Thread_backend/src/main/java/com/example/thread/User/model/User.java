package com.example.thread.User.model;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.example.thread.Connections.model.Connections;
import com.example.thread.Likes.model.Likes;
import com.example.thread.Roles.model.Role;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(nullable = false, updatable = false)
    private String userName;
    @Column(nullable = false, updatable = true)
    private String userFullName;
    @Column(nullable = false, updatable = true)
    private String userPassword;
    @Column(nullable = false, updatable = false)
    private String email;
    @Column(nullable = true, updatable = true)
    private String city;
    @Column(nullable = true, updatable = true)
    private String gender;
    @Column(nullable = true, updatable = true)
    private String securityq;
    @Column(nullable = true, updatable = true)
    private String DOB;
    @Column(nullable = true, updatable = true)
    private String profileImage = "defaultProfile.jpg";

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE", joinColumns = {
            @JoinColumn(name = "USER_ID")
    }, inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID")
    })
    @Column(nullable = false, updatable = true)
    private Set<Role> role;

    @OneToMany(mappedBy = "connector")
    @Column(nullable = true, updatable = true)
    private List<Connections> connector;

    @OneToMany(mappedBy = "userLiked", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Likes> liked;

    public User() {
        super();
    }

    public User(String userName, String userFullName, String userPassword, String email, String city, String gender,
            String dOB,
            String profileImage, Set<Role> role) {
        this.userName = userName;
        this.userFullName = userFullName;
        this.userPassword = userPassword;
        this.email = email;
        this.city = city;
        this.gender = gender;
        this.DOB = dOB;
        this.profileImage = profileImage;
        this.role = role;
    }

    public User(String userName, String userFullName, String userPassword, String email, String city, String gender,
            String dOB,
            String profileImage) {
        this.userName = userName;
        this.userFullName = userFullName;
        this.userPassword = userPassword;
        this.email = email;
        this.city = city;
        this.gender = gender;
        this.DOB = dOB;
        this.profileImage = profileImage;
    }

    // public User(String userName, String userFullName, String userPassword, String email) {
    //     this.userName = userName;
    //     this.userFullName = userFullName;
    //     this.userPassword = userPassword;
    //     this.email = email;
    // }

    public User(String userName, String userFullName, String userPassword, String email, String securityq) {
        this.userName = userName;
        this.userFullName = userFullName;
        this.userPassword = userPassword;
        this.email = email;
        this.securityq = securityq;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String dOB) {
        this.DOB = dOB;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public List<Connections> getConnector() {
        return connector;
    }

    public void setConnector(List<Connections> connector) {
        this.connector = connector;
    }

    public String getSecurityq() {
        return securityq;
    }

    public void setSecurityq(String securityq) {
        this.securityq = securityq;
    }

    public List<Likes> getLiked() {
        return liked;
    }

    public void setLiked(List<Likes> liked) {
        this.liked = liked;
    }

}
