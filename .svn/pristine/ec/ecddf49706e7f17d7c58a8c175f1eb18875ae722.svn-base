package com.bdcor.pip.web.spem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.core.utils.StringUtils;
import com.bdcor.pip.web.spem.dao.ScmStaticMapper;
import com.bdcor.pip.web.spem.domain.ScmStaticVo;
import com.bdcor.pip.web.spem.filter.ScmStaticFilter;
import com.bdcor.pip.web.spem.service.ScmStaticService;
import com.bdcor.pip.web.sys.rbac.service.UserService;
@Service
@Transactional
public class ScmStaticServiceImpl implements ScmStaticService {
	
	@Autowired
	private ScmStaticMapper scmStaticMapper;
	@Autowired
	private UserService userService; 

	@Override
	public List<ScmStaticVo> scmStatic(ScmStaticFilter filter) {
		filter.setProjectId (Securitys.getUser().getCurrent_projectId());
		
		//按照配置的数据权限 过滤 数据   
		String sql = userService.getUserDataLimitSQL(Securitys.getUserId());   
		filter.setLccCode(sql);
		if(StringUtils.isNotBlank(filter.getAreaCode()) && sql.indexOf(filter.getAreaCode())==-1){
			filter.setAreaCode("-100"); 
		}    
		return scmStaticMapper.scmStatic(filter);
	} 


}
