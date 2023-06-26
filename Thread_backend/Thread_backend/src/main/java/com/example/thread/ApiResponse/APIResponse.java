package com.example.thread.ApiResponse;

import java.util.Vector;

public class APIResponse {
    private String status = "fail";
    private String message = "Something went wrong";
    private Vector<?> data;
    private int count = 0;

    public APIResponse() {
        super();
    }

    public APIResponse(String status, String message, Vector<?> data, int count) {
        // super();
        this.status = status;
        this.message = message;
        this.data = data;
        this.count = count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Vector<?> getData() {
        return data;
    }

    public void setData(Vector<?> data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
