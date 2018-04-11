package com.bdcor.pip.web.fee.service.impl;

import com.bdcor.pip.web.fee.dao.NCDepartVoMapper;
import com.bdcor.pip.web.fee.domain.NCDepartVo;
import com.bdcor.pip.web.fee.service.NCDepartVoService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NCDepartVoServiceImpl implements NCDepartVoService {
    @Autowired
    private NCDepartVoMapper nCDepartVoMapper; 

    private static final Logger logger = LoggerFactory.getLogger(NCDepartVoServiceImpl.class);

    

    public NCDepartVo selectByPrimaryKey(String deptCode) {
        return this.nCDepartVoMapper.selectByPrimaryKey(deptCode);
    }


    public int deleteByPrimaryKey(String deptCode) {
        return this.nCDepartVoMapper.deleteByPrimaryKey(deptCode);
    }

    public int updateByPrimaryKeySelective(NCDepartVo record) {
        return this.nCDepartVoMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(NCDepartVo record) {
        return this.nCDepartVoMapper.updateByPrimaryKey(record);
    }

    /*public int deleteByExample(Criteria example) {
        return this.nCDepartVoMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(NCDepartVo record, Criteria example) {
        return this.nCDepartVoMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(NCDepartVo record, Criteria example) {
        return this.nCDepartVoMapper.updateByExample(record, example);
    }*/

    public int insert(NCDepartVo record) {
        return this.nCDepartVoMapper.insert(record);
    }

    public int insertSelective(NCDepartVo record) {
        return this.nCDepartVoMapper.insertSelective(record);
    }


	@Override
	public List<NCDepartVo> getAllNCDepart() {
	 
		return nCDepartVoMapper.getAllNCDepart();
	}
}