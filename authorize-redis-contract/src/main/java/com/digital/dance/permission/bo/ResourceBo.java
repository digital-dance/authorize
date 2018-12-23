package com.digital.dance.permission.bo;

import com.digital.dance.core.mybatis.page.PageParam;

import java.io.Serializable;
import java.util.Date;

public class ResourceBo extends PageParam implements Serializable {
    /**
     * 资源编号
     *
     * @mbg.generated
     */
    private String resourceId;

    /**
     * 图标
     *
     * @mbg.generated
     */
    private String icon;

    /**
     * 资源名称
     *
     * @mbg.generated
     */
    private String resourceName;

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

    private String menuId;
    /**
     * 资源地址
     *
     * @mbg.generated
     */
    private String module;

    /**
     * 菜单排序
     *
     * @mbg.generated
     */
    private String sort;

    /**
     * 父资源编号
     *
     * @mbg.generated
     */
    private String parentId;

    /**
     * 类型:1-后台资源，2-前台资源
     *
     * @mbg.generated
     */
    private String type;

    /**
     * 角色字符串
     *
     * @mbg.generated
     */
    private String resourceValue;


    private static final long serialVersionUID = 1L;

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
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

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    private String operationResourceId;
    public String getOperationResourceId() {
        return operationResourceId;
    }

    public void setOperationResourceId(String operationResourceId) {
        this.operationResourceId = operationResourceId == null ? null : operationResourceId.trim();
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getResourceValue() {
        return resourceValue;
    }

    public void setResourceValue(String resourceValue) {
        this.resourceValue = resourceValue;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", resourceId=").append(resourceId);
        sb.append(", icon=").append(icon);
        sb.append(", resourceName=").append(resourceName);
        sb.append(", matchPath=").append(matchPath);
        sb.append(", url=").append(url);
        sb.append(", sort=").append(sort);
        sb.append(", parentId=").append(parentId);
        sb.append(", type=").append(type);
        sb.append(", createAt=").append(insertOn);
        sb.append(", updateAt=").append(updateOn);
        sb.append(", resourceValue=").append(resourceValue);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ResourceBo other = (ResourceBo) that;
        return (this.getResourceId() == null ? other.getResourceId() == null : this.getResourceId().equals(other.getResourceId()))
                && (this.getIcon() == null ? other.getIcon() == null : this.getIcon().equals(other.getIcon()))
                && (this.getResourceName() == null ? other.getResourceName() == null : this.getResourceName().equals(other.getResourceName()))
                && (this.getMatchPath() == null ? other.getMatchPath() == null : this.getMatchPath().equals(other.getMatchPath()))
                && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
                && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()))
                && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()))
                && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
                && (this.getInsertOn() == null ? other.getInsertOn() == null : this.getInsertOn().equals(other.getInsertOn()))
                && (this.getUpdateOn() == null ? other.getUpdateOn() == null : this.getUpdateOn().equals(other.getUpdateOn()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getResourceId() == null) ? 0 : getResourceId().hashCode());
        result = prime * result + ((getIcon() == null) ? 0 : getIcon().hashCode());
        result = prime * result + ((getResourceName() == null) ? 0 : getResourceName().hashCode());
        result = prime * result + ((getMatchPath() == null) ? 0 : getMatchPath().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getInsertOn() == null) ? 0 : getInsertOn().hashCode());
        result = prime * result + ((getUpdateOn() == null) ? 0 : getUpdateOn().hashCode());
        return result;
    }
}