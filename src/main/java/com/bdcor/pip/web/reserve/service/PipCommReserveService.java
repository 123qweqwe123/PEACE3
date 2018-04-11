package com.bdcor.pip.web.reserve.service;

import com.bdcor.pip.web.data.domain.PipCommPatient;
import com.bdcor.pip.web.data.domain.PipCommPatientKey;
import com.bdcor.pip.web.reserve.domain.PipCommReserve;
import com.bdcor.pip.web.reserve.domain.ReserveResult;
import com.bdcor.pip.web.reserve.filter.PipCommReserveDetailFilter;
import com.bdcor.pip.web.reserve.filter.PipCommReserveFilter;

import java.util.List;
import java.util.Map;

public interface PipCommReserveService {

	
	 List<PipCommReserve> getPipCommReserveList(PipCommReserveFilter filter);  
	
	
	 PipCommPatient selectByPrimaryKey(PipCommPatientKey key);
	
	 List<PipCommReserve> selectPipCommReserveListForPatientId(PipCommReserve pcr);

	 void  updatePipCommReserve(PipCommReserve pcr);
	
	 Boolean  saveReserveDetailUpdateReserve(PipCommReserveDetailFilter pcdf);
	
     List<ReserveResult> reserveResult(Map<String, String> map);

	 List<Map<String,String>> getGroupData(String year, String month,String ismsg );

     List<Map<String,String>> getGroupData(int year, int month , String ismsg  );

    List<Map<String,String>> getDataInfo( PipCommReserveFilter filter);

     String[] getDateArr( String year , String month );

     String[] getDateArr( int y , int m );

     int updateViewDate(String pid ,String newdate);
	}
