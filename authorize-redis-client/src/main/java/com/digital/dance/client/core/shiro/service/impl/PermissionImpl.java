package com.digital.dance.client.core.shiro.service.impl;

import com.digital.dance.client.core.shiro.filter.PermissionFilter;
import com.digital.dance.client.core.shiro.filter.ShiroFilterUtils;
import com.digital.dance.common.utils.Constants;
import com.digital.dance.common.utils.GsonUtils;
import com.digital.dance.common.utils.ResponseVo;
import com.digital.dance.framework.infrastructure.commons.Log;
import com.digital.dance.framework.infrastructure.commons.StringTools;
import com.digital.dance.framework.sso.entity.LoginInfo;
import com.digital.dance.framework.sso.entity.LoginUserRole;
import com.digital.dance.framework.sso.filter.SSOLoginFilter;
import com.digital.dance.permission.bo.ResourceBo;
import com.digital.dance.service.Permission;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PermissionImpl implements Permission {

    Log log = new Log(PermissionImpl.class);

    public String[] prepareAllowSuffixs(String allowSuffixs) {
        return (StringUtils.isNotBlank(allowSuffixs)) ? (("(\\."+allowSuffixs +")$").replace(";", ")$;(\\.")).split(";") : new String[0];
    }

    public boolean isPassedRequest(String[] passedPaths, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        boolean flag = false;
        String requestPath = request.getRequestURL().toString().toLowerCase();
        //Matcher matcher = Pattern.matches(regex, input);
        for (String passedPath : passedPaths) {
            if(StringUtils.isBlank(passedPath)) continue;

            Pattern pattern = Pattern.compile( passedPath.toLowerCase().replace("/", "\\/").replace("**", "(.*)?") );

            Matcher matcher = pattern.matcher(requestPath);
            flag = matcher.find();
            if (flag) {
                log.info(
                        "sso client request path '" + requestPath + "'is matched,filter chain will be continued.");

                flag = true;
                break;
            }
        }
        return flag;
    }


    public boolean isAccessAllowedBasedRole(String requestPath, String requestHttpMethod, LoginInfo loginInfo) {
        boolean retValue = false;
        if( loginInfo != null ) {
            List<LoginUserRole> loginUserRoles = loginInfo.getUserRoles();

            if( loginUserRoles == null || loginUserRoles.size() < 1 )return false;
            for (LoginUserRole loginUserRole : loginUserRoles) {
                List<ResourceBo> resourceBos = PrivilegeCacheManager.listRoleBranchResourceByKey(loginUserRole.getRoleId()
                        , loginUserRole.getOrgId(), loginUserRole.getDepartmentId());
                retValue = isPermission( requestPath, requestHttpMethod, resourceBos );
                if(retValue) return true;
            }
        }
        return false;
    }

    public boolean isAccessAllowedBasedUser(String requestPath, String requestHttpMethod, LoginInfo loginInfo) {
        List<ResourceBo> resourceBos = PrivilegeCacheManager.listUserPrivilegeResourceByKey(loginInfo.getUserId());

        return isPermission( requestPath, requestHttpMethod, resourceBos );
    }

    public boolean isPermission(String requestPath, String requestHttpMethod, String resourceBosJsonObj) {
        ArrayList<ResourceBo> resourceBos = GsonUtils.toObject(resourceBosJsonObj, new ArrayList<ResourceBo>().getClass());
        return isPermission( requestPath, requestHttpMethod, resourceBos );
    }

    protected boolean isPermission(String requestPath, String requestHttpMethod, List<ResourceBo> resourceBos) {
        if(resourceBos == null || resourceBos.size() < 1){
            return false;
        }
        boolean retValue = false;
        for (ResourceBo resourceBo : resourceBos) {
            if ( resourceBo == null ) continue;
            if ( resourceBo.getUrl() != null && !"".equals( resourceBo.getUrl() ) ) {

                String resourceHttpMethod = StringTools.isEmpty(resourceBo.getHttpMethod()) ? "" : resourceBo.getHttpMethod().toLowerCase();
                String regex = resourceBo.getUrl().replace("/", "\\/").replace("**", "(.*)?");

                Pattern pattern = Pattern.compile(regex);

                Matcher m = pattern.matcher(requestPath);

                if (m.find() &&
                        (StringTools.isEmpty(resourceHttpMethod)
                                || resourceHttpMethod.indexOf(requestHttpMethod) > -1
                        )
                        ) {
                    retValue = true;
                    return retValue;
                }
            }
            if  ( "get".equals(requestHttpMethod) && resourceBo.getRoutingUrl() != null && !"".equals( resourceBo.getRoutingUrl() ) ) {
                String routingUrlRegex = resourceBo.getRoutingUrl().replace("/", "\\/").replace("**", "(.*)?");
                Pattern routingUrlPattern = Pattern.compile(routingUrlRegex);
                Matcher routingUrlM = routingUrlPattern.matcher(requestPath);
                if ( routingUrlM.find() ) {
                    retValue = true;
                    return retValue;
                }
            }
        }
        return retValue;
    }

}
