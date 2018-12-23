package com.digital.dance.permission.service;

import java.net.URISyntaxException;

public interface AuthorizeControlService {
    /**
     *
     * @param model org.springframework.ui.Model
     * @param request javax.servlet.http.HttpServletRequest
     * @return
     */
    public Object authorize(Object model, Object request);
}
