package com.example.backendapi;

import java.util.List;

public class CustomizedResponse<T>
{
    private String message;
    private List<T> body;

    public CustomizedResponse()
    {

    }

    public CustomizedResponse(String message, List<T> body)
    {
        this.message = message;
        this.body = body;
    }

    //Getters and Setters

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getBody() {
        return body;
    }

    public void setBody(List<T> body) {
        this.body = body;
    }
}
