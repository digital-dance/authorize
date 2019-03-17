package com.digital.dance.client.core.shiro.service.impl;

import com.digital.dance.client.core.shiro.cache.VCache;
import com.digital.dance.framework.sso.entity.LoginUserRole;
import com.digital.dance.permission.bo.ResourceBo;
import com.digital.dance.permission.bo.RoleBranchResourceBo;
import com.digital.dance.permission.service.PermissionService;

import org.springframework.beans.factory.annotation.Autowired;

import com.digital.dance.permission.bo.*;

import com.digital.dance.permission.service.PermissionService;


import java.util.ArrayList;
import java.util.TreeSet;
import java.util.List;
import java.util.Set;

//

import com.digital.dance.common.utils.SpringUtils;
import com.digital.dance.framework.infrastructure.commons.*;
import com.digital.dance.framework.sso.entity.LoginInfo;

import com.digital.dance.framework.sso.util.LoginContext;
import com.digital.dance.framework.sso.util.SSOLoginManageHelper;

import com.digital.dance.commons.exception.BizException;

import com.digital.dance.commons.security.utils.RSACoderUtil;
import com.digital.dance.commons.serialize.json.utils.JSONUtils;

import java.util.*;

import com.digital.dance.common.utils.BeanCopyUtil;
//
public class PrivilegeCacheManager {
    static Log log = new Log(PrivilegeCacheManager.class);


    public static List<ResourceBo> listRoleBranchResourceByKey( String roleId, String orgId, String departmentId ){

//        SSOLoginManageHelper ssologinManageHelper = SpringUtils.getBean("ssologinManageHelper");

        departmentId = departmentId == null ? "" : departmentId;
        String key = getRoleBranchResourceKey( roleId, orgId, departmentId );
        long len = VCache.getLenByList(key);
        List<ResourceBo> lRB = VCache.getVByList(key, 0, (int)len, ResourceBo.class);

        try {
            SSOLoginManageHelper ssologinManageHelper = SpringUtils.getBean("ssologinManageHelper");
            ResponseVo responseVo = new ResponseVo();
            if( lRB == null || lRB.size() < 1 ){

                Map<String, String> pMap = new HashMap<String, String>();
                pMap.put("roleId", roleId);
                pMap.put("orgId", orgId);
                pMap.put("departmentId", departmentId);
                String responseVoStr = HttpClientUtil.executeGetReq(
                        ssologinManageHelper.getPrivilegeCacheServiceUrl()+"/permission/resource"
                        , new HashMap<String, String>(), pMap);
                responseVo = (ResponseVo) JSONUtils.toObject(responseVoStr, ResponseVo.class);
                lRB = new ArrayList<ResourceBo>();
                List lRBObjs = (List)responseVo.getResult();
                for (Object obj : lRBObjs){
                    ResourceBo resourceBo = new ResourceBo();
                    BeanCopyUtil.copy(obj, resourceBo);
                    lRB.add(resourceBo);
                }
            }

        } catch (Exception ex){
            log.error("sso client error.", ex);
            throw new BizException("decode token error.");
        }
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

        try {
            SSOLoginManageHelper ssologinManageHelper = SpringUtils.getBean("ssologinManageHelper");
            ResponseVo responseVo = new ResponseVo();
            if( lRB == null || lRB.size() < 1 ){

                Map<String, String> pMap = new HashMap<String, String>();
                pMap.put("userId", userId);

                String responseVoStr = HttpClientUtil.executeGetReq(
                        ssologinManageHelper.getPrivilegeCacheServiceUrl()+"/permission/resource/user"
                        , new HashMap<String, String>(), pMap);
                responseVo = (ResponseVo) JSONUtils.toObject(responseVoStr, ResponseVo.class);
                lRB = new ArrayList<ResourceBo>();
                List lRBObjs = (List)responseVo.getResult();
                for (Object obj : lRBObjs){
                    ResourceBo resourceBo = new ResourceBo();
                    BeanCopyUtil.copy(obj, resourceBo);
                    lRB.add(resourceBo);
                }
            }

        } catch (Exception ex){
            log.error("sso client error.", ex);
            throw new BizException("decode token error.");
        }
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

    public static List<LoginUserRole> getLoginUserRole( String userId ){
        String key = PrivilegeCacheManager.getUserRolesKey( userId );
        long len = VCache.getLenByList( key );
        List<LoginUserRole> loginUserRoles = VCache.getVByList(key, 0, (int)len, LoginUserRole.class);

        try {
            SSOLoginManageHelper ssologinManageHelper = SpringUtils.getBean("ssologinManageHelper");
            ResponseVo responseVo = new ResponseVo();
            if( loginUserRoles == null || loginUserRoles.size() < 1 ){

                Map<String, String> pMap = new HashMap<String, String>();
                pMap.put("userId", userId);

                String responseVoStr = HttpClientUtil.executeGetReq(
                        ssologinManageHelper.getLoginServiceUrl()+"/login/loginUserRole"
                        , new HashMap<String, String>(), pMap);
                responseVo = (ResponseVo) JSONUtils.toObject(responseVoStr, ResponseVo.class);
                loginUserRoles = new ArrayList<LoginUserRole>();
                List lRBObjs = (List)responseVo.getResult();
                for (Object obj : lRBObjs){
                    LoginUserRole loginUserRole = new LoginUserRole();
                    BeanCopyUtil.copy(obj, loginUserRole);
                    loginUserRoles.add(loginUserRole);
                }
            }

        } catch (Exception ex){
            log.error("sso client error.", ex);
            throw new BizException("decode token error.");
        }
        return loginUserRoles;
    }
}
