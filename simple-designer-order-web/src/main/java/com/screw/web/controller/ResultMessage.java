package com.screw.web.controller;

import java.io.Serializable;

public class ResultMessage implements Serializable {
    private Boolean success;
    private String message = "";
    private Object data;

    private ResultMessage() {
    }

    public static ResultMessage success() {
        ResultMessage message = new ResultMessage();
        message.setSuccess(true);
        return message;
    }

    public static ResultMessage success(Object data) {
        ResultMessage message = new ResultMessage();
        message.setSuccess(true);
        message.setMessage("Successfully.");
        message.setData(data);
        return message;
    }

    public static ResultMessage failed(String messageText) {
        ResultMessage message = new ResultMessage();
        message.setSuccess(false);
        message.setMessage(messageText);
        return message;
    }

    public static ResultMessage failed(String messageText, Object data) {
        ResultMessage message = new ResultMessage();
        message.setSuccess(false);
        message.setMessage(messageText);
        message.setData(data);
        return message;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    void setMessage(String message) {
        this.message = message;
    }

    void setData(Object data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public String getMessage() {
        return this.message;
    }

    public Object getData() {
        return this.data;
    }
}