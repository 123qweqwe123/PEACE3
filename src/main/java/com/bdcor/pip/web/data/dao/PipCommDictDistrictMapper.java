package com.bdcor.pip.web.data.dao;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.data.domain.PipCommDictDistrict;
import com.bdcor.pip.web.data.domain.PipCommDictDistrictExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface PipCommDictDistrictMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_COMM_DICT_DISTRICT
     *
     * @mbggenerated
     */
    int countByExample(PipCommDictDistrictExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_COMM_DICT_DISTRICT
     *
     * @mbggenerated
     */
    int deleteByExample(PipCommDictDistrictExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_COMM_DICT_DISTRICT
     *
     * @mbggenerated
     */
    int insert(PipCommDictDistrict record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_COMM_DICT_DISTRICT
     *
     * @mbggenerated
     */
    int insertSelective(PipCommDictDistrict record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_COMM_DICT_DISTRICT
     *
     * @mbggenerated
     */
    List<PipCommDictDistrict> selectByExample(PipCommDictDistrictExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_COMM_DICT_DISTRICT
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") PipCommDictDistrict record, @Param("example") PipCommDictDistrictExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_COMM_DICT_DISTRICT
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") PipCommDictDistrict record, @Param("example") PipCommDictDistrictExample example);
}