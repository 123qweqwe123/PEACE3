package com.bdcor.pip.client.tools;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/* 
 * 
 * 
 * @author calvin
 */
public class JsonMapper<T> {

	/**
	 * 将对象转化为json数据
	 * @param obj
	 * @return
	 */
	public static String objectToJson(Object obj){
		ObjectMapper mapper = new ObjectMapper();  
		try {
			String backStr = mapper.writeValueAsString(obj);
//			System.out.println(backStr);
			return backStr;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Map jsonToMap(String json){
		ObjectMapper mapper = new ObjectMapper(); 
		try {
			return mapper.readValue(json, Map.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static List jsonToList(String json){
		ObjectMapper mapper = new ObjectMapper(); 
		try {
			return mapper.readValue(json, List.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Object jsonToList(String json,Class c){
		ObjectMapper mapper = new ObjectMapper(); 
		try {
			return mapper.readValue(json, c);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static void main(String[] a){
		String s = "[{\"filename\":\"lcc_team1_1408682239902.zip\",\"result\":\"1\"}]";
		List m = jsonToList(s);
		System.out.println("");
	}
	
	
	
}
