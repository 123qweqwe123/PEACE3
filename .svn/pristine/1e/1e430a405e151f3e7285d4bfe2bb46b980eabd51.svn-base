package com.bdcor.pip.client.tools;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.LineIterator;

public abstract class LogHandler {
	//-========================================================日志操作
	/**
	 * 加载日志，
	 * <br>
	 * 给定日志文件的路径和文件名称，加载日志
	 * 
	 * @param path 文件路径
	 * @param name 文件名称
	 * @return
	 */
	public static List<VersionLog> loadLogs(String path,String name) {
		List<VersionLog> logs = new ArrayList<VersionLog>();
		
		File logf = new File(path + name);
		if (!logf.exists()) {
			return logs;
		}
		LineIterator it = null;
		try {
			it = FileUtils.lineIterator(logf, "UTF-8");
			while (it.hasNext()) {
				String line = it.nextLine();
				if(line==null || line.trim().length()==0){
					continue;
				}
				logs.add(new VersionLog(line));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			LineIterator.closeQuietly(it);
		}
		return logs;
	}
	
	
	
	
	/**
	 * 保存日志，
	 * @param path 日志文件的路径及日志文件名称
	 * @param logs 待保存的日志文件
	 * @param nullToSave  ture:logs为null或无数据时，也保存；
	 */
	public static void saveLogs(String filePName,List<VersionLog> logs,boolean nullToSave) {
		
		if (!nullToSave && (logs==null || logs.size()==0)) {
			return;
		}
		StringBuffer sb = new StringBuffer();
		for(Log log:logs){
			sb.append(log).append("\n");
		}
		//System.out.println(filePName);
		File logf = FileUtils.createFile(filePName);
		try {
			FileUtils.writeStringToFile(logf, sb.toString(), false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
