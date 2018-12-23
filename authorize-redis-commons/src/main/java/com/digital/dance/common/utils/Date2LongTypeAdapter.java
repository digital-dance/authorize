package com.digital.dance.common.utils;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


public class Date2LongTypeAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {

	
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
    	/*
    	String format = "yyyy-MM-dd";
		if(json.getAsString()!=null&&!json.getAsString().equals("")){
			if(json.toString().length()>10){
				format = "yyyy-MM-dd HH:mm:ss";
			}
		}else{
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date formattedDate = null;
		try {
			formattedDate = formatter.parse(json.getAsString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return formattedDate;
		*/
    	Pattern pattern = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");
    	String format = "yyyy-MM-dd";
    	Matcher m = pattern.matcher(json.getAsJsonPrimitive().getAsString());
    	
    	SimpleDateFormat formatter = new SimpleDateFormat(format);
    	boolean dateFlag = m.matches();
    	
    	if(json.getAsJsonPrimitive().getAsString()!=null){
    		if(dateFlag) {
    			try {
					return formatter.parse(json.getAsJsonPrimitive().getAsString());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
				}  
    		}else{
    			return new java.util.Date(json.getAsJsonPrimitive().getAsLong());        
    		}
    	}else{
    		return null;
    	}
		return null;
    }

    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getTime());          
    }

}

