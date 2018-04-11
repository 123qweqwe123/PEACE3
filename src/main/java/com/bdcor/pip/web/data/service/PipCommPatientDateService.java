/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.data.service 
 */
/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.data.service  
 */

package com.bdcor.pip.web.data.service;

import com.bdcor.pip.web.data.domain.PipCommPatientDate;

/**
 * description:
 * 
 * @author yangfeng 创建时间：2015-12-17
 */
public interface PipCommPatientDateService {

	/**
	 * description:通过patientId，projectId查找
	 * 
	 * @author yangfeng
	 * @param patientId
	 * @return
	 * @update 2015-12-17
	 */
	PipCommPatientDate selectPatientDate(String patientId);

	/**
	 * description:
	 * 
	 * @author yangfeng
	 * @param patient
	 * @update 2015-12-18
	 */
	void updateByPatientId(PipCommPatientDate patient) throws Exception;

	void updateAppointmentDateFirst(PipCommPatientDate patient) throws Exception;

}
