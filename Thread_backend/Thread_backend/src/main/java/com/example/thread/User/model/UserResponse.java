package com.example.thread.User.model;

public class UserResponse {
    private String userName;
    private String userFullName;
    private String profileImage = "defaultProfile.jpg";

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public UserResponse() {
        super();
    }

    public UserResponse(User user) {
        this.userName = user.getUserName();
        this.userFullName = user.getUserFullName();
        this.profileImage = user.getProfileImage();
    }
}
