package com.bdcor.pip.web.qn.controller;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.data.util.ExcelExportConfig;
import com.bdcor.pip.data.util.ExcelExportUtil;
import com.bdcor.pip.web.qn.filter.TimeStatisticFilter;
import com.bdcor.pip.web.qn.service.TimeStatisticService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.util.List;
import java.util.Map;

/**
 * 问卷录入时长统计
 * @author xu
 * @date 上午9:29:08
 */
@Controller
@RequestMapping("qn/timeStatistic") 
public class TimeStatisticController {
	
	@Autowired
	private TimeStatisticService timeStatisticService;
	
	@RequestMapping
	public String init(){
		return "qn/time/statistic";
	}
	
	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JqgridResponse<Map<String,Object>> list(TimeStatisticFilter filter) { 
		List<Map<String,Object>> data = timeStatisticService.list(filter);
		JqgridResponse<Map<String,Object>> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(data);
		return response;
	}
	
	@RequestMapping(value = "export")
	public ModelAndView export(TimeStatisticFilter filter,HttpServletRequest request,HttpServletResponse response)throws Exception {
		request.setCharacterEncoding("utf-8");
		java.io.BufferedOutputStream bos = null;
		try {
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("问卷录入时长统计.xls".getBytes("utf-8"), "ISO8859-1"));
			filter.setRows(20000);
			List<Map<String,Object>> list = timeStatisticService.list(filter);
			bos = new BufferedOutputStream(response.getOutputStream());
			HSSFWorkbook workbook = new HSSFWorkbook(); 
			String[] titleArr = {"LCCID","医院名称","录入时长平均值(单位:分钟)","中位数(单位:分钟)","上四分位数-下四分位数(单位:分钟)","最长时间(单位:分钟)","最短时间(单位:分钟)"};
			String[] keyArr = {"LCC_CODE","LCC_NAME","AVGTIME","MEDIANTIME","FTIME","MAXTIME","MINTIME"};
			ExcelExportConfig config = new ExcelExportConfig();
			config.setSheetName("问卷录入时长统计");
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
