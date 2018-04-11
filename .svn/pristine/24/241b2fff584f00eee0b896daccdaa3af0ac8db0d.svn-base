package com.bdcor.pip.client.tools;



import java.util.Date;


/**
 * 文件回执日志
 * 
 * @author Administrator
 *
 */
public class ReceiptLog extends Log{
	
	private String fileType = "bus";
	private String machineName;
	private String zipFileName;
	private String fName;
	private Date receipTime;
	private String uploadResult;
	
	public ReceiptLog(){}
	
	public ReceiptLog(String fileType,String machineName,String zipFileName,String fName,String uploadResult){
		this.fileType = fileType;
		this.machineName = machineName;
		this.zipFileName = zipFileName;
		this.fName = fName;
		this.receipTime = new Date();
		this.uploadResult = uploadResult;
	}
	
	public ReceiptLog(String line){
		String[] splitStr = line.split(SPLIT);
		this.fileType = splitStr[0];
		this.machineName = splitStr[1];
		this.zipFileName = splitStr[2];
		this.fName = splitStr[3];
		this.receipTime = DateUtils.parseDateTime(DATETIME_FORMANT, splitStr[4]);
		this.uploadResult = splitStr[5];
	}
	
	public String toString(){
		String toStr = this.fileType+SPLIT+
				this.machineName+SPLIT+
				this.zipFileName+SPLIT+
				this.fName+SPLIT+
				DateUtils.fromatDateTime(DATETIME_FORMANT, this.receipTime)+SPLIT+
				this.uploadResult;
		return toStr;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getMachineName() {
		return machineName;
	}

	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}

	public String getZipFileName() {
		return zipFileName;
	}

	public void setZipFileName(String zipFileName) {
		this.zipFileName = zipFileName;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public Date getReceipTime() {
		return receipTime;
	}

	public void setReceipTime(Date receipTime) {
		this.receipTime = receipTime;
	}

	public String getUploadResult() {
		return uploadResult;
	}

	public void setUploadResult(String uploadResult) {
		this.uploadResult = uploadResult;
	}
	
	/**
	 * 验证是否为有效的回执行
	 * @param line
	 * @return
	 */
	public static boolean isReceiptLine(String line){
		if(line == null || "".equals(line)){
			return false;
		}
		
		String[] splitStr = line.split(SPLIT);
		if(splitStr.length != 6){
			return false;
		}
		return true;
	}
	
}
