/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.qn.service 
 */

package com.bdcor.pip.web.qn.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bdcor.pip.web.qn.dao.ProgramReportDao;
import com.bdcor.pip.web.qn.filter.ReportFilter;
import com.bdcor.pip.web.qn.service.ReportService;

/**  
 * description:  
 * @author yangfeng 创建时间：2016年3月3日         
 */
@Service
public class ReportServiceImpl implements ReportService
{
	@Autowired
	private ProgramReportDao reportMapper;
	public List<Map<String, Object>> bloodReport(ReportFilter filter)
	{
		List<Map<String,Object>> bloodReportSum = reportMapper.bloodReportSum(filter);
		List<Map<String,Object>> bloodReport = reportMapper.bloodReport(filter);
		bloodReport.addAll(bloodReportSum);
		return bloodReport;
	}
	public List<Map<String, Object>> bloodReportSum(ReportFilter filter)
	{
		List<Map<String,Object>> bloodReportSum = reportMapper.bloodReportSum(filter);
		return bloodReportSum;
	}
	@Override
	public List<Map<String, Object>> eventReport(ReportFilter filter)
	{
		List<Map<String, Object>> eventReportSum = reportMapper.eventReportSum(filter);
		List<Map<String,Object>> eventReport = reportMapper.eventReport(filter);
		eventReport.addAll(eventReportSum);
		return eventReport;
	}
	@Override
	public List<Map<String, Object>> eventReportSum(ReportFilter filter)
	{
		List<Map<String, Object>> eventReportSum = reportMapper.eventReportSum(filter);
		return eventReportSum;
	}
	@Override
	public List<Map<String, Object>> eventDetailReport(ReportFilter filter)
	{
		return reportMapper.eventDetailReport(filter);
	}
	@Override
	public List<Map<String, Object>> eventDetailReport()
	{
		return reportMapper.eventDetailReportAll();
	}
	@Override
	public List<Map<String, Object>> uqsAnswerqnLogReport(ReportFilter filter)
	{
		List<Map<String,Object>> uqsAnswerqnLogReportSum = reportMapper.uqsAnswerqnLogReportSum(filter);
		List<Map<String,Object>> uqsAnswerqnLogReport = reportMapper.uqsAnswerqnLogReport(filter);
		uqsAnswerqnLogReport.addAll(uqsAnswerqnLogReportSum);
		return uqsAnswerqnLogReport;
	}

	/**
	 * 导出时刨除总计行
	 * @param filter
	 * @return
	 */
	public List<Map<String, Object>> uqsAnswerqnLogReportNoSum(ReportFilter filter)
	{
		List<Map<String,Object>> uqsAnswerqnLogReport = reportMapper.uqsAnswerqnLogReport(filter);
		return uqsAnswerqnLogReport;
	}

	@Override
	public List<Map<String, Object>> uqsAnswerqnLogReportSum(ReportFilter filter)
	{
		List<Map<String,Object>> uqsAnswerqnLogReportSum = reportMapper.uqsAnswerqnLogReportSum(filter);
		return uqsAnswerqnLogReportSum;
	}
	@Override
	public List<Map<String, Object>> uqsAnswerqnLogReport()
	{
		return reportMapper.uqsAnswerqnLogReportAll();
	}
	@Override
	public List<Map<String, Object>> uqsAnswerqnBloodReport(ReportFilter filter)
	{
		List<Map<String, Object>> uqsAnswerqnBloodReportSum = reportMapper.uqsAnswerqnBloodReportSum(filter);
		List<Map<String, Object>> uqsAnswerqnBloodReport = reportMapper.uqsAnswerqnBloodReport(filter);
		uqsAnswerqnBloodReport.addAll(uqsAnswerqnBloodReportSum);
		return uqsAnswerqnBloodReport;
	}
	@Override
	public List<Map<String, Object>> uqsAnswerqnBloodReportSum(ReportFilter filter)
	{
		List<Map<String, Object>> uqsAnswerqnBloodReportSum = reportMapper.uqsAnswerqnBloodReportSum(filter);
		return uqsAnswerqnBloodReportSum;
	}
	@Override
	public List<Map<String, Object>> uqsAnswerqnBloodReport()
	{
		return reportMapper.uqsAnswerqnBloodReportAll();
	}
	@Override
	public List<Map<String, Object>> bloodReport()
	{
		
		return reportMapper.bloodReportAll();
	}
	@Override
	public List<Map<String, Object>> eventReport()
	{
		return reportMapper.eventReportAll();
	}
	@Override
	public List<Map<String, Object>> uqsBloodReportDetailList(ReportFilter filter)
	{
		
		return reportMapper.uqsBloodReportDetailList(filter);
	}

}

