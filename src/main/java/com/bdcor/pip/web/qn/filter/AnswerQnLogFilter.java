package com.bdcor.pip.web.qn.filter;

import com.bdcor.pip.core.persistence.domain.BaseFilter;

/**
 * Created by root on 16-7-6.
 */
public class AnswerQnLogFilter extends BaseFilter {

    public int state;

    public String pid ;

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}

