package com.bdcor.pip.web.common.controller;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.ibatis.binding.MapperProxy;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.core.utils.DateUtil;
import com.bdcor.pip.core.utils.SpringContextHolder;
import com.bdcor.pip.core.utils.StringUtils;
import com.bdcor.pip.data.util.ReflectionUtils;


@Controller
@RequestMapping(value = "comm/datapage")
public class DataPageController {

	
	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JqgridResponse getDataPage(HttpServletRequest request, HttpServletResponse response) throws Throwable{
		Map<String,String[]> map = request.getParameterMap();
		if ( map.get("entityMapper") == null )
			return null;
		if ( map.get("className") == null )
			return null;
		if ( map.get("queryMethod") == null )
			return null;
		
		String[] ps = null;
		if (map.get("IntegerParams") != null)
			ps = map.get("IntegerParams")[0].split("\\|");
		
		String[] ds = null;
		if ( map.get("DateParams") != null)
			ds = map.get("DateParams")[0].split("\\|");
		
		Map obj = new HashMap();
		for ( String key : map.keySet() ){
			if ( key.equals("page") || key.equals("rows") || key.equals("records") || key.equals("total")){
				obj.put(key, Integer.parseInt(map.get(key)[0]));
			}else if( ps != null && ps.length > 0 ){
				for ( String s : ps){
					if ( key.equals(s)){
						obj.put(key, Integer.parseInt(map.get(key)[0]));
					}
				}
			}else if( ds != null && ds.length > 0 ){
				for ( String s : ps){
					if ( key.equals(s)){
						try{
							obj.put(key, DateUtil.parse(map.get(key)[0]));
						}catch(Exception ex){}
					}
				}
			}else {
				obj.put(key, map.get(key)[0]);
			}
		}
		Object mapper = SpringContextHolder.getBean(map.get("entityMapper")[0].toString());
		MapperProxy proxy = (MapperProxy)ReflectionUtils.getFieldValue(mapper, "h");
		SqlSessionTemplate temp = (SqlSessionTemplate) ReflectionUtils.getFieldValue(proxy, "sqlSession");
		List ret = temp.selectList(""+map.get("className")[0]+"."+map.get("queryMethod")[0], obj);
		JqgridResponse jqresponse = JqgridResponseContext.getJqgridResponse();
		jqresponse.setRows(ret);
		return jqresponse;
	}

}
