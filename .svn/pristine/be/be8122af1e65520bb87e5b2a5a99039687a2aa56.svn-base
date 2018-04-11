package com.bdcor.pip.web.qn.service;

import com.bdcor.pip.web.qn.domain.*;
import com.bdcor.pip.web.qn.filter.EventCheckFilter;
import org.springframework.ui.Model;

import java.util.List;

/**
 * Description:
 * <pre>
 * </pre>
 * Author: huangrupeng
 * Create: 17/5/11 下午3:51
 */
public interface EventCheckService {


    List<PipCommEventVO1> getEventCheckList(EventCheckFilter filter);

    void getEventInfo(String eventCode, Model model);

    void saveOrUpdate(PipCommEventCheck1 check, PipCommEventCheckEr1 er);

    PipCommEventCheckEr1 getErByEventCode(String eventCode);

    /**
     * 添加审核用户
     */
    boolean addAuditUser();

    String addEvent(String eventCode, Model model);

    List<PipCommEventExportVO> getEvent2exportList(EventCheckFilter filter);

    String updateEventCheckStatus(String eventCodeList);
}
