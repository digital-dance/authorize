package com.digital.dance.client.core.shiro.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import com.digital.dance.common.utils.*;

import com.digital.dance.framework.infrastructure.commons.Log;

public class PermissionFilter implements Filter {
	Log log = new Log(PermissionFilter.class);
	private FilterConfig permissionFilterConfig = null;

	public PermissionHelper getPermissionHelper() {
		return (permissionHelper == null) ? new PermissionHelper() : permissionHelper;
	}

	public void setPermissionHelper(PermissionHelper permissionHelper) {
		this.permissionHelper = permissionHelper;
	}

	private PermissionHelper permissionHelper;

	public void init(FilterConfig filterConfig) throws ServletException {
		permissionFilterConfig = filterConfig;
		getPermissionHelper().init(filterConfig);
	}

	public void doFilter(ServletRequest req, ServletResponse rep, FilterChain chain)
			throws IOException, ServletException {

		if( getPermissionHelper().isAllowed(req, rep) ){

			chain.doFilter(req, rep);
			return;

		} else {
			ResponseVo responseVo = new ResponseVo();
			responseVo.setCode(Constants.ReturnCode.NOPRIVILEGE.Code());
			HttpServletRequest request = (HttpServletRequest) req;
			String requestHttpMethod = request.getMethod().toLowerCase();
			responseVo.setMsg("无访问权限:" + request.getRequestURL().toString() + ":" + requestHttpMethod);
			ShiroFilterUtils.responseOutput(rep, responseVo);
		}

	}


	@Override
	public void destroy() {
		permissionFilterConfig = null;
	}

}
