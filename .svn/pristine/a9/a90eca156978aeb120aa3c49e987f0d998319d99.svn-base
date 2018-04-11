package com.bdcor.pip.web.msg.service.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bdcore.webservice.client.MsgClient;
import com.bdcore.webservice.client.config.ClientConfig;

@Component
@Scope("singleton") 
public class MobileClient{
	
	private static MsgClient client;
	
	private static String pro="01";
	
	@Value("${msg_server_ip}")
	private String serverIp;
	
	@Value("${msg_server_port}")
	private String serverPort;
	
	@Value("${msg_reply_port}")
	private String replyPort;
	
	@Value("${msg_reply_ip}")
	private String replyIp;
	
	@Value("${msg_send_status_ip}")
	private String msg_send_status_ip;
	
	@Value("${msg_send_status_port}")
	private String msg_send_status_port;
	public MobileClient(){
		System.out.println("...........");
	}
	
	public MsgClient getClient(){
		return client;
	}

	@PreDestroy
	public void destoryClient() {
		System.out.println("销毁client");
		if(client!=null){
			client.stop();
		}
	}
	
	@PostConstruct
	private void initClient(){
		if(client==null){
			ClientConfig config = new ClientConfig();
			config.put(ClientConfig.MSG_SERVER_IP, serverIp);
			config.put(ClientConfig.MSG_SERVER_PORT, serverPort);
			config.put(ClientConfig.PROJ_ID, pro);
			config.put(ClientConfig.REPLY_SERVER_IP, replyIp);
			config.put(ClientConfig.REPLY_SERVER_PORT, replyPort);
			config.put(ClientConfig.SEND_STATUS_SERVER_IP, msg_send_status_ip);
			config.put(ClientConfig.SEND_STATUS_SERVER_PORT, msg_send_status_port);
			client = new MsgClient(config); 
			client.start();
		}
	}

}
