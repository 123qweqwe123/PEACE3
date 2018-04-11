package com.bdcor.pip.web.msg.controller;

import java.io.BufferedOutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.data.constant.ExcelDataConstant;
import com.bdcor.pip.data.util.ExcelExportBatchUtil;
import com.bdcor.pip.web.msg.domain.MsgReplyVo;
import com.bdcor.pip.web.msg.filter.MsgNoReplyFilter;
import com.bdcor.pip.web.msg.filter.MsgReplyFilter;
import com.bdcor.pip.web.msg.service.MsgReplyService;

@Controller
@RequestMapping("msg/reply")
public class MsgReplyController {
	
	@Autowired
	private MsgReplyService msgReplyService;
	
	@RequestMapping
	public String init(){
		return "msg/reply/list";
	}
	
	@RequestMapping(value = "list", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JqgridResponse<MsgReplyVo> list(MsgReplyFilter filter){
		List<MsgReplyVo> msgReplyList = msgReplyService.list(filter);
		if(msgReplyList != null && msgReplyList.size()>0){
			for(int i=0;i<msgReplyList.size();i++){
				msgReplyList.get(i).setId(i+"");
			}
		}
		JqgridResponse<MsgReplyVo> response = JqgridResponseContext.getJqgridResponse();
        response.setRows(msgReplyList);
        return response;
	}
	
	@RequestMapping(value="openmodal/showAllMsg")
	public String showAllMsg(@RequestParam(required=true,value="lccCode")String lccCode,@RequestParam(required=true,value="patientId")String patientId,Model model){
		model.addAttribute("patientVo", msgReplyService.getPatient(lccCode,patientId));
		model.addAttribute("msgList", msgReplyService.showAllMsg(lccCode,patientId));
		return "msg/reply/showAllMsg";
	}
	
	@RequestMapping(value = "/replyExport")
	public ModelAndView replyExport(MsgReplyFilter filter, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		java.io.BufferedOutputStream bos = null;
		try {
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("接收短信查看.xls".getBytes("utf-8"), "ISO8859-1"));
			filter.setRows(999999);
			List<Map<String, Object>> list = msgReplyService.getMaplist(filter);
			bos = new BufferedOutputStream(response.getOutputStream());
			SXSSFWorkbook workbook = new SXSSFWorkbook(ExcelExportBatchUtil.SXSSWORKBOOK_MEMORY); 
			ExcelExportBatchUtil.createAndFillingExcel(list, ExcelDataConstant.HEAD_MSGREPLY, ExcelDataConstant.DATA_MSGREPLY, workbook);
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
