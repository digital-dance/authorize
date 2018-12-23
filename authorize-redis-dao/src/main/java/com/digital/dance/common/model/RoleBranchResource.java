package com.digital.dance.common.model;

import com.digital.dance.core.mybatis.page.PageParam;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hpe on 2017/11/23.
 */
public class RoleBranchResource extends PageParam implements Serializable {
    /**
     * 分公司id
     *
     * @mbg.generated
     */
    private String orgId;

    private String departmentId;

    /**
     *
     */
    private String roleId;

    /**
     * 资源id
     *
     * @mbg.generated
     */
    private String resourceId;


    /**
     * 资源名称
     *
     * @mbg.generated
     */
    private String resourceName;

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

    /**
     *
     *
     * @mbg.generated
     */
    private String resourceValue;

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceValue() {
        return resourceValue;
    }

    public void setResourceValue(String resourceValue) {
        this.resourceValue = resourceValue;
    }
    private Integer state;

    private Date insertOn;

    private String insertBy;

    private Date updateOn;

    private String updateBy;

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
