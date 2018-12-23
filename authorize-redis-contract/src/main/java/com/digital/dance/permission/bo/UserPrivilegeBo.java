package com.digital.dance.permission.bo;

import com.digital.dance.core.mybatis.page.PageParam;

import java.io.Serializable;
import java.util.Date;

public class UserPrivilegeBo extends PageParam implements Serializable {
    private String userPrivilegeId;

    private String userId;

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

    public String getUserPrivilegeId() {
        return userPrivilegeId;
    }

    public void setUserPrivilegeId(String userPrivilegeId) {
        this.userPrivilegeId = userPrivilegeId == null ? null : userPrivilegeId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
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