package com.bdcor.pip.web.data.service;

import java.util.List;

import com.bdcor.pip.web.data.domain.PipCommRcc;
import com.bdcor.pip.web.data.filter.PipCommRccFilter;

public interface PipCommRccService {

	public List<PipCommRcc> queryRccList(PipCommRccFilter pcrf);

	/**
	 * description: 检查是否重复
	 * 
	 * @author yangfeng
	 * @param lccCode
	 * @return
	 * @update 2015-10-14
	 */
	public Boolean checkRccCodeExists(String lccCode) throws Exception;

	public void addPipCommRcc(PipCommRcc pipCommRcc) throws Exception;

	public void updatePipCommRcc(PipCommRcc pipCommRcc) throws Exception;

	/**
	 * description:
	 * 
	 * @author yangfeng
	 * @param rccCode
	 * @return
	 * @update 2015-10-14
	 */
	public PipCommRcc selectPipCommRccByRccCode(String rccCode)
			throws Exception;

	/**
	 * description: 删除
	 * 
	 * @author yangfeng
	 * @param rccCode
	 * @update 2015-10-16
	 */
	public void deleteByRccCode(String rccCode) throws Exception;

}
