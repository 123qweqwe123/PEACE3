package com.bdcor.pip.web.material.supp.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.core.exception.ServiceException;
import com.bdcor.pip.core.utils.DateUtil;
import com.bdcor.pip.core.utils.GenerateKey;
import com.bdcor.pip.core.utils.PinyingUtils;
import com.bdcor.pip.web.material.supp.dao.SupplierDao;
import com.bdcor.pip.web.material.supp.domain.Supplier;
import com.bdcor.pip.web.material.supp.filter.SupplierFilter;
import com.bdcor.pip.web.material.supp.service.SupplierService;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	private SupplierDao supplierDao;

	@Override
	public List<Supplier> getAllSuppliers(SupplierFilter filter) {
		List<Supplier> supplierList = supplierDao.getAllSuppliers(filter);
		for(int i=0; i<supplierList.size();i++){
			Supplier supp = supplierList.get(i);
			if(supp.getQualificationDisableDate()==null){
				supplierList.get(i).setStatus("1");
			}else{
				if(supp.getQualificationDisableDate().getTime()<new Date().getTime()){
					supplierList.get(i).setStatus("2");
				}else{
					supplierList.get(i).setStatus("1");
				}
			}
		}
		return supplierList;
	}

	@Override
	public Supplier getSupplierByCode(String supplierCode) {
		
		Supplier supplier = supplierDao.getSupplierByCode(supplierCode);
		supplier.setQualificationDisableDateStr(DateUtil.dateToString(supplier.getQualificationDisableDate(), "yyyy-MM-dd"));
		return supplier;
	}

	@Override
	public void addSupplier(Supplier supplier) {
		try{
			supplier.setSupplierCode(GenerateKey.getKey(GenerateKey.PREFIX_MATERIAL));
			supplier.setHelpCode(PinyingUtils.getJM(supplier.getSupplierName()));
			Date quaDisDate = supplier.getQualificationDisableDate();
			if(quaDisDate!=null){
				if(quaDisDate.getTime()<new Date().getTime()){
					supplier.setStatus("2");
				}else{
					supplier.setStatus("1");
				}
			}else{
				supplier.setStatus("1");
			}
			
			supplierDao.addSupplier(supplier);
		}catch(Exception e){
			throw new ServiceException("添加供应商失败！", e);
		}
	}

	@Override
	public void updateSupplier(Supplier supplier) {
		try{
			supplier.setHelpCode(PinyingUtils.getJM(supplier.getSupplierName()));
			Date quaDisDate = supplier.getQualificationDisableDate();
			if(quaDisDate!=null){
				if(quaDisDate.getTime()<new Date().getTime()){
					supplier.setStatus("2");
				}else{
					supplier.setStatus("1");
				}
			}else{
				supplier.setStatus("1");
			}
			supplierDao.updateSupplier(supplier);
		}catch(Exception e){
			throw new ServiceException("修改供应商失败！", e);
		}
	}

	@Override
	public void delete(String supplierCode) {
		try{
			supplierDao.delete(supplierCode);
		}catch(Exception e){
			throw new ServiceException("删除供应商失败！", e);
		}
	}

	@Override
	public Supplier checkNameExists(String supplierName) {
		return supplierDao.checkNameExists(supplierName);
	}
}
