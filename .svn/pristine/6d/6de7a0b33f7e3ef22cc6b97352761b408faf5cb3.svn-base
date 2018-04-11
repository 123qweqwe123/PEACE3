package com.bdcor.pip.client.file.filter;

import com.bdcor.pip.client.ClientInfo;
import com.bdcor.pip.client.SysInfo;
import com.bdcor.pip.data.util.CryptoUtil;

/**
 * xml文件过滤器,
 * 加密的xml文件文件解密；
 * 未加密的xml文件加密。
 * 
 * 默认加密和解密key为LCC_NO.
 * @author Administrator
 *
 */
public class XmlContentFilter extends FileFilter{
	
	public XmlContentFilter(){

	}
	public XmlContentFilter(String key){
		super(key);
	}
	
	/**
	 * 加密
	 * @param str
	 * @return
	 */
	public String encryptStr(String str){
		//调试模式不加密
		if(SysInfo.isDegug()){
			return str;
		}else{
			String crypContent = null;
			try {
				crypContent = CryptoUtil.encrypt(key, str);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return crypContent;
		}
	}
	/**
	 * 解密
	 * @param str
	 * @return
	 */
	public String decryptionStr(String str){
		//调试模式不加密
		if(SysInfo.isDegug()){
			return str;
		}else{
			String crypContent;
			try {
				crypContent = CryptoUtil.decrypt(key, str);
			} catch (Exception e) {
				return str;
			}
			return crypContent;
		}
	}
	
	
}
