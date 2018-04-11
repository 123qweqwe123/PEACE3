/**
 * MyAuthenticationTokenImpl.java Create on 2012-11-7
 * Copyright(c) Gener-Tech Inc.
 * ALL Rights Reserved.
 */
package com.bdcor.pip.web.sys.rbac.authentication;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * <pre>
 * 功能说明：
 * </pre>
 * 
 * @author <a href="mailto:wang.g@gener-tech.com">WangGang</a>
 * @version 1.0
 */
@SuppressWarnings("serial")
public class MyAuthenticationToken extends UsernamePasswordToken {

	private String loginType;
	private String key;
	private String url;
	//private String tenantName;

	public MyAuthenticationToken() {
	};

	public MyAuthenticationToken(final String username, final char[] password) {
		super(username, password, false, null);
	}

	public MyAuthenticationToken(final String username, final String password) {
		super(username, password, false, null);
	}

	public MyAuthenticationToken(final String username, final char[] password,
			final String host) {
		super(username, password, false, host);
	}

	public MyAuthenticationToken(final String username, final String password,
			final String host) {
		super(username, password, false, host);
	}

	/*public MyAuthenticationToken(final String username, final char[] password,
			final String host, String tenantName) {
		super(username, password, false, host);
		this.tenantName = tenantName;
	}

	public MyAuthenticationToken(final String username, final String password,
			final String host, String tenantName) {
		super(username, password, false, host);
		this.tenantName = tenantName;
	}*/

	public MyAuthenticationToken(final String username, final char[] password,
			final boolean rememberMe) {
		super(username, password, rememberMe, null);
	}

	public MyAuthenticationToken(final String username, final String password,
			final boolean rememberMe) {
		super(username, password, rememberMe, null);
	}

	/*public MyAuthenticationToken(final String username, final char[] password,
			final boolean rememberMe, final String tenantName) {
		super(username, password, rememberMe, null);
		this.tenantName = tenantName;
	}

	public MyAuthenticationToken(final String username, final String password,
			final boolean rememberMe, final String tenantName) {
		super(username, password, rememberMe, null);
		this.tenantName = tenantName;
	}*/

	public MyAuthenticationToken(final String username, final char[] password,
			final boolean rememberMe, final String host,
			final String loginType, final String key, final String url) {
		super(username, password, rememberMe, host);
		this.key = key;
		this.loginType = loginType;
		this.url = url;
	}

	public MyAuthenticationToken(final String username, final String password,
			final boolean rememberMe, final String host,
			final String loginType, final String key, final String url) {
		super(username, password, rememberMe, host);
		this.key = key;
		this.loginType = loginType;
		this.url = url;
	}

	/*public MyAuthenticationToken(final String username, final char[] password,
			final boolean rememberMe, final String host,
			final String loginType, final String key, final String url,
			String tenantName) {
		super(username, password, rememberMe, host);
		this.key = key;
		this.loginType = loginType;
		this.url = url;
		this.tenantName = tenantName;
	}

	public MyAuthenticationToken(final String username, final String password,
			final boolean rememberMe, final String host,
			final String loginType, final String key, final String url,
			String tenantName) {
		super(username, password, rememberMe, host);
		this.key = key;
		this.loginType = loginType;
		this.url = url;
		this.tenantName = tenantName;
	}
*/
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public void setKey(String key) {
		this.key = key;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.genertech.sahm.common.shiro.MyAuthenticationToken#getLoginType()
	 */
	public String getLoginType() {
		return this.loginType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.genertech.sahm.common.shiro.MyAuthenticationToken#getKey()
	 */
	public String getKey() {
		return this.key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void clear() {
		super.clear();
		this.key = null;
		this.loginType = null;
		this.url = null;
		//this.tenantName = null;
	}

	/*public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}*/

}
