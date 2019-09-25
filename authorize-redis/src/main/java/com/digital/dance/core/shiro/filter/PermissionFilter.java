package com.digital.dance.core.shiro.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.digital.dance.common.utils.Constants;
import com.digital.dance.common.utils.GsonUtils;
import com.digital.dance.common.utils.ResponseVo;
import com.digital.dance.core.shiro.cache.VCache;
import com.digital.dance.commons.security.utils.RSACoderUtil;
import com.digital.dance.commons.serialize.json.utils.JSONUtils;
import com.digital.dance.core.shiro.service.impl.CacheInitializer;
import com.digital.dance.framework.infrastructure.commons.Log;
import com.digital.dance.framework.infrastructure.commons.StringTools;
import com.digital.dance.framework.sso.entity.LoginInfo;
import com.digital.dance.framework.sso.entity.LoginUserRole;
import com.digital.dance.framework.sso.filter.SSOLoginFilter;
import com.digital.dance.framework.sso.util.LoginContext;
import com.digital.dance.framework.sso.util.SSOLoginManageHelper;
import com.digital.dance.permission.bo.ResourceBo;
import com.digital.dance.service.Permission;
import com.digital.dance.service.Request;
import com.digital.dance.service.Response;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class PermissionFilter implements Filter, Permission {
	Log log = new Log(PermissionFilter.class);
	FilterConfig permissionFilterConfig = null;

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

	public void init(FilterConfig filterConfig) throws ServletException {
		WebApplicationContext applicationcontext = WebApplicationContextUtils
				.getRequiredWebApplicationContext(filterConfig.getServletContext());
		this.ssologinManageHelper = ((SSOLoginManageHelper) applicationcontext.getBean("ssologinManageHelper",
				SSOLoginManageHelper.class));

		homePageUrl = filterConfig.getInitParameter(init_param_homePageUrl);
		currentWebSiteName = ssologinManageHelper.getWebSiteCode();

		casWebSiteName = filterConfig.getInitParameter(init_param_cas_web_site_name);

		String allowUrls = filterConfig.getInitParameter(init_param_allowUrl);
		passedPaths = (StringUtils.isNotBlank(allowUrls)) ? allowUrls.split(";") : new String[0];

		String allowSuffixs = filterConfig.getInitParameter(init_param_allowSuffix);
		allowSuffix = prepareAllowSuffixs( allowSuffixs );

		String bizloginUrls = filterConfig.getInitParameter(init_param_bizloginUrl);
		bizloginUrl = (StringUtils.isNotBlank(bizloginUrls)) ? bizloginUrls.split(";") : new String[0];

		String readonlyUrl = filterConfig.getInitParameter(init_param_readonlyUrl);
		readonlyUrls = (StringUtils.isNotBlank(readonlyUrl)) ? readonlyUrl.split(";") : new String[0];

	}

	public String[] prepareAllowSuffixs(String allowSuffixs) {
		return (StringUtils.isNotBlank(allowSuffixs)) ? (("(\\."+allowSuffixs +")$").replace(";", ")$;(\\.")).split(";") : new String[0];
	}
	public boolean isPassedRequest( String[] passedPaths, HttpServletRequest request, HttpServletResponse response )
			throws IOException, ServletException{
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

	@Override
	public boolean isPassedRequest(String[] passedPaths, Request request, Response response) {
		throw new NotImplementedException("isPassedRequest method of Permission is not implemented in PermissionFilter.");
	}

	public void doFilter(ServletRequest req, ServletResponse rep, FilterChain chain)
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
			chain.doFilter(request, response);
			return;
		}

		if (requestPath.toLowerCase().indexOf(casLoginurl.split("\\?")[0].toLowerCase()) > -1){
			log.info(
					"sso client request path '" + requestPath + "'is in login page, filter chain will be continued.");
			chain.doFilter(request, response);
			return;
		}

		LoginInfo loginInfo = SSOLoginFilter.getLoginInfoFromSession(request);

		String requestHttpMethod = request.getMethod().toLowerCase();
		boolean retValue = false;

		//通过只读页
		passedFlag = isPassedRequest( readonlyUrls, request, response );
		if( loginInfo != null && passedFlag ){

			chain.doFilter(request, response);
			return;

		}

		if( loginInfo != null ) {
			List<LoginUserRole> loginUserRoles = loginInfo.getUserRoles();
			if( loginUserRoles == null || loginUserRoles.size() < 1 ){
				String key = CacheInitializer.getUserRolesKey( loginInfo.getUserId() );
				long len = VCache.getLenByList( key );
				loginUserRoles = VCache.getVByList(key, 0, (int)len, LoginUserRole.class);
				loginInfo.setUserRoles(loginUserRoles);
				SSOLoginFilter.setLoginInfo2Session(request, loginInfo);
			}

		}

		if( loginInfo != null && loginInfo.getRolePrivilegeInd()
				&& isAccessAllowedBasedRole( requestPath, requestHttpMethod, loginInfo) ){

			chain.doFilter(request, response);
			return;
		} else if( loginInfo != null && !loginInfo.getRolePrivilegeInd()
				&& isAccessAllowedBasedUser( requestPath, requestHttpMethod, loginInfo) ){

			chain.doFilter(request, response);
			return;
		} else {
			ResponseVo responseVo = new ResponseVo();
			responseVo.setCode(Constants.ReturnCode.NOPRIVILEGE.Code());
			responseVo.setMsg("无访问权限:" + requestPath + ":" + requestHttpMethod);
			ShiroFilterUtils.responseOutput(response, responseVo);
		}

	}
	public boolean isAccessAllowedBasedRole(String requestPath, String requestHttpMethod, LoginInfo loginInfo) {
		boolean retValue = false;
		if( loginInfo != null ) {
			List<LoginUserRole> loginUserRoles = loginInfo.getUserRoles();

			if( loginUserRoles == null || loginUserRoles.size() < 1 )return false;
			for (LoginUserRole loginUserRole : loginUserRoles) {
				List<ResourceBo> resourceBos = CacheInitializer.listRoleBranchResourceByKey(loginUserRole.getRoleId()
						, loginUserRole.getOrgId(), loginUserRole.getDepartmentId());
				retValue = isPermission( requestPath, requestHttpMethod, resourceBos );
				if(retValue) return true;
			}
		}
		return false;
	}

	public boolean isAccessAllowedBasedUser(String requestPath, String requestHttpMethod, LoginInfo loginInfo) {
		List<ResourceBo> resourceBos = CacheInitializer.listUserPrivilegeResourceByKey(loginInfo.getUserId());

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

	@Override
	public void destroy() {
		permissionFilterConfig = null;
	}

	public static void main(String[] args){
		ArrayList<ResourceBo> resourceBos = new ArrayList<ResourceBo>();
		ResourceBo bo = new ResourceBo();
		bo.setPageSize(1);
		bo.setPageIndex(2);
		bo.setUrl("gh");
		resourceBos.add(bo);

		String resourceBosJsonObj = GsonUtils.toJsonStr(resourceBos);
		ArrayList<ResourceBo> resourceBos1 = GsonUtils.toObject(resourceBosJsonObj, new ArrayList<ResourceBo>().getClass());

		ArrayList<ResourceBo> resourceBos2 = GsonUtils.toObject("[{\"offsetNum\":2,\"pageIndex\":2,\"pageSize\":1,\"url\":\"gh\"}]", new ArrayList<ResourceBo>().getClass());

		resourceBosJsonObj = GsonUtils.toJsonStr(resourceBos2);
	}
}
