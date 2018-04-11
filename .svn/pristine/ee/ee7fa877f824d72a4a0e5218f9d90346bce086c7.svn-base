package com.bdcor.pip.web.scm.controller;

import java.io.BufferedOutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.data.constant.ExcelDataConstant;
import com.bdcor.pip.data.util.ExcelExportBatchUtil;
import com.bdcor.pip.web.qn.filter.EventExportFilter;
import com.bdcor.pip.web.qn.filter.ScmSearchFilter;
import com.bdcor.pip.web.qn.filter.ScmSearchFilterNoBase;
import com.bdcor.pip.web.qn.service.AnswerQnService;
import com.bdcor.pip.web.scm.filter.ScmFilter;

/**
 * 做样本查询
 */
@Controller
@RequestMapping("scm/search")
public class ScmSearchController {
	@Autowired
	private AnswerQnService answerQnService;
	@RequestMapping()
	public String init(){
		return "scm/search/list";
	}
	
	@RequestMapping(value="list")
	@ResponseBody
	public JqgridResponse<Map<String, Object>> listBlood(ScmSearchFilter filter){
		List<Map<String, Object>> list = answerQnService.listBlood(filter);
		JqgridResponse<Map<String,Object>> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(list);
		return response;
	}
	@RequestMapping(value = "/export")
	public ModelAndView exportexcel(HttpServletRequest request,
			HttpServletResponse response, ScmSearchFilterNoBase filter)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		try {
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("血样列表.xls".getBytes("utf-8"), "ISO8859-1"));
			List<Map<String,Object>> list = answerQnService.listBlood(filter);
			SXSSFWorkbook workbook = new SXSSFWorkbook(ExcelExportBatchUtil.SXSSWORKBOOK_MEMORY); 
			ExcelExportBatchUtil.createAndFillingExcel(list, ExcelDataConstant.HEAD_BLOODLIST, ExcelDataConstant.DATA_BLOODLIST, workbook);
			workbook.write(response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response.getOutputStream() != null)
				response.getOutputStream().close();
		}
		return null;
	}
}
