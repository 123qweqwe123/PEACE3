package com.bdcor.pip.web.msg.controller;

import java.io.BufferedOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.msg.dao.MsgNoReplyDao;
import com.bdcor.pip.web.msg.dao.PipMsgHandlerMapper;
import com.bdcor.pip.web.msg.domain.PipMsgHandler;
import com.bdcor.pip.web.msg.domain.PipMsgHandlerExample;
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
import com.bdcor.pip.web.msg.domain.MsgNoReplyVo;
import com.bdcor.pip.web.msg.filter.MsgNoReplyFilter;
import com.bdcor.pip.web.msg.service.MsgNoReplyService;

@Controller
@RequestMapping("msg/noReply")
public class MsgNoReplyController {
	
	@Autowired
	private MsgNoReplyService msgNoReplyService;

	@Autowired
	private PipMsgHandlerMapper handlerDao;

	@Autowired
	private MsgNoReplyDao noreplyDao;

	@RequestMapping
	public String init(){
		return "msg/noReply/list";
	}
	
	@RequestMapping(value = "list", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JqgridResponse<MsgNoReplyVo> list(MsgNoReplyFilter filter){
		List<MsgNoReplyVo> msgNoReplyList = msgNoReplyService.list(filter);
		if(msgNoReplyList != null && msgNoReplyList.size()>0){
			for(int i=0;i<msgNoReplyList.size();i++){
				msgNoReplyList.get(i).setId(i+"");
			}
		}
		JqgridResponse<MsgNoReplyVo> response = JqgridResponseContext.getJqgridResponse();
        response.setRows(msgNoReplyList);
        return response;
	}
	
	@RequestMapping(value = "/noReplyExport")
	public ModelAndView noReplyExport(MsgNoReplyFilter filter, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		java.io.BufferedOutputStream bos = null;
		try {
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("未回复短信.xls".getBytes("utf-8"), "ISO8859-1"));
			filter.setRows(999999);
			List<Map<String, Object>> list = msgNoReplyService.getMaplist(filter);
			bos = new BufferedOutputStream(response.getOutputStream());
			SXSSFWorkbook workbook = new SXSSFWorkbook(ExcelExportBatchUtil.SXSSWORKBOOK_MEMORY); 
			ExcelExportBatchUtil.createAndFillingExcel(list, ExcelDataConstant.HEAD_MSGNOREPLY, ExcelDataConstant.DATA_MSGNOREPLY, workbook);
			workbook.write(bos);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null)
				bos.close();
		}
		return null;
	}


	// 未回复短信监控
	@RequestMapping("watch")
	public String showWatch(){
		return "msg/noReply/watch";
	}

	@RequestMapping("watchlist")
	public @ResponseBody JqgridResponse<Map<String,Object>> getList(MsgNoReplyFilter filter)
	{
		List l = msgNoReplyService.getNoreplyList(filter);
		JqgridResponse<Map<String,Object>> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(l);
		return response;
	}

	@RequestMapping(value = "/watchExport")
	public ModelAndView watchExport(MsgNoReplyFilter filter, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		java.io.BufferedOutputStream bos = null;
		try {
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("未回复短信监控.xls".getBytes("utf-8"), "ISO8859-1"));
			filter.setRows(66537);
			List<Map<String, Object>> list = msgNoReplyService.getNoreplyList(filter);
			bos = new BufferedOutputStream(response.getOutputStream());
			SXSSFWorkbook workbook = new SXSSFWorkbook(ExcelExportBatchUtil.SXSSWORKBOOK_MEMORY);
			ExcelExportBatchUtil.createAndFillingExcel(list, ExcelDataConstant.HEAD_MSGNOREPLY_WATCH, ExcelDataConstant.DATA_UMSGNOREPLY_WATCH, workbook);
			workbook.write(bos);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null)
				bos.close();
		}
		return null;
	}

	@RequestMapping("openmodalShowInfo")
	public String openmodalNoreplyInfo(Model model,String lccCode ,
									   String patientId){

		model.addAttribute("lccCode",lccCode);
		model.addAttribute("patientId",patientId);

		return "msg/noReply/noreplyInfo";
	}

	// 查询待处理短信信息
	@RequestMapping("watiHandlerlist")
	public @ResponseBody JqgridResponse<Map<String,Object>>
	getWatiHandlerlist(String patientId){
		List l = noreplyDao.getWaitMsg(patientId);
		JqgridResponse<Map<String,Object>> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(l);
		return response;
	}

	// 查询已处理短信信息
	@RequestMapping("hasHandlerlist")
	public @ResponseBody JqgridResponse<PipMsgHandler> gethasHandlerlist(String patientId){
		PipMsgHandlerExample pe = new PipMsgHandlerExample();
			pe.createCriteria().andPatientIdEqualTo(patientId);
		List l = handlerDao.selectByExample(pe);
		JqgridResponse<PipMsgHandler> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(l);
		return response;
	}

	@RequestMapping("saveHandler")
	public  @ResponseBody
	ResponseEntity saveHandler(String MSG_NAME,String MOBILE,String handlerDate,String isHandler,String MSGSEND_ID,
							   String noreplyReason,String isPass ,String isGetmsg,String PATIENT_ID){
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			PipMsgHandler po = new PipMsgHandler();
			po.setId(UUID.randomUUID().toString());
			if(isHandler != null &&  !"".equals(isHandler)){
				po.setIsHandler(Integer.parseInt(isHandler));
			}
			if(isPass != null &&  !"".equals(isPass)){
				po.setIsPass(Integer.parseInt(isPass));
			}
			if(isGetmsg != null &&  !"".equals(isGetmsg)){
				po.setIsGetmsg(Integer.parseInt(isGetmsg));
			}
			po.setMsgName(MSG_NAME);
			po.setHandlerDate(sdf.parse(handlerDate));
			po.setNoreplyReason(noreplyReason);
			po.setMobile(MOBILE);
			po.setMsgsendId(MSGSEND_ID);
			po.setPatientId(PATIENT_ID);
			po.setCreateName(Securitys.getUserName());
			po.setCreateBy(Securitys.getUserId());
			po.setCreateDate(new Date());
			handlerDao.insert(po);
			res.put("success", true);
			return new ResponseEntity(res, HttpStatus.OK);
		} catch (Exception e) {
			res.put("success", false);
			return new ResponseEntity(res, HttpStatus.OK);
		}
	}
}
