package com.bdcor.pip.client.tools;



import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import com.bdcor.pip.client.ClientInfo;
import com.bdcor.pip.data.util.CryptoUtil;

public class FindUDisk {
	/** 标记文件名 **/
	public static String U_SIGN_FNAME = "1234567.txt";
	
	/**
	 * 查找标记文件 
	 * @return  标记文件的父文件的目录
	 * @throws MulUDiskFindException 
	 * @throws UDiskNotFindException 
	 */
	public static boolean findU() throws UDiskNotFindException, MulUDiskFindException {
		return findUPath()==null?false:true;
	}
	
	public static String findUPath() throws UDiskNotFindException, MulUDiskFindException {
		
		List<String> uPaths = new ArrayList<String>();
		
		File[] roots = File.listRoots();
		for (int i = 0; i < roots.length; i++) {
			File f = roots[i];
			File[] searchFiles = f.listFiles(new FileFilter() {
				@Override
				public boolean accept(File pathname) {
					if (pathname.getName() != null
							&& pathname.getName().equals(U_SIGN_FNAME)) {
						return true;
					}
					return false;
				}
			});
			if(searchFiles != null && searchFiles.length == 1) {
				for(File flagFile:searchFiles){
					try{
						String key = FileUtils.readFileToString(flagFile);
						if ( key.equals(getUFileKey(null)) ||  key.equals(getUFileKey("001"))){
							uPaths.add(flagFile.getParent());
						}
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}
			}
		}
		
		if(uPaths.size()==0){
			throw new UDiskNotFindException("未找到u盘");
		}
		if(uPaths.size()>1){
			throw new MulUDiskFindException("找到多个u盘");
		}
		
		return uPaths.get(0); 
	}
	
	
	/**
	 * 文件中记录的key
	 * @return  
	 * @throws Exception 
	 */
	public static String getUFileKey(String defailKey) throws Exception {
		String key = ClientInfo.LCC_NO;
		if(defailKey!=null){
			key = defailKey;
		}
		if(key==null || key.equals("")){
			key = "001";
		}
		return CryptoUtil.encrypt(key, "心血管病高危人群早期筛查与综合干预项目");
	
	}
	

	
}
