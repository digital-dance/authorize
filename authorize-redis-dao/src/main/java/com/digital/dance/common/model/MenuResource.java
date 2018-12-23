package com.digital.dance.common.model;

import com.digital.dance.core.mybatis.page.PageParam;

import java.io.Serializable;
import java.util.Date;

public class MenuResource extends PageParam implements Serializable {
    private String resourceId;

    private String menuId;

    private String operationResourceId;

    private Integer state;

    private Date insertOn;

    private String insertBy;

    private Date updateOn;

    private String updateBy;

    private String operationResourceUrl;

    private String operationResourceHttpMethod;

    private String routingUrl;

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId == null ? null : resourceId.trim();
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

    public String getOperationResourceId() {
        return operationResourceId;
    }

    public void setOperationResourceId(String operationResourceId) {
        this.operationResourceId = operationResourceId == null ? null : operationResourceId.trim();
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

    public String getOperationResourceUrl() {
        return operationResourceUrl;
    }

    public void setOperationResourceUrl(String operationResourceUrl) {
        this.operationResourceUrl = operationResourceUrl == null ? null : operationResourceUrl.trim();
    }

    public String getOperationResourceHttpMethod() {
        return operationResourceHttpMethod;
    }

    public void setOperationResourceHttpMethod(String operationResourceHttpMethod) {
        this.operationResourceHttpMethod = operationResourceHttpMethod == null ? null : operationResourceHttpMethod.trim();
    }

    public String getRoutingUrl() {
        return routingUrl;
    }

    public void setRoutingUrl(String routingUrl) {
        this.routingUrl = routingUrl == null ? null : routingUrl.trim();
    }
}