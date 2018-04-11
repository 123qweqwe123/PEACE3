package com.bdcor.pip.web.common.service.impl;

import com.bdcor.pip.web.common.dao.PipSysParameterMapper;
import com.bdcor.pip.web.common.domain.PipSysParameter;
import com.bdcor.pip.web.common.domain.PipSysParameterExample;
import com.bdcor.pip.web.common.service.SysParameterService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Description:
 * Author: huangrupeng
 * Create: 17/3/3 上午9:25
 */
public class SysParameterServiceImpl implements SysParameterService{

    @Autowired
    PipSysParameterMapper pipSysParameterMapper;

    @Override
    public List<PipSysParameter> getParameterByType(String type) {
        PipSysParameterExample exp = new PipSysParameterExample();
        exp.createCriteria().andTypeEqualTo(type);
        return pipSysParameterMapper.selectByExample(exp);
    }
}
