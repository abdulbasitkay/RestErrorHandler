package com.abulkay.errorhandling;

import java.io.Serializable;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

/**
 * Created by AbdulBasit.
 */
public class ApiException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * contains redundantly the HTTP status of the response sent back to the
     * client in case of error, so that the developer does
     * not have to look into the response headers.
     */
    private int status;

    // detailed error description for developers
    private String developerMessage;

    public ApiException() {
    }

    public ApiException(int status, String message, String developerMessage) {
        super(message);
        this.status = status;
        this.developerMessage = developerMessage;
    }

    public ApiException(String message) {
        super(message);
        this.developerMessage = message;
        this.status = BAD_REQUEST.getStatusCode();
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDeveloperMessage() {
        return this.developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }
}
