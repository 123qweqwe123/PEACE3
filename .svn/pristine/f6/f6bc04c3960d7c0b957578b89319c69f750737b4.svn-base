package com.bdcor.pip.client.tools;




public class StringUtils extends org.apache.commons.lang3.StringUtils{

	/**
	 * unicode转换为中文
	 * @param utfString
	 * @return
	 */
	 public static String convertUnicode(String utfString){  
	        StringBuilder sb = new StringBuilder();  
	        int i = -1;  
	        int pos = 0;  
	          
	        while((i=utfString.indexOf("\\u", pos)) != -1){  
	            sb.append(utfString.substring(pos, i));  
	            if(i+5 < utfString.length()){  
	                pos = i+6;  
	                sb.append((char)Integer.parseInt(utfString.substring(i+2, i+6), 16));  
	            }  
	        }  
	        sb.append(utfString.substring(pos));
	        return sb.toString();  
	    }  
	/**
	 * 删除字符串中的非字母和非数字的字符
	 * @return
	 */
	public static String deleteNotAtoZAnd0to9(String s){
		if(s==null || s.length()==0){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		String sL = "abcdefghijklmnopqrstuvwxyz" ;
        String sU = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" ;
        String digit = "0123456789";
        char c ;
        for(int i=0 ; i<s.length() ;i++) {
            c = s.charAt(i) ;
            if(sL.indexOf(c) != -1) {
            	sb.append(c+"");
            }
            else if(sU.indexOf(c) != -1) {
            	sb.append(c+"");
            } else if(digit.indexOf(c) != -1){
            	sb.append(c+"");
            }
        }
        return sb.toString();
	}
	
	/**
	 * 
	 * @param s
	 * @param toLength
	 * @return
	 */
	public static String addBlankToLeng(String s,int toLength){
		int strLength = s.length();
		if(strLength>=toLength){
			return s;
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append(s);
		for(int i=0;i<(toLength-strLength);i++){
			sb.append(" ");
		}
		return sb.toString();
	}
	
	/**
	 * 修改字符串的第一个字母为小写
	 * @param s
	 * @return
	 */
	public static String firstCharToLower(String s){
		return StringUtils.replaceOnce(s, s.charAt(0)+"", (s.charAt(0)+"").toLowerCase());
	}
	
	public static void main(String[] a){
		System.out.println(deleteNotAtoZAnd0to9("1j/khKhjk/lhklHL/Hjb90kBN/o9"));
		System.out.println(deleteNotAtoZAnd0to9("9ABCDE//KFaji//dojak3$%*#%/&*&_4"));
		System.out.println(deleteNotAtoZAnd0to9("1j.kh.Khj/k.lh kl/HLH jb90k/B No9"));
	}
}
