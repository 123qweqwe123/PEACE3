package com.bdcor.pip.web.quality.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.core.utils.GenerateKey;
import com.bdcor.pip.core.utils.StringUtils;
import com.bdcor.pip.web.pro.promgt.domain.Lcc;
import com.bdcor.pip.web.pro.promgt.domain.LccUser;
import com.bdcor.pip.web.pro.promgt.service.LccService;
import com.bdcor.pip.web.pro.promgt.service.LccUserService;
import com.bdcor.pip.web.quality.domain.Examine;
import com.bdcor.pip.web.quality.domain.ExamineFtp;
import com.bdcor.pip.web.quality.filter.ExamineFilter;
import com.bdcor.pip.web.quality.filter.ExamineFtpFilter;
import com.bdcor.pip.web.quality.service.ExamineService;

@Controller
@RequestMapping("quality/examine")
public class ExamineController {

	@Autowired
	private ExamineService examineService;
	@Autowired
	private LccService lccService;
	@Autowired
	private LccUserService lccUserService;
	
	@RequestMapping
	public String init(){
		return "quality/examine/list";
	}
	
	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<Examine> getAllExamines(ExamineFilter filter){
		List<Examine> list = examineService.getAllExamines(filter);
		JqgridResponse<Examine> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(list);
		return response;
	}
	
    @RequestMapping(value = "openmodaladdexamineinput", method = RequestMethod.GET)
    public ModelAndView openInput(@RequestParam(value = "id", required = false) String id) {
    	ModelAndView mav = new ModelAndView("quality/examine/form");
    	if(id!=null&&!"".equals(id)){
    		Examine examine = examineService.getExamineById(id);
    		mav.addObject("examine", examine);
    		List<LccUser> exaLccUserList = lccUserService.getLccUserByLccCode(examine.getExamineLccCode());
    		mav.addObject("exaLccUserList", exaLccUserList);
    	}
    	
    	//List<Lcc> lccList = lccService.getLccByOrganType(new String[]{"3","4"});
    	List<Lcc> lccList = lccService.getAllActiveLcc();
    	mav.addObject("lccList", lccList);
    	
    	//List<Lcc> exaUserLccList = lccService.getLccByOrganType(new String[]{"3"});
    	List<Lcc> exaUserLccList = lccService.getAllActiveLcc();
    	mav.addObject("exaUserLccList", exaUserLccList);
    	
    	return mav;
    }
    
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "addExamine", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> addExamine(Examine examine){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			if(StringUtils.isEmpty(examine.getId())){
				examineService.addExamine(examine);
			}else{
				examineService.updateExamine(examine);
			}
			result.put("success", true);
		}catch(Exception e){
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> delete(String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			examineService.delete(id);
			result.put("success", true);
		}catch(Exception e){
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "getLccUserByLccCode", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> getLccUserByLccCode(String lccCode) {
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			List<LccUser> lccUserList = lccUserService.getLccUserByLccCode(lccCode);
			result.put("lccUserList", lccUserList);
			result.put("success", true);
		}catch(Exception e){
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
    @RequestMapping(value = "openmodaluploadexamineinput", method = RequestMethod.GET)
    public ModelAndView openUploadInput(@RequestParam(value = "id") String id,@RequestParam(value = "lccCode") String lccCode) {
    	ModelAndView mav = new ModelAndView("quality/examine/uploadform");
    	mav.addObject("id", id);
    	mav.addObject("lccCode", lccCode);
    	return mav;
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "uploadExamine", method = RequestMethod.POST)   
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file,@RequestParam("id") String id,@RequestParam("lccCode") String lccCode, HttpServletRequest request) {   
    	Map<String, Object> result = new HashMap<String, Object>();
        String ctxPath = request.getSession().getServletContext().getRealPath("/") + "\\" + "uploadfiles\\";   
        File dirPath = new File(ctxPath);   
        if (!dirPath.exists()) {
        	dirPath.mkdir();   
        }
        String oldFileName = file.getOriginalFilename();
        String fileName = "EXA"+lccCode+id+GenerateKey.getKey(GenerateKey.PREFIX_EXAMINE_FTP)+oldFileName.substring(oldFileName.lastIndexOf("."));
        
        File newFilePath = new File(ctxPath + "/" + fileName);
        try {   
        	file.transferTo(newFilePath);
            
            String examineFtpId = examineService.addExamineFtp(oldFileName, fileName, ctxPath, id);
            
            result.put("examineFtpId", examineFtpId);
            result.put("success", true);
        } catch (Exception e) {
        	if(newFilePath.isFile()&&newFilePath.exists()){
        		newFilePath.delete();
        	}
            result.put("success", false);  
        }
        return new ResponseEntity(result, HttpStatus.OK);
    }  
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "deleteFile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> deleteFile(String examineFtpId, String fileName) {
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			ExamineFtp exFtp = examineService.getExamineFtp(examineFtpId,fileName);
			if(exFtp!=null){
				
				Boolean boo = examineService.deleteFile(examineFtpId, fileName, exFtp.getExamineId());
				if(boo){
					String path = exFtp.getFilePath()+exFtp.getFileName();
					File file = new File(path);
					if(file.isFile()&&file.exists()){
						file.delete();
		        	}
				}
				
			}
			result.put("success", true);
		}catch(Exception e){
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="checkFileNameExists",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> checkFileNameExists(@RequestParam(value = "id") String id, @RequestParam(value = "fileName") String fileName){
        Boolean result = examineService.checkFileNameExists(id, fileName);
        Map<String,Object> res = new HashMap<String,Object>();
        res.put("result", result);
        return new ResponseEntity(res,HttpStatus.OK);
    }
    
    @RequestMapping(value = "openmodalshowfilenameinput", method = RequestMethod.GET)
    public ModelAndView openUploadInput(@RequestParam(value = "examineId") String examineId) {
    	ModelAndView mav = new ModelAndView("quality/examine/showfilenameform");
    	//List<ExamineFtp> exFtpList = examineService.getExamineFtps(examineId);
    	//mav.addObject("exFtpList", exFtpList);
    	mav.addObject("examineId", examineId);
    	return mav;
    }
    
	@RequestMapping(value = "examineFtpNameList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<ExamineFtp> examineFtpNameList(ExamineFtpFilter filter){
		List<ExamineFtp> list = examineService.getAllExamineFtps(filter);
		JqgridResponse<ExamineFtp> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(list);
		return response;
	}
}
