package com.bdcor.pip.web.data.controller;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.multipart.MultipartFile;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.core.utils.CustomizedPropertyPlaceholderConfigurer;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.data.domain.PipSysNews;
import com.bdcor.pip.web.data.domain.PipSysNewsExample;
import com.bdcor.pip.web.data.filter.NewsFilter;
import com.bdcor.pip.web.data.service.PipSysNewsService;
import com.bdcor.pip.web.pro.promgt.service.FileService;


@Controller
@RequestMapping(value = "pro/news")
public class NewsController {
	Logger log=LoggerFactory.getLogger(NewsController.class);
	
	@Autowired
	private PipSysNewsService pipSysNewsService;
	
	@Autowired
	private FileService fileService;
	
	@RequestMapping
    public String init(Model model){
		model.addAttribute("typeList", pipSysNewsService.queryNews(null));
        return "pro/news/newslist";
    }
	
	@RequestMapping(value="openmodaluploadPage", method=RequestMethod.GET)  
    public String upload(Model model){
		model.addAttribute("typeList", fileService.getAdjunctType());
        return "pro/news/uploadform";
    }
	
	@RequestMapping(value = "listNews", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JqgridResponse<PipSysNews> listNews(NewsFilter newsfilter){
			List<PipSysNews> newsList = pipSysNewsService.queryNewsByOrder(newsfilter);		
		JqgridResponse<PipSysNews> response = JqgridResponseContext.getJqgridResponse();
        response.setRows(newsList);
        return response;
	}
	
	@RequestMapping(value="deleteNews", method=RequestMethod.POST)  
    public ResponseEntity deleteNews(@RequestParam Long id,HttpServletRequest request) { 
		Map<String, Object> result = new HashMap<String, Object>();
		if ( id != null ){
			try{
				this.pipSysNewsService.deleteNews(id);
				result.put("success", true);
			}catch(Exception ex){
				result.put("msg", "删除新闻失败");
	    		result.put("success", false);
			}
		}else{
			result.put("msg", "指定的id不能为空");
    		result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}

	
	@RequestMapping(value="upload", method=RequestMethod.POST)  
    public ResponseEntity addUser(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException{ 

		Map<String, Object> result = new HashMap<String, Object>();
    	long fileSize = file.getSize();
    	if(fileSize > 10240000){
    		result.put("msg", "文件大小不能超过10M!");
    		result.put("success", false);
    		return new ResponseEntity(result, HttpStatus.OK);
    	}
    	
    	String projectId = Securitys.getUser().getCurrent_projectId();
        //String ctxPath = request.getSession().getServletContext().getRealPath("") + "/uploadfiles/";
    	String ctxPath = CustomizedPropertyPlaceholderConfigurer.getContextProperty("news.path");
        
        File dirPath = new File(ctxPath);   
        if (!dirPath.exists()) {
        	dirPath.mkdirs();
        }
        String fileName =file.getOriginalFilename();
        
        PipSysNewsExample ex = new PipSysNewsExample();
        ex.createCriteria().andFileNameEqualTo(fileName);
        List<PipSysNews> tlist = this.pipSysNewsService.selectNews(ex);
        if ( tlist != null && tlist.size() >0 ){
        	result.put("msg", "文件不能重名");
    		result.put("success", false);
    		return new ResponseEntity(result, HttpStatus.OK);
        }
        
        File filePath = new File(ctxPath + fileName); 
        
        try {   
        	if(!filePath.exists()){
            	filePath.createNewFile();
            }
            file.transferTo(filePath);  
            PipSysNews record=new PipSysNews();
            record.setId(System.currentTimeMillis());
            record.setChannel(request.getParameter("channel"));
            record.setType(request.getParameter("type"));
            record.setTitle(request.getParameter("title"));
            record.setName(request.getParameter("name"));
            record.setFileName(fileName);
            record.setCreateDate(new Date());
            record.setAuthor(Securitys.getUser().getName());
            pipSysNewsService.insertSelective(record);
            result.put("success", true);
        } catch (Exception e) {
        	e.printStackTrace();
        	if(filePath.isFile()&&filePath.exists()){  
        		filePath.delete();
        	}
        	result.put("msg", "上传失败，请联系管理员！");
            result.put("success", false);  
        }
        return new ResponseEntity(result, HttpStatus.OK);
    } 
}
