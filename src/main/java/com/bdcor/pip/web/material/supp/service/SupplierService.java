package com.bdcor.pip.web.material.supp.service;

import java.util.List;

import com.bdcor.pip.web.material.supp.domain.Supplier;
import com.bdcor.pip.web.material.supp.filter.SupplierFilter;

public interface SupplierService {

	List<Supplier> getAllSuppliers(SupplierFilter filter);

	Supplier getSupplierByCode(String supplierCode);

	void addSupplier(Supplier supplier);

	void updateSupplier(Supplier supplier);

	void delete(String supplierCode);

	Supplier checkNameExists(String supplierName);

}
