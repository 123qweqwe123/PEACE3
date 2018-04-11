package com.bdcor.pip.web.data.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdcor.pip.core.mapper.JaxbMapper;
import com.bdcor.pip.data.util.CryptoUtil;
import com.bdcor.pip.data.util.FileUtil;
import com.bdcor.pip.data.xml.vo.Questionnaire;
import com.bdcor.pip.web.data.service.UqsQuestionnaireService;


@Controller
@RequestMapping("/interface")
public class UqsServiceContorller {

	//private demoService demoService;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	
	@Autowired
	private UqsQuestionnaireService uqsQuestionnaireService;
	
	@RequestMapping(value="templateInput",method = RequestMethod.GET)
	public  String   interactive(Model model){
		return "interface/templateInput";
	} 
	
	@RequestMapping(value="ftpinfo",method = RequestMethod.GET)
	@ResponseBody
	public Object ftpinfo(String projectId , String lccCode , Model model){
		//TODO: 通过 projectId  和 lccCode 获取LCC的相关配置信息
		
		StringBuffer path = new StringBuffer();
		path.append("/");
		path.append(projectId);
		path.append("/");
		
		path.append("12");
		path.append("/");
		path.append(lccCode);
		path.append("/");
		path.append(sdf.format(new Date()));
		path.append("/");
		
		Map<String,Object> ret = new HashMap<String,Object>();
		ret.put("ftpIp", "192.168.1.2");
		ret.put("ftpPort", "21");
		ret.put("ftpPath", path.toString());
		ret.put("ftpUser", "ftp");
		try {
			ret.put("ftpPassword", CryptoUtil.encrypt(lccCode, "ftp"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
	}
	
	
	@RequestMapping(value="resultNotification",method = RequestMethod.GET)
	@ResponseBody
	public Object resultNotification(String kName , String kValue , Model model) throws Exception{
		
		
		List<Object> ret = new ArrayList<Object>();
		try{
			String txt = CryptoUtil.decrypt(kName, kValue);
			
			/*JSONObject jo = new JSONObject(txt);
			JSONArray ary = jo.getJSONArray("fileList");
			
			for ( int i = 0 ; i < ary.length() ; i ++ ){
				Object o = ary.get(i);
				Map<String , Object> map = new HashMap<String, Object>();
				map.put( "filename", ReflectionUtils.getFieldValue(o, "filename"));
				
				//TODO:检查文件
				map.put( "result", "1");
				ret.add(map);
			}*/
			
			//TODO: 测有效的文件信息发送通知
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return ret;
	}
	
	@RequestMapping(value="uqsTemplate")
	@ResponseBody
	public Object uqsTemplate(@RequestParam("xmlfile")String xmlfile , Model model){
		
		Map<String , Object> map = new HashMap<String, Object>();
		
		String xml = "";
		if ( xmlfile != null && !xmlfile.equals("") )
			xml = FileUtil.readFileByLines(xmlfile);
		else{
			xml = FileUtil.readFileByLines("D:\\uqs\\UQS_001_006_001.xml");
			//xml = FileUtil.readFileByLines("C:\\Users\\dinglin\\Desktop\\UQS002\\UQS_002_001_002.xml");
		}
		if ( xml.indexOf("<") != 0 )
			xml = xml.substring(xml.indexOf("<"));
		try{
			Questionnaire qn = JaxbMapper.fromXml(xml, Questionnaire.class);
			uqsQuestionnaireService.save(qn);
		}catch(Exception ex){
			map.put("msg", ex.getMessage());
			ex.printStackTrace();
		}
		return map;
	}
	/**
	@RequestMapping(value = "showresult", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String showresult(HttpServletRequest request){
		
		String projectId = request.getParameter("projectId");
		String paperId = request.getParameter("paperId");
		String intervId = request.getParameter("patientId");
		String showbar = request.getParameter("showbar");
		String pathString = request.getSession().getServletContext().getRealPath("") + "/WEB-INF/views/uqs/";
		String filePath = pathString + "UQS_" + projectId + "_" + paperId.substring(3,6) + "_001.xml";
		File f = new File(filePath);
		Paper paper = this.uqsQuestionnaireService.getPaper(projectId, f);
		
		
		request.setAttribute("paper", paper);
		
		Map<String,String> map = new HashMap<String,String>();
		
		Result result = this.uqsQuestionnaireService.getResult(projectId, intervId, paperId);
		
		if(result != null && result.getOptions() != null){
			for(ResultOption ro:result.getOptions()){
				String dictName = null;//ResultService.getDictName(paper,ro.getQuestionSetId(),ro.getQuestionId(),ro.getResultId(),ro.getResutlStr());
				if(dictName == null){
					map.put(ro.getQuestionSetId()+"_"+ro.getQuestionId()+"_"+ro.getResultId(),ro.getResutlStr());
				}else{
					map.put(ro.getQuestionSetId()+"_"+ro.getQuestionId()+"_"+ro.getResultId(),dictName);
					map.put("dict"+ro.getQuestionSetId()+"_"+ro.getQuestionId()+"_"+ro.getResultId(),ro.getResutlStr());
				}
			}
		}
		request.setAttribute("resultOptionMap", map);
	
    	return "uqs/page";
    }
	
	**/

}
