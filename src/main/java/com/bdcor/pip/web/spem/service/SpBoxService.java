package com.bdcor.pip.web.spem.service;

import com.bdcor.pip.web.spem.filter.SpBoxFilter;
import com.bdcor.pip.web.spem.filter.TubeFilter;
import com.bdcor.pip.web.spem.domain.SpBoxVo;
import java.util.List;

public interface SpBoxService {
    
    SpBoxVo selectByPrimaryKey(String id);

    List<SpBoxVo> selectByExample(String example,String spemType);
    
    List<SpBoxVo> spboxListByWaybillNo(String waybill_no);
    
    List<SpBoxVo> spboxListByWaybillNoByfilter(SpBoxFilter fi); 
    
    List<SpBoxVo> spboxListByfilter(SpBoxFilter fi);  
    
    int deleteByPrimaryKey(String id);
    
    int updateWayBillNo(SpBoxVo record); 
    
    int removeWayBillNo(String waybill_no); 
    
    int update_status(String waybill_no);
   
    int countByProjectId(String id); 

    int updateByExampleSelective(SpBoxVo record, SpBoxFilter example);

    int updateByExample(SpBoxVo record, SpBoxFilter example); 

    int insert(SpBoxVo record); 

    int insertSelective(SpBoxVo record);
    
    List microList(TubeFilter filter);
    
    List microListBywaybill_no(TubeFilter filter);
}