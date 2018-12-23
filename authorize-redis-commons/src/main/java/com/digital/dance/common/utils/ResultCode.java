package com.digital.dance.common.utils;

import java.util.HashMap;
import java.util.Map;

public class ResultCode {
	private static Map<String, String> codeMap=new HashMap<String, String>();
	static{
		codeMap.put("0", "注册成功");
		codeMap.put("50001", "AppID无效");
		codeMap.put("50002", "AppKey无效");
		codeMap.put("50003", "时间戳已过期");
		codeMap.put("10001", "用户不存在");
		codeMap.put("10002", "密码不正确");
		codeMap.put("10003", "请求参数不符合要求 ");
		codeMap.put("10004", "手机已被占用");
		codeMap.put("10005", "邮箱已被占用 ");
		codeMap.put("10006", "手机和邮箱都为空");
		codeMap.put("10007", "短信验证码校验不正确");
		codeMap.put("10008", "密码不符合规范");
		codeMap.put("10009", " 用户处理非激活状态 ");
		codeMap.put("10010", " 已被绑定");
		//codeMap.put("10011", "已被绑定 ");
		codeMap.put("10012", " 自定义账号已被占用 ");
		codeMap.put("10013", "自定义账号长度超出32位");
		codeMap.put("99999", "操作失败，服务器异常");
	}
	public static String getMsg(String code){
		if(code==null||code.equals("")){
			return "";
		}
		
		return codeMap.get(code)==null?"":codeMap.get(code).toString();
	}
}
