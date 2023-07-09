package com.example.thread.TeamAboutUs.model;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int memberId;
    private String avatar;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String description;
    private String title;
    private String email;
    private String linkedin;
    private String twitter;
    private String github;

    public AboutUsTeam() {
        super();
    }

    public AboutUsTeam(String avatar, String name, String description, String title, String email,
            String linkedin, String twitter, String github) {
        this.avatar = avatar;
        this.name = name;
        this.description = description;
        this.title = title;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
