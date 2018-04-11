package com.bdcor.pip.web.msg.controller;

import java.io.BufferedOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.bdcor.pip.web.msg.domain.MsgSendVo;
import com.bdcor.pip.web.msg.filter.MsgSendFilter;
import com.bdcor.pip.web.msg.filter.MsgSendNoFilter;
import com.bdcor.pip.web.msg.service.MsgSendService;

@Controller
@RequestMapping("msg/send")
public class MsgSendController {
	
	@Autowired
	private MsgSendService msgSendService;
	
	
	
	@RequestMapping(value = "export")
	public ModelAndView sendExport(HttpServletRequest request,
			HttpServletResponse response,MsgSendNoFilter filter)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		java.io.BufferedOutputStream bos = null;
		try {
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("短信发送.xls".getBytes("utf-8"), "ISO8859-1"));
			List<Map<String,Object>> list = msgSendService.sendReport(filter);
			bos = new BufferedOutputStream(response.getOutputStream());
			SXSSFWorkbook workbook = new SXSSFWorkbook(ExcelExportBatchUtil.SXSSWORKBOOK_MEMORY); 
			ExcelExportBatchUtil.createAndFillingExcel(list, ExcelDataConstant.HEAD_MSG, ExcelDataConstant.DATA_MSG, workbook);
			workbook.write(bos);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null)
				bos.close();
		}
		return null;
	}
	@RequestMapping
	public String init(){
		return "msg/send/list";
	}
	
	@RequestMapping(value = "list", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JqgridResponse<MsgSendVo> list(MsgSendFilter filter){
		List<MsgSendVo> msgSendList = msgSendService.list(filter);
		JqgridResponse<MsgSendVo> response = JqgridResponseContext.getJqgridResponse();
        response.setRows(msgSendList);
        return response;
	}
	
	@RequestMapping(value="openmodal/toEdit")
	public String toEdit(@RequestParam(required=false,value="id")String id,Model model){
		model.addAttribute("msgTypeList", msgSendService.getMsgTypeList());
		if(id!=null && id.trim().length()>0){
			MsgSendFilter filter = new MsgSendFilter();
			filter.setSendId(id);
			List<MsgSendVo> msgSendList = msgSendService.list(filter);
			if(msgSendList.size()>0){
				model.addAttribute("sendVo", msgSendList.get(0));
			}
		}
		return "msg/send/edit";
	}
	
	/**
	 * description:  根据LCC获取PATIENT(可收短信的)
	 */
	@RequestMapping(value = "getPatient", produces = "application/json")
	@ResponseBody
	public List<Map<String,String>> getPatient(@RequestParam(value="lccCode",required=false)String lccCode) {
		if(lccCode==null || lccCode.trim().length()==0)return null;
		return msgSendService.getPatient(lccCode);

	}
	
	/**
	 * 短信发送状态  状态码 及 状态码对应的说明
	 */
	@RequestMapping(value = "getReportCode", produces = "application/json")
	@ResponseBody
	public List<Map<String,String>> getReportCode() {
		List<Map<String,String>> r =  msgSendService.getReportCode();
		return r;
	}
	
	
	/**
	 * description:  根据typeCode获取短信列表
	 */
	@RequestMapping(value = "getMsgListByType", produces = "application/json")
	public @ResponseBody ResponseEntity getMsgListByType(@RequestParam(value="typeCode",required=true)String typeCode) {
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			List<Map<String,String>> list =  msgSendService.getMsgListByType(typeCode);
			res.put("data", list);
			res.put("success", true);
			return new ResponseEntity(res, HttpStatus.OK);
		} catch (Exception e) {
			res.put("success", false);
			return new ResponseEntity(res, HttpStatus.OK);
		}
	}
	
	
	/**
	 * description:  编辑页面保存
	 */
	@RequestMapping(value = "sendSave", produces = "application/json")
	public @ResponseBody ResponseEntity sendSave(HttpServletRequest request) {
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			Map<String,Object> sendMap = new HashMap<String, Object>();
			sendMap.put("ID", request.getParameter("ID"));
			sendMap.put("LCC_CODE", request.getParameter("LCC_CODE"));
			sendMap.put("PATIENT_ID", request.getParameter("PATIENT_ID"));
			sendMap.put("SENDTIME_PREINSTALL", request.getParameter("SENDTIME_PREINSTALL"));
			sendMap.put("MSG_ID", request.getParameter("MSG_ID"));
			sendMap.put("MSG_NAME", request.getParameter("MSG_NAME"));
			
			int i = 0;
			i =  msgSendService.sendSave(sendMap);
			if(i>0){
				res.put("success", true);
			}else{
				res.put("success", false);
			}
			return new ResponseEntity(res, HttpStatus.OK);
		} catch (Exception e) {
			res.put("success", false);
			return new ResponseEntity(res, HttpStatus.OK);
		}
	}
	
	/**
	 * description: 删除
	 */
	@RequestMapping(value = "sendDelete", produces = "application/json")
	public @ResponseBody ResponseEntity sendDelete(@RequestParam(value="ids",required=true)String ids) {
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			int i = 0;
			i =  msgSendService.sendDelete(ids.split(","));
			if(i>0){
				res.put("success", true);
			}else{
				res.put("success", false);
			}
			return new ResponseEntity(res, HttpStatus.OK);
		} catch (Exception e) {
			res.put("success", false);
			return new ResponseEntity(res, HttpStatus.OK);
		}
	}
	
	/**
	 * description:  根据typeCode获取短信列表
	 */
	@RequestMapping(value = "testSend", produces = "application/json")
	public @ResponseBody ResponseEntity testSend(@RequestParam(value="mobile",required=true)String mobile) {
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			msgSendService.testSend(mobile);
			return new ResponseEntity(res, HttpStatus.OK);
		} catch (Exception e) {
			res.put("success", false);
			return new ResponseEntity(res, HttpStatus.OK);
		}
	}
}
