package com.digital.dance.client.core.shiro.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.digital.dance.common.utils.LoggerUtils;
import net.sf.json.JSONObject;

import com.digital.dance.common.utils.*;

/**
 * 
 * 开发公司：itboy.net<br/>
 * 版权：itboy.net<br/>
 * <p>
 * 
 * Shiro Filter 工具类
 * 
 * <p>
 * 
 * 区分　责任人　日期　　　　说明<br/>
 * 创建　周柏成　2016年5月27日 　<br/>
 * <p>
 * *******
 * <p>
 * 
 * @author xinyuliu
 * @email i@itboy.net
 * @version 1.0,2016年5月27日 <br/>
 * 
 */
public class ShiroFilterUtils {
	final static Class<? extends ShiroFilterUtils> CLAZZ = ShiroFilterUtils.class;
//	//登录页面
//	static final String LOGIN_URL = "/u/login.shtml";
//	//踢出登录提示
//	final static String KICKED_OUT = "/open/kickedOut.shtml";
//	//没有权限提醒
//	final static String UNAUTHORIZED = "/open/unauthorized.shtml";
	/**
	 * 是否是Ajax请求
	 * @param request
	 * @return
	 */
	public static boolean isAjax(ServletRequest request){
		return "XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"));
	}
	
	/**
	 * response 输出JSON
	 * @param response
	 * @param resultMap
	 * @throws IOException
	 */
	public static void out(ServletResponse response, Map<String, String> resultMap){
		
		PrintWriter out = null;
		try {
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.println(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			LoggerUtils.fmtError(CLAZZ, e, "输出JSON报错。");
		}finally{
			if(null != out){
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * response 输出JSON
	 * @param response
	 * @param resultVo
	 * @throws IOException
	 */
	public static void out(ServletResponse response, ResponseVo resultVo){

		PrintWriter out = null;
		try {
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.println(JSONObject.fromObject(resultVo).toString());
		} catch (Exception e) {
			LoggerUtils.fmtError(CLAZZ, e, "输出JSON报错。");
		}finally{
			if(null != out){
				out.flush();
				out.close();
			}
		}
	}

	public static void responseOutput(ServletResponse response, ResponseVo resultVo) {
		Exception localThrowable0 = null;
		PrintWriter out = null;
		try {
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.write(GsonUtils.toJsonStr(resultVo));
		} catch (Exception localThrowable) {
			localThrowable0 = localThrowable;
			LoggerUtils.error(CLAZZ, localThrowable0.getMessage(), localThrowable0);

		} finally {
			if (out != null)

				try {
					out.flush();
					out.close();
				} catch (Exception x2) {
					if (localThrowable0 != null)
						localThrowable0.addSuppressed(x2);
					LoggerUtils.error(CLAZZ, localThrowable0.getMessage(), localThrowable0);
				}
		}
	}
}
