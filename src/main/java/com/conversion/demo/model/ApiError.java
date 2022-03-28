package com.conversion.demo.model;

public class ApiError {

    private String errorMessage;
    private String requestedURI;

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setRequestedURI(String requestedURI) {
        this.requestedURI = requestedURI;
    }
}
