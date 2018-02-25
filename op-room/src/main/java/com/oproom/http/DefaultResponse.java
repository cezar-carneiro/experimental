package com.oproom.http;

/**
 * Created by Cezar Carneiro on 22/1/2018.
 */

public class DefaultResponse {

    private Boolean status;
    private String message;

    public DefaultResponse() {
    }

    public DefaultResponse(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
