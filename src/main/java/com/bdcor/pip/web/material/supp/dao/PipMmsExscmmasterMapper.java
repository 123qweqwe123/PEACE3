package com.bdcor.pip.web.material.supp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.material.supp.domain.OrderDetal;
import com.bdcor.pip.web.material.supp.domain.PipMmsExscmmaster;
import com.bdcor.pip.web.material.supp.domain.PipMmsExscmmasterExample;
import com.bdcor.pip.web.material.supp.filter.PipMmsExscmmasterFilter;

@MyBatisRepository
public interface PipMmsExscmmasterMapper {
	int countByExample(PipMmsExscmmasterExample example);

	int deleteByExample(PipMmsExscmmasterExample example);

	int deleteByPrimaryKey(Short id);

	int insert(PipMmsExscmmaster record);

	int insertSelective(PipMmsExscmmaster record);

	List<PipMmsExscmmaster> selectByExample(PipMmsExscmmasterExample example);

	PipMmsExscmmaster selectByPrimaryKey(String id);

	int updateByExampleSelective(@Param("record") PipMmsExscmmaster record,
			@Param("example") PipMmsExscmmasterExample example);

	int updateByExample(@Param("record") PipMmsExscmmaster record,
			@Param("example") PipMmsExscmmasterExample example);

	int updateByPrimaryKeySelective(PipMmsExscmmaster record);

	int updateByPrimaryKey(PipMmsExscmmaster record);

	public List<PipMmsExscmmaster> selectPipMmsExscmmasterByLccCode(
			PipMmsExscmmaster pmem);

	public List<OrderDetal> selectPipMmsExscmmasterByArchivesNo(OrderDetal od);

	/**
	 * description:
	 * 
	 * @author yangfeng
	 * @param filter
	 * @return
	 * @update 2015-11-4
	 */
	List<PipMmsExscmmaster> selectByFilter(PipMmsExscmmasterFilter filter);
	List<PipMmsExscmmaster> selectByFilterForAdmin(PipMmsExscmmasterFilter filter);

	/**
	 * description:
	 * 
	 * @author yangfeng
	 * @param ids
	 * @param state
	 * @update 2015-11-4
	 */
	void changeState(@Param("ids") String ids, @Param("state") String state);

	List<PipMmsExscmmaster> listByIds(@Param("exorderNos")String [] ids);
	/**
	 * 查找列表也
	 * @param pmemf
	 * @return
	 */
	List<PipMmsExscmmaster> selectPipMmsExscmmasterByFilter(PipMmsExscmmasterFilter pmemf);
	/**
	 * 
	 * description:通过订单号查找记录  
	 * @author yangfeng  
	 * @param exorderNo
	 * @return   
	 * @update 2016年1月7日
	 */
	PipMmsExscmmaster selectModelByExorderNo(@Param("exorderNo")String exorderNo);

	/**
	 * 校验是否过期
	 * @param ids
	 * @return
	 */
	int checkIsExpired(@Param("ids") String ids);
}