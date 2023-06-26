package com.example.thread.ContactUs.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.thread.ContactUs.model.ContactUsQuery;

public interface ContactUsQueryRepo extends JpaRepository<ContactUsQuery, Integer> {
    @Query(value = "select * from contact_us_query where query_id=?1", nativeQuery = true)
    public ContactUsQuery findByQueryId(Integer queryId);

    @Query(value = "select * from contact_us_query", nativeQuery = true)
    public List<ContactUsQuery> findAll();

    @Query(value = "select * from contact_us_query where is_answered IN (false,FALSE,0,NULL) ", nativeQuery = true)
    public List<ContactUsQuery> findUnansweredQuery();

    @Query(value = "select * from contact_us_query where is_answered IN (true,TRUE,1) ", nativeQuery = true)
    public List<ContactUsQuery> findAnsweredQuery();

}
