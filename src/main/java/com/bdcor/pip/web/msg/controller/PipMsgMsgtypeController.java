package com.bdcor.pip.web.msg.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.core.utils.GenerateKey;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.core.utils.StringUtils;
import com.bdcor.pip.web.msg.domain.MsgMsgtype;
import com.bdcor.pip.web.msg.filter.PipMsgMsgtypeFilter;
import com.bdcor.pip.web.msg.service.MsgMsgtypeService;

/**
 * description: 短信分类字段
 * @author: huang cz  创建时间：2016年5月12日 
 *
 */
@Controller
@RequestMapping("msg/msgtype")
public class PipMsgMsgtypeController {
	
	@Autowired
	MsgMsgtypeService msgtypeService;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@RequestMapping
	public String init(){
		return "msg/msgtype/list";
	}
	
	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JqgridResponse<MsgMsgtype> getQuestionnaireList(PipMsgMsgtypeFilter filter) throws Exception{
		List<MsgMsgtype> list = msgtypeService.msgTypeList(filter);
		JqgridResponse<MsgMsgtype> response = JqgridResponseContext.getJqgridResponse();
        response.setRows(list); 
        return response;
	}
	
	@RequestMapping(value = "openmodaladdmsgtype", method = RequestMethod.GET)
    public ModelAndView openInput(@RequestParam(value = "msgId", required = false) String msgId) {
    	ModelAndView mav = new ModelAndView("msg/msgtype/form");
    	if(msgId!=null && !"".equals(msgId)){
    		MsgMsgtype msgType = msgtypeService.getMsgTypeById(msgId);
    		mav.addObject("msgType", msgType);
    	}
    	return mav;
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "addMsgtype", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> addMsgType(MsgMsgtype msgType) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (msgType == null)
			return null;
		try {
			String createDate = sdf.format(new Date());
			msgType.setCreateDate(createDate);
			String createBy = Securitys.getUserId();
			msgType.setCreateBy(createBy);
			if (StringUtils.isEmpty(msgType.getId())) {
				msgType.setId(GenerateKey.getKey(GenerateKey.PREFIX_COMMON));
				msgtypeService.addMsgType(msgType);
				result.put("isAdd", true);
			} else {
				msgtypeService.updateMsgType(msgType);
				result.put("isAdd", false);
			}
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "deleteMsgtype", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> deletePatient(String msgId) {
		Map<String, Object> result = new HashMap<String, Object>();
		boolean flag = false;
		String message = "删除失败！";
		try {
			int i = msgtypeService.deleteMsgType(msgId);
			if(i>0){
				flag = true;
				message = "删除成功！";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.put("message", message);
		result.put("flag", flag);
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "getMsgtypeByCode", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> getMsgtypeByCode(String typeCode) {
		Map<String, Object> result = new HashMap<String, Object>();
		boolean flag = false;
		try {
			MsgMsgtype msgType = null;
			msgType = msgtypeService.getMsgtypeByCode(typeCode);
			if(msgType!=null){
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.put("success", flag);
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
	
}
