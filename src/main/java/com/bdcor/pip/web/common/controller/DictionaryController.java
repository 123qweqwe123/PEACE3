package com.bdcor.pip.web.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdcor.pip.core.mapper.JsonMapper;
import com.bdcor.pip.web.common.service.CommonJdbcService;

@Controller
@RequestMapping("dict") 
public class DictionaryController {

	@Autowired
	private CommonJdbcService commonJdbcService;

	private static List<Map<String,Object>> area;
	
	
	@RequestMapping(value="areaData",produces="application/json")
	@ResponseBody
	public  List<Map<String,Object>> areaData(HttpServletRequest request){
//		@RequestParam String type,@RequestParam String pid
		String type = request.getParameter("type");
		String pid = request.getParameter("pid");
		
		
		if(StringUtils.isNotBlank(type)&&StringUtils.isNotBlank(pid)){
			type = "DISTRICT.0"+type;

			Map<String,Object> params = new HashMap<String,Object>();
			params.put("table", "PIP_COMM_DICT_DISTRICT");
			params.put("columns", "VCODE|VNAME".split("\\|"));
			
			JsonMapper jm = JsonMapper.nonDefaultMapper();		
			params.put("where", jm.fromJson("[{\"column\":\"DICT_CODE\",\"operate\":\"=\",\"value\":\""+type+"\"}," +
					"{\"column\":\"VCODE\",\"operate\":\"like\",\"value\":\""+pid+"%\"}]", List.class));
			List<Map<String, String>> dbdataLines = this.commonJdbcService.selectJsonForAutocomplete(params);
			
			List<Map<String,Object>> rData = new ArrayList<Map<String,Object>>();
			for ( Map<String, String> line : dbdataLines ){
				Map<String,Object> oneDataNode = new HashMap<String,Object>();
				oneDataNode.put("VCODE", line.get("VCODE"));
				oneDataNode.put("VNAME", line.get("VNAME"));
				rData.add(oneDataNode);	
			}
			return rData;
		}
		
		
		
		if ( area != null && area.size() > 0 ){
			return area;
		}else{
			List<Map<String,Object>> ret = new ArrayList<Map<String,Object>>();
			JsonMapper jm = JsonMapper.nonDefaultMapper();		
			
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("table", "PIP_COMM_DICT_DISTRICT");
			params.put("columns", "VCODE|VNAME".split("\\|"));
			
			params.put("where", jm.fromJson("[{\"column\":\"DICT_CODE\",\"operate\":\"=\",\"value\":\"DISTRICT.01\"}]", List.class));
			List<Map<String, String>> province = this.commonJdbcService.selectJsonForAutocomplete(params);
			
			for ( Map<String, String> p : province ){
				
				Map<String,Object> jp = new HashMap<String,Object>();
				jp.put("VCODE", p.get("VCODE"));
				jp.put("VNAME", p.get("VNAME"));
				Map<String,Object> cparams = new HashMap<String,Object>();
				cparams.put("table", "PIP_COMM_DICT_DISTRICT");
				cparams.put("columns", "VCODE|VNAME".split("\\|"));
				cparams.put("where", jm.fromJson("[{\"column\":\"DICT_CODE\",\"operate\":\"=\",\"value\":\"DISTRICT.02\"},{\"column\":\"VCODE\",\"operate\":\"like\",\"value\":\""+p.get("VCODE")+"%\"}]", List.class));
				
				List<Map<String, String>> city = this.commonJdbcService.selectJsonForAutocomplete(cparams);
				List<Map<String,Object>> cp = new ArrayList<Map<String,Object>>();
				for ( Map<String, String> c : city ){
					
					Map<String,Object> jc = new HashMap<String,Object>();
					jc.put("VCODE", c.get("VCODE"));
					jc.put("VNAME", c.get("VNAME"));
					
					Map<String,Object> dparams = new HashMap<String,Object>();
					dparams.put("table", "PIP_COMM_DICT_DISTRICT");
					dparams.put("columns", "VCODE|VNAME".split("\\|"));
					dparams.put("where", jm.fromJson("[{\"column\":\"DICT_CODE\",\"operate\":\"=\",\"value\":\"DISTRICT.03\"},{\"column\":\"VCODE\",\"operate\":\"like\",\"value\":\""+c.get("VCODE")+"%\"}]", List.class));
					
					List<Map<String, String>> distinct = this.commonJdbcService.selectJsonForAutocomplete(dparams);
					
					jc.put("cc", distinct);
					cp.add(jc);
				}
				jp.put("cp", cp);
				ret.add(jp);
			}
			area = ret;
			return ret;
		}
	}
	

	
	
}
