package com.example.thread.AboutUs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "about_us_team")
public class AboutUsTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int memberId;

    private String avatar;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String desc;

    private String title;
    private String email;
    private String linkedin;
    private String twitter;
    private String github;

    public AboutUsTeam() {
        super();
    }

    public AboutUsTeam(int memberId, String avatar, String title, String desc, String name, String email,
            String linkedin, String twitter, String github) {
        this.memberId = memberId;
        this.avatar = avatar;
        this.title = title;
        this.desc = desc;
        this.name = name;
        this.email = email;
        this.linkedin = linkedin;
        this.twitter = twitter;
        this.github = github;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

}
