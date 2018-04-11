package com.bdcor.pip.client.tools;




public interface ISentMsg {
	/**
	 * 发送服务器状态信息
	 * @throws NoServerException 
	 */
	public String sentMsg(Object json) throws NoServerException;
	
	/**
	 * 退出
	 */
	public void quit();
	
}
