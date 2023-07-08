package com.example.thread.AboutUs.service;

import java.util.List;

import com.example.thread.AboutUs.model.AboutUsTeam;

public interface AboutUsTeamService {
   public List<AboutUsTeam> getTeam();
   public List<AboutUsTeam> addMember(AboutUsTeam member);

}
