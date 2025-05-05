package com.example.demo.student;

public class StudentApiResponse {

    private int status;
    private String message;
    private Object data;


    public StudentApiResponse(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }


    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
