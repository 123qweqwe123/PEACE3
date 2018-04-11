package com.bdcor.pip.web.qn.controller;

import com.bdcor.pip.client.tools.JsonMapper;
import com.bdcor.pip.client.vo.paper.*;
import com.bdcor.pip.client.xml.model.result.ResultQuestionnaireDocumentBean;
import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.core.security.util.PassWordUtil;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.data.constant.ExcelDataConstant;
import com.bdcor.pip.data.util.ExcelExportBatchUtil;
import com.bdcor.pip.web.common.service.ComboxService;
import com.bdcor.pip.web.data.filter.PatientFilter;
import com.bdcor.pip.web.msg.service.MsgJoinService;
import com.bdcor.pip.web.pro.promgt.service.LccService;
import com.bdcor.pip.web.qn.service.AnswerQnService;
import com.bdcor.pip.web.qn.service.impl.AnswerQnlogServiceImpl;
import com.bdcor.pip.web.reserve.filter.PipCommReserveFilter;
import com.bdcor.pip.web.sys.rbac.domain.User;
import com.bdcor.pip.web.sys.rbac.service.UserService;
import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("qn") 
public class AnswerQnController {

	@Autowired
	private AnswerQnService answerQnService;
	@Autowired
	private ComboxService comboxService;
	@Autowired
	private LccService lccService;
	@Autowired
	private UserService userService;

	@Autowired
	private MsgJoinService msgJoinService;
    @Autowired
	private AnswerQnlogServiceImpl ansqnService;

