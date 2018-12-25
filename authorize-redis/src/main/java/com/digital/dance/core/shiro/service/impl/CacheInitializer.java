package com.digital.dance.core.shiro.service.impl;

//import com.digital.dance.permission.service.UserRoleService;
import com.digital.dance.common.utils.BeanCopyUtil;
import com.digital.dance.common.utils.LoggerUtils;
import com.digital.dance.permission.bo.ResourceBo;
import com.digital.dance.permission.bo.RoleBranchResourceBo;
import com.digital.dance.permission.service.PermissionService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.digital.dance.common.utils.LoggerUtils;

import org.springframework.beans.factory.annotation.Autowired;
import com.digital.dance.core.shiro.cache.*;
import com.digital.dance.permission.bo.*;
import com.digital.dance.common.utils.BeanCopyUtil;
import com.digital.dance.permission.service.PermissionService;


import java.util.ArrayList;
import java.util.TreeSet;
import java.util.List;
import java.util.Set;

public class CacheInitializer implements
        ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private PermissionService permissionService;

//    @Autowired
//    private UserRoleService userRoleService;

    /**
     * 当一个ApplicationContext被初始化或刷新触发
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {//root application context 没有parent.
            reflushRoleBranchResourceCashe( permissionService );
//          reflushUserRoleCashe(userRoleService);
            reflushUserPrivilegeResourceCashe( permissionService );

            System.out.println("=========================spring容器初始化完毕, reflushRoleBranchResourceCache执行完毕=========================");

        }
    }

    public static void reflushUserPrivilegeResourceCashe(PermissionService permissionService) {

        List<UserPrivilegeBo> retBos = permissionService.selectUserPrivilege( new UserPrivilegeBo() );

        VCache.delKeysByPrefix( getUserPrivilegeResourceKeyPrefix() );

        for(UserPrivilegeBo rrb : retBos){
            String key = getUserPrivilegeResourceKey( rrb.getUserId() );

            ResourceBo rb = new ResourceBo();
            BeanCopyUtil.copy(rrb, rb);

            VCache.setVByList(key, rb);
        }

        LoggerUtils.info(VCache.class,"reflushRoleBranchResourceCache执行完毕" );
    }

    public static void reflushRoleBranchResourceCashe(PermissionService permissionService) {

        List<RoleBranchResourceBo> retBos = permissionService.listRoleBranchResource();

        VCache.delKeysByPrefix( getRoleBranchResourceKeyPrefix() );
        for(RoleBranchResourceBo rrb : retBos){
            String key = getRoleBranchResourceKey( rrb.getRoleId(), rrb.getOrgId(), rrb.getDepartmentId() );

            ResourceBo rb = new ResourceBo();
            BeanCopyUtil.copy(rrb, rb);

            VCache.setVByList(key, rb);
        }
        LoggerUtils.info(VCache.class,"reflushRoleBranchResourceCache执行完毕" );
    }

    public static List<ResourceBo> listRoleBranchResourceByKey( String roleId, String orgId, String departmentId ){
        departmentId = departmentId == null ? "" : departmentId;
        String key = getRoleBranchResourceKey( roleId, orgId, departmentId );
        long len = VCache.getLenByList(key);
        List<ResourceBo> lRB = VCache.getVByList(key, 0, (int)len, ResourceBo.class);
        return lRB;
    }

    public static void updateRoleBranchResourceByKey( String roleId , String orgId, String departmentId, ResourceBo resourceBo ){
        departmentId = departmentId == null ? "" : departmentId;
        String key = getRoleBranchResourceKey( roleId,  orgId, departmentId );
        List<ResourceBo> rbos = listRoleBranchResourceByKey( roleId,  orgId, departmentId );
        List<ResourceBo> indexs = new ArrayList<ResourceBo>();
        for(int i = 0 ; i < rbos.size(); i ++ ){
            if( rbos.get(i).getResourceId() == resourceBo.getResourceId() ){
                indexs.add(rbos.get(i));
            }
        }
        rbos.removeAll(indexs);
        rbos.add(resourceBo);

        VCache.delByKey( VCache.buildKey( key ) );
        VCache.setVByListMutiElements( key, rbos );
    }

    public static void delRoleBranchResourceByKey(String roleId , String orgId, String departmentId , ResourceBo resourceBo){
        departmentId = departmentId == null ? "" : departmentId;
        String key = getRoleBranchResourceKey( roleId,  orgId, departmentId );
        List<ResourceBo> rbos = listRoleBranchResourceByKey( roleId,  orgId, departmentId );
        List<ResourceBo> indexs = new ArrayList<ResourceBo>();
        for(int i = 0 ; i < rbos.size(); i ++ ){
            if(rbos.get(i).getResourceId() == resourceBo.getResourceId()){
                indexs.add(rbos.get(i));
            }
        }
        rbos.removeAll(indexs);
        //rbos.add(resourceBo);

        VCache.delByKey( VCache.buildKey( key ) );
        VCache.setVByListMutiElements(key, rbos);
    }

    public static void addRoleBranchResourceByKey(String roleId , String orgId, String departmentId , ResourceBo resourceBo){
        departmentId = departmentId == null ? "" : departmentId;
        String key = getRoleBranchResourceKey(roleId,  orgId, departmentId);

        VCache.setVByList(key, resourceBo);
    }

    public static String getRoleBranchResourceKey(String roleId , String orgId, String departmentId ){
        departmentId = departmentId == null ? "" : departmentId;
        return getRoleBranchResourceKeyPrefix() + roleId + "_" + orgId + "_" + departmentId;
    }

    public static String getRoleBranchResourceKeyPrefix( ){
        return "_RoleBranchResource_";
    }

    public static String getUserPrivilegeResourceKey( String userId ){

        return getUserPrivilegeResourceKeyPrefix() + userId;
    }

    public static String getUserPrivilegeResourceKeyPrefix( ){
        return "_UserPrivilegeResource_";
    }

    public static List<ResourceBo> listUserPrivilegeResourceByKey( String userId ){

        String key = getUserPrivilegeResourceKey( userId );
        long len = VCache.getLenByList(key);
        List<ResourceBo> lRB = VCache.getVByList(key, 0, (int)len, ResourceBo.class);
        return lRB;
    }

    public static void updateUserPrivilegeResourceByKey( String userId, ResourceBo resourceBo ){

        String key = getUserPrivilegeResourceKey( userId );
        List<ResourceBo> rbos = listUserPrivilegeResourceByKey( userId );
        List<ResourceBo> indexs = new ArrayList<ResourceBo>();
        for(int i = 0 ; i < rbos.size(); i ++ ){
            if( rbos.get(i).getResourceId() == resourceBo.getResourceId() ){
                indexs.add(rbos.get(i));
            }
        }
        rbos.removeAll(indexs);
        rbos.add(resourceBo);

        VCache.delByKey( VCache.buildKey( key ) );
        VCache.setVByListMutiElements( key, rbos );
    }

    public static void delUserPrivilegeResourceByKey(String userId, ResourceBo resourceBo){

        String key = getUserPrivilegeResourceKey( userId );
        List<ResourceBo> rbos = listUserPrivilegeResourceByKey( userId );
        List<ResourceBo> indexs = new ArrayList<ResourceBo>();
        for(int i = 0 ; i < rbos.size(); i ++ ){
            if(rbos.get(i).getResourceId() == resourceBo.getResourceId()){
                indexs.add(rbos.get(i));
            }
        }
        rbos.removeAll(indexs);
        //rbos.add(resourceBo);

        VCache.delByKey( VCache.buildKey( key ) );
        VCache.setVByListMutiElements(key, rbos);
    }

    public static void addUserPrivilegeResourceByKey(String userId, ResourceBo resourceBo){

        String key = getUserPrivilegeResourceKey( userId );

        VCache.setVByList(key, resourceBo);
    }

    //======================reflushUserRole========================
//    public static void reflushUserRoleCashe(UserRoleService userRoleService) {
//
//        List<UserRoleBo> retBos = userRoleService.selectAll();
//
//        VCache.delKeysByPrefix( getUserRoleKeyPrefix() );
//        for(UserRoleBo rrb : retBos){
//            String key = getUserRoleKey(rrb.getUserAgentId().toString(),  rrb.getRoleId().toString());
//
//            RoleBo rb = new RoleBo();
//            BeanCopyUtil.copy(rrb, rb);
//            VCache.setVByList(key, rb);
//        }
//        LoggerUtils.info(VCache.class,"reflushUserRoleCache执行完毕" );
//    }
//
//    public static List<RoleBo> listRolesByKey(String userAgentId , String roleId){
//        String key = getUserRoleKey(userAgentId,  roleId);
//        long len = VCache.getLenByList(key);
//        List<RoleBo> lRB = VCache.getVByList(key, 0, (int)len, RoleBo.class);
//        return lRB;
//    }

    public static String getUserRoleKey(String userAgentId , String roleId){
        return getUserRoleKeyPrefix() + userAgentId + "_" + roleId;
    }

    public static String getUserRoleKeyPrefix( ){
        return "_UserRole_";
    }

//===============================
//    public static void updateUserRoleByKey(String userAgentId , String roleId, RoleBo resourceBo){
//        String key = getUserRoleKey(userAgentId,  roleId);
//        List<RoleBo> rbos = listRolesByKey( userAgentId,  roleId );
//        List<RoleBo> indexs = new ArrayList<RoleBo>();
//        for(int i = 0 ; i < rbos.size(); i ++ ){
//            if(rbos.get(i).getRoleId() == resourceBo.getRoleId()){
//                indexs.add(rbos.get(i));
//            }
//        }
//        rbos.removeAll(indexs);
//        rbos.add(resourceBo);
//        VCache.delByKey(key);
//        VCache.setVByListMutiElements(key, rbos);
//    }
//
//    public static void delUserRoleByKey(String userAgentId , String roleId, RoleBo resourceBo){
//        String key = getUserRoleKey(userAgentId,  roleId);
//        List<RoleBo> rbos = listRolesByKey( userAgentId,  roleId );
//        List<RoleBo> indexs = new ArrayList<RoleBo>();
//        for(int i = 0 ; i < rbos.size(); i ++ ){
//            if(rbos.get(i).getRoleId() == resourceBo.getRoleId()){
//                indexs.add(rbos.get(i));
//            }
//        }
//        rbos.removeAll(indexs);
//        //rbos.add(resourceBo);
//        VCache.delByKey(key);
//        VCache.setVByListMutiElements(key, rbos);
//    }

//    public static void addUserRoleByKey(String userAgentId , String roleId, RoleBo resourceBo){
//        String key = getUserRoleKey(userAgentId,  roleId);
//
//        VCache.setVByList(key, resourceBo);
//    }

    public static String getUserRolesKey( String userId ){

        return getUserRolesKeyPrefix() + userId;
    }

    public static String getUserRolesKeyPrefix( ){
        return "_UserRoles_";
    }
}
