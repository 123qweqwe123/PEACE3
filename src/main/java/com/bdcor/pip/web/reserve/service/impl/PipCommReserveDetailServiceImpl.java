package com.bdcor.pip.web.reserve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.web.reserve.dao.PipCommReserveDetailMapper;
import com.bdcor.pip.web.reserve.domain.PipCommReserveDetail;
import com.bdcor.pip.web.reserve.service.PipCommReserveDetailService;


@Service
@Transactional
public class PipCommReserveDetailServiceImpl implements PipCommReserveDetailService {
	
	
	@Autowired
	PipCommReserveDetailMapper pipCommReserveDetailMapper;

	@Override
	public void savePipCommReserveDetail(PipCommReserveDetail pcrd) {

		pipCommReserveDetailMapper.savePipCommReserveDetail(pcrd);
		
	}
	
	 public List<PipCommReserveDetail>  selectPipCommReserveDetail(PipCommReserveDetail pcrd){
		 
		 
		return  pipCommReserveDetailMapper.selectPipCommReserveDetail(pcrd);
		 
	 }


	
	
	
}
