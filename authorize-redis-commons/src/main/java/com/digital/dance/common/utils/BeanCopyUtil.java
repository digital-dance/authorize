package com.digital.dance.common.utils;

import org.springframework.beans.BeanUtils;

/**
 * Created by hpe on 2017/11/21.
 */
public class BeanCopyUtil {
    public static void copy(Object sourceObj, Object targetObj){
        BeanUtils.copyProperties(sourceObj, targetObj);
    }
}
