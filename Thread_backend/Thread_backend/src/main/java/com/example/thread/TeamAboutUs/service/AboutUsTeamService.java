package com.example.thread.TeamAboutUs.service;

import java.util.List;

import com.example.thread.TeamAboutUs.model.AboutUsTeam;

public interface AboutUsTeamService {
   public List<AboutUsTeam> getTeam();

   public List<AboutUsTeam> addMember(AboutUsTeam member);

   public void initTeam();
}
