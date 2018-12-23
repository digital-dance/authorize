package com.digital.dance.core.mybatis;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class SqlSessionHelper {

    protected static SqlSessionTemplate sqlSessionTemplate;

    public static SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    public static void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        SqlSessionHelper.sqlSessionTemplate = sqlSessionTemplate;
    }
}
