package com.bdcor.pip.web.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.common.domain.PipCommRemark;
import com.bdcor.pip.web.common.domain.PipCommRemarkExample;
import com.bdcor.pip.web.common.domain.PipCommRemarkVO;

@MyBatisRepository
public interface PipCommRemarkMapper {
    int countByExample(PipCommRemarkExample example);

    int deleteByExample(PipCommRemarkExample example);

    int deleteByPrimaryKey(String id);

    int insert(PipCommRemark record);

    int insertSelective(PipCommRemark record);

    List<PipCommRemark> selectByExample(PipCommRemarkExample example);

    PipCommRemark selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PipCommRemark record, @Param("example") PipCommRemarkExample example);

    int updateByExample(@Param("record") PipCommRemark record, @Param("example") PipCommRemarkExample example);

    int updateByPrimaryKeySelective(PipCommRemark record);

    int updateByPrimaryKey(PipCommRemark record);

    /**
     * 备注以前是和模块关联
     * 现在改为和类别关联
     * 在任意模块通过主键能查到和该主键关联的所有备注
     * @param
     * @return
     */
    List<PipCommRemarkVO> selectCountByModuleId(PipCommRemark record);
}

