package com.digital.dance.client.core.shiro.filter;

import com.digital.dance.client.core.shiro.service.impl.PermissionImpl;
import com.digital.dance.client.core.shiro.service.impl.PrivilegeCacheManager;
import com.digital.dance.common.utils.Constants;
import com.digital.dance.common.utils.GsonUtils;
import com.digital.dance.common.utils.ResponseVo;
import com.digital.dance.framework.infrastructure.commons.Log;
import com.digital.dance.framework.sso.entity.LoginInfo;
import com.digital.dance.framework.sso.entity.LoginUserRole;
import com.digital.dance.framework.sso.filter.SSOLoginFilter;
import com.digital.dance.framework.sso.util.SSOLoginManageHelper;
import com.digital.dance.permission.bo.ResourceBo;
import com.digital.dance.service.Permission;

import com.digital.dance.common.utils.SpringUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class PermissionHelper {
	Log log = new Log(PermissionHelper.class);

	private FilterConfig permissionFilterConfig = null;

	@Autowired
	private Permission permission;

	private SSOLoginManageHelper ssologinManageHelper;

	private String[] passedPaths = null;
	private String[] allowSuffix = null;

	private String[] bizloginUrl = null;
	private String[] readonlyUrls = null;
	private String currentWebSiteName = null;
	private String casWebSiteName = null;
	private String homePageUrl = null;
	private static final String headerName = "X-Auth-Token";
	private static final String init_param_allowUrl = "allowUrl";
	private static final String init_param_readonlyUrl = "readonlyUrl";
	private static final String init_param_homePageUrl = "homePageUrl";
	private static final String init_param_cas_web_site_name = "cas_web_site_name";
	private static final String init_param_allowSuffix = "allowSuffix";
	private static final String init_param_bizloginUrl = "bizloginUrl";

	public static final String CAS_COOKIE_NAME = "cas";

	public void init(FilterConfig filterConfig) {

		this.setSsologinManageHelper(((SSOLoginManageHelper) SpringUtils.getBean("ssologinManageHelper")));

		this.setHomePageUrl(filterConfig.getInitParameter(init_param_homePageUrl));

		this.setCurrentWebSiteName(ssologinManageHelper.getWebSiteCode());

		this.setCasWebSiteName(filterConfig.getInitParameter(init_param_cas_web_site_name));

		String allowUrls = filterConfig.getInitParameter(init_param_allowUrl);
		this.setPassedPaths((StringUtils.isNotBlank(allowUrls)) ? allowUrls.split(";") : new String[0]);

		String allowSuffixs = filterConfig.getInitParameter(init_param_allowSuffix);
		this.setAllowSuffix(prepareAllowSuffixs(allowSuffixs));

		String bizloginUrls = filterConfig.getInitParameter(init_param_bizloginUrl);
		this.setBizloginUrl((StringUtils.isNotBlank(bizloginUrls)) ? bizloginUrls.split(";") : new String[0]);

		String readonlyUrl = filterConfig.getInitParameter(init_param_readonlyUrl);
		this.setReadonlyUrls((StringUtils.isNotBlank(readonlyUrl)) ? readonlyUrl.split(";") : new String[0]);

	}

    public void init(Map<Object, Object> properties) {

        this.setSsologinManageHelper(((SSOLoginManageHelper) SpringUtils.getBean("ssologinManageHelper")));

        this.setHomePageUrl((String) properties.get(init_param_homePageUrl));

        this.setCurrentWebSiteName(ssologinManageHelper.getWebSiteCode());

        this.setCasWebSiteName((String) properties.get(init_param_cas_web_site_name));

        String allowUrls = (String) properties.get(init_param_allowUrl);
        this.setPassedPaths((StringUtils.isNotBlank(allowUrls)) ? allowUrls.split(";") : new String[0]);

        String allowSuffixs = (String) properties.get(init_param_allowSuffix);
        this.setAllowSuffix(prepareAllowSuffixs(allowSuffixs));

        String bizloginUrls = (String) properties.get(init_param_bizloginUrl);
        this.setBizloginUrl((StringUtils.isNotBlank(bizloginUrls)) ? bizloginUrls.split(";") : new String[0]);

        String readonlyUrl = (String) properties.get(init_param_readonlyUrl);
        this.setReadonlyUrls((StringUtils.isNotBlank(readonlyUrl)) ? readonlyUrl.split(";") : new String[0]);

    }

	private String[] prepareAllowSuffixs(String allowSuffixs) {
		return getPermission().prepareAllowSuffixs( allowSuffixs );
	}
	private boolean isPassedRequest(String[] passedPaths, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException{

		return getPermission().isPassedRequest( passedPaths, request, response );
	}

	public Boolean isAllowed(ServletRequest req, ServletResponse rep)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;

		HttpServletResponse response = (HttpServletResponse) rep;

		String requestPath = request.getRequestURL().toString();
		String casLoginurl = this.ssologinManageHelper.getCasLoginurl();

		//通过无需权限限制的资源
		boolean passedFlag = isPassedRequest( passedPaths, request, response )
				|| isPassedRequest( allowSuffix, request, response )
				|| isPassedRequest( bizloginUrl, request, response );
		if( passedFlag ){
			return true;
		}

		if (requestPath.toLowerCase().indexOf(casLoginurl.split("\\?")[0].toLowerCase()) > -1){
			log.info(
					"sso client request path '" + requestPath + "'is in login page, filter chain will be continued.");
			return true;
		}

		LoginInfo loginInfo = SSOLoginFilter.getLoginInfoFromSession(request);

		String requestHttpMethod = request.getMethod().toLowerCase();
		boolean retValue = false;

		//通过只读页
		passedFlag = isPassedRequest( readonlyUrls, request, response );
		if( loginInfo != null && passedFlag ){

			return true;

		}

		if( loginInfo != null ) {
			List<LoginUserRole> loginUserRoles = loginInfo.getUserRoles();
			if( loginUserRoles == null || loginUserRoles.size() < 1 ){
				loginUserRoles = PrivilegeCacheManager.getLoginUserRole( loginInfo.getUserId() );

				loginInfo.setUserRoles(loginUserRoles);
				SSOLoginFilter.setLoginInfo2Session(request, loginInfo);
			}

		}

		//验证基于角色的访问权限
		if( loginInfo != null && loginInfo.getRolePrivilegeInd()
				&& isAccessAllowedBasedRole( requestPath, requestHttpMethod, loginInfo) ){

			return true;
		} else if( loginInfo != null && !loginInfo.getRolePrivilegeInd()
				&& isAccessAllowedBasedUser( requestPath, requestHttpMethod, loginInfo) ){
			//通过基于用户的访问权限
			return true;
		} else {
			ResponseVo responseVo = new ResponseVo();
			responseVo.setCode(Constants.ReturnCode.NOPRIVILEGE.Code());
			responseVo.setMsg("无访问权限:" + requestPath + ":" + requestHttpMethod);
			ShiroFilterUtils.responseOutput(response, responseVo);
			return false;
		}

	}
	protected boolean isAccessAllowedBasedRole(String requestPath, String requestHttpMethod, LoginInfo loginInfo) {

		return getPermission().isAccessAllowedBasedRole( requestPath, requestHttpMethod, loginInfo );
	}

	protected boolean isAccessAllowedBasedUser(String requestPath, String requestHttpMethod, LoginInfo loginInfo) {
		return getPermission().isAccessAllowedBasedUser( requestPath, requestHttpMethod, loginInfo );
	}

	protected boolean isPermission(String requestPath, String requestHttpMethod, List<ResourceBo> resourceBos) {

		String resourceBosJsonObj = GsonUtils.toJsonStr(resourceBos);
		return getPermission().isPermission( requestPath, requestHttpMethod, resourceBosJsonObj);
	}

//	@Override
	public void destroy() {
		permissionFilterConfig = null;
	}

	public Permission getPermission() {
		return permission == null ? new PermissionImpl() : permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}


	public SSOLoginManageHelper getSsologinManageHelper() {
		return ssologinManageHelper;
	}

	public void setSsologinManageHelper(SSOLoginManageHelper ssologinManageHelper) {
		this.ssologinManageHelper = ssologinManageHelper;
	}

	public String[] getPassedPaths() {
		return passedPaths;
	}

	public void setPassedPaths(String[] passedPaths) {
		this.passedPaths = passedPaths;
	}

	public String[] getAllowSuffix() {
		return allowSuffix;
	}

	public void setAllowSuffix(String[] allowSuffix) {
		this.allowSuffix = allowSuffix;
	}

	public String[] getBizloginUrl() {
		return bizloginUrl;
	}

	public void setBizloginUrl(String[] bizloginUrl) {
		this.bizloginUrl = bizloginUrl;
	}

	public String[] getReadonlyUrls() {
		return readonlyUrls;
	}

	public void setReadonlyUrls(String[] readonlyUrls) {
		this.readonlyUrls = readonlyUrls;
	}

	public String getCurrentWebSiteName() {
		return currentWebSiteName;
	}

	public void setCurrentWebSiteName(String currentWebSiteName) {
		this.currentWebSiteName = currentWebSiteName;
	}

	public String getCasWebSiteName() {
		return casWebSiteName;
	}

	public void setCasWebSiteName(String casWebSiteName) {
		this.casWebSiteName = casWebSiteName;
	}

	public String getHomePageUrl() {
		return homePageUrl;
	}

	public void setHomePageUrl(String homePageUrl) {
		this.homePageUrl = homePageUrl;
	}

	public FilterConfig getPermissionFilterConfig() {
		return permissionFilterConfig;
	}

	public void setPermissionFilterConfig(FilterConfig permissionFilterConfig) {
		this.permissionFilterConfig = permissionFilterConfig;
	}
}
