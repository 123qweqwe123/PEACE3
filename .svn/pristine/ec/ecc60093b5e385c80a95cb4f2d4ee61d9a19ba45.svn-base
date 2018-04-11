package com.bdcor.pip.client.tools;



import java.io.IOException;
import java.io.StringWriter;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class Message {

	private Integer msgType;
	private String cmd;
	private String msg;
	public Message(){
		
	}
	
	public Message(int msgType,String cmd,String msg){
		this.msgType = msgType;
		this.cmd = cmd;
		this.msg = msg;
	}
	
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Integer getMsgType() {
		return msgType;
	}
	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}

	public static Message fromJson(String jsonStr){
		ObjectMapper mapper =  new ObjectMapper();  
		
		try {
			return mapper.readValue(jsonStr, Message.class);
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
	
	public static String toJson(Object obj){
		ObjectMapper mapper =  new ObjectMapper();  
		StringWriter sw = new StringWriter();  
		JsonGenerator gen = null;
		try {
			gen = new JsonFactory().createJsonGenerator(sw);
		} catch (IOException e) {
			e.printStackTrace();
		}  
		try {
			mapper.writeValue(gen, obj);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
		try {
			gen.close();
		} catch (IOException e) {
			e.printStackTrace();
		}  
		String json = sw.toString(); 
		
		return json;
	}
	
	
	public static void main(String[] a ){
//		ComMessage msg = new ComMessage(ComMessageType.TYPE_SHOWPAGE_URL,"test2","test3 message");
//		System.out.println(ComMessage.toJson(msg));
		
		String msg = "{\"msgType\":0,\"cmd\":\"test2\",\"msg\":\"test3 message\"}";
		
		Message comMsg = Message.fromJson(msg);
	}
}
