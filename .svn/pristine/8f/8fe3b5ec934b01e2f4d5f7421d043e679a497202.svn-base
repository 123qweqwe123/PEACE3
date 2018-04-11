package com.bdcor.pip.web.spem.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.bdcor.pip.core.exception.ServiceException;
import com.bdcor.pip.core.utils.GenerateKey;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.core.utils.StringUtils;
import com.bdcor.pip.web.spem.dao.IceBoxRegVoMapper;
import com.bdcor.pip.web.spem.domain.IceBoxRegVo;
import com.bdcor.pip.web.spem.domain.SpemTypeVo;
import com.bdcor.pip.web.spem.filter.IceBoxRegFilter;
import com.bdcor.pip.web.spem.service.IceBoxRegService;
import com.bdcor.pip.web.sys.rbac.service.UserService;
@Service
@Transactional
public class IceBoxRegServiceImpl implements IceBoxRegService { 
	
	@Autowired
	private IceBoxRegVoMapper iceBoxRegDao; 
	
	@Autowired
	UserService userService; 
	/**
	 * 冰箱登记查询
	 */
	@Override
	public List<IceBoxRegVo> list(String lccId) { 
		try {
			String  projectid = Securitys.getCurrentProject();
			//冰箱登记，只能查看自己中心的冰箱
			lccId = Securitys.getUser().getLccCode();
			return iceBoxRegDao.list(lccId, projectid);
		} catch (Exception e) {
			throw new ServiceException("操作失败", e);
		}
	}
	
	/**
	 * 冰箱查询
	 */
	@Override
	public  List<IceBoxRegVo> qlist(IceBoxRegFilter fi){
		
		fi.setProject_id(Securitys.getCurrentProject());
		//按照配置的数据权限 过滤 数据   
		String sql = userService.getUserDataLimitSQL(Securitys.getUserId());   
		if(StringUtils.isBlank(fi.getLcc_code()))
		{   
			fi.setLcc_code(sql);
		}else if(sql.indexOf(fi.getLcc_code())>-1){
			fi.setLcc_code("("+fi.getLcc_code()+")"); 
		}else if(sql.indexOf(fi.getLcc_code())==-1){
			fi.setLcc_code("('-100')"); 
		}       
		return iceBoxRegDao.qlist(fi);
	}    

	@Override
	public int save(IceBoxRegVo vo) {

		try {
			if(StringUtils.isBlank(vo.getId())){
				String id = GenerateKey.getKey(GenerateKey.PREFIX_COMMON);
				vo.setId(id);
				vo.setProjectid(Securitys.getCurrentProject());
				vo.setInputer(Securitys.getUserId()); 
				vo.setRegTime(new Date(System.currentTimeMillis()));
				
				String stype = vo.getSpemType();
				String[] types =  stype.split("、");
				for(String type:types){
					if(!StringUtils.isBlank(type)){
						SpemTypeVo svo = new SpemTypeVo();
						svo.setProject_id(Securitys.getCurrentProject());
						svo.setLcc_code(Securitys.getUser().getLccCode());
						svo.setFridge_id(id);
						svo.setBox_type(type);
						svo.setCreate_by(Securitys.getUserId()); 
						svo.setCreate_date(new Date(System.currentTimeMillis()));
						iceBoxRegDao.save_spemType(svo);
					}
				}
				
				return iceBoxRegDao.save(vo); 
			} else{
				SpemTypeVo dvo = new SpemTypeVo();
				dvo.setProject_id(Securitys.getCurrentProject());
				dvo.setFridge_id( vo.getId());
				dvo.setLcc_code(Securitys.getUser().getLccCode());
				iceBoxRegDao.delete_spemType(dvo);
				
				String stype = vo.getSpemType();
				String[] types =  stype.split("、");
				for(String type:types){
					if(!StringUtils.isBlank(type)){
						SpemTypeVo svo = new SpemTypeVo();
						svo.setProject_id(Securitys.getCurrentProject());
						svo.setLcc_code(Securitys.getUser().getLccCode());
						svo.setFridge_id(vo.getId());
						svo.setBox_type(type);
						svo.setCreate_by(Securitys.getUserId()); 
						svo.setCreate_date(new Date(System.currentTimeMillis()));
						iceBoxRegDao.save_spemType(svo);
					}
				}
				
				return iceBoxRegDao.update(vo); 
			}
		} catch (Exception e) {
			throw new ServiceException("操作失败", e);
		} 
	}

	@Override
	public int delete(String id,String projectId) {
		
		try {
			if(null==projectId){
				projectId = Securitys.getCurrentProject();
			}
			
			return iceBoxRegDao.delete(id,projectId);   
		} catch (Exception e) {
			throw new ServiceException("操作失败", e);
		}
	} 

	@Override
	public IceBoxRegVo getById(String id) {
		
		try {
			IceBoxRegVo e = iceBoxRegDao.getById(id);
			SpemTypeVo vo = new SpemTypeVo();
			vo.setProject_id(Securitys.getCurrentProject());
			vo.setFridge_id( id);
			vo.setLcc_code(Securitys.getUser().getLccCode());
			List<SpemTypeVo> types = iceBoxRegDao.spemType_list(vo);
			StringBuffer sb = new StringBuffer();
			for(int i=0;i< types.size();i++)
			{
			   	if(i!=0)
			   	{
			   		sb.append("、");
			   	}
			   	String type = types.get(i).getBox_type();
			   	sb.append(type);
			} 
			
			e.setSpemType(sb.toString());
			
			return e;
		} catch (Exception e) {
			throw new ServiceException("操作失败", e);
		}
	}

	@Override
	public List<Map<String, String>> getFrigdeList(Map<String,Object> map) {
		// TODO Auto-generated method stub
		map.put("projectId", Securitys.getUser().getCurrent_projectId());
		map.put("userId", Securitys.getUser().getId());
		return iceBoxRegDao.getFrigdeList(map);
	}

	@Override
	public List<Map<String,Object>> getExportFrigdeList(Map<String, Object> paramMap) {
		paramMap.put("projectId", Securitys.getUser().getCurrent_projectId());
		paramMap.put("userId", Securitys.getUser().getId());
		return iceBoxRegDao.getExportFrigdeList(paramMap);
	}

}
