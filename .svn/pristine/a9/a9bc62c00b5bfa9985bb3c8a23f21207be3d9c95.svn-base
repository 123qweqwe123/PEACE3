package com.bdcor.pip.web.common.dao;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.common.domain.PipSysExpexcelInfo;
import com.bdcor.pip.web.common.domain.PipSysExpexcelInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@MyBatisRepository
public interface PipSysExpexcelInfoMapper {
    int countByExample(PipSysExpexcelInfoExample example);

    int deleteByExample(PipSysExpexcelInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(PipSysExpexcelInfo record);

    int insertSelective(PipSysExpexcelInfo record);

    List<PipSysExpexcelInfo> selectByExample(PipSysExpexcelInfoExample example);

    PipSysExpexcelInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PipSysExpexcelInfo record, @Param("example") PipSysExpexcelInfoExample example);

    int updateByExample(@Param("record") PipSysExpexcelInfo record, @Param("example") PipSysExpexcelInfoExample example);

    int updateByPrimaryKeySelective(PipSysExpexcelInfo record);

    int updateByPrimaryKey(PipSysExpexcelInfo record);
}