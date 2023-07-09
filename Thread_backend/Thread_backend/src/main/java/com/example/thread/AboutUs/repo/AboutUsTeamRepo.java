package com.example.thread.AboutUs.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.thread.AboutUs.model.AboutUsTeam;

public interface AboutUsTeamRepo extends JpaRepository<AboutUsTeam, Integer> {
    
    @Query(value = "select * from about_us_team", nativeQuery = true)
    public List<AboutUsTeam> findAll(); 

}
