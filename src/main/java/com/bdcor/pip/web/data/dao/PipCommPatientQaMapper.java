package com.bdcor.pip.web.data.dao;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.data.domain.PipCommPatientQa;
import com.bdcor.pip.web.data.domain.PipCommPatientQaExample;
import com.bdcor.pip.web.data.domain.PipCommPatientQaKey;

import java.util.List;

import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface PipCommPatientQaMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_COMM_PATIENT_QA
     *
     * @mbggenerated
     */
    int countByExample(PipCommPatientQaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_COMM_PATIENT_QA
     *
     * @mbggenerated
     */
    int deleteByExample(PipCommPatientQaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_COMM_PATIENT_QA
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(PipCommPatientQaKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_COMM_PATIENT_QA
     *
     * @mbggenerated
     */
    int insert(PipCommPatientQa record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_COMM_PATIENT_QA
     *
     * @mbggenerated
     */
    int insertSelective(PipCommPatientQa record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_COMM_PATIENT_QA
     *
     * @mbggenerated
     */
    List<PipCommPatientQa> selectByExample(PipCommPatientQaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_COMM_PATIENT_QA
     *
     * @mbggenerated
     */
    PipCommPatientQa selectByPrimaryKey(PipCommPatientQaKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_COMM_PATIENT_QA
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") PipCommPatientQa record, @Param("example") PipCommPatientQaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_COMM_PATIENT_QA
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") PipCommPatientQa record, @Param("example") PipCommPatientQaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_COMM_PATIENT_QA
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(PipCommPatientQa record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_COMM_PATIENT_QA
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(PipCommPatientQa record);
}