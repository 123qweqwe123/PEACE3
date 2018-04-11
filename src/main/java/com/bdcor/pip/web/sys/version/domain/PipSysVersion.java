package com.bdcor.pip.web.sys.version.domain;

import java.util.Date;

public class PipSysVersion {
    private String version;

    private Date versionData;

    private String versionText;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getVersionData() {
        return versionData;
    }

    public void setVersionData(Date versionData) {
        this.versionData = versionData;
    }

    public String getVersionText() {
        return versionText;
    }

    public void setVersionText(String versionText) {
        this.versionText = versionText;
    }
}