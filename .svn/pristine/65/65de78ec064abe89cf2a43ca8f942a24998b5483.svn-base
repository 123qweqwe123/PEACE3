/**
 * MyFormAuthenticationFilter.java Create on 2012-11-7
 * Copyright(c) Gener-Tech Inc.
 * ALL Rights Reserved.
 */
package com.bdcor.pip.web.sys.rbac.authentication;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bdcor.pip.core.utils.GenerateKey;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.core.utils.SpringContextHolder;
import com.bdcor.pip.web.sys.logmgt.dao.PipLogLoginMapper;
import com.bdcor.pip.web.sys.logmgt.domain.PipLogLogin;

/**
 * <pre>
 * 功能说明：
 * </pre>
 * 
 * @author <a href="mailto:wang.g@gener-tech.com">WangGang</a>
 * @version 1.0
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

	private Logger log=LoggerFactory.getLogger(MyFormAuthenticationFilter.class);
	
	public static final String LOGIN_TYPE = "loginType";
	public static final String KEY = "key";
	public static final String URL = "url";
	//public static final String TENANT_NAME = "tenantName";

	private String key = KEY;
	private String loginType = LOGIN_TYPE;
	private String url = URL;
	//private String tenantName = TENANT_NAME;

	public String getKey() {
		return key;
	}

	public String getLoginType() {
		return loginType;
	}

	public String getUrl() {
		return url;
	}

	/*public String getTenantName() {
		return tenantName;
	}*/

	protected String getKey(ServletRequest request) {
		return WebUtils.getCleanParam(request, getKey());
	}

	protected String getLoginType(ServletRequest request) {
		return WebUtils.getCleanParam(request, getLoginType());
	}

	protected String getUrl(ServletRequest request) {
		return WebUtils.getCleanParam(request, getUrl());
	}

	/*protected String getTenantName(ServletRequest request) {
		return WebUtils.getCleanParam(request, getTenantName());
	}*/

	protected AuthenticationToken createToken(ServletRequest request,
			ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);
		String type = getLoginType(request);
		String key = getKey(request);
		String jumpUrl = getUrl(request);
		boolean rememberMe = isRememberMe(request);
		request.setAttribute("key", key);
		String host = getHost(request); 
		/*String tenantName = getTenantName(request);*/
		return new MyAuthenticationToken(username, password, rememberMe, host,
				type, key, jumpUrl);
	}
	/**
	 * 登录成功之后仍然跳转到首页
	 */
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token,
			Subject subject, ServletRequest request, ServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return true;
	}
	/**
	 * 添加登录日志
	 */
	@Override
	 protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
		AuthenticationToken token = createToken(request, response);
        PipLogLoginMapper logLoginDao=SpringContextHolder.getBean("pipLogLoginMapper");
        PipLogLogin loginLog=new PipLogLogin();
        loginLog.setMachineIp(request.getRemoteAddr());
        loginLog.setMachineName(request.getRemoteHost());
        if(request instanceof HttpServletRequest){
        	HttpServletRequest req=(HttpServletRequest)request;//浏览器类型
        	loginLog.setUserAgent(req.getHeader("User-Agent"));
        }
        
        loginLog.setLoginTime(new Date());
        if (token == null) {
            String msg = "createToken method implementation returned null. A valid non-null AuthenticationToken " +
                    "must be created in order to execute a login attempt.";
            throw new IllegalStateException(msg);
        }
        loginLog.setUserId(String.valueOf(token.getPrincipal()));
        try {
            Subject subject = getSubject(request, response);
            loginLog.setId(new BigDecimal(GenerateKey.getKey(GenerateKey.PREFIX_PIP_LOG_LOGIN)));
            subject.login(token);
            
            loginLog.setUserId(Securitys.getUserId());	            
            loginLog.setIsSuccess("1");
            loginLog.setLoginName(Securitys.getLoginName());
            return onLoginSuccess(token, subject, request, response);
        } catch (AuthenticationException e) {
        	loginLog.setIsSuccess("2");
            return onLoginFailure(token, e, request, response);
        }finally{
        	logLoginDao.insertSelective(loginLog);
        }
    }
}
