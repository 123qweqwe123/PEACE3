package com.bdcor.pip.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;


public class ClientInfo {
	public static String PROJ_NO = null;
	/** lcc 编号 **/
	public static String LCC_NO = null;
	/** 终端 编号 **/
	public static String MACHINE_NO = null;
	/** 终端 名称 **/
	public static String MACHINE_NAME = null;
	/** 终端 名称  在页面中显示使用 **/
	public static String MACHINE_NAME_SHOW = null;
	
	/** 终端信息设置时间 **/
	public static Date SETEP_DATE = null;

	/** 服务模型， 0：有局域网 1：无局域网 **/
	public static int SYS_MODEL = 1;
	/** 客户端角色， 0：all 1：master 2：worker **/
	public static int SYS_CLIENT_TYPE = 0;
	/** 是否从服务器自动更新数据 **/
	public static boolean SYS_AUTO_UPDATE = true;
	/** 系统语言 **/
	public static String SYS_LANGUAGE_TYPE = "zh";
	public static String SYS_COUNTRY_TYPE = "CN";
	/** 资源文件名 **/
	public static String SYS_MESSAGE_PFILE_NAME = "resources.ResourceBundleMessage";
	
	public static String PAPERLIMIT;
	

	
	/**
	 * 
	 * @return
	 */
	public static String getClientInfo() {
		return PROJ_NO + "_" + LCC_NO + "_" + MACHINE_NO;
	}
	
	
	/**
	 * 是否为master机器
	 * @return
	 */
	public static boolean isMarster(){
		return ClientInfo.MACHINE_NAME.contains("_M");
		
	}
	
	
}
