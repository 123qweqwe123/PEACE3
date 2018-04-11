package com.bdcor.pip.web.data.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdcor.pip.web.data.domain.NewsLcc;
import com.bdcor.pip.web.data.service.NewsService;



@Controller
@RequestMapping(value = "news")
public class NewsLccController {
	
	@Autowired
	private NewsService newsService;
	
	@RequestMapping(value = "lccTree")
	@ResponseBody
	public List<NewsLcc> menuTree() { 
		List<NewsLcc> lccs = newsService.getAllNewsLcc();
		return lccs;				
	}
	
}
