package com.bdcor.pip.web.sys.rbac.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.sys.rbac.domain.LccAccount;
import com.bdcor.pip.web.sys.rbac.domain.User;
import com.bdcor.pip.web.sys.rbac.domain.UserDTO;
import com.bdcor.pip.web.sys.rbac.domain.UserDataLimit;
import com.bdcor.pip.web.sys.rbac.domain.UserRoleDTO;
import com.bdcor.pip.web.sys.rbac.filter.UserFilter;

@MyBatisRepository
public interface UserDao{
    public User queryById(String id);

    public User queryByLoginName(@Param("loginName") String loginName);

    public List<User> queryUsersByLoginName(@Param("loginName") String loginName);
    
    public List<User> getListForLoginName(@Param("loginName") String loginName);
    
    List<UserDTO> list(UserFilter filter);

    public void save(User user);

    public void update(User user);
    
    void delete(String [] ids);

    public void saveUserRole(List<UserRoleDTO> urList);
    
    void deleteUserRole(String userId);
    
    void deleteUserRoles(String[] ids);
    /**
     * 新增账户和lcc关联信息
     * @param userLcc
     */
    public void saveLccAccount(LccAccount userLcc);
    /**
     * 修改账户和lcc关联信息
     * @param userLcc
     */
    public void updateLccAccount(LccAccount userLcc);
    /**
     * 删除账户和lcc关联信息
     * @param ids
     */
    public void deleteLccAccount(String[]ids);
    /**
     * 根据部门或公司获取人员
     * @param organId
     * @return
     */
    List<UserDTO> getUsersByOrganId(@Param("organId") String organId);

    List<UserDataLimit> getLccListBuUserId(UserDataLimit dataLimit);

    public List<Map<String, String>> getDataLimitByUserId(@Param("userId")String userId);
    
	public void deleteDataLimitByUserId(@Param("userId")String userId);
	
	public List<Map> exportAccount(@Param("userId")String userId);

	public List<UserDataLimit> getDataLimitTree(String userId);
}
