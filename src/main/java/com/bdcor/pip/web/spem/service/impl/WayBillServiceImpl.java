package com.bdcor.pip.web.spem.service.impl;

import com.bdcor.pip.core.exception.ServiceException;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.core.utils.StringUtils;
import com.bdcor.pip.web.spem.dao.WayBillVoMapper;

import com.bdcor.pip.web.spem.domain.BillEventVo;
import com.bdcor.pip.web.spem.domain.SpBoxVo;
import com.bdcor.pip.web.spem.domain.WayBillVo;
import com.bdcor.pip.web.spem.filter.WayBillFilter;
import com.bdcor.pip.web.spem.service.BillEventService;
import com.bdcor.pip.web.spem.service.SpBoxService;
import com.bdcor.pip.web.spem.service.WayBillVoService;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WayBillServiceImpl implements WayBillVoService {
    @Autowired
    private WayBillVoMapper wayBillVoMapper;
    @Autowired
	private SpBoxService   spBoxVoService; 
    
    @Autowired
	private BillEventService  billEventVoService;

    private static final Logger logger = LoggerFactory.getLogger(WayBillServiceImpl.class);

    public int countByExample(WayBillFilter example) {
        int count = this.wayBillVoMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public WayBillVo selectByPrimaryKey(String waybill_no) {
        return this.wayBillVoMapper.selectByPrimaryKey(waybill_no);
    }  

    public List<WayBillVo> list(WayBillFilter example) {
    	
    	/*if(StringUtils.isBlank(example.getLccid())) 
    	   example.setLccid( Securitys.getUser().getLccCode()); */
    	example.setProjectId(Securitys.getCurrentProject());
    	
    	StringBuffer sb = new StringBuffer();  
    	
    	if(null!=example.getBoxCodes() && example.getBoxCodes().indexOf(",")>0 )
    	{
    		String arr[] = example.getBoxCodes().split(",");
    		for(String s :arr){
    			if(!StringUtils.isBlank(s)){
    				if(sb.length()==0){
    					sb.append(" and (  )");
    					sb.insert(sb.length()-2," sw.BOX_CODE like'%").insert(sb.length()-2, s).insert(sb.length()-2, "%'  ");
    				}
    				sb.insert(sb.length()-2," or sw.BOX_CODE like'%").insert(sb.length()-2, s).insert(sb.length()-2, "%'  ");
    			} 
    		}
    	}else if(!StringUtils.isBlank(example.getBoxCodes())){
    		sb.append(" and sw.BOX_CODE like '%").append(example.getBoxCodes()).append("%'");
    	} 
    	
    	sb.append(" and sw.LCC_CODE IN ( select LCC_CODE from PIP_SYS_USER_DATA_LIMIT where USER_ID = '");
    	sb.append(Securitys.getUser().getId());
    	sb.append("' )");
    	example.setBoxCodes(sb.toString());
    	
    	
        return this.wayBillVoMapper.list(example);
    }
    
    
public List<WayBillVo> list_lcc(WayBillFilter example) {
    	
    	example.setProjectId(Securitys.getCurrentProject());
    	example.setLccid(Securitys.getUser().getLccCode() );
    	
    	
        return this.wayBillVoMapper.list_lcc(example);
    }
    
    
    public int delete(String waybill_no){
    	try {
    		
    		spBoxVoService.removeWayBillNo(waybill_no);
    		
			return wayBillVoMapper.delete(waybill_no);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("删除操作失败!",e);
		}
    }

    public int deleteByPrimaryKey(String waybill_no) {
        return this.wayBillVoMapper.deleteByPrimaryKey(waybill_no);
    }

    public int updateByPrimaryKeySelective(WayBillVo record) {
        return this.wayBillVoMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(WayBillVo record) {
        return this.wayBillVoMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(WayBillFilter example) {
        return this.wayBillVoMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(WayBillVo record, WayBillFilter example) {
        return this.wayBillVoMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(WayBillVo record, WayBillFilter example) {
        return this.wayBillVoMapper.updateByExample(record, example);
    }

    public int insert(WayBillVo record) {
        return this.wayBillVoMapper.insert(record);
    }

    public int insertSelective(WayBillVo record) {
        return this.wayBillVoMapper.createWayBill(record);
    }

	@Override
	public int createWayBill(WayBillVo record) {
		
		try {
			String boxIds[] = record.getBoxCodes().split(",");
			//String boxIds[] = record.getBoxType().split(",");
			for(int i=0;i<boxIds.length;i++){
				if(null!= boxIds[i] && !"".equals(boxIds[i]))
				{
					int length = boxIds[i].length();
					SpBoxVo vo= new SpBoxVo();
					vo.setProject_id( record.getProjectId());
					vo.setId(boxIds[i].substring(0, length-1));  
					vo.setBoxType( boxIds[i].substring( length-1 , length));
					vo.setWaybill_no(record.getWaybill_no ()); 
					vo.setLcc_code(record.getLccCode()); 
					int stat = spBoxVoService.updateWayBillNo(vo); 
					if(stat==0)
						throw new RuntimeException("设置运单号码失败!");
				}  
			}     
			
			BillEventVo evo = new  BillEventVo(); 
			evo.setProjectId(record.getProjectId());
			evo.setEventCode("1");
			evo.setWaybillNo(record.getWaybill_no());
			evo.setEventDate(new Date());
			evo.setCreateDate(new Date());
			evo.setLiableSb(Securitys.getUser().getId());
			int b = billEventVoService.insertSelective(evo);
			if(b==0)
				throw new RuntimeException("设置运单事件失败!");
			
			int stat = wayBillVoMapper.createWayBill(record); 
			      
			return stat;  
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("生成运单失败!",e);
		}
	}

	@Override
	public Map getNextEventCode(String current_event_code) {
		try {
			return wayBillVoMapper.getNextEventCode(current_event_code);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("获取下一事件代码失败!",e);
		} 
	}
	
	public int processBill(String current_event_code,String waybill_no)
	{
		try {
			Map event_code = getNextEventCode(current_event_code);
			String ec = event_code.get("EVENT_CODE").toString();
			
			if(ec.equals("5")){
				int stat = spBoxVoService.update_status(waybill_no);
				if(stat==0)
					throw new RuntimeException("更新入库状态失败!");
			}
			
			BillEventVo evo = new  BillEventVo(); 
			evo.setProjectId(Securitys.getCurrentProject() );
			evo.setEventCode(ec);
			evo.setWaybillNo(waybill_no);
			evo.setEventDate(new Date());
			evo.setCreateDate(new Date());
			evo.setLiableSb(Securitys.getUser().getId());
			int b = billEventVoService.insertSelective(evo);
			if(b==0)
				throw new RuntimeException("设置运单事件失败!");
			return  b;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("处理运单失败!",e);
		}
	}
}