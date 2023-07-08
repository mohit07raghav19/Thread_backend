package com.example.thread.AboutUs.service;

import java.util.List;

import com.example.thread.AboutUs.model.AboutUsTeam;
import com.example.thread.ContactUs.model.ContactUsQuery;

public interface AboutUsTeamService {
    public ContactUsQuery createQuery(AboutUsTeam contactUsQuery) throws Exception;

    // public void initQuery();

    // public List<ContactUsQuery> getUnanswered();

    // public List<ContactUsQuery> getAll();

    // public List<ContactUsQuery> getAnswered();

}
