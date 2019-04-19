package com.digital.dance.core.shiro.Interceptor;

import com.digital.dance.permission.service.PermissionService;
import com.digital.dance.core.shiro.service.impl.CacheInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RefreshPermissionInterceptor extends HandlerInterceptorAdapter {

    public static final String CONTEXT_PATH = "baseUri";

    @Autowired
    PermissionService permissionService;

    public List<String> restfulPaths;
    //private Log log = new Log(ControllerInterceptor.class);
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int port = request.getServerPort();

        String path = ((HttpServletRequest)request).getRequestURI();

        String basePath = scheme + "://" + serverName + ":" + port + path;

        super.afterCompletion(request, response, handler, ex);
        if(restfulPaths != null && restfulPaths.size() > 0){
            for ( String ite : restfulPaths ) {
                if (ite == null || "".equals( ite )) continue;
                String[] methodPath = ite.split("\\:");
                if(methodPath.length < 2)continue;
                if( !request.getMethod().equalsIgnoreCase( methodPath[0] ) )continue;
                String regex = methodPath[1].replace("/", "\\/").replace("**","(.*)?");
                Pattern pattern = Pattern.compile(regex);

                //否则用MatchPath去模式匹配uri
                Matcher m = pattern.matcher(basePath);
                if (m.find()) {
                    CacheInitializer.reflushRoleBranchResourceCache(permissionService);
                }

            }
        }

    }


    public List<String> getRestfulPaths() {
        return restfulPaths;
    }

    public void setRestfulPaths(List<String> restfulPaths) {
        this.restfulPaths = restfulPaths;
    }

}