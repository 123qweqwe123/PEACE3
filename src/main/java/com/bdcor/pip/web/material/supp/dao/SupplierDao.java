package com.bdcor.pip.web.material.supp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.material.supp.domain.Supplier;
import com.bdcor.pip.web.material.supp.filter.SupplierFilter;

@MyBatisRepository
public interface SupplierDao {

	List<Supplier> getAllSuppliers(SupplierFilter filter);

	Supplier getSupplierByCode(@Param("supplierCode")String supplierCode);

	void addSupplier(Supplier supplier);

	void updateSupplier(Supplier supplier);

	void delete(@Param("supplierCode")String supplierCode);

	Supplier checkNameExists(@Param("supplierName")String supplierName);

}
