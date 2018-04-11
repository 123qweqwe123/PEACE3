package com.bdcor.pip.client.tools;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RunningUtils {

	/**
	 * 运行vb脚本
	 * @param avbCommand
	 * @return
	 */
	public static String runVb(String avbCommand){
		String result = "";
		try {
			File file = File.createTempFile("realhowto", ".vbs");
			file.deleteOnExit();
			FileWriter fw = new java.io.FileWriter(file);
			fw.write(avbCommand);
			fw.close();

			Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			
			String line;
			while ((line = input.readLine()) != null) {
				result += line; 
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 执行bat命令或者文件
	 * @param batName   命令 或者bat文件全路径
	 * @throws Exception
	 */
	public static void runbat(String acommOrFilePName) throws Exception {
		String commOrFilePName = StringUtils.trim(acommOrFilePName);
		if(StringUtils.isBlank(commOrFilePName)){
			return;
		}
		
		String command = null;
		String pFile = "";
		if(StringUtils.endsWithAny(commOrFilePName, new String[]{"bat","bat".toUpperCase()})){
			pFile = commOrFilePName;
		}else{
			if(StringUtils.startsWithAny(commOrFilePName, new String[]{"cmd","cmd".toUpperCase()})){
				command = commOrFilePName;
			}else{
				command = "cmd.exe /C start /b " + commOrFilePName;	
			}
			
		}
		
		try {
			String cmd = null;
			if(command!=null){
				cmd = command;
			}else if(pFile!=null){
				cmd = pFile;
			}
			
			Process process = Runtime.getRuntime().exec(cmd);
			
			if (process.waitFor() != 0) {
				throw new Exception("bat命令或文件 执行错误");
			}
		} catch (IOException ioe) {
			throw new Exception("bat命令或文件 执行错误",ioe);
		} catch (InterruptedException e) {
			throw new Exception("bat命令或文件 执行错误",e);
		}
	}
	
	
	public static void main(String[] a) throws Exception{
		String cmd = "cmd.exe /C start /b D:\\PIP\\001\\BIN\\DBMysql\\start_5.6.bat D:\\PIP\\001\\BIN\\DBMysql\\";
		RunningUtils.runbat(cmd);
	}
	
	/**
	 * 执行bat命令或者文件
	 * @param batName   命令 或者bat文件全路径
	 * @throws Exception
	 */
	public static String runbatWithBanknfo(String batName) throws Exception {
		StringBuffer rStr = new StringBuffer();
		Process process = null;
		InputStream in = null;
		BufferedReader br = null;
		try {
			if(batName.contains(".bat") ||batName.contains("bat".toUpperCase())){
				batName = "cmd.exe /C start /b " + batName;
			}else{
				batName = "cmd.exe /C " + batName;
			}
			 
//			process = Runtime.getRuntime().exec("D:\\PIP\\001\\BIN\\DBMysql\\isRunning_5.6.bat",new String[]{"D:\\PIP\\001\\BIN\\DBMysql\\"});
			process = Runtime.getRuntime().exec(batName);
			  in = process.getInputStream();
			 //用一个读输出流类去读
			 br = new BufferedReader(new InputStreamReader(in));
			 //逐行读取输出到控制台
			 String line ;
			 while ((line = br.readLine()) != null) {
				 rStr.append(line);
			 }
			 
			 int cmdReturnNum = process.waitFor(); 
			 
			if ( cmdReturnNum != 0) {
				throw new Exception("bat命令或文件 执行错误");
			}
			
		} catch (IOException ioe) {
			throw new Exception("bat命令或文件 执行错误",ioe);
		} catch (InterruptedException e) {
			throw new Exception("bat命令或文件 执行错误",e);
		}finally{
			if(in!=null){
				in.close();	
			}
			
			if(br!=null){
				br.close();	
			}
			
		}
		return rStr.toString();
	}
}
