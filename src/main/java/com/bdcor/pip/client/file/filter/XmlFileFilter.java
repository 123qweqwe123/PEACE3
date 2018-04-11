package com.bdcor.pip.client.file.filter;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.bdcor.pip.client.SysInfo;
import com.bdcor.pip.data.util.CryptoUtil;
import com.bdcor.pip.data.util.FileUtils;

/**
 * xml文件过滤器
 * @author Administrator
 *
 */
public class XmlFileFilter extends FileFilter{

	public XmlFileFilter(){}
	
	public XmlFileFilter(String key){
		super(key);
	}
	
	/**
	 * 加密
	 * @param str
	 * @return
	 */
	public void encryptFile(String pFileName){
		//调试模式不加密
		if(SysInfo.isDegug()){
			return;
		}else{
			String content = FileUtils.readFileToString(pFileName);
			String enContent = null;
			try {
				enContent = CryptoUtil.encrypt(key,content);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FileUtils.deleteQuietly(new File(pFileName));
			FileUtils.write(new File(pFileName), enContent, true);
		}
	}
	
	/**
	 * 加密
	 * @param str
	 * @return
	 */
	public void encryptFile(String infile,String outFile){
		//调试模式不加密
		if(SysInfo.isDegug()){
			return;
		}else{
			String content = FileUtils.readFileToString(infile);
			String enContent = null;
			try {
				enContent = CryptoUtil.encrypt(key,content);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FileUtils.write(new File(outFile), enContent, true);
		}
	}
	
	/**
	 * 加密
	 * @param str
	 * @return
	 */
	public void encryptStrToFile(String content,String outFile){
		//调试模式不加密
		if(SysInfo.isDegug()){
			return;
		}else{
			String enContent = null;
			try {
				enContent = CryptoUtil.encrypt(key,content);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FileUtils.write(new File(outFile), enContent, true);
		}
	}
	/**
	 * 解密
	 * @param str
	 * @return
	 * @throws Exception 
	 */
	public InputStream decryptionFile(File file) throws Exception{
		//调试模式不加密
		if(SysInfo.isDegug()){
			try {
				return new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}else{
			try {
				String content = FileUtils.readFileToString(file);
				ByteArrayInputStream tInputStringStream = null;
				try {
					tInputStringStream = new ByteArrayInputStream(CryptoUtil.decrypt(key,content).getBytes());
//					String s = new String(CryptoUtil.decrypt(key,content).getBytes());
//					System.out.println(s);
				} catch (Exception e) {
					throw new Exception();
				}
				return (InputStream)tInputStringStream;
			} catch (Exception e) {
				throw new Exception();
			}
		}
		return null;
	}
}
