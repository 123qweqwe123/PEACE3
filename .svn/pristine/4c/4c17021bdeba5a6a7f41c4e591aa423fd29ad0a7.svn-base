package com.bdcor.pip.web.sys.rbac.service;

import java.util.List;
import java.util.Map;

import com.bdcor.pip.web.sys.rbac.domain.OrganType;
import com.bdcor.pip.web.sys.rbac.domain.Organization;

public interface OrganizationService{

    /**
     * 查询全部
     * @return
     */
    List<Organization> list(Map p);

    /**
     * 添加组织机构
     * @param organization
     */
    String save(Organization organization);

    /**
     * 更新组织机构
     * @param organization
     */
    void update(Organization organization);

    /**
     * 批量删除
     * @param idList
     */
    void delete(List<String> idList);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Organization getById(String id);

    /**
     * 组织机构重命名
     * @param id
     * @param newName
     */
    void rename(String id, String newName);
    
    /**
     * 检查是否存在相同名称
     * @param name
     * @param id
     * @return
     */
    public boolean checkExists(String name,String id);
    
    public List<Organization> getOrgTree(Integer[] types);

	List<OrganType> getOrganType();

	/**
	 * 根据用户Id获取该用户所在部门或公司
	 * @param userId 用户ID
	 * @return
	 */
	public Organization getDepartOrCompanyByUserId(String userId, Integer type);
}
