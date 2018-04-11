package com.bdcor.pip.client.tools;



import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bdcor.pip.client.ClientInfo;
import com.bdcor.pip.core.utils.CustomizedPropertyPlaceholderConfigurer;

/**
 * 
 * 静态变量
 * @author Administrator
 *
 */
public class Constants {
	
	

	/**
	 * 
	 * @param key
	 * @return
	 */
	public static String getPropertyValue(String key){
		String value = CustomizedPropertyPlaceholderConfigurer.getContextProperty(key);
		if(value==null || (value!=null && "".equals(value))){
			return null;
		}
		return value;
	}
	
	/**
	 * <p>先在application-*.property中查找，未找到在default-all.properties中查找；
	 * <p>多没有找到使用 @defaultValue
	 * @param key
	 * @return
	 */
	public static String getPropertyValue(String key,String defaultValue){
		String value = CustomizedPropertyPlaceholderConfigurer.getContextProperty(key);
		if(value==null || (value!=null && "".equals(value))){
			return getDefaultPropertyValue(key,defaultValue);
		}
		return value;
	}
	
	
	
	private static String getDefaultPropertyValue(String key,String defaultValue){
		String value = DefaultConfigurer.getContextProperty(key);
		if(value==null || (value!=null && "".equals(value))){
			return defaultValue;
		}
		return value;
	}
	
	/**
	 * 
	 * @param key    配置文件的key值
	 * @param mustHave  是否必须设置，
	 *                 必须设置但未设置的抛出异常
	 * @return
	 */
	public static String getPropertyValue(String key,boolean mustHave){
		
		String initValue = CustomizedPropertyPlaceholderConfigurer.getContextProperty(key);
		if(mustHave){
			if(initValue==null || "".equals(initValue)){
				throw new DatException(key+"值未在配置文件中定义");
			}
		}
		return initValue;
	}
	
	/** 根目录 **/
	public static String ROOT_DIR = null;
	/** 文件的基础目录 **/
	public static String BASE_DIR = null;
	
	
	//客户端文件类别
	/** 答卷 **/
	public static String CLIENT_FILE_TYPE_UQS = null;
	/** 样本 **/
	public static String CLIENT_FILE_TYPE_SCM = null;
	/** 心电图 **/
	public static String CLIENT_FILE_TYPE_ECG = null;
	/** 超声图 **/
	public static String CLIENT_FILE_TYPE_ECH = null;
	/** 指血**/
	public static String CLIENT_FILE_TYPE_FGB = null;
	/** 颈动脉超声图 **/
	public static String CLIENT_FILE_TYPE_CDU = null;
	/** 骨密度 **/
	public static String CLIENT_FILE_TYPE_BND = null;
	/** 错误文件 **/
	public static String CLIENT_FILE_TYPE_ERROR = null;
	/** 受访者 **/
	public static String CLIENT_FILE_TYPE_PATIENT = null;
	/** 身份证信息 **/
	public static String CLIENT_FILE_TYPE_IDCORDS = null;
	
	
	/**  数据库目录  **/
	public static String DB_FILEPATH = null;
	/** 数据库ddl文件 **/
	public static String DB_DDL_FILE = null;
	/** 待更新ddl文件目录 **/
	public static String DB_DDL_FILE_NOUPDATE = null;
	
	/** 程序目录 **/
	public static String PROCE_DIR = null;
	

	
	
	/**=============================================服务器端文件夹定义   **/                                     
	/** 服务器端答卷保存文件夹 **/
	public static String SERVER_UQS_FLODER_NAME = null;
	/** 服务器端样本保存文件夹 **/
	public static String SERVER_SAM_FLODER_NAME = null;
	/** 服务器端心电图保存文件夹 **/
	public static String SERVER_ECG_FLODER_NAME = null;
	/** 服务器端超声图保存文件夹 **/
	public static String SERVER_ECH_FLODER_NAME = null;
	/** 服务器端指血保存文件夹 **/
	public static String SERVER_FGB_FLODER_NAME = null;
	/** 服务器端颈动脉超声图保存文件夹 **/
	public static String SERVER_CDU_FLODER_NAME = null;
	/** 服务器端骨密度保存文件夹 **/
	public static String SERVER_BND_FLODER_NAME = null;
	
	/** 文件类型和文件名称的对应 **/
	private static Map<String,String> fileTypeNameMaping = new HashMap<String,String>(); 
	
