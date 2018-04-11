package com.bdcor.pip.web.spem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.core.utils.StringUtils;
import com.bdcor.pip.web.spem.dao.FrozenTubeMapper;
import com.bdcor.pip.web.spem.domain.FrozenTubeVo;
import com.bdcor.pip.web.spem.filter.FrozenTubeFilter;
import com.bdcor.pip.web.spem.service.FrozenTubeService;
import com.bdcor.pip.web.sys.rbac.service.UserService;
@Service
@Transactional
public class FrozenTubeServiceImpl implements FrozenTubeService {
	
	@Autowired
	private FrozenTubeMapper frozenTubeMapper; 
	@Autowired
	private UserService userService; 

	@Override
	public List<FrozenTubeVo> spTubeListByfilter(FrozenTubeFilter filter) {
		// TODO Auto-generated method stub
		filter.setProjectId (Securitys.getUser().getCurrent_projectId());
		
		//按照配置的数据权限 过滤 数据   
		String sql = userService.getUserDataLimitSQL(Securitys.getUserId());   
		if(StringUtils.isBlank(filter.getLccIdEq() ))
		{   
			filter.setLccIdEq(sql);
		}else if(sql.indexOf(filter.getLccIdEq())>-1){
			filter.setLccIdEq("("+filter.getLccIdEq()+")"); 
		}else if(sql.indexOf(filter.getLccIdEq())==-1){
			filter.setLccIdEq("('-100')"); 
		}       
		return frozenTubeMapper.spTubeListByfilter(filter);
	} 
	

}