	/**
	 * 
	 * description: 空方法 防止做问卷的时候session失效
	 * @author yangfeng
	 * @return   
	 * @update 2016年1月22日
	 */
	@RequestMapping(value = "blankUrl", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String blankUrl(){
		System.out.println("keep session....");
		return null;
	}
	/**
	 * 答卷
	 * @param request
	 * @param model
	 * @param uqsCode
	 * @param patientId
	 * @return
	 */
	@RequestMapping(value="answerQn")
	public String answerQn(HttpServletRequest request,Model model,@RequestParam("uqsCode")String uqsCode,@RequestParam("patientId")String patientId,@RequestParam(value = "searchparam",defaultValue = "") String searchparam){
		Map<String,String> answerLogMap = answerQnService.getAnswerLogMap(uqsCode,patientId);
		if(answerLogMap == null){
			model.addAttribute("errorMsg","用户ID不存在");
			return "qn/patientList";
		}
		
		String uqsVersion = null;
		if(answerLogMap.get("UQSVERSION")==null || answerLogMap.get("UQSVERSION").trim().length()==0){
			uqsVersion = Securitys.getCurrentProject()+"."+uqsCode+".001";
		}else{
			uqsVersion = answerLogMap.get("UQSVERSION");
		}
		
		String basePath = this.getClass().getResource("/").getPath();
		if(basePath.indexOf(":")>-1){//windows
			basePath = basePath.substring(1);
		}

        String resultPath = answerLogMap.get("UQSFILE");

        String path = basePath+"paper/UQS_"+uqsVersion.replaceAll("\\.","_")+".xml";
        if( StringUtils.isNotBlank(resultPath) ){
            if( "013".equals(uqsCode) && !resultPath.contains(uqsVersion) ){ // 判断是之前的旧版004.002.001问卷
                path = basePath+"paper/UQS_004_002_001.xml";
            }
        }

		Paper qn = answerQnService.getPaperByPath(path);
		  if(qn == null){
			  model.addAttribute("errorMsg","问卷模版不存在");
			  return "qn/patientList";
		  }
		  
		  SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String UQSBeginTime = sf.format(new Date());
		  
		  Result r = null;
		  Map<String,String> map = new HashMap<String,String>();
		  if(resultPath != null && resultPath.trim().length()>0){
			  try{
					r = new Result(ResultQuestionnaireDocumentBean.Factory.parse(new FileInputStream(resultPath)).getQuestionnaire());
			  }catch (Exception e) {
			  }
		  }else{
			  //各问卷默认值
			  if(uqsCode.equals("001")){
				  map.put("1_1_1", answerLogMap.get("PATIENT_NAME"));
				  if("1".equals(answerLogMap.get("SEX"))){
					  map.put("1_2_1","1_2_1");
				  }
				  if("2".equals(answerLogMap.get("SEX"))){
					  map.put("1_2_2","1_2_2");
				  }
				  map.put("1_3_1", answerLogMap.get("BIRTHDAY"));
				  map.put("1_4_1", answerLogMap.get("ID_NUMBER"));
				  map.put("2_2_1", answerLogMap.get("PHONE"));
				  map.put("2_3_1", answerLogMap.get("MOBILE"));
				  map.put("2_4_1", answerLogMap.get("LINK_MAN1"));
				  map.put("2_6_1", answerLogMap.get("LINK_MAN1_MOBILE"));
				  
				  if(!StringUtils.isEmpty(answerLogMap.get("LINK_MAN1_RELATION"))){
					  if(answerLogMap.get("LINK_MAN1_RELATION").equals("父母")){
						  map.put("2_5_1", "2_5_1"); 
					  }
					  else if(answerLogMap.get("LINK_MAN1_RELATION").equals("配偶")){
						  map.put("2_5_2", "2_5_2"); 
					  }
					  else if(answerLogMap.get("LINK_MAN1_RELATION").equals("子女")){
						  map.put("2_5_3", "2_5_3"); 
					  }
					  else if(answerLogMap.get("LINK_MAN1_RELATION").equals("兄弟姐妹")){
						  map.put("2_5_4", "2_5_4"); 
					  }else{
						  map.put("2_5_5", answerLogMap.get("LINK_MAN1_RELATION"));
					  }
				  }
			  }
			  else if(uqsCode.equals("013")){
					List<Map<String, String>> answer001 = msgJoinService.getUqsAnswer(patientId,"001");
					if (answer001 != null && answer001.size() > 0) {
						for (Map<String, String> m : answer001) {
							if(m.get("QUESTIONSET_ID").equals("1")||m.get("QUESTIONSET_ID").equals("2")){
								map.put(m.get("QUESTIONSET_ID") + "_"+ m.get("QUESTION_ID") + "_"+ m.get("OPTION_ID"),
									m.get("ANSWER") == null ? "" : m.get("ANSWER"));

							  if(m.get("QUESTIONSET_ID").equals("2") && m.get("QUESTION_ID").equals("1") && Integer.parseInt(m.get("OPTION_ID").trim())<6){
								  map.put(m.get("QUESTIONSET_ID") + "_"+ m.get("QUESTION_ID") + "_"+ m.get("OPTION_ID"),StringUtils.isEmpty(comboxService.getAreaName(m.get("ANSWER"))) ? m.get("ANSWER") : comboxService.getAreaName(m.get("ANSWER")));
							  }
							}
						}
					}else{
						map.put("1_1_1", answerLogMap.get("PATIENT_NAME"));
						if("1".equals(answerLogMap.get("SEX"))){
							map.put("1_2_1","1_2_1");
						}
						if("2".equals(answerLogMap.get("SEX"))){
							map.put("1_2_2","1_2_2");
						}
						map.put("1_3_1", answerLogMap.get("BIRTHDAY"));
						map.put("1_4_1", answerLogMap.get("ID_NUMBER"));
						map.put("2_2_1", answerLogMap.get("PHONE"));
						map.put("2_3_1", answerLogMap.get("MOBILE"));
						map.put("2_1_7", answerLogMap.get("ADDRESS"));
						map.put("2_4_1", answerLogMap.get("LINK_MAN1"));
						map.put("2_6_1", answerLogMap.get("LINK_MAN1_MOBILE"));
						if(!StringUtils.isEmpty(answerLogMap.get("LINK_MAN1_RELATION"))){
							if(answerLogMap.get("LINK_MAN1_RELATION").equals("父母")){
								map.put("2_5_1", "2_5_1");
							}
							else if(answerLogMap.get("LINK_MAN1_RELATION").equals("配偶")){
								map.put("2_5_2", "2_5_2");
							}
							else if(answerLogMap.get("LINK_MAN1_RELATION").equals("子女")){
								map.put("2_5_3", "2_5_3");
							}
							else if(answerLogMap.get("LINK_MAN1_RELATION").equals("兄弟姐妹")){
								map.put("2_5_4", "2_5_4");
							}else{
								map.put("2_5_5", answerLogMap.get("LINK_MAN1_RELATION"));
							}
						}

						map.put("2_7_1", answerLogMap.get("LINK_MAN2"));
						map.put("2_9_1", answerLogMap.get("LINK_MAN2_MOBILE"));
						if(!StringUtils.isEmpty(answerLogMap.get("LINK_MAN2_RELATION"))){
							if(answerLogMap.get("LINK_MAN2_RELATION").equals("父母")){
								map.put("2_8_1", "2_8_1");
							}
							else if(answerLogMap.get("LINK_MAN2_RELATION").equals("配偶")){
								map.put("2_8_2", "2_8_2");
							}
							else if(answerLogMap.get("LINK_MAN2_RELATION").equals("子女")){
								map.put("2_8_3", "2_8_3");
							}
							else if(answerLogMap.get("LINK_MAN2_RELATION").equals("兄弟姐妹")){
								map.put("2_8_4", "2_8_4");
							}else{
								map.put("2_8_5", answerLogMap.get("LINK_MAN2_RELATION"));
							}
						}

					}
			  }
		  }
		  
		  if(uqsCode.equals("001")){
			  String hosDate=ansqnService.getStartHosDate(patientId,"001",answerLogMap.get("STATE"));
			  for(QuestionGroup qg : qn.getQGroup()){
				if(qg.getQs()!=null && qg.getId().equals("6")){
					for(QuestionC q:qg.getQs()){
						if(q.getOptions()!=null && q.getId().equals("1")){
							q.setDisplayname("自"+hosDate.substring(0,4)+"年"+hosDate.substring(4,6)+"月"+hosDate.substring(6)+"日  ，您是否住过院(至少在医院住过一晚)？");
						}
					}
				}
			  }
		  }
		  
		  
		  if(r != null && r.getOptions() != null){
				if(r.getOptions() != null){
					for(ResultOption ro:r.getOptions()){
						String dictName = comboxService.getDictName(qn,ro.getQuestionSetId(),ro.getQuestionId(),ro.getResultId(),ro.getResutlStr());
						if(dictName == null){
							map.put(ro.getQuestionSetId()+"_"+ro.getQuestionId()+"_"+ro.getResultId(),ro.getResutlStr());
						}else{
							map.put(ro.getQuestionSetId()+"_"+ro.getQuestionId()+"_"+ro.getResultId(),dictName);
							map.put("dict"+ro.getQuestionSetId()+"_"+ro.getQuestionId()+"_"+ro.getResultId(),ro.getResutlStr());
						}
					}
				}
				
				 if(uqsCode.equals("001")){
					  //对查询到的结果数据进行一次地区的过滤  只有是问卷完成查看时才进行地区代码的替换  	edit  by yangfeng 
					  if("1".equals(answerLogMap.get("STATE"))){
						  if(!StringUtils.isEmpty(map.get("2_1_1"))){
							  map.put("2_1_1",StringUtils.isEmpty(comboxService.getAreaName(map.get("2_1_1"))) ? map.get("2_1_1") : comboxService.getAreaName(map.get("2_1_1")));
						  }
						  if(!StringUtils.isEmpty(map.get("2_1_2"))){
							  map.put("2_1_2",StringUtils.isEmpty(comboxService.getAreaName(map.get("2_1_2"))) ? map.get("2_1_2") : comboxService.getAreaName(map.get("2_1_2")));
						  }
						  if(!StringUtils.isEmpty(map.get("2_1_3"))){
							  map.put("2_1_3",StringUtils.isEmpty(comboxService.getAreaName(map.get("2_1_3"))) ? map.get("2_1_3") : comboxService.getAreaName(map.get("2_1_3")));
						  }
						  if(!StringUtils.isEmpty(map.get("2_1_4"))){
							  map.put("2_1_4",StringUtils.isEmpty(comboxService.getAreaName(map.get("2_1_4"))) ? map.get("2_1_4") : comboxService.getAreaName(map.get("2_1_4")));
						  }
						  if(!StringUtils.isEmpty(map.get("2_1_5"))){
							  map.put("2_1_5",StringUtils.isEmpty(comboxService.getAreaName(map.get("2_1_5"))) ? map.get("2_1_5") : comboxService.getAreaName(map.get("2_1_5")));
						  }
					  }
				 }
		  }

		  
		  model.addAttribute("paper",qn);
		  model.addAttribute("resultOptionMap",map);
		  model.addAttribute("resultOptionMapJson",JsonMapper.objectToJson(map));
		  
		  model.addAttribute("UQSBeginTime",UQSBeginTime);
		  model.addAttribute("uqsVersion",uqsVersion);
		  model.addAttribute("patientName", answerLogMap.get("PATIENT_NAME"));
		  model.addAttribute("patientId",patientId);
		  model.addAttribute("uqsCode",uqsCode);

        model.addAttribute("searchparam",searchparam); // 带着查询条件四处跑 最后还得带回去

		  if("1".equals(answerLogMap.get("STATE"))){
			  return "qn/showQn";
		  }
		  return "qn/answerQn";
	}
	/**
	 * 暂存问卷
	 * @param request
	 * @return
	 */
	@RequestMapping(value="saveTempQn")
	@ResponseBody
	public Map<String, Object> saveTempQn(HttpServletRequest request){
		String uqsVersion = request.getParameter("uqsVersion");
		String patientId = request.getParameter("patientId");
		Map<String, Object> result = new HashMap<String, Object>();
		if(uqsVersion==null || uqsVersion.length()<10 || patientId==null){
			result.put("result",2);
			return result;
		}
		
		uqsVersion = uqsVersion.replaceAll("_",".");
		//添加验证逻辑，已经提交过问卷的患者不允许暂存 edit by yangfeng 2016-05-13
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("patientId", patientId);
		param.put("uqsVersion", uqsVersion);
		param.put("state", "1");
		boolean flag =answerQnService.isExistUqs(param);
		if(flag){
			result.put("result",7);
			return result;
		}
		
		String basePath = this.getClass().getResource("/").getPath();
		if(basePath.indexOf(":")>-1){//windows
			basePath = basePath.substring(1);
		}
		String path = basePath+"paper/UQS_"+uqsVersion.replaceAll("\\.","_")+".xml";

		Paper qn = answerQnService.getPaperByPath(path);
		if(qn == null){
			result.put("result",2);
			return result;
		}
  
		Map<String,String> answerLogMap = answerQnService.getAnswerLogMap(uqsVersion.substring(4, 7),patientId);
		if(answerLogMap == null){
			result.put("result",2);
			return result;
		}
		
		try{
			answerQnService.saveQn(request,qn);
			result.put("result",1);
		}catch (Exception e) {
			e.printStackTrace();
			result.put("result",2);
		}
		return result;
	}
	/**
	 * 提交问卷
	 * @param request
	 * @return
	 */
	@RequestMapping(value="saveQn")
	public String saveQn(HttpServletRequest request,RedirectAttributes attr ,@RequestParam(value="searchparam",defaultValue = "") String searchparam){
		String uqsVersion = request.getParameter("uqsVersion");
		String patientId = request.getParameter("patientId");
		if(uqsVersion==null || uqsVersion.length()<10 || patientId==null){
			attr.addFlashAttribute("result",2);
			return "redirect:patientList";
		}
		
		uqsVersion = uqsVersion.replaceAll("_",".");
		//添加验证逻辑，已经提交过问卷的患者不允许暂存 edit by yangfeng 2016-05-13
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("patientId", patientId);
		param.put("uqsVersion", uqsVersion);
		param.put("state", "1");
		boolean flag =answerQnService.isExistUqs(param);
		if(flag){
			attr.addFlashAttribute("result",7);
			return "redirect:patientList"; 
		}
		
		String basePath = this.getClass().getResource("/").getPath();
		if(basePath.indexOf(":")>-1){//windows
			basePath = basePath.substring(1);
		}
		String path = basePath+"paper/UQS_"+uqsVersion.replaceAll("\\.","_")+".xml";

		
		Paper qn = answerQnService.getPaperByPath(path);
		if(qn == null){
			attr.addFlashAttribute("result",2);
			return "redirect:patientList";
		}
  
		Map<String,String> answerLogMap = answerQnService.getAnswerLogMap(uqsVersion.substring(4, 7),patientId);
		if(answerLogMap == null){
			attr.addFlashAttribute("result",2);
			return "redirect:patientList";
		}
		
		try{
			answerQnService.saveQn(request,qn);
			attr.addFlashAttribute("result",1);
		}catch (Exception e) {
			e.printStackTrace();
			attr.addFlashAttribute("result",2);
		}
        attr.addFlashAttribute("searchparam",searchparam);
        return "redirect:patientList";
	}
	
	/**
	 * 患者随访人员列表首页
	 * @return
	 */
	@RequestMapping(value="patientList")
	public String patientList(Model model,HttpServletRequest request){
		if (Securitys.isAdmin() || Securitys.isOrganAdmin()) {
			model.addAttribute("lccDictList", lccService.getDataLimitLcc());
		}
		else{
			model.addAttribute("lccDictList", lccService.getDataLimitLccForLccCode());

		}
		String result = request.getParameter("result");
		request.setAttribute("result",result);
        model.addAttribute("searchparam","");
		Map<String, ?> m = RequestContextUtils.getInputFlashMap(request);
        if( m != null ){
            if( m.get("searchparam") != null ){
                model.addAttribute("searchparam",m.get("searchparam"));
            }
        }
		return "qn/patientList";
	}
	
	/**
	 * method = RequestMethod.POST,
	 */
	@RequestMapping(value = "getPatientList",  produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JqgridResponse<Map<String,Object>> getPatientList(PatientFilter filter) {
        List<Map<String,Object>> data = answerQnService.getPatientList(filter);
        JqgridResponse<Map<String,Object>> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(data);
		return response;
	}

	@RequestMapping(value = "/expoertExcel")
	public ModelAndView watchExport(PatientFilter filter, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		java.io.BufferedOutputStream bos = null;
		try {
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("患者随访管理.xls".getBytes("utf-8"), "ISO8859-1"));
			filter.setRows(66537);
			List<Map<String, Object>> list = answerQnService.exportPatientList(filter);
			bos = new BufferedOutputStream(response.getOutputStream());
			SXSSFWorkbook workbook = new SXSSFWorkbook(ExcelExportBatchUtil.SXSSWORKBOOK_MEMORY);
			ExcelExportBatchUtil.createAndFillingExcel(list, ExcelDataConstant.HEAD_PATIENT_LIST, ExcelDataConstant.DATA_PATIENT_LIST, workbook);
			workbook.write(bos);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				bos.close();
			}
		}
		return null;
	}
	
