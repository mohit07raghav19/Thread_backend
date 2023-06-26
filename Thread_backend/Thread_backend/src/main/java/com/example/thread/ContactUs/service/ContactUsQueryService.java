package com.example.thread.ContactUs.service;

import java.util.List;

import com.example.thread.ContactUs.model.ContactUsQuery;

public interface ContactUsQueryService {
    public ContactUsQuery createQuery(ContactUsQuery contactUsQuery) throws Exception;

    public void initQuery();

    public List<ContactUsQuery> getUnanswered();

    public List<ContactUsQuery> getAll();

    public List<ContactUsQuery> getAnswered();

}
