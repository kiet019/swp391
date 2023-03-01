package com.example.projectswp.data_view_model.blogcategory;

public class ReturnMessage {
    private String message;

    public static ReturnMessage create(String message) {
        ReturnMessage returnMessage = new ReturnMessage();
        returnMessage.setMessage(message);
        return returnMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
