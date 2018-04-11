package com.bdcor.pip.client.tools;



import java.util.Date;
/**
 * 文件导入和导出日志，<br>
 * 前四项必须设置，fVersion没有值时，设置为Log.NO_VERSION_FLAG
 * </br>
 * </br>
 * <li>u盘和客户端中记录字典或者程序包的版本号</li>
 * <li>记录work机下载到u盘中的业务数据的日志</li>
 * 
 * 
 * 
 * 
 * 
 * @author Administrator
 *
 */
public class VersionLog extends Log{
	/** lcc编号  **/
	public static int LCC = 4;
	/** 大夫编号 **/
	public static int DOCTOR = 3;
	/** 受访者编号 **/
	public static int INTER = 2;
	/** 通用字典编号 **/
	public static int DICT = 1;
	/** 程序编号 **/
	public static int PROCE = 5;
	
	public static String[] DName = {"通用字典","受访者","用户帐户信息","lcc信息","程序","问卷"};
	
	
	/** 受访者全量包编号 **/
	public static int INTER_ALL = 201;
	public static String[] DNameOfChild = {"受访者全量包"};

	/**
	 * 同步数据名称
	 * @param type
	 * @return
	 */ 
	public static String getTypeName(int type){
		if(type>10){
//			String tempType = (type+"").substring(0, 1);
//			type = Integer.parseInt(tempType);
			if(type==INTER_ALL){
				return DNameOfChild[0];
			}
		}
		return DName[type-1];
	}
	
	
	public static String NO_VERSION_FLAG = "--";
	private String fileType = "bus";
	private String fName;
	private String fVersion;
	private Date inTime;
	private String inMachine;
	
	private Date outTime;
	private String outMachine;
	
	public static VersionLog newLog(String fName,String fVersion,String inMachine){
		VersionLog log = new VersionLog();
		log.fName = fName;
		log.inMachine = inMachine;
		if(fVersion==null){
			log.fVersion = NO_VERSION_FLAG;
		}
		return log;
	}
	
	public VersionLog(){}
	
	public VersionLog(VersionLog log){
		this.fileType = log.getFileType();
		this.fName = log.getfName();
		this.fVersion = log.getfVersion();
		this.inTime = log.getInTime();
		this.inMachine = log.getInMachine();
		
		this.outTime = log.getOutTime();
		this.outMachine = log.getOutMachine();
		
	}
	
	public VersionLog(String fName,String fVersion,String inMachine){
		this.fName = fName;
		this.inMachine = inMachine;
		this.inTime = new Date();
		if(fVersion==null){
			this.fVersion = NO_VERSION_FLAG;
		}
	}
	
	public VersionLog(String line){
		//System.out.println(line);
		String[] splitStr = line.split(SPLIT);
		this.fileType = splitStr[0];
		this.fName = splitStr[1];
		this.fVersion = splitStr[2];
		this.inTime = DateUtils.parseDateTime(DATETIME_FORMANT, splitStr[3]);
		this.inMachine = splitStr[4];
		if(splitStr.length>5){
			if(!splitStr[5].equals(NO_VERSION_FLAG)){
				this.outTime = DateUtils.parseDateTime(DATETIME_FORMANT, splitStr[5]);
			}
			this.outMachine = splitStr[6];
		}
	}
	
	public String toString(){
		
		String toStr = this.fileType+SPLIT+
				this.fName+SPLIT+
				(this.fVersion==null?NO_VERSION_FLAG:this.fVersion)+SPLIT+
				DateUtils.fromatDateTime(DATETIME_FORMANT, this.inTime) + SPLIT + 
				(((this.inMachine==null)||this.inMachine.equals(""))?NO_VERSION_FLAG:this.inMachine) + SPLIT + 
				(((this.outTime==null)||this.outMachine.equals(""))?NO_VERSION_FLAG:DateUtils.fromatDateTime(DATETIME_FORMANT, this.outTime))+SPLIT+
				(((this.outMachine==null)||this.outMachine.equals(""))?NO_VERSION_FLAG:this.outMachine);
		return toStr;
	}
	
	/**
	 * 是否已经处理,
	 * 文件存在 并且写入时间不为空，则为未处理；否则为已处理
	 * @return
	 */
	public boolean isHandler(){
		if(this.inTime==null && this.fName!=null && !this.fName.equals("null")){
			return false;
		}else{
			return true;
		}
		
	}
	
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getfVersion() {
		return fVersion;
	}
	
	public long getfVersionFloat() {
		if(this.fVersion==null){
			return 0;
		}
		if(this.fVersion.equals(NO_VERSION_FLAG)||"null".equals(this.fVersion)){
			return 0;
		}
		
		return Long.parseLong(fVersion);
	}
	public void setfVersion(String fVersion) {
		this.fVersion = fVersion;
	}
	public Date getInTime() {
		return inTime;
	}
	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}
	public String getInMachine() {
		return inMachine;
	}
	public void setInMachine(String inMachine) {
		this.inMachine = inMachine;
	}
	public Date getOutTime() {
		return outTime;
	}
	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}
	public String getOutMachine() {
		return outMachine;
	}
	public void setOutMachine(String outMachine) {
		this.outMachine = outMachine;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
}
