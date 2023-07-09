package com.example.thread.AboutUs.controller;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.thread.AboutUs.model.AboutUsTeam;
import com.example.thread.AboutUs.service.AboutUsTeamService;
import com.example.thread.ApiResponse.APIResponse;

@RestController
@RequestMapping("/aboutus")
public class AboutUsTeamController {
    @Autowired
    private AboutUsTeamService service;
    private APIResponse apiResponse;

    @PostMapping(value = "/addMember", produces = "application/json")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> addMember(@RequestBody AboutUsTeam mem) {
        apiResponse = new APIResponse();
        Vector<AboutUsTeam> vec = new Vector<>();

        if (mem != null) {
            try {
                List<AboutUsTeam> l = this.service.addMember(mem);
                vec.addAll(l);
                apiResponse.setStatus("success");
                apiResponse.setCount(1);
                apiResponse.setMessage("Team member Added Successfully");
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
            apiResponse.setMessage("Team member Could Not be Added");
            apiResponse.setData(null);
        }
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getTeam", produces = "application/json")
    public ResponseEntity<?> getTeam() {
        apiResponse = new APIResponse();
        Vector<AboutUsTeam> vec = new Vector<>();

        try {
            List<AboutUsTeam> l = this.service.getTeam();
            vec.addAll(l);
            apiResponse.setStatus("success");
            apiResponse.setCount(1);
            apiResponse.setMessage("Team member Fetched Successfully");
            apiResponse.setData(vec);
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setData(null);
            apiResponse.setStatus("fail");
            apiResponse.setCount(0);
        }

        return ResponseEntity.ok(apiResponse);
    }

}
