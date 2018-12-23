package com.digital.dance.permission.service.impl;

import com.digital.dance.common.dao.PermissionMapper;
import com.digital.dance.common.dao.PermissionMapper;
import com.digital.dance.common.dao.UserPrivilegeMapper;
import com.digital.dance.common.model.*;
import com.digital.dance.permission.bo.*;
import com.digital.dance.common.utils.BeanCopyUtil;
import com.digital.dance.permission.service.PermissionService;
import com.digital.dance.core.shiro.service.impl.CacheInitializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
* ResourceService实现
* Created by admin on 2017/6/29.
*/
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionMapper permissionMapper;

    @Autowired
    UserPrivilegeMapper userPrivilegeMapper;

    @Override
    public int deleteByPrimaryKey(String resourceId) {
        return permissionMapper.deleteByPrimaryKey( resourceId );
    }

    @Override
    public int insert(ResourceBo record) {
        SResource sResource = new SResource();
        BeanCopyUtil.copy(record, sResource);
        return permissionMapper.insert( sResource );
    }

    @Override
    public ResourceBo selectByPrimaryKey(String resourceId) {
        SResource sResource = permissionMapper.selectByPrimaryKey( resourceId );
        ResourceBo resourceBo = new ResourceBo();
        BeanCopyUtil.copy(sResource, resourceBo);
        return resourceBo;
    }

    @Override
    public List<ResourceBo> selectAll() {
        List<ResourceBo> retBos = new ArrayList<ResourceBo>();
        List<SResource> rets = permissionMapper.selectAll();
        for (SResource e : rets){
            ResourceBo resourceBo = new ResourceBo();
            BeanCopyUtil.copy(e, resourceBo);
            retBos.add(resourceBo);
        }

        return retBos;
    }

    @Override
    public List<ResourceBo> selectPagedAll(ResourceBo record) {

        SResource sResource = new SResource();
        BeanCopyUtil.copy(record, sResource);
        List<SResource> rets = permissionMapper.selectPagedAll(sResource);

        List<ResourceBo> retBos = new ArrayList<ResourceBo>();
        for (SResource e : rets){
            ResourceBo resourceBo = new ResourceBo();
            BeanCopyUtil.copy(e, resourceBo);

            resourceBo.setPageIndex(record.getPageIndex());
            resourceBo.setPageSize(record.getPageSize());
            retBos.add(resourceBo);
        }

        return retBos;
    }

    @Override
    public int updateByPrimaryKey(ResourceBo record) {
        SResource sResource = new SResource();
        BeanCopyUtil.copy(record, sResource);
        return permissionMapper.updateByPrimaryKey( sResource );
    }

    @Override
    public List<ResourceBo> listResourceByUserAgentId(String userAgentId){
        List<ResourceBo> retBos = new ArrayList<ResourceBo>();
        List<SResource> rets = permissionMapper.listPermissionByUserAgentId(userAgentId);
        for (SResource e : rets){
            ResourceBo resourceBo = new ResourceBo();
            BeanCopyUtil.copy(e, resourceBo);
            retBos.add(resourceBo);
        }

        return retBos;
    }

    @Override
    public List<RoleBranchResourceBo> listRoleBranchResource() {
        List<RoleBranchResourceBo> retBos = new ArrayList<RoleBranchResourceBo>();
        List<RoleBranchResource> rets = permissionMapper.listRoleBranchResource();
        for (RoleBranchResource e : rets){
            RoleBranchResourceBo roleBranchResourceBo = new RoleBranchResourceBo();
            BeanCopyUtil.copy(e, roleBranchResourceBo);
            retBos.add(roleBranchResourceBo);
        }

        return retBos;
    }

    @Override
    public List<UserPrivilegeBo> selectUserPrivilege(UserPrivilegeBo record) {
        UserPrivilege userPrivilege = new UserPrivilege();
        BeanCopyUtil.copy(record, userPrivilege);
        List<UserPrivilegeBo> retBos = new ArrayList<UserPrivilegeBo>();
        List<UserPrivilege> rets = userPrivilegeMapper.selectUserPrivilege(userPrivilege);
        for (UserPrivilege e : rets){
            UserPrivilegeBo userPrivilegeBo = new UserPrivilegeBo();
            BeanCopyUtil.copy(e, userPrivilegeBo);
            retBos.add(userPrivilegeBo);
        }

        return retBos;
    }
}