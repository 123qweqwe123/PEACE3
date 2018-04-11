package com.bdcor.pip.web.data.dao;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.data.domain.PipReportFactory;
import com.bdcor.pip.web.data.domain.PipReportFactoryExample;
import com.bdcor.pip.web.data.domain.PipReportFactoryKey;

import java.util.List;

import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface PipReportFactoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_REPORT_FACTORY
     *
     * @mbggenerated
     */
    int countByExample(PipReportFactoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_REPORT_FACTORY
     *
     * @mbggenerated
     */
    int deleteByExample(PipReportFactoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_REPORT_FACTORY
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(PipReportFactoryKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_REPORT_FACTORY
     *
     * @mbggenerated
     */
    int insert(PipReportFactory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_REPORT_FACTORY
     *
     * @mbggenerated
     */
    int insertSelective(PipReportFactory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_REPORT_FACTORY
     *
     * @mbggenerated
     */
    List<PipReportFactory> selectByExample(PipReportFactoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_REPORT_FACTORY
     *
     * @mbggenerated
     */
    PipReportFactory selectByPrimaryKey(PipReportFactoryKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_REPORT_FACTORY
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") PipReportFactory record, @Param("example") PipReportFactoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_REPORT_FACTORY
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") PipReportFactory record, @Param("example") PipReportFactoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_REPORT_FACTORY
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(PipReportFactory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_REPORT_FACTORY
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(PipReportFactory record);
}