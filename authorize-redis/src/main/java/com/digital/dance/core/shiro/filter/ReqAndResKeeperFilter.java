package com.digital.dance.core.shiro.filter;

import com.digital.dance.common.utils.UtilPath;
import com.digital.dance.common.utils.UtilPath;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * xss攻击filter
 * @author xinyuliu
 *
 */
public class ReqAndResKeeperFilter implements Filter {
	FilterConfig filterConfig = null;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;

		HttpServletResponse res = (HttpServletResponse) response;
		UtilPath.setCurrentRequest((HttpServletRequest)request);
		UtilPath.setCurrentResponse(response);
		chain.doFilter(request,response);
		//begin
		(res).setHeader("Access-Control-Allow-Origin", "*");
		(res).setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
		(res).setHeader("Access-Control-Allow-Headers", "Origin, SESSION, Cookie, Set-Cookie, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With, Access-Control-Allow-Origin, Access-Control-Allow-Methods, X-Auth-Token, Access-Control-Allow-Credientials");
		(res).setHeader("Access-Control-Expose-Headers", "X-Auth-Token");
		(res).setHeader("Access-Control-Allow-Credientials", "true");
		//end
		res.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String requestPath = req.getRequestURL().toString();
	}

	@Override
	public void destroy() {
		filterConfig = null;
	}

}
