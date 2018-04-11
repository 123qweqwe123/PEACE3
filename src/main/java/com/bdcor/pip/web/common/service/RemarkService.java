package com.bdcor.pip.web.common.service;

import java.util.List;
import java.util.Map;

import com.bdcor.pip.web.common.domain.PipCommRemark;

public interface RemarkService {
   /**
    * 由主键查询主键下不同类别对应的备注
    * @param remark
    * @return
    */
   Map<Short,List<PipCommRemark>> getRemakListByPk(PipCommRemark remark);
   
   public void setRemarkCount(Object srcList,String remarkFieldName,String...paraName);
}
