package com.bdcor.pip.web.spem.service;

import com.bdcor.pip.web.spem.domain.BillEventVo;
import com.bdcor.pip.web.spem.domain.BillEventVoKey;

import java.util.List;

public interface BillEventService {
    /*int countByExample(Criteria example);*/

    BillEventVo selectByPrimaryKey(BillEventVoKey key);

    List<BillEventVo> selectByExample(String waybill_no);

    int deleteByPrimaryKey(BillEventVoKey key);

    int updateByPrimaryKeySelective(BillEventVo record);

    int updateByPrimaryKey(BillEventVo record);

    /*int deleteByExample(Criteria example);*/

    /*int updateByExampleSelective(BillEventVo record, Criteria example);

    int updateByExample(BillEventVo record, Criteria example);*/

    int insert(BillEventVo record);

    int insertSelective(BillEventVo record);
}