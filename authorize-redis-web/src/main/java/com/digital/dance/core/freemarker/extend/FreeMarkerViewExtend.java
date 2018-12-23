package com.digital.dance.core.freemarker.extend;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

//import com.digital.dance.core.shiro.token.manager.TokenManagerService;
//import com.digital.dance.permission.bo.UserAgentBo;
import com.digital.dance.common.utils.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

//import com.digital.dance.user.bo.UUserBo;

import com.digital.dance.core.statics.Constant;

import com.digital.dance.common.utils.*;

public class FreeMarkerViewExtend extends FreeMarkerView {

	//@Autowired
//	TokenManagerService tokenManagerService = SpringUtils.getBean("tokenManagerService");;

	
	protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request){
		
		try {
			super.exposeHelpers(model, request);
		} catch (Exception e) {
			LoggerUtils.fmtError(FreeMarkerViewExtend.class,e, "FreeMarkerViewExtend 加载父类出现异常。请检查。");
		}
		model.put(Constant.CONTEXT_PATH, request.getContextPath());
		model.putAll(Ferrmarker.initMap);
//		Object token = tokenManagerService.getToken(request.getSession().getId(), "");
//		if(token instanceof UserAgentBo){
//			UserAgentBo userAgentBo = (UserAgentBo)token;
//			UUserBo uUserBo = new UUserBo();
//			uUserBo.setEmail(userAgentBo.getLoginName());
//			uUserBo.setNickname(userAgentBo.getLoginName());
//			uUserBo.setId(userAgentBo.getUserAgentId());
//			uUserBo.setStatus((long)userAgentBo.getStatus());
//			token = uUserBo;
//		}
//		//String ip = IPUtils.getIP(request);
//		if(tokenManagerService.isLogin()){
//			model.put("token", token);//登录的token
//		}
		
		model.put("_time", new Date().getTime());
		model.put("NOW_YEAY", Constant.NOW_YEAY);//今年
		
		model.put("_v", Constant.VERSION);//版本号，重启的时间
		model.put("cdn", Constant.DOMAIN_CDN);//CDN域名
		model.put("basePath", request.getContextPath());//base目录。
		
	}
}
