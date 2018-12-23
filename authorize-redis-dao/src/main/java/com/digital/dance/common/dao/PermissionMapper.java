package com.digital.dance.common.dao;

import com.digital.dance.common.model.SResource;
import com.digital.dance.common.model.RoleBranchResource;
//import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//@Mapper
public interface PermissionMapper {
    int deleteByPrimaryKey(String resourceId);

    int insert(SResource record);

    SResource selectByPrimaryKey(String resourceId);

    List<SResource> selectAll();

    List<SResource> selectPagedAll(SResource record);

    int updateByPrimaryKey(SResource record);

    List<SResource> listPermissionByUserAgentId(String userAgentId);

    List<RoleBranchResource> listRoleBranchResource();
}