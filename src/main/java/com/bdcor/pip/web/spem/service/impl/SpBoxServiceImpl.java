package com.bdcor.pip.web.spem.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.core.exception.ServiceException;
import com.bdcor.pip.core.utils.PinyingUtils;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.core.utils.StringUtils;
import com.bdcor.pip.web.spem.dao.SpBoxMapper;
import com.bdcor.pip.web.spem.domain.SpBoxVo;
import com.bdcor.pip.web.spem.filter.SpBoxFilter;
import com.bdcor.pip.web.spem.filter.TubeFilter;
import com.bdcor.pip.web.spem.service.SpBoxService;
import com.bdcor.pip.web.sys.rbac.service.UserService;
 
@Service
@Transactional
public class SpBoxServiceImpl implements SpBoxService {    
    @Autowired
    private SpBoxMapper spBoxVoMapper; 
    @Autowired
	UserService userService; 

    private static final Logger logger = LoggerFactory.getLogger(SpBoxServiceImpl.class);

     

    public SpBoxVo selectByPrimaryKey(String id) {
        return this.spBoxVoMapper.selectByPrimaryKey(id);
    }

    public List<SpBoxVo> selectByExample(String id,String lcc_code) {
    	String projectId = Securitys.getCurrentProject();
        return this.spBoxVoMapper.selectByExample(id,lcc_code,projectId);
    }
    
    /**
     * 根据运单号 获取盒子 无分页 
     */
    public List<SpBoxVo> spboxListByWaybillNo(String waybill_no) {
        return this.spBoxVoMapper.spboxListByWaybillNo(waybill_no);
    } 
    /**
     * 获取 盒子  分页
     * @param fi
     * @return
     */
    public List<SpBoxVo> spboxListByWaybillNoByfilter(SpBoxFilter fi) {
        return this.spBoxVoMapper.spboxListByWaybillNoByfilter(fi);
    }
   

    public int deleteByPrimaryKey(String id) {
        return this.spBoxVoMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(SpBoxVo record) {
        return this.spBoxVoMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(SpBoxVo record) {
        return this.spBoxVoMapper.updateByPrimaryKey(record);
    }

   

    public int insert(SpBoxVo record) {
        return this.spBoxVoMapper.insert(record);
    }

    public int insertSelective(SpBoxVo record) {
        return this.spBoxVoMapper.insertSelective(record);
    }

	@Override
	public int updateByExampleSelective(SpBoxVo record, SpBoxFilter example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByExample(SpBoxVo record, SpBoxFilter example) {
		// TODO Auto-generated method stub
		return 0;
	} 

	@Override
	public List microList(TubeFilter filter) {  
		   
		try {
			filter.setProjectid(Securitys.getCurrentProject());
			List<Map> result=this.spBoxVoMapper.microList(filter);
			for(Map row:result){//循环结果,将姓名转化为拼音
				Object name=row.get("PATIENTNAME");
				if(name!=null){
					String helpCode=PinyingUtils.getJM((String)name);
					row.put("HELP_CODE", helpCode);
				}
			}
			return result;
		}  catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("操作失败", e);
		}    
	}  
	@Override
	public List microListBywaybill_no(TubeFilter filter) {
		   
		try {
			filter.setProjectid(Securitys.getCurrentProject());
			List<Map> result=this.spBoxVoMapper.microListBywaybill_no(filter);
			for(Map row:result){//循环结果,将姓名转化为拼音
				Object name=row.get("PATIENTNAME");
				if(name!=null){
					String helpCode=PinyingUtils.getJM((String)name);
					row.put("HELP_CODE", helpCode);
				}
			}
			return result;
		}  catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("操作失败", e);
		}  
	} 

	@Override
	public int countByProjectId(String projectId) {
		
		return spBoxVoMapper.countByProjectId(projectId);
	}

	@Override
	public int updateWayBillNo(SpBoxVo record) {
		 
		return spBoxVoMapper.updateWayBillNo(record);
	} 
	
	@Override
	public int update_status(String waybill_no){
		
		try {
			return spBoxVoMapper.update_status(waybill_no);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("更新入库状态失败",e);
		}  
	} 
	
	@Override
	public int removeWayBillNo(String waybill_no){ 
		return spBoxVoMapper.removeWayBillNo(waybill_no);
	}

	@Override
	public List<SpBoxVo> spboxListByfilter(SpBoxFilter fi) {
			   //按照配置的数据权限 过滤 数据   
				String sql = userService.getUserDataLimitSQL(Securitys.getUserId());   
				if(StringUtils.isBlank(fi.getLccid() ))
				{   
					fi.setLccid(sql);
				}else if(sql.indexOf(fi.getLccid())>-1){
					fi.setLccid("("+fi.getLccid()+")"); 
				}else if(sql.indexOf(fi.getLccid())==-1){
					fi.setLccid("('-100')"); 
				}       
		      return spBoxVoMapper.spboxListByfilter(fi);
	}
}