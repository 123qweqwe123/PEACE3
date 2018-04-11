package com.bdcor.pip.web.sys.rbac.domain;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class Organization{
    private String id;
    @NotBlank
    @Length(max = 50)
    private String name;

    private Long grade;

    private String parentId;

    //private String tenantId;

    private String createUser;

    private Date createDatetime;

    private String updateUser;

    private Date updateDatetime;

    private String remark;

    private Integer isDelete;
    
    private Integer type;
    
    private String principal;

    public Organization(String id, String name, Long grade, String parentId, String createUser, Date createDatetime, String updateUser, Date updateDatetime, String remark,
            Integer isDelete, Integer type, String principal) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.parentId = parentId;
        //this.tenantId = tenantId;
        this.createUser = createUser;
        this.createDatetime = createDatetime;
        this.updateUser = updateUser;
        this.updateDatetime = updateDatetime;
        this.remark = remark;
        this.isDelete = isDelete;
        this.type = type;
        this.principal = principal;
    }

    public Organization() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getGrade() {
        return grade;
    }

    public void setGrade(Long grade) {
        this.grade = grade;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}
    
}