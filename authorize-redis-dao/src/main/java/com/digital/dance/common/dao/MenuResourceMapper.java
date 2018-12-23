package com.digital.dance.common.dao;

import com.digital.dance.common.model.MenuResource;

public interface MenuResourceMapper {
    int deleteByPrimaryKey(String resourceId);

    int insert(MenuResource record);

    int insertSelective(MenuResource record);

    MenuResource selectByPrimaryKey(String resourceId);

    int updateByPrimaryKeySelective(MenuResource record);

    int updateByPrimaryKey(MenuResource record);
}