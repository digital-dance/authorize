package com.digital.dance.common.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by hpe on 2017/11/23.
 */
@Component
public class SystemInit implements InitializingBean,ApplicationContextAware{



    @Override
    public void afterPropertiesSet() throws Exception {

    }

    ////////////////////////////////////////////////////////////////

    //此方法一定不要写成static
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        //实际上是把applicationContext传入到了SpringContextUtil里面
        SpringContextUtil.setApplicationContext(applicationContext);
    }
}
