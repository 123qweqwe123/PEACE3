package com.bdcor.pip.web.sys.rbac.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bdcor.pip.core.beanvalidator.BeanValidators;
import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.core.utils.DateUtil;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.data.util.CryptoUtil;
import com.bdcor.pip.web.pro.promgt.domain.Project;
import com.bdcor.pip.web.pro.promgt.service.LccUserService;
import com.bdcor.pip.web.pro.promgt.service.ProjectMgtService;
import com.bdcor.pip.web.progress.filter.ProgressFilter;
import com.bdcor.pip.web.sys.rbac.domain.Organization;
import com.bdcor.pip.web.sys.rbac.domain.User;
import com.bdcor.pip.web.sys.rbac.domain.UserDTO;
import com.bdcor.pip.web.sys.rbac.domain.UserDataLimit;
import com.bdcor.pip.web.sys.rbac.filter.UserFilter;
import com.bdcor.pip.web.sys.rbac.service.OrganizationService;
import com.bdcor.pip.web.sys.rbac.service.RoleService;
import com.bdcor.pip.web.sys.rbac.service.UserService;



/**
 * <pre>
 * 鍔熻兘璇存槑锛氱敤鎴风鐞咰ontroller
 * </pre>
 * 
 * @author <a href="mailto:shao.gq@gener-tech.com">ShaoGuoqing</a>
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/sys/rbac/user")
public class UserController{
    
    @Autowired
    private UserService userService; 
    
    @Autowired
    private OrganizationService orgService;
    
    @Autowired
    private RoleService roleService; 
    
   // @Autowired
   // private LccUserService lccUserService;
    
    @Autowired
    private ProjectMgtService projectMgtService;
    
    /**
     * 鍒濆鍖�
     * @return
     */
    @RequiresPermissions("/sys/rbac/user")
    @RequestMapping
    public ModelAndView init(){
       
        ModelAndView mav = new ModelAndView("/sys/rbac/user/list");
        
        return mav;
    }
    
    /***
     * 鍒濆鍖栫紪杈戦〉闈�
     * 
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions(value={"user:update","user:add"},logical=Logical.OR)
    @RequestMapping(value = "openmodaluserinput", method = RequestMethod.GET)
    public ModelAndView userInput(@RequestParam(value = "id", required = false) String id) {
       
        ModelAndView mav = new ModelAndView("sys/rbac/user/form");
        if (!StringUtils.isBlank(id)) {
            
            User user = userService.getById(id); 
            
            mav.addObject("user", user);
            
            //鐢ㄦ埛鎷ユ湁鐨勮鑹�
            mav.addObject("ownRoles",roleService.getRolesByUserIdAndOrganId(id,Securitys.getOrganId()));
            
             if(null!=user){
            	 Organization Org = orgService.getById(user.getOrganizationId());
            	 mav.addObject("organization", Org);
              } 
        }
        
        List<Organization> list = orgService.list(new HashMap()); 
        
        mav.addObject("organizations", list);
        
        mav.addObject("roles", roleService.getAllByOrganId(Securitys.getOrganId()));
        
        //mav.addObject("lccUserList", lccUserService.getLccUsersByProjectId());
        mav.addObject("projectId", Securitys.getUser().getCurrent_projectId());
        
        return mav;
    }
    
    
    
    /**
     * 鏌ヨ鐢ㄦ埛鍒楄〃
     * @param filter
     * @return
     */
    @RequiresPermissions("/sys/rbac/user")
    @RequestMapping(value = "list",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public JqgridResponse<UserDTO> list(UserFilter filter) {
    	String userId= Securitys.getUserId();
    	filter.setUserId(userId);
    	filter.setProjectId(Securitys.getUser().getCurrent_projectId());
        List<UserDTO> userList = userService.list(filter);
        
        
        
        JqgridResponse<UserDTO> response = JqgridResponseContext.getJqgridResponse();
       
        response.setRows(userList);
      
        return response;
    }

    /**
     * 鏂板鐢ㄦ埛
     * @param user
     * @return
     */
    @RequiresPermissions("user:add")
    @RequestMapping(value = "save",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> create(User user,@RequestParam(value="rIds",required=false)String rIds) {
        
        // 璋冪敤JSR303 Bean Validator杩涜鏍￠獙, 寮傚父灏嗙敱ConstraintViolationExceptionHandler缁熶竴澶勭悊.
        // BeanValidators.validateWithException(user);
        
        //user.setOrganizationId(Securitys.getOrganId());
        
        user.setIsAdmin(null);
        
        if(StringUtils.isNotBlank(rIds)){
            
            String[] rids = rIds.split(",");
           
            userService.save(user,rids);
        }else{

            userService.save(user);
        }
        
        Map<String, Object> res = new HashMap<String, Object>();
        
        res.put("success", true);
        
        return new ResponseEntity(res, HttpStatus.OK);
    }
    
    /**
     * 鏇存柊鐢ㄦ埛
     * @param user
     * @return
     */
    @RequiresPermissions("user:update")
    @RequestMapping(value = "update",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> update(User user,@RequestParam(value="rIds",required=false)String rIds) {
        
        // 璋冪敤JSR303 Bean Validator杩涜鏍￠獙, 寮傚父灏嗙敱ConstraintViolationExceptionHandler缁熶竴澶勭悊.
      //   BeanValidators.validateWithException(user);
        
        if(StringUtils.isNotBlank(rIds)){
            
            String[] rids = rIds.split(",");
           
           // userService.update(user,rids);
            userService.saveOrUpdateUserRole(user, rids, true);
            
        }else{
            userService.update(user,null);
        }   
        
        Map<String, Object> res = new HashMap<String, Object>();
        
        res.put("success", true);
        
        return new ResponseEntity(res, HttpStatus.OK);
    }
    
    /**
     * 鍒犻櫎鐢ㄦ埛
     * @param ids
     * @return
     */
    @RequiresPermissions("user:delete")
    @RequestMapping(value = "delete",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> delete(@RequestParam(value="ids",required=true)String ids) {
        
        String[] idArr = ids.split(",");
       
        userService.delete(idArr);
       
        Map<String, Object> res = new HashMap<String, Object>();
       
        res.put("success", true);
       
        return new ResponseEntity(res, HttpStatus.OK);
    }
    
    /**
     * 妫�煡鐢ㄦ埛鍚嶆槸鍚﹀瓨鍦�
     * @param loginName
     * @param id
     * @return
     */
    @RequestMapping(value="checkLoginNameExists",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> checkLoginNameExists(@RequestParam(value="loginName",required=true)String loginName,@RequestParam(value="id",required=false)String id){
       
        Boolean result = userService.chekcLoginNameExists(loginName, id);
       
        Map<String,Object> res = new HashMap<String,Object>();
       
        res.put("result", result);
       
        return new ResponseEntity(res,HttpStatus.OK);
    }
    
    /**
     * 妫�煡鏃у瘑鐮佹槸鍚︽湁鏁�
     * @param id
     * @param password
     * @return
     */
    @RequestMapping(value="checkConfirmOldPassword",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> checkConfirmOldPassword(@RequestParam(value="id",required=false)String id,@RequestParam(value="password",required=true)String password){
       
        Boolean result = userService.checkConfirmOldPassword(id, password);
       
        Map<String,Object> res = new HashMap<String,Object>();
       
        res.put("result", result);
       
        return new ResponseEntity(res,HttpStatus.OK);
    }
    
    /**
     * 閲嶇疆瀵嗙爜
     * @param id
     * @param newPassword
     * @return
     */
    @RequiresPermissions("user:resetpassword")
    @RequestMapping(value="resetPassword",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> resetPassword(@RequestParam(value="id",required=true)String id,@RequestParam(value="newPassword",required=true)String newPassword){ 
       
        userService.resetPassword(id, newPassword);
        
        Map<String,Object> res = new HashMap<String,Object>();
        
        res.put("success", true);
        
        return new ResponseEntity(res,HttpStatus.OK);
    }
    
    
    @RequiresPermissions("basic:changepassword")
    @RequestMapping(value="modifyPassword",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> modifyPassword(@RequestParam(value="currentPassword",required=true)String currentPassword,@RequestParam(value="newPassword",required=true)String newPassword,@RequestParam(value="id",required=false)String id){ 
        
        userService.modifyPassword(currentPassword, newPassword,id);
        
        Map<String,Object> res = new HashMap<String,Object>();
        
        res.put("success", true);
        SecurityUtils.getSubject().logout();
        return new ResponseEntity(res,HttpStatus.OK);
    }

    /**
     * 鍒濆鍖栦慨鏀瑰瘑鐮侀〉闈�
     * @return
     */
    @RequiresPermissions("basic:changepassword")
    @RequestMapping(value = "openmodalupdatepassword", method = RequestMethod.GET)
    public ModelAndView updatePassword(@RequestParam(value="id",required=false)String id) {
        
        ModelAndView mav = new ModelAndView("sys/rbac/user/updatePasswordForm");
        
        if(StringUtils.isBlank(id)){
        
            id = Securitys.getUserId();
        }
        
        mav.addObject("user", userService.getById(id));
        
        return mav;
    }
    
    
    @RequestMapping(value = "getProject",produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Project> getProject(@RequestParam(value="loginName",required=true)String loginName) {
       
        List<User> userList = userService.queryUsersByLoginName(loginName);
        List<Project> ret = new ArrayList<Project>();
        if ( userList != null ){
        	for ( User u : userList){
        		Project p = this.projectMgtService.getProjectById(u.getProjectId());
        		if ( p != null ){
        			ret.add(p);
        		}
        	}
        }
        
        return ret;
    }
    

    /**
     *  寰楀埌璇ラ」鐩笅闈㈢殑鎵�湁lcc
     * @return
     */
    @RequestMapping(value = "getAllLcc", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<UserDataLimit> getPermissions(UserDataLimit ud){
        return userService.getUserDataLimit(ud);
    }
    
    @RequiresPermissions("/sys/rbac/user:datapermision")
    @RequestMapping(value = "openmodaldatalimit", method = RequestMethod.GET)
    public ModelAndView toDataLimit(@RequestParam(value = "userId") String userId) {
    	ModelAndView mav = new ModelAndView("sys/rbac/user/data_limit_form");
    	List<String> datas=userService.getUserDatas(userId);
    	mav.addObject("userId", userId);
    	mav.addObject("datas", datas);
        return mav;
    }
    
    @RequestMapping(value = "getAllDataLimit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<UserDataLimit> getDataLimitTree(){
      return userService.getDataLimitTree();
    
    }
    /**
     * 
     * @param destLcc 鐢ㄦ埛鎷ユ湁鏉冮檺鐨刲cc鍒楄〃
     * @param sourceLcc 鐢ㄦ埛娌℃湁鏉冮檺鐨刲cc鍒楄〃
     * @param id
     * @return
     */
    @RequiresPermissions("/sys/rbac/user:datapermision")
    @RequestMapping(value = "updateDataLimit",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> updateDataLimit(String destLcc,String userId) {
    	Map<String, Object> res = new HashMap<String, Object>();
        userService.updataLccLimit(userId,destLcc);
        res.put("success", true);
        return new ResponseEntity(res, HttpStatus.OK);
    }
    
    @RequestMapping("/exportexcel")   
    public ModelAndView exportexcel(HttpServletRequest request, HttpServletResponse response)   
            throws Exception {   
		//response.setContentType("text/html;charset=utf-8");   
        request.setCharacterEncoding("UTF-8");   
        java.io.BufferedInputStream bis = null;   
        java.io.BufferedOutputStream bos = null;   
  
        String ctxPath = request.getSession().getServletContext().getRealPath("/") 
        		+ File.separator + "WEB-INF"
        		+ File.separator + "views"
                + File.separator + "sys" 
        		+ File.separator + "rbac" 
                + File.separator + "user" + File.separator; 
        String tempName = "account";
        String template = ctxPath + tempName + ".xls"; 
        String datafile = ctxPath + "DataReport.xls";
        XLSTransformer transformer = new XLSTransformer();
        try {   
            response.setContentType("application/x-msdownload;");   
            response.setHeader("Content-disposition", "attachment; filename="  
                    + new String("账户管理.xls".getBytes("utf-8"), "ISO8859-1"));   
            //response.setHeader("Content-Length", String.valueOf(fileLength));
            List<Map> list = this.userService.exportExcelSQL(Securitys.getUser().getId());
    		Map beans = new HashMap();
    		beans.put("resultList",list);
            transformer.transformXLS(template,beans,datafile);
            
            bis = new BufferedInputStream(new FileInputStream(datafile));   
            bos = new BufferedOutputStream(response.getOutputStream());   
            byte[] buff = new byte[2048];   
            int bytesRead;   
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {   
                bos.write(buff, 0, bytesRead);   
            }   
        } catch (Exception e) {   
            e.printStackTrace();   
        } finally {   
            if (bis != null)   
                bis.close();   
            if (bos != null)   
                bos.close();   
        }   
        return null;   
	}
    
    @RequestMapping("/resetKey")  
    @ResponseBody
    public ResponseEntity<?> resetKey(HttpServletRequest request, HttpServletResponse response)   
            throws Exception {  
    	Map<String, Object> res = new HashMap<String, Object>();
    	String u = request.getParameter("user");
    	String l = request.getParameter("lcc");
    	if ( StringUtils.isNotBlank(u) && StringUtils.isNotBlank(l)){
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    		try{
    			res.put("key", CryptoUtil.encrypt(l + u + sdf.format(new Date()), "true"));
    			res.put("code", "0");
    		}catch(Exception ex){
    			res.put("code", "9");
    			res.put("msg", ex.getMessage());
    		}
    	}else{
    		res.put("code", "1");
    		res.put("msg", "鍙傛暟閿欒");
    	}
    	return new ResponseEntity(res, HttpStatus.OK);
    }
    
}
