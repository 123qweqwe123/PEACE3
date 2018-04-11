/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.msg.controller 
 */

package com.bdcor.pip.web.msg.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.web.msg.service.RandomGroupService;

@Controller()
@RequestMapping("random/group")
public class RandomGroupController{	
	
	@Autowired
	private RandomGroupService randomGroupService;
	
	@RequestMapping
	public String init(HttpServletRequest request){
		return "random/group/list";
	}
	
	@RequestMapping(value = "getRanomGroupList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JqgridResponse<Map<String, Object>> getRanomGroupList() { 
		List<Map<String,Object>> data = randomGroupService.getRandomGroup();
		JqgridResponse<Map<String,Object>> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(data);
		return response;
	}
	
	@RequestMapping(value = "getGroupSum", method = RequestMethod.GET)
	public @ResponseBody Map<String, List<?>> getGroupSum() {
		List<String> dayList = new ArrayList<String>();
		List<Integer> nonControlGroupList = new ArrayList<Integer>();
		List<Integer> nonTestGroupList = new ArrayList<Integer>();
		List<Integer> diabetesControlGroupList = new ArrayList<Integer>();
		List<Integer> diabetesTestGroupList = new ArrayList<Integer>();

		int nonControlGroupSum = 0;
		int nonTestGroupSum = 0;
		int diabetesControlGroupSum = 0;
		int diabetesTestGroupSum = 0;
		List<Map<String,Object>> data = randomGroupService.getEverydayAddNum();
		for(Map<String, Object> map : data) {
			String createDate = (String) map.get("CREATE_DATE");
			dayList.add(createDate);
			
			String groupStr = (String) map.get("GROUPLIST");
			String sumStr = (String) map.get("SUMLIST");
			
			String group[] = groupStr.split(",");
			String sum[] = sumStr.split(",");
			
			if(groupStr.contains("01")) {
				int position = Arrays.binarySearch(group, "01");
				nonControlGroupSum += Integer.parseInt(sum[position]);
				nonControlGroupList.add(nonControlGroupSum);
			} else {
				nonControlGroupList.add(nonControlGroupSum);
			}
			if(groupStr.contains("02")) {
				int position = Arrays.binarySearch(group, "02");
				nonTestGroupSum += Integer.parseInt(sum[position]);
				nonTestGroupList.add(nonTestGroupSum);
			} else {
				nonTestGroupList.add(nonTestGroupSum);
			}
			if(groupStr.contains("11")) {
				int position = Arrays.binarySearch(group, "11");
				diabetesControlGroupSum += Integer.parseInt(sum[position]);
				diabetesControlGroupList.add(diabetesControlGroupSum);
			} else {
				diabetesControlGroupList.add(diabetesControlGroupSum);
			}
			if(groupStr.contains("12")) {
				int position = Arrays.binarySearch(group, "12");
				diabetesTestGroupSum += Integer.parseInt(sum[position]);
				diabetesTestGroupList.add(diabetesTestGroupSum);
			} else {
				diabetesTestGroupList.add(diabetesTestGroupSum);
			}
			
		}
		
		Map<String, List<?>> map = new HashMap<String, List<?>>();
		map.put("dayList", dayList);
		map.put("nonControlGroupList", nonControlGroupList);
		map.put("nonTestGroupList", nonTestGroupList);
		map.put("diabetesControlGroupList", diabetesControlGroupList);
		map.put("diabetesTestGroupList", diabetesTestGroupList);
		return map;
	}
}

