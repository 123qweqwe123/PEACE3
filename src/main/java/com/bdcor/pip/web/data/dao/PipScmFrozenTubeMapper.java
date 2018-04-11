package com.bdcor.pip.web.data.dao;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.data.domain.PipScmFrozenTube;
import com.bdcor.pip.web.data.domain.PipScmFrozenTubeExample;
import com.bdcor.pip.web.data.domain.PipScmFrozenTubeKey;

import java.util.List;

import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface PipScmFrozenTubeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_SCM_FROZEN_TUBE
     *
     * @mbggenerated
     */
    int countByExample(PipScmFrozenTubeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_SCM_FROZEN_TUBE
     *
     * @mbggenerated
     */
    int deleteByExample(PipScmFrozenTubeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_SCM_FROZEN_TUBE
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(PipScmFrozenTubeKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_SCM_FROZEN_TUBE
     *
     * @mbggenerated
     */
    int insert(PipScmFrozenTube record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_SCM_FROZEN_TUBE
     *
     * @mbggenerated
     */
    int insertSelective(PipScmFrozenTube record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_SCM_FROZEN_TUBE
     *
     * @mbggenerated
     */
    List<PipScmFrozenTube> selectByExample(PipScmFrozenTubeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_SCM_FROZEN_TUBE
     *
     * @mbggenerated
     */
    PipScmFrozenTube selectByPrimaryKey(PipScmFrozenTubeKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_SCM_FROZEN_TUBE
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") PipScmFrozenTube record, @Param("example") PipScmFrozenTubeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_SCM_FROZEN_TUBE
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") PipScmFrozenTube record, @Param("example") PipScmFrozenTubeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_SCM_FROZEN_TUBE
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(PipScmFrozenTube record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_SCM_FROZEN_TUBE
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(PipScmFrozenTube record);
}