package com.bdcor.pip.web.sys.rbac.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.core.exception.ServiceException;
import com.bdcor.pip.core.utils.Collections3;
import com.bdcor.pip.core.utils.GenerateKey;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.sys.logmgt.service.LogService;
import com.bdcor.pip.web.sys.rbac.dao.OrganizationDao;
import com.bdcor.pip.web.sys.rbac.dao.UserDao;
import com.bdcor.pip.web.sys.rbac.domain.OrganType;
import com.bdcor.pip.web.sys.rbac.domain.Organization;
import com.bdcor.pip.web.sys.rbac.domain.User;
import com.bdcor.pip.web.sys.rbac.service.OrganizationService;

@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService{

    private static Logger logger = LoggerFactory.getLogger(OrganizationServiceImpl.class);

    @Autowired
    private OrganizationDao organizationDao;
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private LogService logService;

    /*
     * (non-Javadoc)
     * @see com.bdcor.pip.web.sys.rbac.service.OrganizationService#queryById(java.lang.String)
     */
    @Override
    public Organization getById(String id) {
        try {
            return organizationDao.queryById(id);
        } catch (Exception e) {
            //logService.error(Consts.SystemModel.SYS,"查询组织架构列表失败");
            throw new ServiceException("查询组织架构列表失败", e);
        }
    }

    /*
     * (non-Javadoc)
     * @see com.bdcor.pip.web.sys.rbac.service.OrganizationService#list()
     */
    @Override
    public List<Organization> list(Map condit) {
        try {
            return organizationDao.list(condit);
        } catch (Exception e) {
            //logService.error(Consts.SystemModel.SYS, "查询组织架构列表失败");
        	e.printStackTrace();
            throw new ServiceException("查询组织架构列表失败", e);
        }

    }

    /*
     * (non-Javadoc)
     * @see com.bdcor.pip.web.sys.rbac.service.OrganizationService#save(com.bdcor.pip.web.sys.rbac.domain.Organization)
     */
    @Override
    public String save(Organization organization) {
        Organization oldOrganization = organizationDao.findByNameAndOrganId(organization.getName(), null,Securitys.getOrganId());
        if (null != oldOrganization) {
            throw new ServiceException("已存在同名组织架构");
        }
        try {
            //String organizationId = Identities.uuid();
        	String organizationId = GenerateKey.getKey(GenerateKey.PREFIX_ORGANIZATION);
            organization.setId(organizationId);
            organization.setIsDelete(0);
            organization.setCreateUser(Securitys.getUserId());
            organization.setCreateDatetime(new Date());
            organizationDao.save(organization);
            logger.info("用户" + Securitys.getUserId() + "创建组织架构:" + "organizationId");
            //logService.info(Consts.SystemModel.SYS, "创建组织架构");
            return organizationId;
        } catch (Exception e) {
            //logService.error(Consts.SystemModel.SYS, "添加组织架构列表失败");
            throw new ServiceException("添加组织架构列表失败", e);
        }
    }

    /*
     * (non-Javadoc)
     * @see com.bdcor.pip.web.sys.rbac.service.OrganizationService#delete(java.util.List)
     */
    @Override
    public void delete(List<String> idList) {
        idList.remove("");
        idList.remove(null);
        if (Collections3.isEmpty(idList)) {
            throw new ServiceException("请选择要删除的组织机构");
        }
        try {
            organizationDao.delete(idList);
            logger.info("用户" + Securitys.getUserId() + "删除组织架构:" + Collections3.convertToString(idList, ","));
            //logService.warning(Consts.SystemModel.SYS, "删除组织架构");
        } catch (Exception e) {
            //logService.error(Consts.SystemModel.SYS, "删除组织架构失败");
            throw new ServiceException("删除租户失败", e);
        }

    }

    /*
     * (non-Javadoc)
     * @see com.bdcor.pip.web.sys.rbac.service.OrganizationService#update(com.bdcor.pip.web.sys.rbac.domain.Organization)
     */
    @Override
    public void update(Organization organization) {
        String tenantId = organization.getId();
        if (StringUtils.isBlank(tenantId)) {
            throw new ServiceException("请选择要修改的组织架构");
        }
        Organization oldOrganization = organizationDao.findByNameAndOrganId(organization.getName(),organization.getId(), tenantId);
        if (null != oldOrganization && !tenantId.equals(oldOrganization.getId())) {
            throw new ServiceException("已存在同名组织架构");
        }
        try {
            organization.setUpdateUser(Securitys.getUserId());
            organization.setUpdateDatetime(new Date());
            organizationDao.update(organization);
            logger.info("用户" + Securitys.getUserId() + "修改组织架构:" + organization.getId());
            //logService.info(Consts.SystemModel.SYS, "修改组织架构");
        } catch (Exception e) {
            //logService.error(Consts.SystemModel.SYS, "修改组织架构失败");
            throw new ServiceException("修改组织架构失败", e);
        }

    }

    /*
     * (non-Javadoc)
     * @see com.bdcor.pip.web.sys.rbac.service.OrganizationService#rename(java.lang.String, java.lang.String)
     */
    @Override
    public void rename(String id, String newName) {
        
        try {
            Organization org = getById(id);
            
            org.setName(newName);
            
            update(org);
            //logService.info(Consts.SystemModel.SYS, "组织机构重命名");
        } catch (Exception e) {
            
            //logService.error(Consts.SystemModel.SYS, "组织机构重命名失败");
            throw new ServiceException("组织机构重命名失败",e);
        }
    }
    
    public boolean checkExists(String name,String id){
    	Organization oldOrganization = organizationDao.findByNameAndOrganId(name,id, Securitys.getOrganId());
        if (null != oldOrganization) {
            return true;
        }
        return false;
    }

	@Override
	public List<Organization> getOrgTree(Integer[] types) {
		return organizationDao.getOrgTree(types);
	}

	@Override
	public List<OrganType> getOrganType() {
		return organizationDao.getOrganType();
	}

	@Override
	public Organization getDepartOrCompanyByUserId(String userId, Integer type) {
		User user = userDao.queryById(userId);
		Organization org = organizationDao.getDepartOrCompanyByUserId(user.getOrganizationId(), type);
		return org;
	}

    
}
