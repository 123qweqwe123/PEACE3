package com.bdcor.pip.web.pro.promgt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.pro.promgt.domain.PipCommLccEthical;
import com.bdcor.pip.web.pro.promgt.filter.EthicalFilter;

@MyBatisRepository
public interface PipCommLccEthicalDao {

	void insertPipCommLccEthical(PipCommLccEthical pcle);

	List<EthicalFilter> listByLccCode(@Param("lccCode") String lccCode,@Param("projectId") String projectId);

	PipCommLccEthical selectByLccCodeAndEcode(@Param("lccCode")String lccCode, @Param("ethicalPaperCode") String ethicalPaperCode);
	/**
	 * 这个方法不是通用方法 只是修改伦理时间使用
	 * @param pipCommLccEthical
	 */
	void updatePipCommLccEthicalById(PipCommLccEthical pipCommLccEthical);

}
