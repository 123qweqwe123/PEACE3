package com.bdcor.pip.web.material.supp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.material.supp.domain.MaterName;
import com.bdcor.pip.web.material.supp.filter.MaterNameFilter;

@MyBatisRepository
public interface MaterNameDao {

	List<MaterName> getAllMaterNames(MaterNameFilter filter);

	MaterName getMaterlNameByCode( @Param("materlCode") String materlCode);

	void addMaterNameService(MaterName materName);

	void updateMaterName(MaterName materName);

	void delete(String materlCode);

	MaterName checkNameExists(@Param("materlName") String materlName);

}
