/**
 * LoginController.java Create on 2013-6-24
 * Copyright(c) Gener-Tech Inc.
 * ALL Rights Reserved.
 */
package com.bdcor.pip.web.sys.rbac.controller;

import java.util.regex.Pattern;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam; 

import com.bdcor.pip.web.sys.rbac.authentication.MyAuthenticationToken;
import com.bdcor.pip.web.sys.rbac.authentication.MyFormAuthenticationFilter;
import com.bdcor.pip.web.sys.rbac.authentication.constant.Constants;


/**
 * <pre>
 * 功能说明：LoginController负责打开登录页面(GET请求)和登录出错页面(POST请求)
 * </pre>
 * 
 * @author <a href="mailto:wang.g@gener-tech.com">WangGang</a>
 * @version 1.0
 */
@Controller
public class LoginController {

	Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) { 
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "login";
	} 

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String fail(
			@RequestParam(MyFormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String username,
			@RequestParam(MyFormAuthenticationFilter.DEFAULT_PASSWORD_PARAM) String password,
			ServletRequest request, Model model,HttpSession session) {
		Subject subject = SecurityUtils.getSubject();
		String jumpUrl = "login";
		if (subject.isAuthenticated()) {
			try {
				// switch to another user
				MyAuthenticationToken token = new MyAuthenticationToken(
						username, password, request.getRemoteHost());
				subject.login(token);
				//正则匹配	 密码不能全为数字或者全为字母，必须大于6个字符小于16个字符，可以是字母和数字和特殊符号的任意组合
				//boolean matches = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z\\!\\#\\$\\%\\^\\&\\*\\.\\·\\@\\(\\)\\-\\~\\_]{6,30}$").matcher(password).matches();
				boolean matches = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,30}$").matcher(password).matches();
				if(!matches){
					model.addAttribute("neetAlterPassword", true);
					//这个session是用来限制用户点击后退按钮跳过验证设置的
					session.setAttribute("secret_A", "A");
				}
				else{
					session.removeAttribute("secret_A");
					model.addAttribute("neetAlterPassword", false);
				}
				
				jumpUrl = "login";
			} catch (AuthenticationException e) {
				model.addAttribute("errorMessage", e.toString());
			}
		} else {
			model.addAttribute(
					MyFormAuthenticationFilter.DEFAULT_USERNAME_PARAM, username);
			model.addAttribute("neetAlterPassword", false);
		}
		return jumpUrl;
	}
	
	/**
	 * 管理端首页
	 */
	@RequestMapping(value = "/managementIndex", method = RequestMethod.GET)
	public String managementIndex(Model model) { 
		return "managementIndex";
	} 
}
