package com.bdcor.pip.client.tools;


import java.util.HashMap;
import java.util.Map;


public class DataInfo {
	
	/** lcc编号  **/
	public static int SEND_DOWN_LCC = 4;
	/** 大夫编号 **/
	public static int SEND_DOWN_DOCTOR = 3;
	/** 受访者编号 **/
	public static int SEND_DOWN_INTER = 2;
	/** 通用字典编号 **/
	public static int SEND_DOWN_DICT = 1;
	/** 程序编号 **/
	public static int SEND_DOWN_PROCE = 5;
	/** 问卷编号 **/
	public static int SEND_DOWN_PAGER = 6;
	
	public static String[] DName = {"通用字典","受访者","用户帐户信息","lcc信息","程序","问卷"};
	
	
	/** 受访者全量包编号 **/
	public static int SEND_DOWN_INTER_ALL = 201;
	public static String[] DNameOfChild = {"受访者全量包"};

	/**
	 * 同步数据名称
	 * @param type
	 * @return
	 */ 
	public static String downloadTypeName(int type){
		if(type>10){
//			String tempType = (type+"").substring(0, 1);
//			type = Integer.parseInt(tempType);
			if(type==SEND_DOWN_INTER_ALL){
				return DNameOfChild[0];
			}
		}
		return DName[type-1];
	}
	
	
	//-------------------------------------------------------------------
	
	
	//客户端文件类别
	/** 答卷 **/
	public static String CLIENT_FILE_TYPE_UQS = "UQS";
	/** 样本 **/
	public static String CLIENT_FILE_TYPE_SCM = "SCM";
	/** 受访者 **/
	public static String CLIENT_FILE_TYPE_PATIENT = "PATIENT";
	/** 身份证信息 **/
	public static String CLIENT_FILE_TYPE_IDCORDS = "IDCORDS";
	/** 终端信息 **/
	public static String CLIENT_FILE_TYPE_TERMINAL = "TERMINAL";
	/** 日志  **/
	public static String CLIENT_FILE_TYPE_LOG = "LOG";
	/** 密码修改  **/
	public static String CLIENT_FILE_TYPE_PWD = "PWD";
	
	/** 所有业务数据 **/
	public static String CLIENT_FILE_TYPE_ALL_BUSDATA = "ALLBUS";
	
	
	private static Map<String,String> typeAndNameMap = new HashMap<String,String>();
	static{
		typeAndNameMap.put(CLIENT_FILE_TYPE_UQS, "答卷");
		typeAndNameMap.put(CLIENT_FILE_TYPE_SCM, "样本");
		typeAndNameMap.put(CLIENT_FILE_TYPE_PATIENT, "受访者");
		typeAndNameMap.put(CLIENT_FILE_TYPE_IDCORDS, "身份证");
		typeAndNameMap.put(CLIENT_FILE_TYPE_TERMINAL, "终端信息");
		typeAndNameMap.put(CLIENT_FILE_TYPE_LOG, "日志");
		typeAndNameMap.put(CLIENT_FILE_TYPE_ALL_BUSDATA, "业务数据");
		typeAndNameMap.put(CLIENT_FILE_TYPE_PWD, "密码");
	}
	
	public static String busDataTypeName(String type){
		String typeName = typeAndNameMap.get(type);
		return typeName==null?"未知":typeName;
	}
	
	
	/**上传文件类型：受访者 **/
	public static String UPLOAD_FILE_TYPE_PATIENT = "1";
	/**上传文件类型：答卷 **/
	public static String UPLOAD_FILE_TYPE_PAPER = "2";
	/**上传文件类型：样本数据 **/
	public static String UPLOAD_FILE_TYPE_SCM = "3";
	/**上传文件类型：未登记人员 **/
	public static String UPLOAD_FILE_TYPE_NOCHECKIN = "4";
	/**上传文件类型：终端信息 **/
	public static String UPLOAD_FILE_TYPE_TERMINAL = "5";
	/**上传文件类型：日志 **/
	public static String UPLOAD_FILE_TYPE_LOG = "6";
	/**上传文件类型：密码修改 **/
	public static String UPLOAD_FILE_TYPE_PWD = "7";
	
	
	

	/**
	 * 
	 * @param uType
	 * @return
	 */
	public static String uploadFileType(String uType){
		if(uType.equals(DataInfo.CLIENT_FILE_TYPE_IDCORDS)){
			return UPLOAD_FILE_TYPE_PATIENT;
		}else if(uType.equals(DataInfo.CLIENT_FILE_TYPE_PATIENT)){
			return UPLOAD_FILE_TYPE_PATIENT;
		}else if(uType.equals(DataInfo.CLIENT_FILE_TYPE_SCM)){
			return UPLOAD_FILE_TYPE_SCM;
		}else if(uType.equals(DataInfo.CLIENT_FILE_TYPE_UQS)){
			return UPLOAD_FILE_TYPE_PAPER;
		}else if(uType.equals(DataInfo.CLIENT_FILE_TYPE_TERMINAL)){
			return UPLOAD_FILE_TYPE_TERMINAL;
		}else if(uType.equals(DataInfo.CLIENT_FILE_TYPE_LOG)){
			return UPLOAD_FILE_TYPE_LOG;
		}else if(uType.equals(DataInfo.CLIENT_FILE_TYPE_PWD)){
			return UPLOAD_FILE_TYPE_PWD;
		}
		return "";
	}
}
