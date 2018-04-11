package com.bdcor.pip.web.quality.controller;

import java.io.BufferedOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.data.constant.ExcelDataConstant;
import com.bdcor.pip.data.util.ExcelExportBatchUtil;
import com.bdcor.pip.web.data.dao.UqsAnswerMapper;

@Controller
@RequestMapping("quality/uqscompletetab")
public class UqsCompleteTabController {
	
	@Autowired
	UqsAnswerMapper uqsAnswerMapper;
	
	@RequestMapping
	public String init(){
		return "quality/uqsCompleteTab/list";
	}
	
	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JqgridResponse<Map<String,Object>> getQuestionnaireList(
			String lccCode, String sidx, String sord) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("lccCode", lccCode);
		paramMap.put("sidx", sidx);
		paramMap.put("sord", sord);
//		if(Securitys.getSubject().isAuthenticated()){
//			Securitys.getUser().getLccCode();
//		}
		List list = uqsAnswerMapper.getUqsCompleteTab(paramMap);
		JqgridResponse<Map<String,Object>> response = JqgridResponseContext.getJqgridResponse();
        response.setRows(list); 
        return response;
	}
	
	@RequestMapping(value = "export", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView uqsAnswerqnLogReportExport(String lccCode, String sidx, String sord,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("lccCode", lccCode);
		paramMap.put("sidx", sidx);
		paramMap.put("sord", sord);
		request.setCharacterEncoding("utf-8");
		java.io.BufferedOutputStream bos = null;
		try {
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("随机问卷完成统计表.xls".getBytes("utf-8"), "ISO8859-1"));
			List list = uqsAnswerMapper.getUqsCompleteTab(paramMap);
			bos = new BufferedOutputStream(response.getOutputStream());
			SXSSFWorkbook workbook = new SXSSFWorkbook(ExcelExportBatchUtil.SXSSWORKBOOK_MEMORY); 
			ExcelExportBatchUtil.createAndFillingExcel(list, ExcelDataConstant.HEAD_UQSCOMPLETETAB, ExcelDataConstant.DATA_UQSCOMPLETETAB, workbook);
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
