package com.bdcor.pip.web.fee.service;


import com.bdcor.pip.web.fee.domain.RegTypeVo;
import java.util.List;

public interface RegTypeVoService {
   /* int countByExample(Criteria example);
    int deleteByExample(Criteria example);

    int updateByExampleSelective(RegTypeVo record, Criteria example);

    int updateByExample(RegTypeVo record, Criteria example);
    
    List<RegTypeVo> selectByExample(Criteria example);*/

    RegTypeVo selectByPrimaryKey(String typeCode);

   
    
    List<RegTypeVo> getAll( );

    int deleteByPrimaryKey(String typeCode);

    int updateByPrimaryKeySelective(RegTypeVo record);

    int updateByPrimaryKey(RegTypeVo record);

   

    int insert(RegTypeVo record);

    int insertSelective(RegTypeVo record);
}