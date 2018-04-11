package com.bdcor.pip.client.tools;



import java.io.File;


public class H2 {
	private static String dbName = "datDb";
	private static String dbPath = "";
	
	public static void init(){
		String dbNameUserSet =  Constants.getPropertyValue("dat_db_name");
		if(dbNameUserSet!=null && !"".equals(dbNameUserSet)){
			H2.dbName = dbNameUserSet;
		}
		
		dbPath = DirectoryInfo.instance().DAT_BASE_Dir()+File.separator+""+File.separator+"BIN"+File.separator+"DB"+File.separator+"";
	}
	
	

	public static String getDbPath() {
		return dbPath;
	}
	
	public static String getDbName(){
		return dbName;
	}
	
}
