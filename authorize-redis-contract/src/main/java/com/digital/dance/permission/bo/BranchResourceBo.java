package com.digital.dance.permission.bo;
import com.digital.dance.core.mybatis.page.PageParam;

import java.io.Serializable;
import java.util.Date;

public class BranchResourceBo extends PageParam implements Serializable {
    /**
     * 分公司id
     *
     * @mbg.generated
     */
    private String orgId;

    private String departmentId;

    /**
     * 资源id
     *
     * @mbg.generated
     */
    private String resourceId;

    /**
     * 1-后台资源，2-前台资源
     *
     * @mbg.generated
     */
    private String type;

    private static final long serialVersionUID = 1L;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }


    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orgId=").append(orgId);
        sb.append(", resourceId=").append(resourceId);
        sb.append(", type=").append(type);
        sb.append(", createAt=").append(insertOn);
        sb.append(", updateAt=").append(updateOn);
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
        BranchResourceBo other = (BranchResourceBo) that;
        return (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
            && (this.getResourceId() == null ? other.getResourceId() == null : this.getResourceId().equals(other.getResourceId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getInsertOn() == null ? other.getInsertOn() == null : this.getInsertOn().equals(other.getInsertOn()))
            && (this.getUpdateOn() == null ? other.getUpdateOn() == null : this.getUpdateOn().equals(other.getUpdateOn()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOrgId() == null) ? 0 : getOrgId().hashCode());
        result = prime * result + ((getResourceId() == null) ? 0 : getResourceId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getInsertOn() == null) ? 0 : getInsertOn().hashCode());
        result = prime * result + ((getUpdateOn() == null) ? 0 : getUpdateOn().hashCode());
        return result;
    }

    /**
     * 安全匹配路径
     *
     * @mbg.generated
     */
    private String matchPath;

    public String getMatchPath() {
        return matchPath;
    }

    public void setMatchPath(String matchPath) {
        this.matchPath = matchPath;
    }

    /**
     * 资源地址
     *
     * @mbg.generated
     */
    private String url;
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    /**
     * 资源httpMethod
     *
     * @mbg.generated
     */
    private String httpMethod;

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    private String routingUrl;

    public String getRoutingUrl() {
        return routingUrl;
    }

    public void setRoutingUrl(String routingUrl) {
        this.routingUrl = routingUrl;
    }

    private String menuId;
    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
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