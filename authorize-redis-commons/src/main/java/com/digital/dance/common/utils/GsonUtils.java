package com.digital.dance.common.utils;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;

/**
 * 
 * JSON转换器
 * 
 *
 */
public class GsonUtils {
	
	
	private static Gson gson = null;
	
	/**
	 * 静态代码块(初始化GSON)
	 */
	static{
	    GsonBuilder gb=new GsonBuilder();  
        gb.registerTypeAdapter(java.util.Date.class , new Date2LongTypeAdapter()).setDateFormat(DateFormat.LONG);
		gson = gb.create();
	}
	
	/**
	 * 获取PHP请求JSON串(填充Bean对象)
	 * @param <T>
	 * @param json
	 * @param classOfT
	 * @return <T> T
	 */
	public static <T> T getJson(String json,Class<T> classOfT){
		return  gson.fromJson(json, classOfT);
	}
	
	public static <T> T getJson(String json,Type typeOfT){
		return  gson.fromJson(json, typeOfT);
	}
	
	/**
	 * 将对象转换为JSON字符串
	 * @param obj
	 * @return String
	 */
	public static String toJson(Object obj){
		return gson.toJson(obj);
	}
	
	public static String toJsonStr( Object obj ) {
		return JSON.toJSONString( obj );
	}

	public static <T> T toObject( String json, Class<T> clazz ) {
		if( json == null || json.trim().length() == 0 ) {
			return null;
		}
		return JSON.parseObject( json, clazz );
	}
	
	
	/**
	 * 返回格式 : {"code":"0","data":"",desc:"exec success"}
	 * @return
	 */
	public static String retJson(){
		return GsonUtils.retJson("0", "","success");
	}
	
	
	/**
	 * 返回格式 : {"code":"0","data":{}/[{},{}...],desc:"exec success"}
	 * @return
	 */
	public static String retJson(Object data){
		return GsonUtils.retJson("0", data,"success");
	}
	
	/**
	 * 返回格式 : {"code":"11","data":"",desc:"exec success"}
	 * @return
	 */
	public static String retJson(String errcode,String errdesc){
		return GsonUtils.retJson(errcode,"",errdesc);
	}
	
	/**
	 * 用户自定义Json
	 * @param code
	 * @param data
	 * @param desc
	 * @return
	 */
	public  static String retJson(String code,Object data,String desc){
		Map<String,Object> map = new HashMap<String,Object>();
		String retJson = null;
		map.put("code",code);
		if(data != null && !"".equals(data.toString().trim())){
		    //Gson gson = new GsonBuilder().setDateFormat(DateFormat.LONG).create();
		    //map.put(GlobalVar.DATA,gson.toJson(data));
            map.put("data",data);
			//map.put(GlobalVar.DATA,GsonUtils.toJson(data));
		}else{
			map.put("data","");
		}
		if(desc != null && !"".equals(desc)){
			map.put("desc", desc);
		}else{
			map.put("desc", "");
		}
		retJson = GsonUtils.toJson(map);
		map.clear();map = null;
		return retJson;
	}
}
