package com.bdcor.pip.web.sys.tablecreate.controller;

import java.util.ArrayList;
import java.util.List;

import org.pentaho.di.core.logging.LogLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdcor.pip.kettle.service.KettleException;
import com.bdcor.pip.kettle.service.KettleService;
import com.bdcor.pip.web.sys.tablecreate.service.PaperTableCreateService;

@Controller
@RequestMapping("sys/paperTableCreate")
public class PaperTableCreateController {
	
	@Autowired
	private PaperTableCreateService paperTableCreateService;
	
	@Autowired
	private KettleService kettleService;
	
	@RequestMapping
	public @ResponseBody String createTable(){
		paperTableCreateService.createTable();
        return "";
	}
	
	@RequestMapping(value="insertData")
	public @ResponseBody String insertData(){
		paperTableCreateService.insertData();
        return "";
	}
	
	@RequestMapping(value="kettleTest")
	public @ResponseBody String kettleTest(){
		List<String> fileList = new ArrayList<String>();
		fileList.add("D:/a/a.f");
		try {
			kettleService.runKettleTasks(fileList, null, null, LogLevel.DEBUG);
		} catch (KettleException e) {
			e.printStackTrace();
		}
        return "";
	}
	
	
}
