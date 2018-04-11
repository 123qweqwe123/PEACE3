package com.bdcor.pip.web.fee.service.impl;

import com.bdcor.pip.web.fee.dao.RegTypeVoMapper;
import com.bdcor.pip.web.fee.domain.RegTypeVo;
import com.bdcor.pip.web.fee.service.RegTypeVoService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional 
public class RegTypeVoServiceImpl implements RegTypeVoService {
    @Autowired
    private RegTypeVoMapper regTypeVoMapper;
 
    private static final Logger logger = LoggerFactory.getLogger(RegTypeVoServiceImpl.class);

   /* public int countByExample(Criteria example) {
        int count = this.regTypeVoMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }
    
    public int deleteByExample(Criteria example) {
        return this.regTypeVoMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(RegTypeVo record, Criteria example) {
        return this.regTypeVoMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(RegTypeVo record, Criteria example) {
        return this.regTypeVoMapper.updateByExample(record, example);
    }
    public List<RegTypeVo> selectByExample(Criteria example) {
        return this.regTypeVoMapper.selectByExample(example);
    }*/


    public RegTypeVo selectByPrimaryKey(String typeCode) {
        return this.regTypeVoMapper.selectByPrimaryKey(typeCode);
    }   

    
    public List<RegTypeVo> getAll() {
        return this.regTypeVoMapper.getAll();
    }

    public int deleteByPrimaryKey(String typeCode) {
        return this.regTypeVoMapper.deleteByPrimaryKey(typeCode);
    }

    public int updateByPrimaryKeySelective(RegTypeVo record) {
        return this.regTypeVoMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(RegTypeVo record) {
        return this.regTypeVoMapper.updateByPrimaryKey(record);
    }

    
    public int insert(RegTypeVo record) {
        return this.regTypeVoMapper.insert(record);
    }

    public int insertSelective(RegTypeVo record) {
        return this.regTypeVoMapper.insertSelective(record);
    }



}