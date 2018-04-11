package com.bdcor.pip.web.reserve.dao;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.reserve.domain.PipCommReserve;
import com.bdcor.pip.web.reserve.domain.ReserveResult;
import com.bdcor.pip.web.reserve.filter.PipCommReserveFilter;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


@MyBatisRepository
public interface PipCommReserveMapper {

	public List<PipCommReserve> getPipCommReserveList(PipCommReserveFilter filter);

	
	public List<PipCommReserve> selectPipCommReserveListForPatientId(PipCommReserve pcr);
	
	public void  updatePipCommReserve(PipCommReserve pcr);
	
	
	public void   updatePipCommReserveForQuartzYellow();
	
	public void   updatePipCommReserveForQuartzRed();
	
	public void  creatPipCommReserveForQuartzRed();
	
	public  List<ReserveResult> reserveResult(Map<String, String> map);

	List<Map<String,String>> getGroupData(Map<String,String> m);

	List<Map<String,String>> getDataInfo(PipCommReserveFilter filter);

    Map<String,String> getViewColumn(String pid);

    int updatePlandate(@Param("col") String col , @Param("colvalue") String colvalue , @Param("pid") String pid);

	List<Map<String,Object>> getPlanViewInfo(PipCommReserveFilter filter);
}
