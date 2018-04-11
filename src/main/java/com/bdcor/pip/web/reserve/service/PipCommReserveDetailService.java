package com.bdcor.pip.web.reserve.service;

import java.util.List;

import com.bdcor.pip.web.reserve.domain.PipCommReserveDetail;

public interface PipCommReserveDetailService {


	 public void  savePipCommReserveDetail(PipCommReserveDetail pcrd);
		
	
	 public List<PipCommReserveDetail>  selectPipCommReserveDetail(PipCommReserveDetail pcrd);
	
}
