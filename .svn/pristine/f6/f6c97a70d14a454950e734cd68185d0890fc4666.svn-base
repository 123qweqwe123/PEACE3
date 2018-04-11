/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.material.supp.controller 
 */
/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.material.supp.controller  
 */

package com.bdcor.pip.web.material.supp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdcor.pip.web.material.supp.domain.PipMmsDefstorehouse;
import com.bdcor.pip.web.material.supp.service.PipMmsDefstorehouseService;

/**  
 * description: 库房控制层 
 * @author yangfeng 创建时间：2015-11-9         
 */
@Controller
@RequestMapping("material/defstorehouse")
public class PipMmsDefstorehouseController {
	@Autowired
	PipMmsDefstorehouseService pipMmsDefstorehouseService;
	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<PipMmsDefstorehouse> getListDetal(
			@RequestParam("lccCode")String lccCode) {
		List<PipMmsDefstorehouse> list = pipMmsDefstorehouseService.getAllHouseListByLccCode(lccCode);
		return list;
	}
}

