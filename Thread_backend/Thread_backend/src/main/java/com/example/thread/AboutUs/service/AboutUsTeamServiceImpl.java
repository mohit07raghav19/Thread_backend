package com.example.thread.AboutUs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.thread.AboutUs.model.AboutUsTeam;
import com.example.thread.AboutUs.repo.AboutUsTeamRepo;

@Service
public class AboutUsTeamServiceImpl implements AboutUsTeamService {

    @Autowired
    private AboutUsTeamRepo aboutRepo;

    @Override
    public List<AboutUsTeam> getTeam() {
        return this.aboutRepo.findAll();
    }

    @Override
    public List<AboutUsTeam> addMember(AboutUsTeam member) {
        this.aboutRepo.save(member);
        return this.aboutRepo.findAll();
    }
}