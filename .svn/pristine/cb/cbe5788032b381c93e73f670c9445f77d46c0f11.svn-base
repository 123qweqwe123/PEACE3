package com.bdcor.pip.web.pro.promgt.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.core.exception.ServiceException;
import com.bdcor.pip.core.utils.DateUtil;
import com.bdcor.pip.core.utils.PinyingUtils;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.core.utils.StringUtils;
import com.bdcor.pip.web.pro.promgt.dao.LccDao;
import com.bdcor.pip.web.pro.promgt.dao.LccUserDao;
import com.bdcor.pip.web.pro.promgt.dao.PipCommLccEthicalDao;
import com.bdcor.pip.web.pro.promgt.domain.Lcc;
import com.bdcor.pip.web.pro.promgt.domain.PipCommLccEthical;
import com.bdcor.pip.web.pro.promgt.filter.LccFilter;
import com.bdcor.pip.web.pro.promgt.service.LccService;
import com.bdcor.pip.web.sys.rbac.dao.UserDao;
import com.bdcor.pip.web.sys.rbac.domain.LccAccount;

@Service
@Transactional
public class LccServiceImpl implements LccService {

	@Autowired
	private LccDao lccDao;
	@Autowired
	private LccUserDao lccUserDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private PipCommLccEthicalDao pipCommLccEthicalDao;

	@Override
	public List<LccFilter> getAllLccs(LccFilter filter) {
		filter.setProjectId(Securitys.getUser().getCurrent_projectId());
		filter.setUserId(Securitys.getUserId());
		return lccDao.getAllLccs(filter);
	}

	@Override
	public List<Lcc> getAllActiveLcc() {
		return lccDao.getAllActiveLcc(Securitys.getUser()
				.getCurrent_projectId());
	}
	@Override
	public List<Lcc> getAllActiveLccByAuthority() {
		return lccDao.getAllActiveLccByAuthority(Securitys.getUser()
				.getCurrent_projectId(),Securitys.getUserId());
	}
	
	@Override
	public List<Lcc> getDataLimitLcc() {
		return lccDao.getDataLimitLcc(Securitys.getUser()
				.getCurrent_projectId(), Securitys.getUserId());
	}

	public List<Lcc> getDataLimitLccForLccCode() {
		return lccDao.getDataLimitLccForLccCode(Securitys.getUser()
				.getCurrent_projectId(), Securitys.getUserId(), Securitys
				.getUser().getLccCode());
	}
	@Override
	public List<Lcc> getLccList() {
		return lccDao.getLccList(Securitys.getUser().getCurrent_projectId());
	}
	
	@Override
	public String getLccCode(String areaCode) {
		String lccCode = lccDao.getLccCode(areaCode);
		return lccCode;
	}

	@Override
	@CacheEvict(value = "sysCache", key = "#lccCode+'getLcc'")
	public Lcc getLcc(String lccCode) {
		System.out.println(Securitys.getUser());
		Lcc lcc = lccDao.getLcc(lccCode, Securitys.getUser()
				.getCurrent_projectId());
		if (null!= lcc.getContractSignDate() ) {
			lcc.setContractSignDateStr(DateUtil.dateToString(
					lcc.getContractSignDate(), "yyyy-MM-dd"));
		}
		if (null!= lcc.getEthicalDisableDate() ) {
			lcc.setEthicalDisableDateStr(DateUtil.dateToString(
					lcc.getEthicalDisableDate(), "yyyy-MM-dd"));
		}
		if (null!= lcc.getEthicalEffectiveDate() ) {
			lcc.setEthicalEffectiveDateStr(DateUtil.dateToString(
					lcc.getEthicalEffectiveDate(), "yyyy-MM-dd"));
		}
		if (null!= lcc.getExpectStartDate() ) {
			lcc.setExpectStartDateStr(DateUtil.dateToString(
					lcc.getExpectStartDate(), "yyyy-MM-dd"));
		}
		if (null != lcc.getHigherApproveDate()) {
			lcc.setHigherApproveDateStr(DateUtil.dateToString(
					lcc.getHigherApproveDate(), "yyyy-MM-dd"));
		}
		return lcc;
	}

