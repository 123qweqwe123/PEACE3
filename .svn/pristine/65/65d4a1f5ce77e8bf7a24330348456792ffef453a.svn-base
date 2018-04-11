package com.bdcor.pip.web.sys.rbac.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.sys.rbac.domain.OrganType;
import com.bdcor.pip.web.sys.rbac.domain.Organization;

@MyBatisRepository
public interface OrganizationDao{

    void save(Organization organization);

    Organization queryById(String id);

    List<Organization> list(Map hashMap);

    void delete(List<String> idList);

    void update(Organization organization);

    Organization findByNameAndOrganId(@Param("name") String name,@Param("id") String id, @Param("organId") String organId);

	List<Organization> getOrgTree(Integer[] types);

	List<OrganType> getOrganType();

	Organization getDepartOrCompanyByUserId(@Param("organizationId") String organizationId, @Param("type") Integer type);
}
