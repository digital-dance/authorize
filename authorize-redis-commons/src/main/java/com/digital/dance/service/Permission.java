package com.digital.dance.service;

import com.digital.dance.framework.sso.entity.LoginInfo;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Permission {

    public String[] prepareAllowSuffixs(String allowSuffixs);

    public boolean isPassedRequest(String[] passedPaths, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException;

    public boolean isPassedRequest(String[] passedPaths, Request request, Response response);

    public boolean isAccessAllowedBasedRole(String requestPath, String requestHttpMethod, LoginInfo loginInfo);

    public boolean isAccessAllowedBasedUser(String requestPath, String requestHttpMethod, LoginInfo loginInfo);

    public boolean isPermission(String requestPath, String requestHttpMethod, String resourceBosJsonObj);
}
