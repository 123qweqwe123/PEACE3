/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.qn.dao 
 */

package com.bdcor.pip.web.qn.dao;

import java.util.List;
import java.util.Map;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.qn.filter.ReportFilter;
import org.apache.ibatis.annotations.Param;

/**  
 * description:  
 * @author yangfeng 创建时间：2016年3月3日         
 */
@MyBatisRepository
public interface ProgramReportDao
{
	/**
	 * 
	 * description:  采样器具包报表
	 * @author yangfeng  
	 * @param filter
	 * @return   
	 * @update 2016年3月4日
	 */
	List<Map<String, Object>> bloodReport(ReportFilter filter);
	List<Map<String, Object>> bloodReportSum(ReportFilter filter);
	/**
	 * 
	 * description:  事件报表
	 * @author yangfeng  
	 * @param filter
	 * @return   
	 * @update 2016年3月4日
	 */
	List<Map<String, Object>> eventReport(ReportFilter filter);
	/**
	 * 
	 * description:  事件进度报表明细
	 * @author yangfeng  
	 * @param filter
	 * @return   
	 * @update 2016年3月4日
	 */
	List<Map<String, Object>> eventDetailReport(ReportFilter filter);
	/**
	 * 
	 * description:  问卷完成情况报表
	 * @author yangfeng  
	 * @param filter
	 * @return   
	 * @update 2016年3月4日
	 */
	List<Map<String, Object>> uqsAnswerqnLogReport(ReportFilter filter);
	/**
	 * 
	 * description:  血检报表
	 * @author yangfeng  
	 * @param filter
	 * @return   
	 * @update 2016年3月4日
	 */
	List<Map<String, Object>> uqsAnswerqnBloodReport(ReportFilter filter);
	/**
	 * 
	 * description:  采样器具包报表 all
	 * @author yangfeng  
	 * @return   
	 * @update 2016年3月7日
	 */
	List<Map<String, Object>> bloodReportAll();
	/**
	 * 
	 * description:  事件进度全部
	 * @author yangfeng  
	 * @return   
	 * @update 2016年3月7日
	 */
	List<Map<String, Object>> eventReportAll();
	/**
	 * 
	 * description:  事件进度报表明细 全部
	 * @author yangfeng  
	 * @param filter
	 * @return   
	 * @update 2016年3月4日
	 */
	List<Map<String, Object>> eventDetailReportAll();
	/**
	 * 
	 * description:  血检报表全部
	 * @author yangfeng  
	 * @return   
	 * @update 2016年3月7日
	 */
	List<Map<String, Object>> uqsAnswerqnBloodReportAll();
	/**
	 * 
	 * description:  问卷完成情况报表全部
	 * @author yangfeng  
	 * @return   
	 * @update 2016年3月7日
	 */
	List<Map<String, Object>> uqsAnswerqnLogReportAll();
	List<Map<String, Object>> eventReportSum(ReportFilter filter);
	List<Map<String, Object>> uqsAnswerqnLogReportSum(ReportFilter filter);
	List<Map<String, Object>> uqsAnswerqnBloodReportSum(ReportFilter filter);
	/**
	 * 
	 * description:  血检报表明细 主要是查看未录入的血检报告
	 * @author yangfeng  
	 * @return   
	 * @update 2016年4月13日
	 */
	List<Map<String, Object>> uqsBloodReportDetailList(ReportFilter filter);

	List<Map<String, Object>> getBloodGroupListAll(@Param("lccCode") String lccCode);
	List<Map<String, Object>> getBloodGroupListJX(@Param("lccCode") String lccCode);
	List<Map<String, Object>> getBloodGroupListCHAT(@Param("lccCode") String lccCode);
	List<Map<String, Object>> getBloodInfoFor6(ReportFilter filter);

	List<Map<String, Object>> getBloodInfoFor12(ReportFilter filter);

	List<Map<String, Object>> getBloodGroupListAll_LAST(@Param("lccCode") String lccCode);
	List<Map<String, Object>> getBloodGroupGYSF(@Param("lccCode") String lccCode);
	List<Map<String, Object>> getBloodGroupFGYMF(@Param("lccCode") String lccCode);


	List<Map<String,Object>> uqsQnlogReport(ReportFilter filter);

	List<Map<String,Object>> uqsQnlogReportSum(ReportFilter filter);
}

