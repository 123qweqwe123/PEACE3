package com.bdcor.pip.web.fee.domain;

import java.io.Serializable;

public class RegTypeVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 类型代码
     */
    private String typeCode;

    /**
     * 类型名称
     */
    private String typeName;

    /**
     * 说明
     */
    private String remarks;

    /**
     * @return 类型代码
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * @param typeCode 
	 *            类型代码
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode == null ? null : typeCode.trim();
    }

    /**
     * @return 类型名称
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * @param typeName 
	 *            类型名称
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    /**
     * @return 说明
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks 
	 *            说明
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}