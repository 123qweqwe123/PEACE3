package com.bdcor.pip.web.common.domain;

import java.util.List;

public class PipCommRemarkVO {
   private String pk1;//主键1
   private String pk2;//主键2
   private String pk3;//主键3
   private Short moduleId;//类别ID
   private Integer count;//备注数量
   List<PipCommRemark>remarkList;//主键下面对应的每个类别下的备注列表
   
   public String getPk1() {
      return pk1;
   }
   public void setPk1(String pk1) {
      this.pk1 = pk1;
   }
   public String getPk2() {
      return pk2;
   }
   public void setPk2(String pk2) {
      this.pk2 = pk2;
   }
   public String getPk3() {
      return pk3;
   }
   public void setPk3(String pk3) {
      this.pk3 = pk3;
   }
   public Short getModuleId() {
      return moduleId;
   }
   public void setModuleId(Short moduleId) {
      this.moduleId = moduleId;
   }
   public Integer getCount() {
      return count;
   }
   public void setCount(Integer count) {
      this.count = count;
   }
   public List<PipCommRemark> getRemarkList() {
      return remarkList;
   }
   public void setRemarkList(List<PipCommRemark> remarkList) {
      this.remarkList = remarkList;
   } 

   
}
