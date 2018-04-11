package com.bdcor.pip.web.fee.service;

import com.bdcor.pip.web.fee.domain.NCDepartVo;
import java.util.List;

public interface NCDepartVoService {
    

    NCDepartVo selectByPrimaryKey(String deptCode);

    /*List<NCDepartVo> selectByExample(Criteria example);*/
    
    List<NCDepartVo> getAllNCDepart();
    
    

    int deleteByPrimaryKey(String deptCode);

    int updateByPrimaryKeySelective(NCDepartVo record);

    int updateByPrimaryKey(NCDepartVo record);

    /*int deleteByExample(Criteria example);

    int updateByExampleSelective(NCDepartVo record, Criteria example);

    int updateByExample(NCDepartVo record, Criteria example);*/

    int insert(NCDepartVo record);

    int insertSelective(NCDepartVo record);
}