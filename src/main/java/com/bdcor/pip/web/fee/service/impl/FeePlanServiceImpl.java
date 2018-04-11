package com.bdcor.pip.web.fee.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.core.exception.ServiceException;
import com.bdcor.pip.core.utils.GenerateKey;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.core.utils.StringUtils;
import com.bdcor.pip.web.fee.dao.FeePlanDAO;
import com.bdcor.pip.web.fee.domain.FeePlanVo;
import com.bdcor.pip.web.fee.service.FeePlanService; 
import com.bdcor.pip.web.sys.rbac.service.UserService;

@Service
@Transactional 
public class FeePlanServiceImpl implements FeePlanService {
	@Autowired
	FeePlanDAO feePlanDAO; 
	@Autowired
	UserService userService; 

	@Override
	public List<FeePlanVo> list(String lccid) {
		
		
		try {
			String projectid= Securitys.getCurrentProject();//只 取出 当前项目
			//lccid = Securitys.getUser().getLccCode();//只能查看自己单位的权限
			
			//按照配置的数据权限 过滤 数据   
			String sql = userService.getUserDataLimitSQL(Securitys.getUserId());   
			if(StringUtils.isBlank(lccid))
			{   
				lccid = sql;
			}else if(sql.indexOf(lccid)>-1){
				lccid= "("+ lccid +")"; 
			}else if(sql.indexOf(lccid)==-1){
				lccid = "('-100')"; 
			}       
			
			
			return feePlanDAO.list(lccid,projectid);
		} catch (Exception e) {
			throw new ServiceException("操作失败", e);
		}
	}

	@Override
	public int save(FeePlanVo vo) {
		if(StringUtils.isBlank(vo.getId())){
			vo.setId(GenerateKey.getKey(GenerateKey.PREFIX_COMMON));
			vo.setOrganId(Securitys.getUser().getLccCode());
			vo.setProjectId(Securitys.getCurrentProject());
			vo.setReg_person(Securitys.getUser().getId() );
			vo.setReg_time(new Date(System.currentTimeMillis())); 
		}   
        
		return feePlanDAO.save(vo);
	}
	
	@Override
	public FeePlanVo getById(String id){
		
		return feePlanDAO.getById( id);
	}

	@Override
	public int delete(String id) {
		
		return feePlanDAO.delete(id);
	}     

}
