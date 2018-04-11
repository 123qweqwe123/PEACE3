package com.bdcor.pip.web.qn.dao;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.qn.filter.ReportFilter;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 16-11-21.
 */
@MyBatisRepository
public interface PipQnReportDao {

    List<Map<String,Object>> bloodlist(@Param("lccCode")String lccCode);
    List<Map<String,Object>> getNoCenterBloodlist(ReportFilter filter);

    List<Map<String,Object>> getDbpListData(ReportFilter filter);
	List<Map<String,Object>> getDbpDataForExcel(ReportFilter filter);

	List<Map<String,Object>> getDbpListDataByLast(ReportFilter filter);
	List<Map<String,Object>> getDbpDataForExcelByLast(ReportFilter filter);

    int insertDbpInfo(Map<String,String> m);

    int updateDbpInfo(Map<String,String> m);

    int deleteDbpInfo(Map<String,String> m);

    List<Map<String,Object>> getDbpGroupData(ReportFilter filter);

	List<Map<String,Object>> getDbpGroupDataByLast(ReportFilter filter);
}
