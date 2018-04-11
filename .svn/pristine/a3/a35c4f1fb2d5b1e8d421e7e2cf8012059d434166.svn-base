package com.bdcor.pip.web.data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.core.utils.StringUtils;
import com.bdcor.pip.web.data.dao.PipCommRccMapper;
import com.bdcor.pip.web.data.domain.PipCommRcc;
import com.bdcor.pip.web.data.filter.PipCommRccFilter;
import com.bdcor.pip.web.data.service.PipCommRccService;

@Service
public class PipCommRccServiceImpl implements PipCommRccService {
	@Autowired
	private PipCommRccMapper pipCommRccMapper;

	@Override
	public List<PipCommRcc> queryRccList(PipCommRccFilter pcrf) {

		return pipCommRccMapper.queryRccList(pcrf);
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @throws Exception
	 * 
	 * @see com.bdcor.pip.web.data.service.PipCommRccService#checkLccCodeExists(java.lang.String)
	 */
	@Override
	public Boolean checkRccCodeExists(String rccCode) throws Exception {
		if (StringUtils.isEmpty(rccCode)) {
			throw new Exception("rccCode不能为空！");
		}
		PipCommRcc selectPipCommRcc = pipCommRccMapper.selectPipCommRcc(
				rccCode, Securitys.getUser().getCurrent_projectId());
		if (null != selectPipCommRcc) {
			return true;
		}
		return false;
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.bdcor.pip.web.data.service.PipCommRccService#addPipCommRcc(com.bdcor.pip.web.data.domain.PipCommRcc)
	 */
	@Override
	public void addPipCommRcc(PipCommRcc pipCommRcc) throws Exception {
		pipCommRcc.setProjectId(Securitys.getUser().getCurrent_projectId());
		pipCommRccMapper.insert(pipCommRcc);
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.bdcor.pip.web.data.service.PipCommRccService#updatePipCommRcc(java.lang.String)
	 */
	@Override
	public void updatePipCommRcc(PipCommRcc pipCommRcc) throws Exception {
		if (StringUtils.isEmpty(pipCommRcc.getRccCode())) {
			throw new Exception("rccCode 不能为空！");
		}
		pipCommRccMapper.updateByRccCode(pipCommRcc);
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @throws Exception
	 * 
	 * @see com.bdcor.pip.web.data.service.PipCommRccService#selectPipCommRccByRccCode(java.lang.String)
	 */
	@Override
	public PipCommRcc selectPipCommRccByRccCode(String rccCode)
			throws Exception {
		if (StringUtils.isEmpty(rccCode)) {
			throw new Exception("rccCode不能为空！");
		}
		PipCommRcc selectPipCommRcc = pipCommRccMapper.selectPipCommRcc(
				rccCode, Securitys.getUser().getCurrent_projectId());
		return selectPipCommRcc;
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.bdcor.pip.web.data.service.PipCommRccService#deleteByRccCode(java.lang.String)
	 */
	@Override
	public void deleteByRccCode(String rccCode) throws Exception {
		pipCommRccMapper.deleteByRccCode(rccCode, Securitys.getUser()
				.getCurrent_projectId());
	}
}