	public Lcc getLcc(String lccCode, String projectId) {
		return lccDao.getLcc(lccCode, projectId);
	}

	@Override
	public void addLcc(Lcc lcc) {
		try {
			lcc.setProjectId(Securitys.getUser().getCurrent_projectId());
			if (!StringUtils.isEmpty(lcc.getLccName())) {
				lcc.setHelpCode(PinyingUtils.getJM(lcc.getLccName()));
			}
			lccDao.addLcc(lcc);
			if(lcc.getIsEthical().equals("1")){
				// 保存一次伦理记录
				PipCommLccEthical pcle = new PipCommLccEthical();
				pcle.setLccCode(lcc.getLccCode());
				pcle.setEthicalType("2");
				pcle.setEthicalEffectiveDate(lcc.getEthicalEffectiveDate());
				pcle.setEthicalDisableDate(lcc.getEthicalDisableDate());
				pcle.setEthicalPaperCode(lcc.getEthicalPaperCode());
				pcle.setCreateBy(Securitys.getUser().getLoginName());
				pcle.setCreateDate(new Date());
				pipCommLccEthicalDao.insertPipCommLccEthical(pcle);
			}
			LccAccount la = new LccAccount();
			if (!StringUtils.isEmpty(lcc.getLccCode())) {
				la.setLccCode(lcc.getLccCode());
				la.setUserId(Securitys.getUserId());
				userDao.saveLccAccount(la);
			}
		} catch (Exception e) {
			throw new ServiceException("添加失败！", e);
		}
	}

