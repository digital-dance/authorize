package com.digital.dance.core.shiro.cache;

import com.digital.dance.framework.codis.impl.GsonUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

/**
 * 
 * @author liuxiny
 *
 */
public class AppPropsConfig {

	public static Map<Object, Object> getProperties(String pClassPath, Class pClass){
		InputStream iStream = pClass.getClassLoader().getResourceAsStream(pClassPath);
		Map<Object, Object> map = getProperties( iStream );
		return map;
	}

	public static Map<Object, Object> getProperties(String pPropertiesFilePath){
		InputStream iStream = null;
		Map<Object, Object> map = null;
		try {
			iStream = new BufferedInputStream(new FileInputStream(new File(pPropertiesFilePath)));
			map = getProperties( iStream );
		} catch (FileNotFoundException e) {
			String exJson = GsonUtils.toJsonStr(e);
			LoggerUtils.error(AppPropsConfig.class, exJson);
		}
		return map;
	}

	public static Map<Object, Object> getProperties(InputStream pInputStream){
		Map<Object, Object> map = new HashMap<>();
		Properties cfgProperties = new Properties();
		try {
			cfgProperties.load(pInputStream);
			for (Entry e : cfgProperties.entrySet()) {
				map.put( e.getKey(), e.getValue() );
			}

		} catch (IOException e) {
			String exJson = GsonUtils.toJsonStr(e);
			LoggerUtils.error(AppPropsConfig.class, exJson);
		} finally {
			if( pInputStream != null ){
				try {
					pInputStream.close();
				} catch (IOException e) {
					String exJson = GsonUtils.toJsonStr(e);
					LoggerUtils.error(AppPropsConfig.class, exJson);
				}
			}
		}
		return map;
	}
}
