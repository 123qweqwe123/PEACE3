package com.bdcor.pip.client.tools;



import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Field;

/**
 * U盘中 文件夹
 * @author Administrator
 *
 */
public class UFileInfo extends LogHandler{
	//U盘文件夹
	/** 程序 **/
	public static String U_PROGRAM = "PIP/#projId#/BIN/";
	/** 字典 **/
	public static String U_DICT = "PIP/#projId#/DATA/DICT/";
	/** 问卷 **/
	public static String U_QUEST = "PIP/#projId#/DATA/TEMPLATE/";
	
	/** 答卷 **/
	public static String U_FILE_UQS = "/PIP/ProjectID/DATA/USERDATA/UQS";
	/** 样本 **/
	public static String U_FILE_SCM = "/PIP/ProjectID/DATA/USERDATA/SAM";
	/** 心电图 **/
	public static String U_FILE_ECG = "/PIP/ProjectID/DATA/USERDATA/ECG";
	/** 超声图 **/
	public static String U_FILE_ECH = "/PIP/ProjectID/DATA/USERDATA/ECH";
	/** 指血**/
	public static String U_FILE_FGB = "/PIP/ProjectID/DATA/USERDATA/FGB";
	/** 颈动脉超声图 **/
	public static String U_FILE_CDU = "/PIP/ProjectID/DATA/USERDATA/CDU";
	/** 骨密度 **/
	public static String U_FILE_BND = "/PIP/ProjectID/DATA/USERDATA/BND";
	/** 错误文件 **/ 
	public static String U_FILE_ERROR = "/PIP/ProjectID/DATA/USERDATA/ERROR";
	/** 受访者 **/
	public static String U_FILE_PAT = "/PIP/ProjectID/DATA/USERDATA/PAT";
	/** 身份证信息保存目录 **/
	public static String U_FILE_IDCORDS = "/PIP/ProjectID/DATA/USERDATA/IDCORDS";
	
	


	/** 业务数据日志文件名称   **/
	public static String LOGFILE_NAME = "log.txt";
	
	/** 数据上传回执日志   **/
	public static String RECEIPT_LOGFILE_NAME = "receipt_log.txt";
	
	/** 非业务数据日志文件名称 **/
	public static String VERSION_FILE_NAME = "uVersion.txt";
	
	//====================================================文件操作
	
	/**
	 * 版本文件路径
	 * @return
	 */
	public static String getVersionFilePath(){
		return new File(DirectoryInfo.instance().U_DICT_DATA_Dir()+U_FILE_UQS).getParent();
	}
	/**
	 * 业务文件
	 * @param fileType
	 * @return
	 */
	public static File[] businessFiles(String fileType){
		File[] files = businessParentFile(fileType).listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				if(pathname.getName().contains(".zip")){
					return true;
				}
				return false;
			}
		});
		
		if(files==null){
			throw new RuntimeException("路径：“"+businessParentFile(fileType).getPath()+"”读写错误。请确保此路径可读写。");
		}
		
		return files;
	}
	
	
	/**
	 * u盘中指定的文件类型的文件
	 * @param fileType 文件类型
	 * @return
	 */
	public static File businessParentFile(String fileType){
		String filePath = businessParentFilePath(fileType);
		
		File f = null;
		if(filePath!=null){
			f = new File(filePath);
			if(!f.exists()){
				boolean createSuc = f.mkdirs();
				if(!createSuc){
					throw new DatException("在U盘中创建文件夹失败："+filePath);
				}
			}
		}
		return f;
	}
	
	/**
	 * u盘中指定的文件类型的文件路径
	 * @param fileType 文件类型
	 * @return
	 */
	public static String businessParentFilePath(String fileType){
		String filePath = null;
		try {
			Field field = UFileInfo.class.getField("U_FILE_"+fileType.toUpperCase());
//			filePath =  FindUDisk.searchSignFile()+field.get(null).toString();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		File f = new File(filePath);
		if(!f.exists()){
			f.mkdirs();
		}
//		DatLogger.logSysCommun(UFileInfo.class, "查找到的U盘业务数据路径："+filePath);
		return filePath;
	}
	
	
	
	
}
