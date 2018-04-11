package com.bdcor.pip.web.material.supp.filter;

import com.bdcor.pip.core.persistence.domain.BaseFilter;

public class SupplierFilter extends BaseFilter {

	private String supplierName;

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
}
