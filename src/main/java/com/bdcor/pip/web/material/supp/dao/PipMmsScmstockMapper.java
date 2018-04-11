package com.bdcor.pip.web.material.supp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.material.supp.domain.PipMmsScmstock;
import com.bdcor.pip.web.material.supp.domain.PipMmsScmstockExample;
import com.bdcor.pip.web.material.supp.filter.PipMmsScmstockFilter;

@MyBatisRepository
public interface PipMmsScmstockMapper {
	int countByExample(PipMmsScmstockExample example);

	int deleteByExample(PipMmsScmstockExample example);

	int insert(PipMmsScmstock record);

	int insertSelective(PipMmsScmstock record);

	List<PipMmsScmstock> selectByExample(PipMmsScmstockExample example);

	int updateByExampleSelective(@Param("record") PipMmsScmstock record,
			@Param("example") PipMmsScmstockExample example);

	int updateByExample(@Param("record") PipMmsScmstock record,
			@Param("example") PipMmsScmstockExample example);

	int updateByArchivesNoAndProjectId(@Param("archivesNo") String archivesNo,
			@Param("projectId") String projectId);

	List<PipMmsScmstock> list(PipMmsScmstockFilter filter);

	public List<PipMmsScmstock> archivesNoAllNoUse();
	
	public List<PipMmsScmstock> archivesNoAllByLccCode(@Param("lccCode")String lccCode);
	void changeNum(@Param("ids") String ids);

	/**
	 * description:
	 * 
	 * @author yangfeng
	 * @param pipMmsScmstock
	 * @return
	 * @update 2015-12-11
	 */
	PipMmsScmstock selectByModel(PipMmsScmstock pipMmsScmstock);

	/**
	 * 
	 * description:通过箱编号，库存编码，物资信息编码，LCCID，项目id来更新数据
	 * 
	 * @author yangfeng
	 * @param pipMmsScmstock
	 * @update 2015-12-11
	 */
	public void updateStockNumSelective(PipMmsScmstock pipMmsScmstock);
}