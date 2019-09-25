package com.digital.dance.service;

public class Request {
    String requestPath;
    String requestHttpMethod;

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    public String getRequestHttpMethod() {
        return requestHttpMethod;
    }

    public void setRequestHttpMethod(String requestHttpMethod) {
        this.requestHttpMethod = requestHttpMethod;
    }
}
