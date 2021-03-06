package com.bdcor.pip.web.fee.dao;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.fee.domain.PipActualReg;
import com.bdcor.pip.web.fee.domain.PipActualRegExample;
import com.bdcor.pip.web.fee.filter.FeeLccRegFilter;

import java.util.List;
import org.apache.ibatis.annotations.Param;
@MyBatisRepository
public interface PipActualRegMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_FEE_ACTUAL_REG
     *
     * @mbggenerated
     */
    int countByExample(PipActualRegExample example); 

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_FEE_ACTUAL_REG
     *
     * @mbggenerated
     */
    int deleteByExample(PipActualRegExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_FEE_ACTUAL_REG
     *
     * @mbggenerated
     */
    int delete(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_FEE_ACTUAL_REG
     *
     * @mbggenerated
     */
    //int save(PipActualReg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_FEE_ACTUAL_REG
     *
     * @mbggenerated
     */
    int save(PipActualReg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_FEE_ACTUAL_REG
     *
     * @mbggenerated
     */
    List<PipActualReg> list(FeeLccRegFilter example); 

    
    PipActualReg getById(String id);    

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_FEE_ACTUAL_REG
     *
     * @mbggenerated
     */
    int update(@Param("record") PipActualReg record); 

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_FEE_ACTUAL_REG
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") PipActualReg record, @Param("example") PipActualRegExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_FEE_ACTUAL_REG
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(PipActualReg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_FEE_ACTUAL_REG
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(PipActualReg record);
}