	/**
	 * 类型对应名称
	 * @param type
	 * @return
	 */
	public static String getTypeName(String type){
		return fileTypeNameMaping.get(type);
	}
	/**
	 * 所有类型的业务类型
	 * @return
	 */
	public static List<String> allBusType(){
		List<String> l = new ArrayList<String>();
		l.add(CLIENT_FILE_TYPE_UQS);
		l.add(CLIENT_FILE_TYPE_SCM);
		l.add(CLIENT_FILE_TYPE_ECG);
		l.add(CLIENT_FILE_TYPE_ECH);
		l.add(CLIENT_FILE_TYPE_FGB);
		l.add(CLIENT_FILE_TYPE_CDU);
		l.add(CLIENT_FILE_TYPE_BND);
		l.add(CLIENT_FILE_TYPE_ERROR);
		l.add(CLIENT_FILE_TYPE_PATIENT);
		l.add(CLIENT_FILE_TYPE_IDCORDS);
		return l;
	}

	/**
	 * 初始化 xml和日志的存放目录
	 */
	public static void initFolder(){

		
		/** 根目录 **/
		ROOT_DIR = getPropertyValue("rootDir");
		//
		File f = new File(FileUtils.classPath());
		String fPath = new File(f.getParent()).getParent()+"\\dataPath.txt";
		if(new File(fPath).exists()){
			ROOT_DIR = FileUtils.readLines(new File(fPath)).get(0);
		}
		
		/** 文件的基础目录 **/
		BASE_DIR = ROOT_DIR + ClientInfo.PROJ_NO+"/";
		
		/** 字典目录 **/
		CFileInfo.DICT_DIR = BASE_DIR + getPropertyValue("dat_data_client_dictFloder");
		/** 模板目录 **/
		CFileInfo.MODEL_DIR = BASE_DIR + getPropertyValue("dat_data_client_examModelFloder");
		/** 结果目录 **/
		CFileInfo.RESULT_DIR = BASE_DIR + getPropertyValue("dat_data_client_resultFloder");
		/** 答卷目录 **/
		CFileInfo.RESULT_UQS_DIR = BASE_DIR + getPropertyValue("dat_data_client_uqsResultFloder");
		/** 样本目录 **/
		CFileInfo.RESULT_SCM_DIR = BASE_DIR + getPropertyValue("dat_data_client_scmResultFloder");
		/** 心电图目录 **/
		CFileInfo.RESULT_ECG_DIR = BASE_DIR + getPropertyValue("dat_data_client_ecgResultFloder");
		/** 超声图目录 **/
		CFileInfo.RESULT_ECH_DIR = BASE_DIR + getPropertyValue("dat_data_client_echResultFloder");
		/** 指血文件目录 **/
		CFileInfo.RESULT_FGB_DIR = BASE_DIR + getPropertyValue("dat_data_client_fgbResultFloder");
		/** 颈动脉超声目录 **/
		CFileInfo.RESULT_CDU_DIR = BASE_DIR + getPropertyValue("dat_data_client_cduResultFloder");
		/** 骨密度目录 **/
		CFileInfo.RESULT_BND_DIR = BASE_DIR + getPropertyValue("dat_data_client_bndResultFloder");
		/** 身份证信息xml文件 **/
		CFileInfo.RESULT_IDCORDS_DIR = BASE_DIR + getPropertyValue("dat_data_client_idcordsFloder");
		/** 受访者 **/
		CFileInfo.RESULT_PAT_DIR = BASE_DIR + getPropertyValue("dat_data_client_patientResultFloder");
		/** 错误文件 **/
		CFileInfo.RESULT_ERROR_DIR = BASE_DIR + getPropertyValue("dat_data_client_errorResultFloder");
		/** 数据备份目录，在数据导出或上传时，备份 **/
		CFileInfo.DATABACKUP_DIR = BASE_DIR + getPropertyValue("dat_data_client_dataBackupFloder");
		
		//客户端文件类别
		/** 答卷 **/
		CLIENT_FILE_TYPE_UQS = getPropertyValue("dat_data_client_fileType_uqs");
		/** 样本 **/
		CLIENT_FILE_TYPE_SCM = getPropertyValue("dat_data_client_fileType_scm");
		/** 心电图 **/
		CLIENT_FILE_TYPE_ECG = getPropertyValue("dat_data_client_fileType_ecg");
		/** 超声图 **/
		CLIENT_FILE_TYPE_ECH = getPropertyValue("dat_data_client_fileType_ech");
		/** 指血**/
		CLIENT_FILE_TYPE_FGB = getPropertyValue("dat_data_client_fileType_fgb");
		/** 颈动脉超声图 **/
		CLIENT_FILE_TYPE_CDU = getPropertyValue("dat_data_client_fileType_cdu");
		/** 骨密度 **/
		CLIENT_FILE_TYPE_BND = getPropertyValue("dat_data_client_fileType_bnd");
		/** 错误文件 **/
		CLIENT_FILE_TYPE_ERROR = getPropertyValue("dat_data_client_fileType_error");
		/** 受访者 **/
		CLIENT_FILE_TYPE_PATIENT = getPropertyValue("dat_data_client_fileType_patient");
		/** 身份证信息 **/
		CLIENT_FILE_TYPE_IDCORDS = getPropertyValue("dat_data_client_fileType_idcords");

		
		fileTypeNameMaping.put(CLIENT_FILE_TYPE_UQS, "答卷");
		fileTypeNameMaping.put(CLIENT_FILE_TYPE_SCM, "样本");
		fileTypeNameMaping.put(CLIENT_FILE_TYPE_ECG, "心电图");
		fileTypeNameMaping.put(CLIENT_FILE_TYPE_ECH, "超声图");
		fileTypeNameMaping.put(CLIENT_FILE_TYPE_FGB, "指血");
		fileTypeNameMaping.put(CLIENT_FILE_TYPE_CDU, "颈动脉超声图");
		fileTypeNameMaping.put(CLIENT_FILE_TYPE_BND, "骨密度");
		fileTypeNameMaping.put(CLIENT_FILE_TYPE_PATIENT, "受访者");
		fileTypeNameMaping.put(CLIENT_FILE_TYPE_ERROR, "错误文件");
		fileTypeNameMaping.put(CLIENT_FILE_TYPE_IDCORDS, "身份证信息保存");

		/** 程序目录 **/
		PROCE_DIR = BASE_DIR + getPropertyValue("procedureFloder");
		
		/** 数据库目录 **/
		DB_FILEPATH = PROCE_DIR + getPropertyValue("dat_db_filePath");
		/** 数据库ddl文件  **/
		DB_DDL_FILE = PROCE_DIR + getPropertyValue("dat_db_ddl_UpdateFilePath");
		/** 待更新ddl文件目录  **/
		DB_DDL_FILE_NOUPDATE = PROCE_DIR + getPropertyValue("dat_db_ddl_NoUpdateFilePath");
		
		
		/** 已经上传的xml 压缩包 **/
		CFileInfo.UPLOAD_XML_DIR = BASE_DIR + getPropertyValue("dat_data_client_uploadxmlFloder");
		
		
		/**=============================================服务器端文件夹定义   **/                                     
		/** 服务器端答卷保存文件夹 **/
		SERVER_UQS_FLODER_NAME = getPropertyValue("dat_data_server_uqsFloderName");
		/** 服务器端样本保存文件夹 **/
		SERVER_SAM_FLODER_NAME = getPropertyValue("dat_data_server_scmFloderName");
		/** 服务器端心电图保存文件夹 **/
		SERVER_ECG_FLODER_NAME = getPropertyValue("dat_data_server_ecgFloderName");
		/** 服务器端心脏超声图保存文件夹 **/
		SERVER_ECH_FLODER_NAME = getPropertyValue("dat_data_server_echFloderName");
		/** 服务器端指血保存文件夹 **/
		SERVER_FGB_FLODER_NAME = getPropertyValue("dat_data_server_fgbFloderName");
		/** 服务器端颈动脉超声图保存文件夹 **/
		SERVER_CDU_FLODER_NAME = getPropertyValue("dat_data_server_cduFloderName");
		/** 服务器端骨密度保存文件夹 **/
		SERVER_BND_FLODER_NAME = getPropertyValue("dat_data_server_bndFloderName");

		/**=============================================U盘中文件夹   **/
		/** 标记文件**/
//		FindUDisk.U_SIGN_FNAME = getPropertyValue("dat_client_u_sign");
		String modelFilePath = getPropertyValue("dat_client_u_dataRoot");
		String rootFilePath = modelFilePath.replace("#projId#", ClientInfo.PROJ_NO);
		
		/** 答卷 **/
		UFileInfo.U_FILE_UQS = rootFilePath+CLIENT_FILE_TYPE_UQS+"/";
		/** 样本 **/
		UFileInfo.U_FILE_SCM = rootFilePath+CLIENT_FILE_TYPE_SCM+"/";
		/** 心电图 **/
		UFileInfo.U_FILE_ECG = rootFilePath+CLIENT_FILE_TYPE_ECG+"/";
		/** 超声图 **/
		UFileInfo.U_FILE_ECH = rootFilePath+CLIENT_FILE_TYPE_ECH+"/";
		/** 指血**/
		UFileInfo.U_FILE_FGB = rootFilePath+CLIENT_FILE_TYPE_FGB+"/";
		/** 颈动脉超声图 **/
		UFileInfo.U_FILE_CDU = rootFilePath+CLIENT_FILE_TYPE_CDU+"/";
		/** 骨密度 **/
		UFileInfo.U_FILE_BND = rootFilePath+CLIENT_FILE_TYPE_BND+"/";
		/** 受访者 **/
		UFileInfo.U_FILE_PAT = rootFilePath+CLIENT_FILE_TYPE_PATIENT+"/";
		/** 错误文件 **/
		UFileInfo.U_FILE_ERROR = rootFilePath+CLIENT_FILE_TYPE_ERROR+"/";
		/** 身份证 **/
		UFileInfo.U_FILE_IDCORDS = rootFilePath+CLIENT_FILE_TYPE_IDCORDS+"/";
		
		
		/** 程序 **/
		UFileInfo.U_PROGRAM = getPropertyValue("dat_client_u_programFloder").replace("#projId#", ClientInfo.PROJ_NO);
		/** 字典 **/
		UFileInfo.U_DICT = getPropertyValue("dat_client_u_dictFloder").replace("#projId#", ClientInfo.PROJ_NO);
		/** 问卷 **/
		UFileInfo.U_QUEST = getPropertyValue("dat_client_u_examModelFloder").replace("#projId#", ClientInfo.PROJ_NO);
		
		
		/**============================================系统属性  **/
	
		
		//DatLogger.logSysStartDown(Constants.class,"开始初始化日志和xml文件的存放文件夹==========================");
		Map<String,String> initfloder = new HashMap<String,String>();
		initfloder.put(CFileInfo.DICT_DIR, "字典");
		initfloder.put(CFileInfo.MODEL_DIR, "试卷模板");
		initfloder.put(CFileInfo.RESULT_DIR, "结果");
		initfloder.put(CFileInfo.RESULT_UQS_DIR, "答卷");
		initfloder.put(CFileInfo.RESULT_SCM_DIR, "样本");
		initfloder.put(CFileInfo.RESULT_ECG_DIR, "心电图");
		initfloder.put(CFileInfo.RESULT_ECH_DIR, "超声图");
		initfloder.put(CFileInfo.RESULT_FGB_DIR, "指血");
		initfloder.put(CFileInfo.RESULT_CDU_DIR, "颈动脉超声");
		initfloder.put(CFileInfo.RESULT_BND_DIR, "密度");
		initfloder.put(CFileInfo.RESULT_PAT_DIR, "受访者");
		initfloder.put(CFileInfo.RESULT_ERROR_DIR, "错误文件");
		initfloder.put(CFileInfo.RESULT_IDCORDS_DIR, "身份证信息xml");
		initfloder.put(CFileInfo.UPLOAD_XML_DIR, "xml文件压缩包");
		initfloder.put(CFileInfo.DATABACKUP_DIR, "数据备份");
		initfloder.put(DB_FILEPATH, "数据库目录");
		initfloder.put(DB_DDL_FILE, "数据库ddl文件");
		initfloder.put(DB_DDL_FILE_NOUPDATE, "待更新ddl文件目录 ");
		
		for(String dirName:initfloder.keySet()){
			String fileName = initfloder.get(dirName);
			if(!new File(dirName).exists()){
				new File(dirName).mkdirs();
				//DatLogger.logSysStartDown(Constants.class,"成功初始化"+fileName+"文件夹");
			}
		}
		
		//DatLogger.logSysStartDown(Constants.class,"完成初始化日志和xml文件的存放文件夹==========================");
	}
	
}
