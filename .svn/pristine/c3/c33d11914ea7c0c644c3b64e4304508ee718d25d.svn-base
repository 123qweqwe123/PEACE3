package com.bdcor.pip.web.material.supp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.material.supp.domain.MaterlInfo;
import com.bdcor.pip.web.material.supp.filter.MaterlInfoFilter;

@MyBatisRepository
public interface MaterlInfoDao {

	List<MaterlInfo> getAllMaterInfos(MaterlInfoFilter filter);

	MaterlInfo getMaterInfoByCode(@Param("materlInfoCode") String materlInfoCode);

	void addMaterInfo(MaterlInfo materlInfo);

	void updateMaterlInfo(MaterlInfo materlInfo);

	void delete(@Param("materlInfoCode") String materlInfoCode);

	String getClassNameByCode(@Param("classCode") String classCode);

	String getSupplierNameByCode(@Param("supplierCode") String supplierCode);
	
	List<Map<String, String>> materlInfoListByLimit(@Param("userId")String userId);

}
