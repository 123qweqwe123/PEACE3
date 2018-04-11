package com.bdcor.pip.web.sys.logmgt.dao;

import java.util.List;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.sys.logmgt.domain.Log;
import com.bdcor.pip.web.sys.logmgt.domain.LogDTO;
import com.bdcor.pip.web.sys.logmgt.filter.LogFilter;

@MyBatisRepository
public interface LogDao{

    List<LogDTO> list(LogFilter filter);

    void save(Log log);

}
