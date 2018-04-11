/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.data.service.impl 
 */
/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.data.service.impl  
 */

package com.bdcor.pip.web.data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.core.utils.GenerateKey;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.data.dao.PipCommPaitentDateremarkMapper;
import com.bdcor.pip.web.data.dao.PipCommPatientDateMapper;
import com.bdcor.pip.web.data.dao.PipCommPatientMapper;
import com.bdcor.pip.web.data.domain.PipCommPaitentDateremark;
import com.bdcor.pip.web.data.domain.PipCommPatient;
import com.bdcor.pip.web.data.domain.PipCommPatientDate;
import com.bdcor.pip.web.data.domain.PipCommPatientDateKey;
import com.bdcor.pip.web.data.domain.PipCommPatientKey;
import com.bdcor.pip.web.data.service.PipCommPatientDateService;

/**
 * description:
 * 
 * @author yangfeng 创建时间：2015-12-17
 */
@Service
@Transactional
public class PipCommPatientDateServiceImpl implements PipCommPatientDateService {
	@Autowired
	private PipCommPatientDateMapper patientDateMapper;
	@Autowired
	private PipCommPaitentDateremarkMapper paitentDateremarkMapper;
	@Autowired
	PipCommPatientMapper pipCommPatientMapper;
	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.bdcor.pip.web.data.service.PipCommPatientDateService#selectPatientDate(java.lang.String)
	 */
	@Override
	public PipCommPatientDate selectPatientDate(String patientId) {
		PipCommPatientDateKey key = new PipCommPatientDateKey();
		key.setPatientId(patientId);
		key.setProjectId(Securitys.getUser().getCurrent_projectId());
		return patientDateMapper.selectByPrimaryKey(key);
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @throws Exception
	 * 
	 * @see com.bdcor.pip.web.data.service.PipCommPatientDateService#updateByPatientId(com.bdcor.pip.web.data.domain.PipCommPatientDate)
	 */
	@Override
	public void updateByPatientId(PipCommPatientDate patient) throws Exception {
		// 获取用户的六月计划随访时间和当前备注
		PipCommPatientDateKey patientDateKey = new PipCommPatientDateKey();
		patientDateKey.setPatientId(patient.getPatientId());
		patientDateKey.setProjectId(Securitys.getUser().getCurrent_projectId());
		PipCommPatientDate patientDate = patientDateMapper
				.selectByPrimaryKey(patientDateKey);
		if (null == patientDate) {
			throw new Exception("没有查找相应的患者预约随访数据");
		}
		if (null != patientDate.getSixPlanDate()) {
			// 如果六月计划时间不为空的话，首先将date表里面的数据移到datemark表中，然后更新数据
			// 插入到datemark表中
			PipCommPaitentDateremark dateremark = new PipCommPaitentDateremark();
			dateremark.setId(GenerateKey.getKey(GenerateKey.PREFIX_COMMON));
			dateremark.setProjectId(patientDate.getProjectId());
			dateremark.setPatientId(patientDate.getPatientId());
			dateremark.setRemark(patientDate.getNowRemark());
			dateremark.setDateType((short) 2); // 六月随访
			dateremark.setFollowDate(patientDate.getSixPlanDate()); // 6月计划时间
			paitentDateremarkMapper.insert(dateremark);
		}
		// 更新date表里面的数据
		patientDate.setSixPlanDate(patient.getSixPlanDate());
		patientDate.setNowRemark(patient.getNowRemark());
		patientDateMapper.updateByPrimaryKeySelective(patientDate);
	}
	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @throws Exception
	 * 
	 * @see com.bdcor.pip.web.data.service.PipCommPatientDateService#updateByPatientId(com.bdcor.pip.web.data.domain.PipCommPatientDate)
	 */
	@Override
	public void updateAppointmentDateFirst(PipCommPatientDate patient) throws Exception {
		PipCommPatientDateKey patientDateKey = new PipCommPatientDateKey();
		patientDateKey.setPatientId(patient.getPatientId());
		patientDateKey.setProjectId(Securitys.getUser().getCurrent_projectId());
		PipCommPatientDate patientDate = patientDateMapper
				.selectByPrimaryKey(patientDateKey);
		if (null == patientDate) {
			PipCommPatientKey key = new PipCommPatientKey();
			key.setPatientId(patient.getPatientId());
			key.setProjectId(Securitys.getUser().getCurrent_projectId());
			PipCommPatient pipCommPatient = pipCommPatientMapper.selectByPrimaryKey(key);
			patient.setPatientName(pipCommPatient.getPatientName());
			patient.setProjectId(Securitys.getUser().getCurrent_projectId());
			patientDateMapper.insert(patient);
		}
		else{
			patient.setProjectId(Securitys.getUser().getCurrent_projectId());
			patientDateMapper.updateByPrimaryKeySelective(patient);
		}
		
	}
	
}
