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
import com.bdcor.pip.web.fee.dao.PipActualRegMapper;

import com.bdcor.pip.web.fee.domain.PipActualReg;
import com.bdcor.pip.web.fee.filter.FeeLccRegFilter;
import com.bdcor.pip.web.fee.service.FeeLccRegService;
import com.bdcor.pip.web.sys.rbac.service.UserService;

@Service
@Transactional
public class FeeLccRegServiceImpl implements FeeLccRegService {
	@Autowired
	PipActualRegMapper pipActDAO; 
	@Autowired
	UserService userService; 

	@Override
	public List<PipActualReg> list(FeeLccRegFilter f) {
		try {
			f.setProjectid( Securitys.getCurrentProject() );
			//按照配置的数据权限 过滤 数据   
			String sql = userService.getUserDataLimitSQL(Securitys.getUserId());   
			if(StringUtils.isBlank(f.getLcc_code()))
			{   
				f.setLcc_code(sql);
			}else if(sql.indexOf(f.getLcc_code())>-1){
				f.setLcc_code("("+f.getLcc_code()+")"); 
			}else if(sql.indexOf(f.getLcc_code())==-1){
				f.setLcc_code("('-100')"); 
			}      
			
			return pipActDAO.list(f);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("查询出错！",e);
		}  
	}
	
	public PipActualReg getById(String id){
		
		return pipActDAO.getById(id);
	} 

	@Override
	public int save(PipActualReg vo) {
		
		 try {
			if(StringUtils.isBlank(vo.getId())){
			    	vo.setId(GenerateKey.getKey(GenerateKey.PREFIX_FEEACT));
			    	vo.setLccid(Securitys.getUser().getLccCode()  );
			    	vo.setLcc_name(Securitys.getUser().getLccName()  );
			    	vo.setInputime(new Date(System.currentTimeMillis()));
			    	vo.setProjectid(Securitys.getCurrentProject());
			    	vo.setProject_name( Securitys.getUser().getCurrent_projectName() );
			    	vo.setSubmitter(Securitys.getUser().getId());
			    	return pipActDAO.save(vo); 
			    } else{ 
			    	return pipActDAO.update(vo); 
			    } 
		} catch (Exception e) {
			 throw new ServiceException("操作数据库失败!",e); 
		}  
        
		
	}

	@Override
	public int delete(String id) {
		
		return pipActDAO.delete(id);  
	}

	@Override
	public int update(PipActualReg ar) {
		// TODO Auto-generated method stub
		return 0;
	}     

}
