package com.bdcor.pip.client.tools;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.filefilter.IOFileFilter;



/**
 * 数据库操作记录文件
 * @author Administrator
 *
 */
public class DbLogFile extends File {

	public DbLogFile(String pathname) {
		super(pathname);
	}

	public String logDateStr(){
		String[] tempStrs = super.getName().split("\\.");
		String logDateStr = tempStrs[tempStrs.length-1];
		return logDateStr;
	}
	
	public Date logDate(){
		Date logDate = DateUtils.parseDate(null,logDateStr());
		return logDate;
	}
	
	/**
	 * 当前操作日志
	 * @return
	 */
	public static List<DbLogFile> currentUpdateFile(final long timeMillion){
		List<DbLogFile> rList = new ArrayList<DbLogFile>();
		String logFilePath = logfilePath();
		
		
		Collection<File> logfiles = FileUtils.listFiles(new File(logFilePath), new IOFileFilter(){
			@Override
			public boolean accept(File file) {
				String fileName = file.getName();
				if(fileName.endsWith("_log")){
					String timeStr = fileName.split("_")[1];
					long time = Long.parseLong(timeStr);
					if(time>timeMillion){
						return true;
					}
				}
				return false;
			}

			@Override
			public boolean accept(File dir, String name) {
				return false;
			}
		}, null);
		
		for(File file:logfiles){
			rList.add(new DbLogFile(file.getPath()));
		}
		
		
		DbLogFile f = new DbLogFile(logFilePath+File.separatorChar+logStartStr+".log");
		if(f.exists()){
			rList.add(f);
		}
		return rList;
	}
	
	public static String dbUpdateLogDir = FileUtils.classPath();
	public static String logStartStr = "dbUpdateOper";
	/**
	 * 执行备份日期前的，更新日志
	 * @param fDbBackDate
	 * @return
	 */
	public static List<DbLogFile> getLogAfterDate(final Date fDbBackDate){
		String logFilePath = logfilePath();
		
		Collection<File> logfiles = FileUtils.listFiles(new File(logFilePath), new IOFileFilter(){
			@Override
			public boolean accept(File file) {
				String fileName = file.getName();
				if(fileName.startsWith(logStartStr) && !fileName.endsWith("log")){
					DbLogFile logFile = new DbLogFile(file.getPath());
					Date logDate = logFile.logDate();
					if(logDate == null){
						return false;
					}
					if(logDate.after(fDbBackDate)){
						return true;
					}
				}
				return false;
			}

			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				return false;
			}
		}, null);
		
		
		List<DbLogFile> rList = new ArrayList<DbLogFile>();
		for(File file:logfiles){
			rList.add(new DbLogFile(file.getPath()));
		}
		
		
		return rList;
	}
	
	/**
	 * 备份当前的操作日志<br>
	 * 
	 * @param currentMille
	 */
	public static String backLogFile(long currentMille){
		String logFilePath = logfilePath();
		DbLogFile f = new DbLogFile(logFilePath+File.separatorChar+logStartStr+".log");
		
		String backupPFile = logFilePath+File.separatorChar+logStartStr+"_"+currentMille+"_log";
		
		String backupPFile2 = DirectoryInfo.instance().DAT_LOG_Dir()+File.separatorChar+logStartStr+"_"+currentMille+"_log."+DateUtils.currentDate();
		
		try {
			FileUtils.copyFile(f, new File(backupPFile));
			FileUtils.copyFile(f, new File(backupPFile2));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		FileUtils.write(f, "", false);
		
		
		
		
		return backupPFile;
	}
	
	public static String logfilePath(){
		String logFilePath = new File(dbUpdateLogDir).getParentFile().getParentFile().getParentFile().getParentFile().getParentFile().getPath()+File.separatorChar+"BIN"+File.separatorChar+"LOG"+File.separatorChar+"db"+File.separatorChar;
		
		return logFilePath;
	}
}
