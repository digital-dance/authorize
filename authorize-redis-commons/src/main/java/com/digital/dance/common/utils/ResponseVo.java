package com.digital.dance.common.utils;

import java.io.Serializable;

/**
 * 
 * @author xinyuliu
 *
 * time:2016年8月19日上午11:49:38
 */
public class ResponseVo implements Serializable {
	private static final long serialVersionUID = -6590925366067539814L;
	
	private String code;
	private String msg;
	private Object result;

	public ResponseVo(String code){
		this.code = code;
	}
	
	public ResponseVo(String code,String msg){
		this.code = code;
		this.msg = msg;
	}
	
	public ResponseVo(String code,String msg,Object obj){
		this.code = code;
		this.msg = msg;
		this.result = obj;
	}
	
	public ResponseVo(){ }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "ResponseVo [code=" + code + ", msg=" + msg + ", result=" + result +  "]";
	}
	
}