	/**
	 * 校验采血号
	 * @param bloodNo
	 * @return
	 */
	@RequestMapping(value = "checkBloodNo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity checkBloodNo(@RequestParam("bloodNo")String bloodNo,@RequestParam(required=false,value="lccCode")String lccCode){
		Map<String,Object> res = new HashMap<String, Object>();
		res.put("success", true);
		Map<String, Object> map  = answerQnService.checkBloodNo(bloodNo,lccCode);
		if(null ==map){
			res.put("success", false);
			res.put("msg", "采血包无效！");
		}
		else if("1".equals(map.get("PACKAGE_STATE").toString())){
			res.put("success", false);
			res.put("msg", "采血包已经使用！");
		}else if("3".equals(map.get("PACKAGE_STATE").toString())){
			res.put("success", false);
			res.put("msg", "采血包已经报损！");
		}else if("4".equals(map.get("PACKAGE_STATE").toString())){
			res.put("success", false);
			res.put("msg", "采血包已经过期！");
		}
		return new ResponseEntity(res, HttpStatus.OK);
	}
	
	/**
	 * 校验帐号密码
	 * @param loginName
	 * @param loginPwd
	 * @return
	 */
	@RequestMapping(value = "checkPwd", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity checkPwd(@RequestParam("loginName")String loginName,@RequestParam("loginPwd")String loginPwd){
		Map<String,Object> res = new HashMap<String, Object>();
		res.put("success", false);
		
		if(loginName.equals(Securitys.getUser().getLoginName())){
			User user = null;
			try{
				user = userService.findUserByLoginName(loginName);
			}catch (Exception e) {
				
			}
			if(user !=null){
				byte[] salt = PassWordUtil.decodeHex(user.getSalt());
				String pwd = PassWordUtil.getEncodePassWord(loginPwd, salt);
				if(pwd.equals(user.getPassword())){
					res.put("success", true);
				}
			}
		}
		return new ResponseEntity(res, HttpStatus.OK);
	}


	@RequestMapping("jumpto006")
	public String jumpToUqs006(HttpServletRequest request,Model model,@RequestParam("patientId")String patientId,
                               @RequestParam(value="uqsCode",defaultValue = "006",required = false)String uqsCode,
                               @RequestParam(value="logId",defaultValue = "",required = false)String logId){
//        return "forward:/qn/eventMgt/openmodalReportAfter?patientId="+patientId+"&uqsCode=006&logId=&time="+new Date().getTime();

        Map<String,String> answerLogMap = answerQnService.getAnswerLogMap_MIN(uqsCode,patientId,logId);
        if(answerLogMap == null){
            model.addAttribute("errorMsg","用户ID不存在");
            return "qn/eventMgt/reportAfter";
        }

        String uqsVersion = null;
        if(answerLogMap.get("UQSVERSION")==null || answerLogMap.get("UQSVERSION").trim().length()==0){
            uqsVersion = Securitys.getCurrentProject()+"."+uqsCode+".001";
        }else{
            uqsVersion = answerLogMap.get("UQSVERSION");
        }


        String path = request.getSession().getServletContext().getRealPath("/")+"WEB-INF/classes/paper/UQS_"+uqsVersion.replaceAll("\\.","_")+".xml";
        //String path = "D:\\gener-dev\\workspace\\CHINAPEACE3-web\\src\\main\\resources"+"/paper/UQS_"+uqsVersion.replaceAll("\\.","_")+".xml";
        Paper qn = answerQnService.getPaperByPath(path);
        if(qn == null){
            model.addAttribute("errorMsg","问卷模版不存在");
            return "qn/eventMgt/reportAfter";
        }

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String UQSBeginTime = sf.format(new Date());

        String resultPath = answerLogMap.get("UQSFILE");
        Result r = null;
        if(resultPath != null && resultPath.trim().length()>0){
            try{
                r = new Result(ResultQuestionnaireDocumentBean.Factory.parse(new FileInputStream(resultPath)).getQuestionnaire());
            }catch (Exception e) {
            }
        }

        Map<String,String> map = new HashMap<String,String>();
        if(r != null && r.getOptions() != null){
            if(r.getOptions() != null){
                for(ResultOption ro:r.getOptions()){
                    String dictName = comboxService.getDictName(qn,ro.getQuestionSetId(),ro.getQuestionId(),ro.getResultId(),ro.getResutlStr());
                    if(dictName == null){
                        map.put(ro.getQuestionSetId()+"_"+ro.getQuestionId()+"_"+ro.getResultId(),ro.getResutlStr());
                    }else{
                        map.put(ro.getQuestionSetId()+"_"+ro.getQuestionId()+"_"+ro.getResultId(),dictName);
                        map.put("dict"+ro.getQuestionSetId()+"_"+ro.getQuestionId()+"_"+ro.getResultId(),ro.getResutlStr());
                    }
                }
            }
        }

        if(uqsCode.equals("006")){
            //自      年   月    日  ，您是否住过院(至少在医院住过一晚)？
            String date2_1=new SimpleDateFormat("yyyyMMdd").format(new Date());
            if(answerLogMap.get("LAST_FOLLOWVIEW_DATE")!=null && answerLogMap.get("LAST_FOLLOWVIEW_DATE").length()==8){
                date2_1=answerLogMap.get("LAST_FOLLOWVIEW_DATE");
            }

            for(QuestionGroup qg : qn.getQGroup()){
                if(qg.getQs()!=null && qg.getId().equals("2")){
                    for(QuestionC q:qg.getQs()){
                        if(q.getOptions()!=null && q.getId().equals("1")){
                            q.setDisplayname("自"+date2_1.substring(0,4)+"年"+date2_1.substring(4,6)+"月"+date2_1.substring(6)+"日  ，您是否住过院(至少在医院住过一晚)？");
                        }
                    }
                }
            }
        }

        model.addAttribute("paper",qn);
        model.addAttribute("resultOptionMap",map);
        model.addAttribute("resultOptionMapJson",JsonMapper.objectToJson(map));

        model.addAttribute("UQSBeginTime",UQSBeginTime);
        model.addAttribute("uqsVersion",uqsVersion);
        model.addAttribute("patientId",patientId);
        model.addAttribute("logId",logId);
        model.addAttribute("uqsTypeName","事件");
        if(logId.trim().length()>0){
            model.addAttribute("isModify",true);
        }



        if(request.getParameter("onlyShow")!=null){
            return "qn/eventMgt/showReport";
        }

        return "qn/eventMgt/reportAfter";

    }

}
