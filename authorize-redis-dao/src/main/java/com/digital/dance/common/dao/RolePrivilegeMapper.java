package com.digital.dance.common.dao;

import com.digital.dance.common.model.RolePrivilege;

public interface RolePrivilegeMapper {
    int deleteByPrimaryKey(String rolePrivilegeId);

    int insert(RolePrivilege record);

    int insertSelective(RolePrivilege record);

    RolePrivilege selectByPrimaryKey(String rolePrivilegeId);

    int updateByPrimaryKeySelective(RolePrivilege record);

    int updateByPrimaryKey(RolePrivilege record);
}