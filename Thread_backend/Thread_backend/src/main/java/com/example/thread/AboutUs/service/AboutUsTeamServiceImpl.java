package com.example.thread.AboutUs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.thread.ContactUs.model.ContactUsQuery;
import com.example.thread.ContactUs.repo.ContactUsQueryRepo;

@Service
public class AboutUsTeamServiceImpl implements AboutUsTeamService {

    @Autowired
    private ContactUsQueryRepo contactUsQueryRepo;

    public void initQuery() {
        if (contactUsQueryRepo.findAll().size() == 0) {
            ContactUsQuery c1 = new ContactUsQuery("Nimesh", "nimesh@gmail.com", "Login Problem",
                    "I am facing problem with Login. Please Help.", false);
            ContactUsQuery c2 = new ContactUsQuery("Krishna Sumit", "krishna@gmail.com",
                    "Just kidding", "Checking your ContactUs Query Page And Apis.", true);
            ContactUsQuery c3 = new ContactUsQuery("Random", "random@gmail.com",
                    "Found a bug in your code",
                    "I have hacked your website.Pay me Rupees 6969 . Then I will tell You..Offer Valid Till Tommorrow Only.",
                    false);
            contactUsQueryRepo.save(c1);
            contactUsQueryRepo.save(c2);
            contactUsQueryRepo.save(c3);
        }

    }

    @Override
    public ContactUsQuery createQuery(ContactUsQuery contactUsQuery) throws Exception {
        String name = contactUsQuery.getName();
        String email = contactUsQuery.getEmail();
        String subject = contactUsQuery.getSubject();
        String message = contactUsQuery.getMessage();
        boolean nameIsNotValid = (name == null) ||
                !name.matches("^[a-zA-Z]+(([' ][a-zA-Z ])?[a-zA-Z]*)*$");
        boolean EmailIsNotValid = (email == null) ||
                !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        boolean subjectIsNotValid = (subject == null);
        boolean messageIsNotValid = (message == null);
        if (nameIsNotValid)
            throw new Exception("Name is not Valid");
        if (EmailIsNotValid)
            throw new Exception("Email is not Valid");
        if (subjectIsNotValid)
            throw new Exception("Subject is not Valid");
        if (messageIsNotValid)
            throw new Exception("Message is not Valid");

        return this.contactUsQueryRepo.save(contactUsQuery);
    }

    @Override
    public List<ContactUsQuery> getUnanswered() {
        return this.contactUsQueryRepo.findUnansweredQuery();
    }

    @Override
    public List<ContactUsQuery> getAll() {
        return this.contactUsQueryRepo.findAll();

    }

    @Override
    public List<ContactUsQuery> getAnswered() {
        return this.contactUsQueryRepo.findAnsweredQuery();

    }

}
