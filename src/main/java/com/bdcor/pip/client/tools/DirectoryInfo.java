package com.bdcor.pip.client.tools;



import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bdcor.pip.client.ClientInfo;



public class DirectoryInfo {

	private static Logger log = LoggerFactory.getLogger(DirectoryInfo.class);
	
	private static DirectoryInfo ii = null;
	
	private static final String FILE_SEPARATOR = File.separator;
	
	/**  根目录 :c://pip**/
	private static String DAT_ROOT_Dir = "";
	/**  U盘根目录 :c://pip**/
	private static String U_ROOT_Dir = null;
	
	public static DirectoryInfo instance(){
		if(ii==null){
			ii = new DirectoryInfo();
		}
		return ii;
	}
	
	private DirectoryInfo(){
		//DAT_ROOT_Dir = Constants.getPropertyValue("rootDir","");
		
		String userDirStr = System.getProperty("user.dir");
		
		String confFilePath = "";
		//eclipse 下配置文件路径
		File configFileInEclipse = new File(userDirStr+File.separator+"dataPath.txt");
		if(configFileInEclipse.exists()){
			DAT_ROOT_Dir = FileUtils.readFileToString(configFileInEclipse);
		}
//		else{
//			File userDir = new File(userDirStr);
//			confFilePath = userDir.getParentFile().getParentFile().getPath()+File.separator+"conf"+File.separator+"dataPath.txt";
//			
//			DAT_ROOT_Dir = FileUtils.readFileToString(new File(confFilePath));
//		}
		
		
		
		
		
		
	}
	
	/**
	 * 当前工作目录
	 * @return
	 */
	public String userDir(){
		return  System.getProperty("user.dir");
	}
	
	/** 基础目录(程序更新目录)：C://pip/001 **/
	public String DAT_BASE_Dir(){
		String s = StringUtils.chomp(DAT_ROOT_Dir) + FILE_SEPARATOR +ClientInfo.PROJ_NO;
		FileUtils.createFoldes(s);
		return s;
	}
	/** 临时缓存目录:C://pip/001/TEMPDIR **/
	public String DAT_TEMP_Dir(){
		String s = DAT_BASE_Dir()+FILE_SEPARATOR+"TEMPDIR";
		FileUtils.createFoldes(s);
		return s;
	}
	/** 字典目录:C://pip/001/DATA/DICT **/
	public String DAT_DICT_Dir(){
		String s = DAT_BASE_Dir()+FILE_SEPARATOR+"DATA"+FILE_SEPARATOR+"DICT";
		FileUtils.createFoldes(s);
		return s;
	}
	/** 日志:C://pip/001/BIN/LOG **/
	public String DAT_LOG_Dir(){
		String s = DAT_BASE_Dir()+FILE_SEPARATOR+"BIN"+FILE_SEPARATOR+"LOG";
		FileUtils.createFoldes(s);
		return s;
	}
	/** 数据备份目录:C://pip/001/DATA/TEMPLATE **/
	public String DAT_DATABACKUP_Dir(){
		String s = DAT_BASE_Dir()+FILE_SEPARATOR+"DATA"+FILE_SEPARATOR+"DATABACKUP";
		FileUtils.createFoldes(s);
		return s;
	}
	/** 问卷目录:C://pip/001/DATA/TEMPLATE **/
	public String DAT_TEMPLATE_Dir(){
		String s = DAT_BASE_Dir()+FILE_SEPARATOR+"DATA"+FILE_SEPARATOR+"TEMPLATE";
		FileUtils.createFoldes(s);
		return s;
	}
	/** 用户数据目录:C://pip/001/DATA/USERDATA **/
	public String DAT_USERDATA_Dir(){
		String s = DAT_BASE_Dir()+FILE_SEPARATOR+"DATA"+FILE_SEPARATOR+"USERDATA";
		FileUtils.createFoldes(s);
		return s;
	}
	/** 问卷目录 :C://pip/001/DATA/USERDATA/UQS **/
	public String DAT_BUS_UQS_Dir(){
		String s = DAT_BASE_Dir()+FILE_SEPARATOR+"DATA"+FILE_SEPARATOR+
				"USERDATA"+FILE_SEPARATOR+""+
				DataInfo.CLIENT_FILE_TYPE_UQS+File.separator+
				DateUtils.currentDate();
		FileUtils.createFoldes(s);
		return s;
	}
	/** 密码目录 :C://pip/001/DATA/USERDATA/PWD **/
	public String DAT_BUS_PWD_Dir(){
		String s = DAT_BASE_Dir()+FILE_SEPARATOR+
				"DATA"+FILE_SEPARATOR+
				"USERDATA"+FILE_SEPARATOR+""+
				DataInfo.CLIENT_FILE_TYPE_PWD+File.separator+
				DateUtils.currentDate();
		FileUtils.createFoldes(s);
		return s;
	}
	
	/** 身份证目录 :C://pip/001/DATA/USERDATA/IDCORD **/
	public String DAT_BUS_IDCORD_Dir(){
		String s = DAT_BASE_Dir()+FILE_SEPARATOR+
				"DATA"+FILE_SEPARATOR+
				"USERDATA"+FILE_SEPARATOR+""+
				DataInfo.CLIENT_FILE_TYPE_IDCORDS+File.separator+
				DateUtils.currentDate();
		FileUtils.createFoldes(s);
		return s;
	}
	
	
	/** 字典目录：C://pip/001/dict **/
	public String U_DICT_DATA_Dir(){
		String s = getURootDir() + FILE_SEPARATOR +ClientInfo.PROJ_NO + FILE_SEPARATOR + "DICT";
		FileUtils.createFoldes(s);
		return s;
	}
	/** 字典目录：C://pip/001/userdata **/
	public String U_USER_DATA_Dir(){
		String s = getURootDir() + FILE_SEPARATOR +ClientInfo.PROJ_NO + FILE_SEPARATOR + "USERDATA";
		FileUtils.createFoldes(s);
		return s;
	}
	/** 时间文件存放目录：C://pip/001 **/
	public String U_TIME_FILE_Dir(){
		String s = getURootDir() + FILE_SEPARATOR +ClientInfo.PROJ_NO;
		FileUtils.createFoldes(s);
		return s;
	}
	/** 字典目录：C://pip/001/terminal **/
	public String U_TERMINAL_INFO_Dir(){
		String s = getURootDir() + FILE_SEPARATOR +ClientInfo.PROJ_NO + FILE_SEPARATOR + "TEMINAL";
		FileUtils.createFoldes(s);
		return s;
	}
	
	/** u盘根目录 **/
	public String getURootDir(){
		try {
			U_ROOT_Dir = FindUDisk.findUPath()+File.separator+"PIP";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return U_ROOT_Dir;
	}
	/*
	public static void setURootDir(String s){
		U_ROOT_Dir = s;
	}
	*/
}
