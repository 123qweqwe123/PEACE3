package com.bdcor.pip.web.sys.rbac.domain;

import java.util.Date;

public class Permission {
    //主键ID
	private String id;
	public String getId() {
		return  id;
	}
	public void setId(String id) {
		this.id = id;
	}
    //权限名称
	private String name;
	public String getName() {
		return  name;
	}
	public void setName(String name) {
		this.name = name;
	}
    //权限代码
	private String code;
	public String getCode() {
		return  code;
	}
	public void setCode(String code) {
		this.code = code;
	}
    //父权限ID
	private String parentId;
	public String getParentId() {
		return  parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
    /*
    
      */
    //菜单级别
	private Long grade;
	public Long getGrade() {
		return  grade;
	}
	public void setGrade(Long grade) {
		this.grade = grade;
	}
    /*
    
      */
    //排序号
	private Long indexNo;
	public Long getIndexNo() {
		return  indexNo;
	}
	public void setIndexNo(Long indexNo) {
		this.indexNo = indexNo;
	}
    /*
    
      */
    //是否叶节点
	private Integer isLeaf;
	public Integer getIsLeaf() {
		return  isLeaf;
	}
	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}
    //URL路径
	private String url;
	public String getUrl() {
		return  url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
    //创建人
	private String createUser;
	public String getCreateUser() {
		return  createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
    /*
    
      */
    //创建时间
	private Date createDatetime;
	public Date getCreateDatetime() {
		return  createDatetime;
	}
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}
    //更新人
	private String updateUser;
	public String getUpdateUser() {
		return  updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
    /*
    
      */
    //更新时间
	private Date updateDatetime;
	public Date getUpdateDatetime() {
		return  updateDatetime;
	}
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
    //备注
	private String remark;
	public String getRemark() {
		return  remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
    /*
    
      */
    //已删除
	private Integer isDelete;
	public Integer getIsDelete() {
		return  isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	private Integer permissionType;
	public Integer getPermissionType() {
		return permissionType;
	}
	public void setPermissionType(Integer permissionType) {
		this.permissionType = permissionType;
	}
	
	private Integer isResource;
	public Integer getIsResource() {
		return isResource;
	}
	public void setIsResource(Integer isResource) {
		this.isResource = isResource;
	}
	
}