package com.bdcor.pip.web.material.supp.dao;

import java.util.List;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.material.supp.domain.MaterUser;
import com.bdcor.pip.web.material.supp.filter.MaterUserFilter;

@MyBatisRepository
public interface MaterUserDao {
	int insert(MaterUser materUser);
	int update(MaterUser materUser);
	int delete(MaterUser materUser);
	MaterUser getMaterUser(MaterUser materUser);
	List<MaterUser> getAllMaterUser(MaterUserFilter filter);
	String selectMaxUserCode(MaterUser materUser);
	
}
