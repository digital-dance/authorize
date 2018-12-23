package com.digital.dance.common.utils;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IOCStarter {
//private final static Log unitTestLog = new Log(UnitTestBase.class);
	
	public volatile static ClassPathXmlApplicationContext iOCStarterContext = null;
	protected volatile static Object iOCStarterLockObj = new Object();
	public static ClassPathXmlApplicationContext startIOCInstance(String... iOCCfgPath) {
        if (iOCStarterContext == null) {
            synchronized (IOCStarter.iOCStarterLockObj) {
                if (iOCStarterContext == null) {
                    LoggerUtils.debug( IOCStarter.class,
					"-----------begin to load " + iOCCfgPath + " -----------");
                    String[] unitTestConfigLocations = new String[iOCCfgPath.length];
                    	unitTestConfigLocations = iOCCfgPath;
                    iOCStarterContext = new ClassPathXmlApplicationContext(unitTestConfigLocations);
                    iOCStarterContext.start();
                    LoggerUtils.debug( IOCStarter.class,
        					"-----------end to load " + iOCCfgPath + " -----------");

                }
            }
        }
        return iOCStarterContext;
    }
}
