package com.digital.dance.common.dao;

import com.digital.dance.common.model.UserPrivilege;

import java.util.List;

public interface UserPrivilegeMapper {
    int deleteByPrimaryKey(String userPrivilegeId);

    int insert(UserPrivilege record);

    int insertSelective(UserPrivilege record);

    UserPrivilege selectByPrimaryKey(String userPrivilegeId);

    int updateByPrimaryKeySelective(UserPrivilege record);

    int updateByPrimaryKey(UserPrivilege record);

    public List<UserPrivilege> selectUserPrivilege(UserPrivilege record);
}