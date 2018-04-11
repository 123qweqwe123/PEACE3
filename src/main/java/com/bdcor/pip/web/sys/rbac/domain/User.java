package com.bdcor.pip.web.sys.rbac.domain;

import java.util.Date;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class User{
    private String id;
    @NotBlank
    @Length(max = 30)
    private String loginName;
    
    @Length(max = 30)
    private String password;
    
    @Length(max = 30)
    private String name;

    private String salt;

    @Email
    private String email;

    private Integer isAdmin;

    //@NotBlank
    private String organizationId;

    private Integer status;

    private String createUser;

    private Date createDatetime;

    private String updateUser;

    private Date updateDatetime;

    private String remark;

    private Integer isDelete;
    
    private Integer tenantStatus;
    
    private String projectId;
    
    private String lccCode;
    
    private String userCode;

    public User(String id, String loginName, String password, String name, String salt, String email, Integer isAdmin, String organizationId, Integer status, String createUser,
            Date createDatetime, String updateUser, Date updateDatetime, String remark, Integer isDelete) {
        this.id = id;
        this.loginName = loginName;
        this.password = password;
        this.name = name;
        this.salt = salt;
        this.email = email;
        this.isAdmin = isAdmin;
        this.organizationId = organizationId;
        this.status = status;
        this.createUser = createUser;
        this.createDatetime = createDatetime;
        this.updateUser = updateUser;
        this.updateDatetime = updateDatetime;
        this.remark = remark;
        this.isDelete = isDelete;
    }

    public User() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId == null ? null : organizationId.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getTenantStatus() {
        return tenantStatus;
    }

    public void setTenantStatus(Integer tenantStatus) {
        this.tenantStatus = tenantStatus;
    }

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getLccCode() {
		return lccCode;
	}

	public void setLccCode(String lccCode) {
		this.lccCode = lccCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

}