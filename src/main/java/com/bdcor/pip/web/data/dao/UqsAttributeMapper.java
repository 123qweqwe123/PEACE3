package com.bdcor.pip.web.data.dao;

import com.bdcor.pip.web.data.domain.UqsAttribute;
import com.bdcor.pip.web.data.domain.UqsAttributeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.data.domain.UqsAttributeKey;

@MyBatisRepository
public interface UqsAttributeMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PIP_UQS_ATTRIBUTE
	 * @mbggenerated
	 */
	int countByExample(UqsAttributeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PIP_UQS_ATTRIBUTE
	 * @mbggenerated
	 */
	int deleteByExample(UqsAttributeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PIP_UQS_ATTRIBUTE
	 * @mbggenerated
	 */
	int deleteByPrimaryKey(UqsAttributeKey key);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PIP_UQS_ATTRIBUTE
	 * @mbggenerated
	 */
	int insert(UqsAttribute record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PIP_UQS_ATTRIBUTE
	 * @mbggenerated
	 */
	int insertSelective(UqsAttribute record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PIP_UQS_ATTRIBUTE
	 * @mbggenerated
	 */
	List<UqsAttribute> selectByExample(UqsAttributeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PIP_UQS_ATTRIBUTE
	 * @mbggenerated
	 */
	UqsAttribute selectByPrimaryKey(UqsAttributeKey key);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PIP_UQS_ATTRIBUTE
	 * @mbggenerated
	 */
	int updateByExampleSelective(@Param("record") UqsAttribute record,
			@Param("example") UqsAttributeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PIP_UQS_ATTRIBUTE
	 * @mbggenerated
	 */
	int updateByExample(@Param("record") UqsAttribute record,
			@Param("example") UqsAttributeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PIP_UQS_ATTRIBUTE
	 * @mbggenerated
	 */
	int updateByPrimaryKeySelective(UqsAttribute record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PIP_UQS_ATTRIBUTE
	 * @mbggenerated
	 */
	int updateByPrimaryKey(UqsAttribute record);
}