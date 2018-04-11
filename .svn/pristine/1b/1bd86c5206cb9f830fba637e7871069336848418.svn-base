package com.bdcor.pip.web.sys.logmgt.service;

import java.util.List;

import com.bdcor.pip.web.sys.logmgt.domain.LogDTO;
import com.bdcor.pip.web.sys.logmgt.filter.LogFilter;

public interface LogService {
 
	List<LogDTO> list(LogFilter filter); 

	/***
	 * 日志
	 * 
	 * @param model
	 *            <int> 所属模块
	 * @param logType
	 *            <int>日志类型
	 * @param logLevel
	 *            <int>等级
	 * @param logInfo
	 *            <String> 记录的信息
	 */
	void log(int model, int logType, int logLevel, String logInfo);

	/***
	 * 日志
	 * 
	 * @param model
	 *            <int> 所属模块
	 * @param logType
	 *            <int>日志类型
	 * @param logLevel
	 *            <int>等级
	 * @param logInfo
	 *            <String> 记录的信息
	 * @param tenantId
	 *            <String> 租户ID
	 * @param userId
	 *            <String> 用户ID
	 * @param userIp
	 *            <String> 用户IP
	 * @param userName
	 *            <String> 用户登陆名
	 */
	void log(int model, int logType, int logLevel, String logInfo,
			String tenantId, String userId, String userIp, String userName);

	/**
	 * INFO级别，用于记录操作日志 比如新增和修改
	 * 
	 * @param model
	 *            <int> 所属模块
	 * @param logInfo
	 *            <String> 记录的信息
	 * 
	 */
	void info(int model, String logInfo);

	/**
	 * WARNING级别，用于记录操作日志 比如删除
	 * 
	 * @param model
	 *            <int> 所属模块
	 * @param logInfo
	 *            <String> 记录的信息
	 */
	void warning(int model, String logInfo);

	/**
	 * ERROR级别应用日志，比如业务调度失败，定时任务不成功等。
	 * 
	 * @param model
	 *            <int> 所属模块
	 * @param logInfo
	 *            <String> 记录的信息
	 */
	void error(int model, String logInfo);

	/**
	 * ERROR级别系统日志 ，比如数据库出现异常，try..catch中捕获的Exception
	 * 
	 * @param model
	 *            <int> 所属模块
	 * @param logInfo
	 *            <String> 记录的信息
	 */
	void cruel(int model, String logInfo);
}
