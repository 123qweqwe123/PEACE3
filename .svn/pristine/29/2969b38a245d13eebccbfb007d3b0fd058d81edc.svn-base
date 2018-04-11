package com.bdcor.pip.web.spem.service;

import com.bdcor.pip.web.spem.domain.WayBillVo;
import com.bdcor.pip.web.spem.filter.WayBillFilter;

import java.util.List;
import java.util.Map;

public interface WayBillVoService {
	
	int createWayBill(WayBillVo record);   
	
    int countByExample(WayBillFilter example);

    WayBillVo selectByPrimaryKey(String waybilNo);

    List<WayBillVo> list(WayBillFilter example);
    
    List<WayBillVo> list_lcc(WayBillFilter example);

    int deleteByPrimaryKey(String waybilNo);

    int updateByPrimaryKeySelective(WayBillVo record);

    int updateByPrimaryKey(WayBillVo record);

    int deleteByExample(WayBillFilter example);

    int updateByExampleSelective(WayBillVo record, WayBillFilter example);

    int updateByExample(WayBillVo record, WayBillFilter example);

    int insert(WayBillVo record);
    
    int delete(String waybill_no);

    int insertSelective(WayBillVo record);
    
    int processBill(String current_event_code,String waybilNo);
    
    Map getNextEventCode(String current_event_code);
}