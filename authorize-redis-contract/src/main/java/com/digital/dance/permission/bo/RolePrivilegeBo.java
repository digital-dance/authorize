package com.digital.dance.permission.bo;

import com.digital.dance.core.mybatis.page.PageParam;

import java.io.Serializable;
import java.util.Date;

public class RolePrivilegeBo extends PageParam implements Serializable {
    private String rolePrivilegeId;

    private String roleId;

    private String orgId;

    private String departmentId;

    private String privilegeId;

    private String privilegeCategoryCode;

    private Integer state;

    private Date insertOn;

    private String insertBy;

    private Date updateOn;

    private String updateBy;

    private String menuId;
    /**
     * 资源httpMethod
     *
     * @mbg.generated
     */
    private String httpMethod;

    /**
     * 安全匹配路径
     *
     * @mbg.generated
     */
    private String matchPath;

    /**
     * 资源地址
     *
     * @mbg.generated
     */
    private String url;

    private String routingUrl;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMatchPath() {
        return matchPath;
    }

    public void setMatchPath(String matchPath) {
        this.matchPath = matchPath;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getRoutingUrl() {
        return routingUrl;
    }

    public void setRoutingUrl(String routingUrl) {
        this.routingUrl = routingUrl;
    }

    private String operationResourceId;
    public String getOperationResourceId() {
        return operationResourceId;
    }

    public void setOperationResourceId(String operationResourceId) {
        this.operationResourceId = operationResourceId == null ? null : operationResourceId.trim();
    }

    public String getRolePrivilegeId() {
        return rolePrivilegeId;
    }

    public void setRolePrivilegeId(String rolePrivilegeId) {
        this.rolePrivilegeId = rolePrivilegeId == null ? null : rolePrivilegeId.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId == null ? null : departmentId.trim();
    }

    public String getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(String privilegeId) {
        this.privilegeId = privilegeId == null ? null : privilegeId.trim();
    }

    public String getPrivilegeCategoryCode() {
        return privilegeCategoryCode;
    }

    public void setPrivilegeCategoryCode(String privilegeCategoryCode) {
        this.privilegeCategoryCode = privilegeCategoryCode == null ? null : privilegeCategoryCode.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getInsertOn() {
        return insertOn;
    }

    public void setInsertOn(Date insertOn) {
        this.insertOn = insertOn;
    }

    public String getInsertBy() {
        return insertBy;
    }

    public void setInsertBy(String insertBy) {
        this.insertBy = insertBy == null ? null : insertBy.trim();
    }

    public Date getUpdateOn() {
        return updateOn;
    }

    public void setUpdateOn(Date updateOn) {
        this.updateOn = updateOn;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }
}