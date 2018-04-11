package com.bdcor.pip.web.pro.promgt.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.codec.Base64;
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
import org.springframework.web.servlet.ModelAndView;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.core.utils.CustomizedPropertyPlaceholderConfigurer;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.pro.promgt.domain.Adjunct;
import com.bdcor.pip.web.pro.promgt.filter.AdjunctFilter;
import com.bdcor.pip.web.pro.promgt.service.FileService;


@Controller
@RequestMapping(value = "pro/filemgt")
public class FileController {
	Logger log=LoggerFactory.getLogger(FileController.class);
	@Autowired
	private FileService fileService;
	
	@RequestMapping
    public String init(Model model){
		model.addAttribute("typeList", fileService.getAdjunctType());
        return "pro/filemgt/list";
    }
	
//	@RequestMapping("loadfile")
//	public String loadfile(Model model){
//		model.addAttribute("typeList", fileService.getAdjunctType());
//		return "pro/filemgt/loadfile";
//	}
	
	@RequestMapping("loadfile")
	public String loadfile(Model model, String type){
		model.addAttribute("typeList", fileService.getAdjunctType());
		model.addAttribute("type", type);
		return "pro/filemgt/loadfile";
	}
	
	@RequestMapping(value = "listAdjunct", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JqgridResponse<Adjunct> listAdjunct(AdjunctFilter filter){
		List<Adjunct> adjunctList = fileService.listAdjunct(filter);
		JqgridResponse<Adjunct> response = JqgridResponseContext.getJqgridResponse();
        response.setRows(adjunctList);
        return response;
	}
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "uploadDocument", method = RequestMethod.POST)   
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {   
    	
    	Map<String, Object> result = new HashMap<String, Object>();
    	long fileSize = file.getSize();
    	if(fileSize > 10240000){
    		result.put("msg", "文件大小不能超过10M!");
    		result.put("success", false);
    		return new ResponseEntity(result, HttpStatus.OK);
    	}
    	
    	String projectId = Securitys.getUser().getCurrent_projectId();
        //String ctxPath = request.getSession().getServletContext().getRealPath("") + "/uploadfiles/";
    	String ctxPath = CustomizedPropertyPlaceholderConfigurer.getContextProperty("project_document_upload_dir");
        
        File dirPath = new File(ctxPath);   
        if (!dirPath.exists()) {
        	dirPath.mkdirs();
        }
        String fileName =file.getOriginalFilename();//linux 服务器上是gbk的编码
        File filePath = new File(ctxPath + fileName); 
        
        try {   
        	if(!filePath.exists()){
            	filePath.createNewFile();
            }
            file.transferTo(filePath);  
            fileService.saveAdjunct(projectId, file.getOriginalFilename(), ctxPath, request.getParameter("type"),request.getParameter("fileDesc"));
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
    
    @RequestMapping(value = "downloadDocument", method = RequestMethod.GET)  
    public ModelAndView download(Adjunct adjunct, HttpServletRequest request, HttpServletResponse response) {  
  
        BufferedInputStream bis = null;  
        BufferedOutputStream bos = null;  
  
        String downLoadPath = adjunct.getPath() +adjunct.getAdjunctName() ;  
        try {  
        	fileService.updateDownCount(adjunct);
        	response.setContentType("text/html;charset=utf-8");  
        	request.setCharacterEncoding("UTF-8");  
        	
            long fileLength = new File(downLoadPath).length();  
            response.setContentType("application/x-msdownload;");  
            String suffix=adjunct.getAdjunctName().substring(adjunct.getAdjunctName().lastIndexOf("."));
            String downloadFileName=adjunct.getFileDesc().concat(suffix);
            String name=new String(downloadFileName.getBytes("gb2312"), "ISO8859-1" );
            response.setHeader("Content-disposition", "attachment; filename=" + name); 
            response.setHeader("Content-Length", String.valueOf(fileLength));  
            bis = new BufferedInputStream(new FileInputStream(downLoadPath));  
            bos = new BufferedOutputStream(response.getOutputStream());  
            byte[] buff = new byte[2048];  
            int bytesRead;  
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
                bos.write(buff, 0, bytesRead);  
            }
            bos.flush();
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {
				if (bis != null)  
				    bis.close();  
				if (bos != null)  
				    bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}  
        }  
        return null;  
    }
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "deleteDocument", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> deleteAdjunct(Adjunct adjunct){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			fileService.deleteAdjunct(adjunct.getAdjunctId());
			String name = "";
			try {
				name = new String(adjunct.getAdjunctName().getBytes("ISO8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			String path = adjunct.getPath() + (Base64.encodeToString(name.getBytes())).replaceAll("/", "_");
			File filePath = new File(path);
			if(filePath.isFile()&&filePath.exists()){
        		filePath.delete();
        	}
			result.put("success", true);
		}catch(Exception e){
			result.put("success", true);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
    @RequestMapping(value = "openmodaladdjunctinput", method = RequestMethod.GET)
    public String addjunctInput(Model model) {
    	model.addAttribute("typeList", fileService.getAdjunctType());
    	return "pro/filemgt/uploadform";
    }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "checkFileExists", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> checkFileExists(Adjunct adjunct){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			String name = "";
			
			String downLoadPath = adjunct.getPath() + adjunct.getAdjunctName();
			File loadFile = new File(downLoadPath);
			if(!loadFile.exists()){
				result.put("success", true);
			}else{
				result.put("success", true);
			}
		}catch(Exception e){
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
	public static void main(String[]args)throws Exception{
		String f="测试";
		System.out.println(new String(f.getBytes("gb2312"), "utf-8" ));
		 System.getProperties().list(System.out);     
         
	        System.out.println("******************");    
	            
	            
	        final String encoding = System.getProperty("file.encoding");      
	        System.out.println("encoding:"+encoding);     
	            
	            
	        String path= "./哈haha哈AAA璎玥.txt";    
	        System.out.println(path);    
	            
	        // TODO file.encoding=iso8859-1    
	        try {    
	            String newp = new String(path.getBytes("gbk"),encoding);    
	            System.out.println(newp);    
	            File file = new File(newp);    
	            boolean b = file.createNewFile();    
	            System.out.println("file create:"+b);    
	        } catch (UnsupportedEncodingException e1) {    
	            e1.printStackTrace();    
	        } catch (IOException e) {    
	            e.printStackTrace();    
	        }    
	}
}
