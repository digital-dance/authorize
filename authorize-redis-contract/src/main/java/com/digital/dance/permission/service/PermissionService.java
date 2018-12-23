package com.digital.dance.permission.service;


import com.digital.dance.permission.bo.*;

import java.util.List;

/**
* ResourceService接口
* Created by admin on 2017/6/29.
*/
public interface PermissionService {

    int deleteByPrimaryKey(String resourceId);

    int insert(ResourceBo record);

    ResourceBo selectByPrimaryKey(String resourceId);

    List<ResourceBo> selectAll();

    List<ResourceBo> selectPagedAll(ResourceBo record);

    int updateByPrimaryKey(ResourceBo record);

//    List<ResourceBo> listPermissionByUserAgentId(String userAgent);
    public List<ResourceBo> listResourceByUserAgentId(String userAgentId);
    List<RoleBranchResourceBo> listRoleBranchResource();
    List<UserPrivilegeBo> selectUserPrivilege(UserPrivilegeBo record);
}