package com.bdcor.pip.web.common.service;

import com.bdcor.pip.web.common.domain.PipSysParameter;

import java.util.List;

public interface SysParameterService {

    List<PipSysParameter> getParameterByType(String type);
}
