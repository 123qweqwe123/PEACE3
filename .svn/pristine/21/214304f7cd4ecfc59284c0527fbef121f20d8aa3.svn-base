package com.bdcor.pip.web.pro.promgt.service.impl;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.core.exception.ServiceException;
import com.bdcor.pip.core.utils.GenerateKey;
import com.bdcor.pip.core.utils.PinyingUtils;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.pro.promgt.dao.LccUserDao;
import com.bdcor.pip.web.pro.promgt.dao.LinkManDao;
import com.bdcor.pip.web.pro.promgt.domain.LccUser;
import com.bdcor.pip.web.pro.promgt.domain.LinkMan;
import com.bdcor.pip.web.pro.promgt.filter.LccUserFilter;
import com.bdcor.pip.web.pro.promgt.service.LccUserService;

@Service
@Transactional
public class LccUserServiceImpl implements LccUserService {

	@Autowired
	private LccUserDao lccUserDao;
	@Autowired
	private LinkManDao linkManDao;
	@Override
	public List<LccUser> getAllLccUsers(LccUserFilter filter) {
		filter.setProjectId(Securitys.getUser().getCurrent_projectId());
		filter.setUserId(Securitys.getUserId());
		return lccUserDao.getAllLccUsers(filter);
	}

	@Override
	public LccUser getLccUserById(String userCode, String lccRoleType) {
		return lccUserDao.getLccUserById(userCode, Securitys.getUser()
				.getCurrent_projectId(), lccRoleType);
	}

	@Override
	public void addLccUser(LccUser lccUser) {
		try {
			lccUser.setProjectId(Securitys.getUser().getCurrent_projectId());

			if (lccUser.getUserCode() == null
					|| "".equals(lccUser.getUserCode())) {
				// String lccCode =
				// GenerateKey.getKey(GenerateKey.PREFIX_LCC_USER);
				String lccSequence = getLccSequence(lccUser.getLccCode());
				// 单位Code+四位流水
				lccUser.setUserCode(lccUser.getLccCode() + lccSequence);
			}
			// 添加到联系人
			if ("1".equals(lccUser.getAddToLinkMan())) {
				lccUser = addLinkMan(lccUser);
			}
			lccUser.setHelpCode(PinyingUtils.getJM(lccUser.getName()));
			lccUserDao.addLccUser(lccUser);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("添加单位用户失败！", e);
		}
	}

	private String getLccSequence(String lccCode) {
		String lccSequence = "0001";
		String maxLccCode = lccUserDao.getMaxLccCode(Securitys.getUser()
				.getCurrent_projectId(), lccCode);
		if (StringUtils.isEmpty(maxLccCode)) {
			return lccSequence;
		} else {
			String lccSeq = maxLccCode.substring(maxLccCode.length() - 4);
			return formatKey(4, Long.parseLong(lccSeq) + 1);
		}

	}

	private String formatKey(int length, Long keyValue) {
		String pattern = "";
		for (int i = 0; i < length; i++) {
			pattern += "0";
		}
		DecimalFormat df = new DecimalFormat(pattern);
		return df.format(keyValue);
	}

	@Override
	public void updateLccUser(LccUser lccUser) {
		try {
			if ("1".equals(lccUser.getAddToLinkMan())) {
				lccUser = addLinkMan(lccUser);
			}
			lccUser.setHelpCode(PinyingUtils.getJM(lccUser.getName()));
			lccUserDao.updateLccUser(lccUser);
			//更新一次账户的单位 保证这个账号所在的单位也跟着改变
			lccUserDao.updateAccountLccByUserCode(lccUser);
			// addToLinkMan==2 从联系人列表删除
			if ("2".equals(lccUser.getAddToLinkMan())) {
				linkManDao.delete(lccUser.getLinkManCode());
			}

		} catch (Exception e) {
			throw new ServiceException("更新单位用户失败！", e);
		}
	}

	@Override
	public void changeStatus(LccUser lccUser) {
		try {
			lccUserDao.changeStatus(lccUser);
			// 下面1表示激活 都没有写什么逻辑 可能以后有逻辑，先区分预留
			if (lccUser != null && "1".equals(lccUser.getStatus())) {
				lccUserDao.changePipSysAccountStatus(lccUser);
			} else {
				lccUserDao.changePipSysAccountStatus(lccUser);
			}
		} catch (Exception e) {
			throw new ServiceException("更新单位用户失败！", e);
		}
	}

	public void changePipSysAccountStatus(LccUser lccUser) {
		try {
			lccUserDao.changePipSysAccountStatus(lccUser);
		} catch (Exception e) {
			throw new ServiceException("更新单位用户失败！", e);
		}
	}

	@Override
	public List<LccUser> getLccUsersByProjectId() {
		return lccUserDao.getLccUsersByProjectId(Securitys.getUser()
				.getCurrent_projectId());
	}

	private LccUser addLinkMan(LccUser lccUser) {
		String linkManCode = GenerateKey.getKey(GenerateKey.PREFIX_PROJECT);

		LinkMan linkMan = new LinkMan();
		linkMan.setEmail(lccUser.getEmail());
		linkMan.setLccCode(lccUser.getLccCode());
		linkMan.setLccRole(lccUser.getLccRole());
		linkMan.setMobile(lccUser.getMobile());
		linkMan.setPhone(lccUser.getPhone());
		linkMan.setProjectId(Securitys.getUser().getCurrent_projectId());
		linkMan.setStatus("0");
		linkMan.setUsername(lccUser.getName());
		linkMan.setLinkManCode(linkManCode);
		linkManDao.addLinkMan(linkMan);
		lccUser.setLinkManCode(linkManCode);
		return lccUser;
	}

	public List<LccUser> getLccUserByLccCode(String lccCode) {
		return lccUserDao.getLccUserByLccCode(lccCode, Securitys.getUser()
				.getCurrent_projectId());
	}

	@Override
	public List<LccUser> getOneForLccCodeAndProjectIdAndUserCode(
			String lccCode, String projectId, String userCode) {
		return lccUserDao.getOneForLccCodeAndProjectIdAndUserCode(lccCode,
				projectId, userCode);
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.bdcor.pip.web.pro.promgt.service.LccUserService#getLccUserList(java.util.Map)
	 */
	@Override
	public List<Map<String, String>> getLccUserList(Map<String, String> map) {
		return lccUserDao.getLccUserList(map);
	}
}
