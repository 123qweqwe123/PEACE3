package com.bdcor.pip.web.sys.rbac.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.core.exception.ServiceException;
import com.bdcor.pip.core.security.util.PassWordUtil;
import com.bdcor.pip.core.utils.GenerateKey;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.sys.logmgt.service.LogService;
import com.bdcor.pip.web.sys.rbac.dao.UserDao;
import com.bdcor.pip.web.sys.rbac.domain.LccAccount;
import com.bdcor.pip.web.sys.rbac.domain.User;
import com.bdcor.pip.web.sys.rbac.domain.UserDTO;
import com.bdcor.pip.web.sys.rbac.domain.UserDataLimit;
import com.bdcor.pip.web.sys.rbac.domain.UserRoleDTO;
import com.bdcor.pip.web.sys.rbac.filter.UserFilter;
import com.bdcor.pip.web.sys.rbac.service.UserService;
import com.google.common.collect.Lists;

/**
 * 
 * <pre>
 * 功能说明：用户Service
 * </pre>
 * 
 * @author <a href="mailto:wang.g@gener-tech.com">WangGang</a>
 * @version 1.0
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService { 

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private LogService logService;

    @Override
    public User findUserByLoginName(String loginName) { 
        User user = new User();
        user.setLoginName(loginName);
       
        try {
            return userDao.queryByLoginName(loginName);
        } catch (Exception e) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("msg", "查找用户失败");
            e.printStackTrace();
            //logService.error(Consts.SystemModel.SYS, "查找用户失败");
            throw new ServiceException("login", params, e);
        }

    }

    @Override
    public User getById(String id) {
        try {
            return userDao.queryById(id);
        } catch (Exception e) {
            //logService.error(Consts.SystemModel.SYS, "查找用户失败");
            throw new ServiceException("查找用户失败", e);
        }

    }

    @Override
    public List<UserDTO> list(UserFilter filter) {
        try {
            if(Securitys.isAdmin()){
                filter.setIsAdmin(1);
            }else if(Securitys.isOrganAdmin()){
                filter.setIsAdmin(2); 
            }else{
                filter.setIsAdmin(0);
            }
            return userDao.list(filter);
        } catch (Exception e) {
            //logService.error(Consts.SystemModel.SYS, "查询用户列表失败");
            throw new ServiceException("查询用户列表失败", e);
        }
    }

    /*
     * (non-Javadoc)
     * @see com.bdcor.pip.web.sys.rbac.service.UserService#save(com.bdcor.pip.web.sys.rbac.domain.User)
     */
    @Override
    public String save(User user) {
        User oldUser = userDao.queryByLoginName(user.getLoginName());
        if (oldUser != null) {
            
            throw new ServiceException("已存在相同登录名");
        }
        try {
            //String userId = Identities.uuid();
        	String userId = GenerateKey.getKey(GenerateKey.PREFIX_USER);
            byte[] salts = PassWordUtil.getSaltBytes();// 自定义加密串,目前获取8位随即
            user.setSalt(PassWordUtil.getEncodeSalts(salts));
            user.setPassword(PassWordUtil.getEncodePassWord(user.getPassword(),salts));
            user.setId(userId);
            user.setCreateUser(Securitys.getUserId());
            user.setCreateDatetime(new Date());
            user.setIsDelete(0);
            //user.setStatus(2);   //默认是无效
            if(null ==user.getIsAdmin()){
            	user.setIsAdmin(0);
            }
            userDao.save(user);
            LccAccount lccAccount=new LccAccount();
            lccAccount.setUserId(userId);
            lccAccount.setLccCode(user.getLccCode());
            userDao.saveLccAccount(lccAccount);
            logger.info("用户" + Securitys.getUserName() + "创建用户:" + user.getName());
            
            //logService.info(Consts.SystemModel.SYS,"用户" + Securitys.getUserName() + "创建用户:" + user.getName());
            return userId;
            
        } catch (Exception e) {
            //logService.error(Consts.SystemModel.SYS, "新增用户失败");
            throw new ServiceException("新增用户失败", e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.bdcor.pip.web.sys.rbac.service.UserService#login(java.lang.String
     * , java.lang.String, java.lang.String)
     */
    @Override
    public User login(String loginName, String password) {
        User user = null;
        if (StringUtils.isNotEmpty(loginName)
                && StringUtils.isNotEmpty(password)) {
            user = findUserByLoginName(loginName);
            if (null != user) {
                // 判断用户是否有效
               if (user.getStatus() == 2) {
                    throw new DisabledAccountException();
                } 
               /*
                if(user.getTenantStatus() == 0){
                    throw new TenantDisabledException();
                }*/
                byte[] salt = PassWordUtil.decodeHex(user.getSalt());
                String pwd = PassWordUtil.getEncodePassWord(password, salt);
                if (!user.getPassword().equals(pwd)) {
                    throw new AuthenticationException();
                }
            } else {
                throw new UnknownAccountException();
            }
        } else {
            throw new AuthenticationException("login param error...!!!");
        }
        return user;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.bdcor.pip.web.sys.rbac.service.UserService#update(com.genertech
     * .adp.web.sys.rbac.domain.User)
     */
    @Override
    public String update(User user) {

        String oldId = user.getId();

        if (StringUtils.isBlank(oldId)) {
            throw new ServiceException("请选择要修改的用户！");
        }
        User oldUser = userDao.queryByLoginName(user.getLoginName());
        if (oldUser != null && !oldId.equals(oldUser.getId())) {
            throw new ServiceException("已存在相同登录名");
        }
        try {
            user.setUpdateDatetime(new Date());
            user.setUpdateUser(Securitys.getUserId());
            userDao.update(user);
            LccAccount lccAccount=new LccAccount();
            lccAccount.setUserId(oldId);
            lccAccount.setLccCode(user.getLccCode());
           // userDao.updateLccAccount(lccAccount);
            //logService.info(Consts.SystemModel.SYS, "用户被更新"+user.getLoginName());
            
            return oldId;
        } catch (Exception e) {
            //logService.error(Consts.SystemModel.SYS, "修改用户失败");
            throw new ServiceException("修改用户失败id=" + oldId, e);
        	
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.bdcor.pip.web.sys.rbac.service.UserService#delete(java.lang.String
     * [])
     */
    @Override
    public void delete(String[] ids) {
        try {
            userDao.delete(ids);
            userDao.deleteUserRoles(ids);
            userDao.deleteLccAccount(ids);
            for(String id : ids){
            	User user = userDao.queryById(id);
            	//logService.warning(Consts.SystemModel.SYS, "用户"+user.getName()+"被删除");
            }
          
        } catch (Exception e) {
            //logService.error(Consts.SystemModel.SYS, "删除用户失败");
            throw new ServiceException("删除用户失败", e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.bdcor.pip.web.sys.rbac.service.UserService#resetPassword(java
     * .lang.String, java.lang.String)
     */
    @Override
    public void resetPassword(String id, String newPassword) {
      
        User user = userDao.queryById(id);
      
        if (null == user) {
           
            throw new ServiceException("请选择要重置密码的用户");
        }
        
        try {
            byte[] salts = PassWordUtil.getSaltBytes();// 自定义加密串,目前获取8位随即
            
            user.setSalt(PassWordUtil.getEncodeSalts(salts));
           
            user.setPassword(PassWordUtil.getEncodePassWord(newPassword, salts));
          
            userDao.update(user);
            
            //logService.warning(Consts.SystemModel.SYS, Securitys.getLoginName()+"重置了"+user.getLoginName()+"密码");
        } catch (Exception e) {
            //logService.error(Consts.SystemModel.SYS, "用户密码重置失败");
            throw new ServiceException("用户密码重置失败", e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.bdcor.pip.web.sys.rbac.service.UserService#chekcLoginNameExists
     * (java.lang.String, java.lang.String)
     */
    @Override
    public Boolean chekcLoginNameExists(String loginName, String id) {
        
        User oldUser;
        try {
            oldUser = userDao.queryByLoginName(loginName.trim());
        } catch (Exception e) {
            
            //logService.error(Consts.SystemModel.SYS,"根据租户id和登陆名查询失败");
            throw new ServiceException("根据租户id和登陆名查询失败",e);
        }
        
        if(StringUtils.isNotBlank(id)){
            if(null != oldUser && !id.equals(oldUser.getId())){
                return true;
            }
            return false;
        }
        
        if (null != oldUser) {
            
            return true;
        } 
        
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.bdcor.pip.web.sys.rbac.service.UserService#checkConfirmOldPassword
     * (java.lang.String, java.lang.String)
     */
    @Override
    public Boolean checkConfirmOldPassword(String userId, String password) {
        if (StringUtils.isBlank(userId)) {
            userId = Securitys.getUserId();
        }
        User user = getById(userId);

        if(null == user){
            return false;
        }
        byte[] salt = PassWordUtil.decodeHex(user.getSalt());
        String pwd = PassWordUtil.getEncodePassWord(password, salt);
        if (!user.getPassword().equals(pwd)) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.bdcor.pip.web.sys.rbac.service.UserService#modifyPassword(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void modifyPassword(String currentPassword, String newPassword,String id) {
        
        if(StringUtils.isBlank(id)){
            
            id = Securitys.getUserId();
        
        }
        
        User user = getById(id);
        
        if(null == user){
            throw new ServiceException("用户不存在");
        }
        byte[] salt = PassWordUtil.decodeHex(user.getSalt());
        String pwd = PassWordUtil.getEncodePassWord(currentPassword, salt);
        if (!user.getPassword().equals(pwd)) {
            throw new ServiceException("旧密码不正确");
        }
        
        String npwd = PassWordUtil.getEncodePassWord(newPassword, salt);
        if (user.getPassword().equals(npwd)) {
        
            throw new ServiceException("新密码不能与旧密码一致");
        }
        
        byte[] salts = PassWordUtil.getSaltBytes();// 自定义加密串,目前获取8位随即
       
        user.setSalt(PassWordUtil.getEncodeSalts(salts));
        
        user.setPassword(PassWordUtil.getEncodePassWord(newPassword,salts));
       try {
       
           userDao.update(user);
           
           //logService.warning(Consts.SystemModel.SYS, "用户密码被修改"+user.getId());
           
       } catch (Exception e) {
           
           //logService.error(Consts.SystemModel.SYS, "修改密码失败！"+user.getId());
           
           throw new ServiceException("修改密码失败！",e);
           
       }
    }

    /*
     * (non-Javadoc)
     * @see com.bdcor.pip.web.sys.rbac.service.UserService#save(com.bdcor.pip.web.sys.rbac.domain.User, java.util.List)
     */
    @Override
    public String save(User user, String[] roles) {
        
        return saveOrUpdateUserRole(user, roles, false);
        
    }

    /*
     * (non-Javadoc)
     * @see com.bdcor.pip.web.sys.rbac.service.UserService#update(com.bdcor.pip.web.sys.rbac.domain.User, java.util.List)
     */
    @Override
    public String update(User user, String[] roles) {
       
        return saveOrUpdateUserRole(user, roles, true);
    }

    /*
     * (non-Javadoc)
     * @see com.bdcor.pip.web.sys.rbac.service.UserService#saveUserRole(java.util.List, java.lang.Boolean)
     */
    @Override
    public String saveOrUpdateUserRole(User user,String[] roles, Boolean isUpdate) {
        
        String userId = null;
        
        //如果更新，删除用户之前的角色
        if(null != isUpdate && isUpdate){
            //更新用户
            userId = update(user);
            
            //删除用户之前的权限
            try {
                userDao.deleteUserRole(userId);
            } catch (Exception e) {
               //logService.error(Consts.SystemModel.SYS, "删除用户角色失败"+user.getName());
            }
            
           // logService.warning(Consts.SystemModel.SYS, "用户角色被删除"+user.getName());
            
        }else{
            //保存用户
            userId = save(user);
            
        }
        
        if(null == roles || roles.length==0){
            return userId;
        }
        
        List<UserRoleDTO> urList = new ArrayList<UserRoleDTO>();
        
        UserRoleDTO ur = null;
        
        //构造用户权限集合
        for(String roleId : roles){
            
            ur = new UserRoleDTO(userId,roleId);
            
            urList.add(ur);
        }  
        
        
       /* System.out.println("\n\n\n\n\n\n\n\n\n\n!!!!!!!!!!!!!!!!!!!!!!  修改用户");  
        
        if(true)  
        	 throw new RuntimeException("保存用户角色失败"); */ 
        
        //更新权限
        if(!urList.isEmpty()){
            try {
                
                userDao.saveUserRole(urList);
                
                //logService.warning(Consts.SystemModel.SYS, Securitys.getLoginName()+"更新"+user.getLoginName()+"的角色");
            } catch (Exception e) {
                //logService.error(Consts.SystemModel.SYS, Securitys.getLoginName()+"保存用户角色失败");
                throw new ServiceException("保存用户角色失败",e);
            }
        }
        return userId;
    }

	@Override
	public List<UserDTO> getUsersByOrganId(String organId) {
		return userDao.getUsersByOrganId(organId);
	}

	@Override
	public List<UserDTO> list(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> queryUsersByLoginName(String loginName) {
		return userDao.queryUsersByLoginName(loginName);
	}

	@Override
	public List<UserDataLimit> getUserDataLimit(UserDataLimit ud) {
		// TODO Auto-generated method stub
		return userDao.getLccListBuUserId(ud);
	}
	/**
	 * 关联用户和lcc的权限
	 */
	@Override
	public void updataLccLimit(String id, String destLcc) {
		// TODO Auto-generated method stub
		userDao.deleteDataLimitByUserId(id);//删除id对应的所有lcc信息
		if(destLcc==null || destLcc.trim().length()==0)return;
		String[]lccs=destLcc.split(",");
		for(String lccCode:lccs){
			LccAccount la=new LccAccount();
			la.setUserId(id);
			la.setLccCode(lccCode);
			userDao.saveLccAccount(la);
		}
	}
	
	
	public String getUserDataLimitSQL(String userId){ 
		
		UserDataLimit ud = new UserDataLimit();
		ud.setUserId(userId);
		ud.setType("2");
		List<UserDataLimit> l = getUserDataLimit(ud);
		
		if(l==null || l.isEmpty()){
        	l = new ArrayList();
        	UserDataLimit dp = new UserDataLimit();
        	dp.setId(Securitys.getUser().getLccCode());
        	l.add(dp); 
        } 
        
        StringBuffer sb = new StringBuffer();
        sb.append("  ( )");
        for(int i=0;i<l.size();i++){
        	if(i!=0)
        	{
        	    sb.insert(sb.length()-1, ",");
        	}
        	sb.insert(sb.length()-1 ,  "'");
        	sb.insert(sb.length()-1 ,  l.get(i).getId());
        	sb.insert(sb.length()-1 ,  "'");
        }
		
		return sb.toString();
	}
	
	@Override
   public List<String> getUserDatas(String userId) {
      List<Map<String,String>> datas=this.userDao.getDataLimitByUserId(userId);
      List<String> dataStrs=Lists.newArrayList();
      for(Map<String,String> m:datas){
         dataStrs.add(m.get("LCC_CODE"));
      }
      return dataStrs;
   }
	
	/**
	 * 获取数据权限树
	 */
   @Override
   public List<UserDataLimit> getDataLimitTree() {
      List<UserDataLimit> dataList=Lists.newArrayList();
      List<UserDataLimit> dataLimit=null;
      if(Securitys.isAdmin()){
         dataLimit=userDao.getDataLimitTree(null);
      }else{
         dataLimit=userDao.getDataLimitTree(Securitys.getUserId());
      }
      //根节点
      UserDataLimit root=new UserDataLimit();
      root.setId("0").setName("全选").setParentId("");
      dataList.add(root);
      List<String>ids=Lists.newArrayList();
      for(UserDataLimit data:dataLimit){
         if("1".equals(data.getLev())){
            data.setParentId("0");
         }else if("2".equals(data.getLev())){//添加一级节点
            UserDataLimit oneLevel=new UserDataLimit();
            oneLevel.setId("r_"+data.getRccCode()).setName(data.getRccName()).setParentId("0");
            if(!ids.contains(data.getRccCode())){//
               dataList.add(oneLevel);
               ids.add(data.getRccCode());
            }
            data.setParentId("r_"+data.getRccCode());
         }
         dataList.add(data);
      }
      return dataList;
   }

	@Override
	public List<Map> exportExcelSQL(String userId) {
		return this.userDao.exportAccount(userId);
	}

	@Override
	public String  getListForLoginName(String loginName) {
		String name=loginName;
		 for(int i=1 ;i<100 ;i++){
			    List<User> listUser= this.userDao.getListForLoginName(name);
	        	if(listUser!=null && listUser.size()>0){
	        		name = loginName+i;
	        	}else{
	        		break;
	        	}
	        	
	        }
		
		return name;
		
	}
	
	public static void main(String[] args) {
		 byte[] salts = PassWordUtil.getSaltBytes();// 自定义加密串,目前获取8位随即
        System.out.println(PassWordUtil.getEncodeSalts(salts));
		System.out.println(PassWordUtil.getEncodePassWord("a123456", salts));
	}
	
	
}
