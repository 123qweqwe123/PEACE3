package com.bdcor.pip.web.fee.service;

import java.util.List;
import com.bdcor.pip.web.fee.domain.PipActualReg;    
import com.bdcor.pip.web.fee.filter.FeeLccRegFilter;

public interface FeeLccRegService {
	
	List<PipActualReg> list(FeeLccRegFilter f);  
	
	int save(PipActualReg ar);  
	
	int delete(String id);   
	
	PipActualReg getById(String id);
	 
	int  update( PipActualReg ar);  
}   
