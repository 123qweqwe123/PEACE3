/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.qn.service 
 */

package com.bdcor.pip.web.qn.service;

import java.util.List;
import java.util.Map;

import com.bdcor.pip.web.qn.filter.ReportFilter;
/**  
 * description:  
 * @author yangfeng 创建时间：2016年3月3日         
 */
public interface ReportService
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
	/**
	 * 
	 * description:  采样器具包报表 all
	 * @author yangfeng  
	 * @return   
	 * @update 2016年3月7日
	 */
	List<Map<String, Object>> bloodReport();
	/**
	 * 
	 * description:  事件报表
	 * @author yangfeng  
	 * @param filter
	 * @return   
	 * @update 2016年3月4日
	 */
	List<Map<String, Object>> eventReport(ReportFilter filter);
	List<Map<String, Object>> eventReportSum(ReportFilter filter);
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
	List<Map<String, Object>> uqsAnswerqnLogReportSum(ReportFilter filter);
	/**
	 * 
	 * description:  血检报表
	 * @author yangfeng  
	 * @param filter
	 * @return   
	 * @update 2016年3月4日
	 */
	List<Map<String, Object>> uqsAnswerqnBloodReport(ReportFilter filter);
	List<Map<String, Object>> uqsAnswerqnBloodReportSum(ReportFilter filter);
	/**
	 * 
	 * description:  事件进度报表 all
	 * @author yangfeng  
	 * @return   
	 * @update 2016年3月7日
	 */
	List<Map<String, Object>> eventReport();
	/**
	 * 
	 * description:  事件进度报表明细 all
	 * @author yangfeng  
	 * @param filter
	 * @return   
	 * @update 2016年3月4日
	 */
	List<Map<String, Object>> eventDetailReport();
	/**
	 * 
	 * description:  血检报表 报表
	 * @author yangfeng  
	 * @return   
	 * @update 2016年3月7日
	 */
	List<Map<String, Object>> uqsAnswerqnBloodReport();
	/**
	 * 
	 * description:  问卷完成情况报表ALL
	 * @author yangfeng  
	 * @return   
	 * @update 2016年3月7日
	 */
	List<Map<String, Object>> uqsAnswerqnLogReport();
	List<Map<String, Object>> bloodReportSum(ReportFilter filter);
	/**
	 * 
	 * description:  血检报表明细
	 * @author yangfeng  
	 * @param filter
	 * @return   
	 * @update 2016年4月13日
	 */
	List<Map<String, Object>> uqsBloodReportDetailList(ReportFilter filter);

	/**
	 * 问卷完成情况导出 刨除总计行
	 * @param filter
	 * @return
	 */
	List<Map<String, Object>> uqsAnswerqnLogReportNoSum(ReportFilter filter);

}

