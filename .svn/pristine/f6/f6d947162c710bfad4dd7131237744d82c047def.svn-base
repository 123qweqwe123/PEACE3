package com.bdcor.pip.web.qn.controller;

import java.io.BufferedOutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bdcor.pip.web.pro.promgt.domain.Lcc;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.data.util.ExcelExportBatchUtil;
import com.bdcor.pip.data.util.ExcelExportConfig;
import com.bdcor.pip.data.util.ExcelExportUtil;
import com.bdcor.pip.web.pro.promgt.service.LccService;
import com.bdcor.pip.web.qn.filter.PatientReportFilter;
import com.bdcor.pip.web.qn.filter.TimeListFilter;
import com.bdcor.pip.web.qn.filter.TimeStatisticFilter;
import com.bdcor.pip.web.qn.service.TimeListService;
import com.bdcor.pip.web.qn.service.TimeStatisticService;

/**
 * 问卷录入时长列表
 * @author xu
 * @date 上午10:29:08
 */
@Controller
@RequestMapping("qn/timeList") 
public class TimeListController {
	
	@Autowired
	private TimeListService timeListService;
	
	@Autowired
	private LccService lccService;
	
	@RequestMapping
	public String init(HttpServletRequest request){
		if(request.getParameter("lccCode")!=null && !"".equals(request.getParameter("lccCode").trim())){
			request.setAttribute("lcc", lccService.getLcc(request.getParameter("lccCode")));
		}else{
			Lcc lcc = new Lcc();
			lcc.setLccCode("");
			lcc.setLccName("");
			request.setAttribute("lcc", lcc);
		}
		return "qn/time/list";
	}
	
	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JqgridResponse<Map<String,Object>> list(TimeListFilter filter) { 
		List<Map<String,Object>> data = timeListService.list(filter);
		JqgridResponse<Map<String,Object>> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(data);
		return response;
	}
	
	@RequestMapping(value = "export")
	public ModelAndView export(TimeListFilter filter,HttpServletRequest request,HttpServletResponse response)throws Exception {
		request.setCharacterEncoding("utf-8");
		java.io.BufferedOutputStream bos = null;
		try {
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("问卷时长列表.xls".getBytes("utf-8"), "ISO8859-1"));
			filter.setRows(1000000);
			List<Map<String,Object>> list = timeListService.list(filter);
			bos = new BufferedOutputStream(response.getOutputStream());
			HSSFWorkbook workbook = new HSSFWorkbook(); 
			String[] titleArr = {"PID","录入时长","开始录入时间","结束录入时间","录入员"};
			String[] keyArr = {"PATIENT_ID","USE_TIME","START_TIME","END_TIME","OPERATOR"};
			ExcelExportConfig config = new ExcelExportConfig();
			config.setSheetName("问卷录入时长列表");
			ExcelExportUtil.fillExcel(list,titleArr,keyArr, workbook,config);
			
			workbook.write(bos);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null)
				bos.close();
		}
		return null;
	}

}
