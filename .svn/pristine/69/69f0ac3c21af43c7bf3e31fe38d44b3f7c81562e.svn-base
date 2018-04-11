package com.bdcor.pip.web.qn.controller;

import java.io.BufferedOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.common.dao.PipSysExpexcelInfoMapper;
import com.bdcor.pip.web.common.domain.PipSysExpexcelInfo;
import com.bdcor.pip.web.qn.dao.ProgramReportDao;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.tools.ant.taskdefs.Java;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.data.constant.ExcelDataConstant;
import com.bdcor.pip.data.util.ExcelExportBatchUtil;
import com.bdcor.pip.web.qn.filter.ReportFilter;
import com.bdcor.pip.web.qn.service.ReportService;

@Controller
@RequestMapping("qn") 
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	@Autowired
	private PipSysExpexcelInfoMapper excelinfpDao;
	/**
	 * 
	 * description:  支持性文件收集报表
	 * @author yangfeng  
	 * @param filter
	 * @return   
	 * @update 2016年3月4日
	 */
	@RequestMapping(value = "supportFileReportList", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<Map<String,Object>> supportFileReportList(ReportFilter filter) { 
		List<Map<String,Object>> uqsBloodReportDetail = reportService.uqsBloodReportDetailList(filter);
		JqgridResponse<Map<String,Object>> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(uqsBloodReportDetail);
		return response;
	}
	/**
	 * 
	 * description:  血检报表明细
	 * @author yangfeng  
	 * @param filter
	 * @return   
	 * @update 2016年3月4日
	 */
	@RequestMapping(value = "uqsBloodReportDetailList", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<Map<String,Object>> uqsBloodReportDetailList(ReportFilter filter) { 
		List<Map<String,Object>> uqsBloodReportDetail = reportService.uqsBloodReportDetailList(filter);
		JqgridResponse<Map<String,Object>> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(uqsBloodReportDetail);
		return response;
	}
	/**
	 * 
	 * description:采血器具包使用报表  
	 * @author yangfeng  
	 * @param filter
	 * @return   
	 * @update 2016年3月4日
	 */
	@RequestMapping(value = "bloodReportList", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<Map<String,Object>> bloodReport(ReportFilter filter) { 
		List<Map<String,Object>> bloodReport = reportService.bloodReport(filter);
		JqgridResponse<Map<String,Object>> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(bloodReport);
		response.setRecords(response.getRecords() == null ? 0 : response.getRecords() -1);
		return response;
	}
	@RequestMapping(value = "bloodReportListSum", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<Map<String,Object>> bloodReportSum(ReportFilter filter) { 
		List<Map<String,Object>> bloodReport = reportService.bloodReportSum(filter);
		JqgridResponse<Map<String,Object>> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(bloodReport);
		return response;
	}
	/**
	 * 
	 * description:  导出采血器具包使用报表  全部
	 * @author yangfeng  
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception   
	 * @update 2016年3月7日
	 */
	@RequestMapping(value = "/bloodReportExport")
	public ModelAndView bloodReportExport(HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		java.io.BufferedOutputStream bos = null;
		try {
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("采样器具包使用报表.xls".getBytes("utf-8"), "ISO8859-1"));
			List<Map<String,Object>> list = reportService.bloodReport();
			bos = new BufferedOutputStream(response.getOutputStream());
			SXSSFWorkbook workbook = new SXSSFWorkbook(ExcelExportBatchUtil.SXSSWORKBOOK_MEMORY); 
			ExcelExportBatchUtil.createAndFillingExcel(list, ExcelDataConstant.HEAD_BLOOD, ExcelDataConstant.DATA_BLOOD, workbook);
			workbook.write(bos);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null)
				bos.close();
		}
		return null;
	}
	
	/**
	 * 
	 * description:  事件进度报表
	 * @author yangfeng  
	 * @param filter
	 * @return   
	 * @update 2016年3月4日
	 */
	@RequestMapping(value = "eventReportList", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<Map<String,Object>> eventReport(ReportFilter filter) { 
		List<Map<String,Object>> eventReport = reportService.eventReport(filter);
		JqgridResponse<Map<String,Object>> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(eventReport);
		response.setRecords(response.getRecords() == null ? 0 : response.getRecords() - 1);
		return response;
	}
	@RequestMapping(value = "eventReportListSum", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<Map<String,Object>> eventReportSum(ReportFilter filter) { 
		List<Map<String,Object>> eventReport = reportService.eventReportSum(filter);
		JqgridResponse<Map<String,Object>> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(eventReport);
		return response;
	}
	/**
	 * 
	 * description:  导出事件进度报表  全部
	 * @author yangfeng  
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception   
	 * @update 2016年3月7日
	 */
	@RequestMapping(value = "/eventReportExport")
	public ModelAndView eventReportExport(ReportFilter filter,HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		java.io.BufferedOutputStream bos = null;
		try {
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("事件进度报表.xls".getBytes("utf-8"), "ISO8859-1"));
            filter.setRows(999999);
			filter.setSord("  ASC ");
			filter.setSidx(" LCC_CODE ");
			List<Map<String,Object>> list = reportService.eventReport(filter);
			bos = new BufferedOutputStream(response.getOutputStream());
			SXSSFWorkbook workbook = new SXSSFWorkbook(ExcelExportBatchUtil.SXSSWORKBOOK_MEMORY); 
			ExcelExportBatchUtil.createAndFillingExcel(list, ExcelDataConstant.HEAD_EVENT, ExcelDataConstant.DATA_EVENT, workbook);
			workbook.write(bos);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null)
				bos.close();
		}
		return null;
	}
	/**
	 * 
	 * description:  事件进度明细报表
	 * @author yangfeng  
	 * @param filter
	 * @return   
	 * @update 2016年3月4日
	 */
	@RequestMapping(value = "eventDetailReportList", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<Map<String,Object>> eventDetailReport(ReportFilter filter) { 
		List<Map<String,Object>> eventDetail = reportService.eventDetailReport(filter);
		JqgridResponse<Map<String,Object>> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(eventDetail);
		return response;
	}
	/**
	 * 
	 * description:  导出事件进度明细报表  全部
	 * @author yangfeng  
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception   
	 * @update 2016年3月7日
	 */
	@RequestMapping(value = "/eventDetailReportExport")
	public ModelAndView eventDetailReportExport(ReportFilter filter,HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
        filter.setPatientName(java.net.URLDecoder.decode(filter.getPatientName(), "UTF-8"));
		request.setCharacterEncoding("utf-8");
		java.io.BufferedOutputStream bos = null;
		try {
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("事件进度明细报表.xls".getBytes("utf-8"), "ISO8859-1"));
            filter.setRows(999999);
			filter.setSord("  ASC ");
			filter.setSidx(" LCC_CODE ");
			List<Map<String,Object>> list = reportService.eventDetailReport(filter);
			bos = new BufferedOutputStream(response.getOutputStream());
			SXSSFWorkbook workbook = new SXSSFWorkbook(ExcelExportBatchUtil.SXSSWORKBOOK_MEMORY); 
			ExcelExportBatchUtil.createAndFillingExcel(list, ExcelDataConstant.HEAD_DETAIL_EVENT, ExcelDataConstant.DATA_DETAIL_EVENT, workbook);
			workbook.write(bos);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null)
				bos.close();
		}
		return null;
	}
	/**
	 * 
	 * description:  问卷完成情况报表
	 * @author yangfeng  
	 * @param filter
	 * @return   
	 * @update 2016年3月4日
	 */
	@RequestMapping(value = "uqsAnswerqnLogReportList", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<Map<String,Object>> uqsAnswerqnLogReport(ReportFilter filter) {
//		List<Map<String,Object>> uqsAnswerqnLog = reportService.uqsAnswerqnLogReport(filter);
        List<Map<String,Object>> uqsAnswerqnLog = reportDao.uqsQnlogReport(filter);
        List<Map<String,Object>> uqsAnswerqnLogSum = reportDao.uqsQnlogReportSum(filter);
        uqsAnswerqnLog.addAll(uqsAnswerqnLogSum);
		JqgridResponse<Map<String,Object>> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(uqsAnswerqnLog);
		response.setRecords(response.getRecords() == null ? 0 : response.getRecords() -1);
		return response;
	}
	@RequestMapping(value = "uqsAnswerqnLogReportListSum", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<Map<String,Object>> uqsAnswerqnLogReportSum(ReportFilter filter) {
		List<Map<String,Object>> uqsAnswerqnLog = reportService.uqsAnswerqnLogReportSum(filter);
		JqgridResponse<Map<String,Object>> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(uqsAnswerqnLog);
		return response;
	}
	/**
	 * 
	 * description:  问卷完成情况报表 导出
	 * @author yangfeng  
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception   
	 * @update 2016年3月7日
	 */
	@RequestMapping(value = "/uqsAnswerqnLogReportExport")
	public ModelAndView uqsAnswerqnLogReportExport(HttpServletRequest request,
			HttpServletResponse response,ReportFilter filter)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		java.io.BufferedOutputStream bos = null;
		try {
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("问卷完成情况报表.xls".getBytes("utf-8"), "ISO8859-1"));
			filter.setRows(9999999);
			filter.setSord("  ASC ");
			filter.setSidx(" LCC_CODE ");
            List<Map<String,Object>> list = reportDao.uqsQnlogReport(filter);
            bos = new BufferedOutputStream(response.getOutputStream());
			SXSSFWorkbook workbook = new SXSSFWorkbook(ExcelExportBatchUtil.SXSSWORKBOOK_MEMORY);
			ExcelExportBatchUtil.createAndFillingExcel(list, ExcelDataConstant.HEAD_UQSANSWERQNLOG, ExcelDataConstant.DATA_UQSANSWERQNLOG, workbook);
			workbook.write(bos);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null)
				bos.close();
		}
		return null;
	}
	/**
	 * 
	 * description:  血检报告
	 * @author yangfeng  
	 * @param filter
	 * @return   
	 * @update 2016年3月4日
	 */
	@RequestMapping(value = "uqsAnswerqnBloodReportList", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<Map<String,Object>> uqsAnswerqnBloodReport(ReportFilter filter) { 
		List<Map<String,Object>> uqsAnswerqnBlood = reportService.uqsAnswerqnBloodReport(filter);
		JqgridResponse<Map<String,Object>> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(uqsAnswerqnBlood);
		response.setRecords(response.getRecords() == null ? 0 : response.getRecords() -1);
		return response;
	}
	/**
	 * 
	 * description:  血检报告
	 * @author yangfeng  
	 * @param filter
	 * @return   
	 * @update 2016年3月4日
	 */
	@RequestMapping(value = "uqsAnswerqnBloodReportListSum", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<Map<String,Object>> uqsAnswerqnBloodReportSum(ReportFilter filter) { 
		List<Map<String,Object>> uqsAnswerqnBlood = reportService.uqsAnswerqnBloodReportSum(filter);
		JqgridResponse<Map<String,Object>> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(uqsAnswerqnBlood);
		return response;
	}
	/**
	 * 
	 * description:  导出血检报告报表  全部
	 * @author yangfeng  
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception   
	 * @update 2016年3月7日
	 */
	@RequestMapping(value = "/uqsAnswerqnBloodReportExport")
	public ModelAndView uqsAnswerqnBloodReportExport(HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		java.io.BufferedOutputStream bos = null;
		try {
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("血检报告报表.xls".getBytes("utf-8"), "ISO8859-1"));
			List<Map<String,Object>> list = reportService.uqsAnswerqnBloodReport();
			bos = new BufferedOutputStream(response.getOutputStream());
			SXSSFWorkbook workbook = new SXSSFWorkbook(ExcelExportBatchUtil.SXSSWORKBOOK_MEMORY); 
			ExcelExportBatchUtil.createAndFillingExcel(list, ExcelDataConstant.HEAD_UQSANSWERQNBLOOD, ExcelDataConstant.DATA_UQSANSWERQNBLOOD, workbook);
			workbook.write(bos);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null)
				bos.close();
		}
		return null;
	}
	@RequestMapping("bloodReport")
	public String initBloodReport(){
		return "qn/report/bloodReport";
	}
	
	@RequestMapping("eventReport")
	public String initEventReport(){
		return "qn/report/eventReport";
	}
	
	@RequestMapping("eventDetailReport")
	public String initEventDetailReport(){
		return "qn/report/eventDetailReport";
	}
	
	@RequestMapping("uqsAnswerqnLogReport")
	public String initUqsAnswerqnLogReport(){
		return "qn/report/uqsAnswerqnLogReport";
	}
	
	@RequestMapping("uqsAnswerqnBloodReport")
	public String initUqsAnswerqnBloodReport(){
		return "qn/report/uqsAnswerqnBloodReport";
	}
	
	@RequestMapping("uqsBloodReportDetail")
	public String initUqsBloodReportDetail(){
		return "qn/report/uqsBloodReportDetail";
	}
	@RequestMapping("supportFileReport")
	public String initSupportFileReport(){
		return "qn/report/supportFileReport";
	}


	@Autowired
	private ProgramReportDao reportDao;

	@RequestMapping("reportBlood2")
	public @ResponseBody JqgridResponse<Map<String,Object>>
	reportBlood2(@RequestParam(value = "lccCode",required = false) String lccCode ,
				 @RequestParam(value = "group",required = false)String group){
		JqgridResponse<Map<String,Object>> resp = JqgridResponseContext.getJqgridResponse();
		if(group == null || "".equals(group)){
			resp.setRows(reportDao.getBloodGroupListAll(lccCode));
		}else if("0".equals(group)){
			resp.setRows(reportDao.getBloodGroupListCHAT(lccCode));
		}else if("1".equals(group)){
			resp.setRows(reportDao.getBloodGroupListJX(lccCode));
		}
		return resp ;
	}
	@RequestMapping(value = "/uqsAnswerqnBloodReportExport2")
	public ModelAndView uqsAnswerqnBloodReportExport2(HttpServletRequest req,
													 HttpServletResponse resp,
													  @RequestParam(value = "lccCode",required = false) String lccCode ,
													  @RequestParam(value = "group",required = false)String group)
			throws Exception {
		req.setCharacterEncoding("utf-8");
		java.io.BufferedOutputStream bos = null;
		try {
			resp.setContentType("application/x-msdownload;");
			resp.setHeader("Content-disposition", "attachment; filename="
					+ new String("6月随访血检报告报表.xls".getBytes("utf-8"), "ISO8859-1"));
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			if(group == null || "".equals(group)){
				list = reportDao.getBloodGroupListAll(lccCode);
			}else if("0".equals(group)){
				list = reportDao.getBloodGroupListCHAT(lccCode);
			}else if("1".equals(group)){
				list = reportDao.getBloodGroupListJX(lccCode);
			}
			bos = new BufferedOutputStream(resp.getOutputStream());
			SXSSFWorkbook workbook = new SXSSFWorkbook(ExcelExportBatchUtil.SXSSWORKBOOK_MEMORY);
			ExcelExportBatchUtil.createAndFillingExcel(list, ExcelDataConstant.HEAD_UQSANSWERQNBLOOD_2, ExcelDataConstant.DATA_UQSANSWERQNBLOOD_2, workbook);
			workbook.write(bos);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null)
				bos.close();
		}
		return null;
	}

	@RequestMapping("reportBlood12")
	public @ResponseBody JqgridResponse<Map<String,Object>>
	reportBlood12(@RequestParam(value = "lccCode",required = false) String lccCode ,
				 @RequestParam(value = "group",required = false)String group){
		JqgridResponse<Map<String,Object>> resp = JqgridResponseContext.getJqgridResponse();
		if(group == null || "".equals(group)){
			resp.setRows(reportDao.getBloodGroupListAll_LAST(lccCode));
		}else if("0".equals(group)){
			resp.setRows(reportDao.getBloodGroupFGYMF(lccCode));
		}else if("1".equals(group)){
			resp.setRows(reportDao.getBloodGroupGYSF(lccCode));
		}
		return resp ;
	}
	@RequestMapping(value = "/BloodReportExport12")
	public ModelAndView uqsAnswerqnBloodReportExport12(HttpServletRequest req,
													  HttpServletResponse resp,
													  @RequestParam(value = "lccCode",required = false) String lccCode ,
													  @RequestParam(value = "group",required = false)String group)
			throws Exception {
		req.setCharacterEncoding("utf-8");
		java.io.BufferedOutputStream bos = null;
		try {
			resp.setContentType("application/x-msdownload;");
			resp.setHeader("Content-disposition", "attachment; filename="
					+ new String("末次随访血检报告报表.xls".getBytes("utf-8"), "ISO8859-1"));
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			if(group == null || "".equals(group)){
				list = reportDao.getBloodGroupListAll_LAST(lccCode);
			}else if("0".equals(group)){
				list = reportDao.getBloodGroupFGYMF(lccCode);
			}else if("1".equals(group)){
				list = reportDao.getBloodGroupGYSF(lccCode);
			}
			bos = new BufferedOutputStream(resp.getOutputStream());
			SXSSFWorkbook workbook = new SXSSFWorkbook(ExcelExportBatchUtil.SXSSWORKBOOK_MEMORY);
			ExcelExportBatchUtil.createAndFillingExcel(list, ExcelDataConstant.HEAD_UQSANSWERQNBLOOD_2, ExcelDataConstant.DATA_UQSANSWERQNBLOOD_2, workbook);
			workbook.write(bos);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null)
				bos.close();
		}
		return null;
	}

	@RequestMapping("getBloodInfo6")
	public @ResponseBody JqgridResponse<Map<String,Object>>
	getBloodInfo6(ReportFilter filter){
        List l = reportDao.getBloodInfoFor6(filter);
		JqgridResponse<Map<String,Object>> resp = JqgridResponseContext.getJqgridResponse();
		resp.setRows(l);
		return resp ;
	}

	@RequestMapping(value = "/uqsAnswerqnBloodReportExport6")
	public ModelAndView uqsAnswerqnBloodReportExport6(HttpServletRequest req,
													  HttpServletResponse resp,ReportFilter filter)
			throws Exception {
		req.setCharacterEncoding("utf-8");
		 // get方式乱码处理
		filter.setPatientName(java.net.URLDecoder.decode(filter.getPatientName()));
		filter.setPatientId(java.net.URLDecoder.decode(filter.getPatientId()));
		filter.setAduType(java.net.URLDecoder.decode(filter.getAduType()));
		filter.setBloodType(java.net.URLDecoder.decode(filter.getBloodType()));
		java.io.BufferedOutputStream bos = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String datestr = sdf.format(new Date());
			String filename = "6月随访血检报告明细"+datestr + "_" + Securitys.getUser().getName()+".xls";
			resp.setContentType("application/x-msdownload;");
			resp.setHeader("Content-disposition", "attachment; filename="
					+ new String(filename.getBytes("utf-8"), "ISO8859-1"));
			filter.setRows(999999);
			List<Map<String,Object>> list = reportDao.getBloodInfoFor6(filter);
			bos = new BufferedOutputStream(resp.getOutputStream());
			SXSSFWorkbook workbook = new SXSSFWorkbook(ExcelExportBatchUtil.SXSSWORKBOOK_MEMORY);
			ExcelExportBatchUtil.createAndFillingExcel(list, ExcelDataConstant.HEAD_UQSANSWERQNBLOOD_6, ExcelDataConstant.DATA_UQSANSWERQNBLOOD_6, workbook);
			workbook.write(bos);

			PipSysExpexcelInfo po = new PipSysExpexcelInfo();
			po.setId(UUID.randomUUID().toString());
			po.setFileName(filename);
			po.setLccCode(Securitys.getUser().getLccCode());
			po.setOperateDate(new Date());
			po.setOperatorId(Securitys.getUser().getId());
			po.setOperatorName(Securitys.getUser().getName());
			po.setPageId("ME0000000000106");
			po.setPageName("血检报告明细");
			excelinfpDao.insert(po);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null)
				bos.close();
		}
		return null;
	}


	@RequestMapping("getBloodInfo12")
	public @ResponseBody JqgridResponse<Map<String,Object>>
	getBloodInfo12(ReportFilter filter){
		List l = reportDao.getBloodInfoFor12(filter);
		JqgridResponse<Map<String,Object>> resp = JqgridResponseContext.getJqgridResponse();
		resp.setRows(l);
		return resp ;
	}

	@RequestMapping(value = "/uqsAnswerqnBloodReportExport12")
	public ModelAndView uqsAnswerqnBloodReportExport12(HttpServletRequest req,
													  HttpServletResponse resp,ReportFilter filter)
			throws Exception {
		req.setCharacterEncoding("utf-8");
		// get方式乱码处理
		filter.setPatientName(java.net.URLDecoder.decode(filter.getPatientName()));
		filter.setPatientId(java.net.URLDecoder.decode(filter.getPatientId()));
		filter.setAduType(java.net.URLDecoder.decode(filter.getAduType()));
		filter.setBloodType(java.net.URLDecoder.decode(filter.getBloodType()));
		java.io.BufferedOutputStream bos = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String datestr = sdf.format(new Date());
			String filename = "末次随访血检报告明细"+datestr + "_" + Securitys.getUser().getName()+".xls";
			resp.setContentType("application/x-msdownload;");
			resp.setHeader("Content-disposition", "attachment; filename="
					+ new String(filename.getBytes("utf-8"), "ISO8859-1"));
			filter.setRows(999999);
			List<Map<String,Object>> list = reportDao.getBloodInfoFor12(filter);
			bos = new BufferedOutputStream(resp.getOutputStream());
			SXSSFWorkbook workbook = new SXSSFWorkbook(ExcelExportBatchUtil.SXSSWORKBOOK_MEMORY);
			ExcelExportBatchUtil.createAndFillingExcel(list, ExcelDataConstant.HEAD_UQSANSWERQNBLOOD_6, ExcelDataConstant.DATA_UQSANSWERQNBLOOD_6, workbook);
			workbook.write(bos);

			PipSysExpexcelInfo po = new PipSysExpexcelInfo();
			po.setId(UUID.randomUUID().toString());
			po.setFileName(filename);
			po.setLccCode(Securitys.getUser().getLccCode());
			po.setOperateDate(new Date());
			po.setOperatorId(Securitys.getUser().getId());
			po.setOperatorName(Securitys.getUser().getName());
			po.setPageId("ME0000000000106");
			po.setPageName("血检报告明细");
			excelinfpDao.insert(po);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null)
				bos.close();
		}
		return null;
	}
}
