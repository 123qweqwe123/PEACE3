package com.bdcor.pip.web.data.dao;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.data.domain.PipSysNewsMarquee;
import com.bdcor.pip.web.data.domain.PipSysNewsMarqueeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface PipSysNewsMarqueeMapper {
    int countByExample(PipSysNewsMarqueeExample example);

    int deleteByExample(PipSysNewsMarqueeExample example);

    int insert(PipSysNewsMarquee record);

    int insertSelective(PipSysNewsMarquee record);

    List<PipSysNewsMarquee> selectByExample(PipSysNewsMarqueeExample example);

    int updateByExampleSelective(@Param("record") PipSysNewsMarquee record, @Param("example") PipSysNewsMarqueeExample example);

    int updateByExample(@Param("record") PipSysNewsMarquee record, @Param("example") PipSysNewsMarqueeExample example);
}