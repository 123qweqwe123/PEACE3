package com.bdcor.pip.web.spem.service.impl;

import com.bdcor.pip.core.exception.ServiceException;
import com.bdcor.pip.web.spem.dao.BillEventMapper;
import com.bdcor.pip.web.spem.domain.BillEventVo;
import com.bdcor.pip.web.spem.domain.BillEventVoKey;
import com.bdcor.pip.web.spem.service.BillEventService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class BillEventServiceImpl implements BillEventService {
    @Autowired
    private BillEventMapper billEventVoMapper; 

    private static final Logger logger = LoggerFactory.getLogger(BillEventServiceImpl.class);

    /*public int countByExample(Criteria example) {
        int count = this.billEventVoMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }*/

    public BillEventVo selectByPrimaryKey(BillEventVoKey key) {
        return this.billEventVoMapper.selectByPrimaryKey(key);
    }

    /*public List<BillEventVo> selectByExample(Criteria example) {
        return this.billEventVoMapper.selectByExample(example);
    }*/

    public int deleteByPrimaryKey(BillEventVoKey key) {
        return this.billEventVoMapper.deleteByPrimaryKey(key);
    }

    public int updateByPrimaryKeySelective(BillEventVo record) {
        return this.billEventVoMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(BillEventVo record) {
        return this.billEventVoMapper.updateByPrimaryKey(record);
    }

   /* public int deleteByExample(Criteria example) {
        return this.billEventVoMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(BillEventVo record, Criteria example) {
        return this.billEventVoMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(BillEventVo record, Criteria example) {
        return this.billEventVoMapper.updateByExample(record, example);
    }*/ 

    public int insert(BillEventVo record) {
        return this.billEventVoMapper.insert(record);
    }

    public int insertSelective(BillEventVo record) {
        return this.billEventVoMapper.insertSelective(record);
    }

	@Override
	public List<BillEventVo> selectByExample(String waybill_no) {

		
		try { 
			return billEventVoMapper.selectByExample(waybill_no);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("查询历史事件失败！！！",e); 
		} 
	}
}