package com.digital.dance.permission.service;


import com.digital.dance.permission.bo.ResourceBo;

import java.util.List;

/**
* SecurityService接口
* Created by admin on 2017/6/29.
*/
public interface SecurityService {

    /**
     * 根据用户 Id 获取所拥有的权限(合并用户角色权限与用户所属公司权限)
     * @param userAgentId
     * @return
     */
    List<ResourceBo> listResourceByUserAgentId(Long userAgentId);
//
//
//    /**
//     * 根据用户id获取所属的角色
//     * @param userAgentId
//     * @return
//     */
//    List<RoleBo> listRoleByUserAgentId(Long userAgentId);
//

}