	@Override
	public void updateLcc(Lcc lcc) {
		try {
			if (!StringUtils.isEmpty(lcc.getLccName())) {
				lcc.setHelpCode(PinyingUtils.getJM(lcc.getLccName()));
			}
			if (2 == lcc.getStatus()) {
				lccUserDao.changeStatusByLccCode(lcc.getStatus(),
						lcc.getLccCode());
			}
			if (1 == lcc.getStatus()) {
				lccUserDao.changeStatusByLccCode(lcc.getStatus(),
						lcc.getLccCode());
			}
			lccDao.updateLcc(lcc);
			if("1".equals(lcc.getIsEthical())){
				PipCommLccEthical pipCommLccEthical = pipCommLccEthicalDao.selectByLccCodeAndEcode(lcc.getLccCode(),lcc.getEthicalPaperCode());
				if(null ==pipCommLccEthical){
					// 单独保存一次 修改伦理的记录
					PipCommLccEthical pcle = new PipCommLccEthical();
					pcle.setLccCode(lcc.getLccCode());
					pcle.setEthicalType("2");
					pcle.setEthicalEffectiveDate(lcc.getEthicalEffectiveDate());
					pcle.setEthicalDisableDate(lcc.getEthicalDisableDate());
					pcle.setEthicalPaperCode(lcc.getEthicalPaperCode());
					pcle.setCreateBy(Securitys.getUser().getLoginName());
					pcle.setCreateDate(new Date());
					pipCommLccEthicalDao.insertPipCommLccEthical(pcle);
				}
				else{
					pipCommLccEthical.setEthicalEffectiveDate(lcc.getEthicalEffectiveDate());
					pipCommLccEthical.setEthicalDisableDate(lcc.getEthicalDisableDate());
					pipCommLccEthical.setEthicalPaperCode(lcc.getEthicalPaperCode());
					pipCommLccEthicalDao.updatePipCommLccEthicalById(pipCommLccEthical);
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("更新失败！", e);
		}
	}

	public String updateStatus(String lccCode, String status) {
		String msg = "";
		try {
			if (!StringUtils.isEmpty(lccCode) && !StringUtils.isEmpty(status)) {
				String projectId = Securitys.getUser().getCurrent_projectId();

				Lcc lcc = getOneForLccCodeAndProjectId(lccCode, projectId);
				if (lcc == null)
					return msg = "没有此记录";
				// 是行政部门就直接激活
				if (null != lcc.getIsAdminDep()
						&& "1".equals(lcc.getIsAdminDep())) {
					lccDao.updateStatus(projectId, lccCode, status);
					return msg = "修改成功";
				} else {
					// 非行政部门就要检测 3个状态是否符合标准
					if (status != null && "1".equals(status)) {
						if (lcc.getIsSignContract() == null
								|| "2".equals(lcc.getIsSignContract())) {
							return msg = "没有签署合同";
						}
						if (lcc.getIsEthical() == null
								|| "2".equals(lcc.getIsEthical())) {
							return msg = "没有通过伦理";
						}
						if (lcc.getIsTraining() == null
								|| "2".equals(lcc.getIsTraining())) {
							return msg = "没有经过培训";
						}
					}
					lccDao.updateStatus(projectId, lccCode, status);

					// 如果单位失活 下面的人 和 人的对应账号都失活
					if (status != null && "2".equals(status)) {
						// 人员失活
						lccUserDao.updateStatusForLccCodeAndProjectId(
								projectId, lccCode, "2");

						// 账号失活
						lccUserDao
								.updatePIPSYSACCOUNTStatusForLccCodeAndProjectId(
										projectId, lccCode, "2");

					}
					return msg = "修改成功";
				}

			}
		} catch (Exception e) {
			throw new ServiceException("更新失败！", e);
		}

		return msg;
	}

	@Override
	public void delete(String lccCode) {
		try {
			lccDao.delete(lccCode, Securitys.getUser().getCurrent_projectId());
		} catch (Exception e) {
			throw new ServiceException("删除失败！", e);
		}
	}

	@Override
	public Boolean checkLccCodeExists(String lccCode) {
		Lcc lcc = lccDao.getLcc(lccCode, Securitys.getUser()
				.getCurrent_projectId());
		if (lcc == null)
			return false;
		return true;
	}

	public List<Lcc> getLccByOrganType(String[] orgType) {
		return lccDao.getLccByOrganType(orgType);
	}

	@Override
	public List<Map<String, String>> getProvinceList(Map<String, String> map) {
		map.put("projectId", Securitys.getUser().getCurrent_projectId());
		map.put("userId", Securitys.getUser().getId());
		return lccDao.getProvinceList(map);
	}

	@Override
	public List<Map<String, String>> getRccList(Map<String, String> map) {
		map.put("projectId", Securitys.getUser().getCurrent_projectId());
		map.put("userId", Securitys.getUser().getId());
		return lccDao.getRccList(map);
	}

	public List<Map<String, String>> getDataLimitLccList(Map<String, String> map) {
		map.put("projectId", Securitys.getUser().getCurrent_projectId());
		map.put("userId", Securitys.getUser().getId());
		return lccDao.getDataLimitLccList(map);
	}
	@Override
	public List<Map<String, String>> getLccListByProvinceCode(Map<String, String> map) {
		map.put("projectId", Securitys.getUser().getCurrent_projectId());
		map.put("userId", Securitys.getUser().getId());
		return lccDao.getLccListByProvinceCode(map);
	}
	
	@Override
	public Lcc getOneForLccCodeAndProjectId(String lccCode, String projectId) {
		List<Lcc> lcc = lccDao.getOneForLccCodeAndProjectId(lccCode, projectId);
		if (lcc == null) {
			return null;
		} else {
			return lcc.get(0);
		}
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.bdcor.pip.web.pro.promgt.service.LccService#getDataLimitLcc(java.lang.String)
	 */
	@Override
	public List<Lcc> getDataLimitLcc(String flag) {
		return lccDao.getDataLimitLccByFlag(Securitys.getUser()
				.getCurrent_projectId(), Securitys.getUserId(), flag);
	}
}
