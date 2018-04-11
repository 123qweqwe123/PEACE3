package com.bdcor.pip.web.material.supp.service;

import java.util.List;
import java.util.Map;

import com.bdcor.pip.web.material.supp.domain.MaterlInfo;
import com.bdcor.pip.web.material.supp.filter.MaterlInfoFilter;

public interface MaterlInfoService {
	/**
	 * 通过权限获取设备信息
	 * @return
	 */
	List<Map<String, String>> materlInfoListByLimit();
	List<MaterlInfo> getAllMaterInfos(MaterlInfoFilter filter);

	MaterlInfo getMaterlInfoByCode(String materlInfoCode);

	void addMaterInfo(MaterlInfo materlInfo);

	void updateMaterInfo(MaterlInfo materlInfo);

	void delete(String materlInfoCode);

}
