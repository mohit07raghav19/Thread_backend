package com.example.thread.ContactUs.controller;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.thread.ApiResponse.APIResponse;
import com.example.thread.ContactUs.model.ContactUsQuery;
import com.example.thread.ContactUs.repo.ContactUsQueryRepo;
import com.example.thread.ContactUs.service.ContactUsQueryService;

@RestController
@RequestMapping("/contactus")
public class ContactUsQueryController {
    @Autowired
    private ContactUsQueryService contactUsQueryService;
    @Autowired
    private ContactUsQueryRepo contactUsQueryRepo;
    private APIResponse apiResponse;

    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<?> createQuery(@RequestBody ContactUsQuery contactUsQuery) {
        apiResponse = new APIResponse();
        Vector<ContactUsQuery> vec = new Vector<>();

        if (contactUsQuery != null) {
            try {
                this.contactUsQueryService.createQuery(contactUsQuery);
                vec.add(contactUsQuery);
                apiResponse.setStatus("success");
                apiResponse.setCount(1);
                apiResponse.setMessage("Contact Us Query Added Successfully");
                apiResponse.setData(vec);
            } catch (Exception e) {
                apiResponse.setMessage(e.getMessage());
                apiResponse.setData(null);
                apiResponse.setStatus("fail");
                apiResponse.setCount(0);
            }
        } else {
            apiResponse.setStatus("fail");
            apiResponse.setCount(0);
            apiResponse.setMessage("Contact Us Query Could Not be Added");
            apiResponse.setData(null);
        }
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(value = "/setasanswered/{queryId}", produces = "application/json")
      @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> setAsAnswered(@PathVariable("queryId") Integer queryId) {
        apiResponse = new APIResponse();
        Vector<ContactUsQuery> vec = new Vector<>();
        ContactUsQuery contactUsQuery = this.contactUsQueryRepo.findByQueryId(queryId);
        if (contactUsQuery != null) {
            contactUsQuery.setAnswered(true);
            this.contactUsQueryRepo.save(contactUsQuery);
            vec.add(contactUsQuery);
            apiResponse.setStatus("success");
            apiResponse.setCount(1);
            apiResponse.setMessage("Contact Us Query Answered Successfully");
            apiResponse.setData(vec);
        } else {
            apiResponse.setStatus("fail");
            apiResponse.setCount(0);
            apiResponse.setMessage("Contact Us Query Could Not be Answered");
            apiResponse.setData(null);
        }
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/all", produces = "application/json")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> getAll() {
        apiResponse = new APIResponse();
        Vector<ContactUsQuery> vec = new Vector<>();

        List<ContactUsQuery> list = this.contactUsQueryService.getAll();
        if (list != null) {
            vec.addAll(list);
            apiResponse.setStatus("success");
            apiResponse.setCount(vec.size());
            apiResponse.setMessage("Contact Us Query Fetched Successfully");
            apiResponse.setData(vec);
        } else {
            apiResponse.setStatus("success");
            apiResponse.setCount(0);
            apiResponse.setMessage("No Contact Us Query");
            apiResponse.setData(null);
        }
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/answered", produces = "application/json")
       @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> getAnswered() {
        apiResponse = new APIResponse();
        Vector<ContactUsQuery> vec = new Vector<>();

        List<ContactUsQuery> list = this.contactUsQueryService.getAnswered();
        if (list != null) {
            vec.addAll(list);
            apiResponse.setStatus("success");
            apiResponse.setCount(vec.size());
            apiResponse.setMessage("Answered Contact Us Query Fetched Successfully");
            apiResponse.setData(vec);
        } else {
            apiResponse.setStatus("success");
            apiResponse.setCount(0);
            apiResponse.setMessage("No Answered Contact Us Query");
            apiResponse.setData(null);
        }
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/unanswered", produces = "application/json")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> getUnAnswered() {
        apiResponse = new APIResponse();
        Vector<ContactUsQuery> vec = new Vector<>();

        List<ContactUsQuery> list = this.contactUsQueryService.getUnanswered();
        if (list != null) {
            vec.addAll(list);
            apiResponse.setStatus("success");
            apiResponse.setCount(vec.size());
            apiResponse.setMessage("UnAnswered Contact Us Query Fetched Successfully");
            apiResponse.setData(vec);
        } else {
            apiResponse.setStatus("success");
            apiResponse.setCount(0);
            apiResponse.setMessage("No UnAnswered Contact Us Query");
            apiResponse.setData(null);
        }
        return ResponseEntity.ok(apiResponse);
    }

}
