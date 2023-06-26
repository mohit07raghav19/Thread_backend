package com.example.thread.ContactUs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contact_us_query")
public class ContactUsQuery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int queryId;
    @Column(updatable = false, nullable = false)
    private String name;
    @Column(updatable = false, nullable = false)
    private String email;
    @Column(updatable = false, nullable = false)
    private String subject;
    @Column(columnDefinition = "LONGTEXT", updatable = false, nullable = false)
    private String message;
    @Column(columnDefinition = "BOOLEAN")
    private boolean isAnswered = false;

    public ContactUsQuery() {
        super();
    }

    public ContactUsQuery(String name, String email, String subject, String message, boolean isAnswered) {
        super();
        this.name = name;
        this.email = email;
        this.subject = subject;
        this.message = message;
        this.isAnswered = isAnswered;
    }

    public ContactUsQuery(int queryId, String name, String email, String subject, String message) {
        super();
        this.queryId = queryId;
        this.name = name;
        this.email = email;
        this.subject = subject;
        this.message = message;

    }

    public int getQueryId() {
        return queryId;
    }

    public void setQueryId(int queryId) {
        this.queryId = queryId;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean isAnswered) {
        this.isAnswered = isAnswered;
    }

}
