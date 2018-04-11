package com.bdcor.pip.web.sys.logmgt.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdcor.pip.core.exception.ServiceException;
import com.bdcor.pip.core.utils.GenerateKey;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.sys.logmgt.dao.LogDao;
import com.bdcor.pip.web.sys.logmgt.domain.Log;
import com.bdcor.pip.web.sys.logmgt.domain.LogDTO;
import com.bdcor.pip.web.sys.logmgt.filter.LogFilter;
import com.bdcor.pip.web.sys.logmgt.service.LogService;

@Service
public class LogServiceImpl implements LogService {

	private static Logger logger = LoggerFactory
			.getLogger(LogServiceImpl.class);

	@Autowired
	private LogDao logDao;

	@Override
	public List<LogDTO> list(LogFilter filter) { 
		try {
			return logDao.list(filter);
		} catch (Exception e) {
			throw new ServiceException("查询日志信息错误", e);
		}
	} 

	@Override
	public void log(int model, int logType, int logLevel, String logInfo) {
		this.log(model, logType, logLevel, logInfo, Securitys.getOrganId(),
				Securitys.getUserId(), Securitys.getIp(),
				Securitys.getUserName());
	}

	@Override
	public void info(int model, String logInfo) {
		//this.log(model, "LogType.BUSINESS", "LogLevel.INFO", logInfo);
	}

	@Override
	public void warning(int model, String logInfo) {
		//this.log(model, LogType.BUSINESS, LogLevel.WARNING, logInfo);
	}

	@Override
	public void error(int model, String logInfo) {
		//this.log(model, LogType.BUSINESS, LogLevel.ERROR, logInfo);
	}

	@Override
	public void cruel(int model, String logInfo) {
		//this.log(model, LogType.SYSTEM, LogLevel.CRUEL, logInfo);
	}

	@Override
	public void log(int model, int logType, int logLevel, String logInfo,
			String tenantId, String userId, String userIp, String userName) {
		Log log = new Log();
		//log.setId(Identities.uuid());
		log.setId(GenerateKey.getKey(GenerateKey.PREFIX_LOG));
		log.setLogDatetime(new Date());
		log.setLogInfo(logInfo);
		log.setLogLevel(logLevel);
		log.setLogType(logType);
		log.setTenantId(tenantId);
		log.setUserId(userId);
		log.setUserIp(userIp);
		log.setUserName(userName);
		log.setModel(model);
		try {
			logDao.save(log);
			logger.info("用户" + userId + "记录日志:" + "tenantId");
		} catch (Exception e) {
			throw new ServiceException("记录日志失败", e);
		}
	}

}
