package com.abulkay.errorhandling;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

/**
 * Created by akay on ${MONT} 2017.
 */
class ApiErrorDto implements Serializable {

    // contains the same HTTP Status code returned by the server
    int status;

    // message describing the error
    String message;

    // extra information that might useful for developers
    String developerMessage;

    public ApiErrorDto() {
    }

    public ApiErrorDto(int status, Exception ex) {
        StringWriter errorStackTrace = new StringWriter();
        ex.printStackTrace(new PrintWriter(errorStackTrace));

        this.developerMessage = errorStackTrace.toString();
        this.status = status;
        this.message = ex.getMessage();
    }

    public ApiErrorDto(String message) {
        this.status = BAD_REQUEST.getStatusCode();
        this.message = message;
        this.developerMessage = message;
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

    public String getDeveloperMessage() {
        return this.developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }
}
