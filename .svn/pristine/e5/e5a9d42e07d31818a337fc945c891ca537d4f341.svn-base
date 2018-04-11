package com.bdcor.pip.web.common.service.impl;

import java.util.List;
import java.util.Map;

import com.bdcor.pip.web.common.dao.PipCommRemarkMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.bdcor.pip.data.util.ReflectionUtils;

import com.bdcor.pip.web.common.domain.PipCommRemark;
import com.bdcor.pip.web.common.domain.PipCommRemarkExample;
import com.bdcor.pip.web.common.domain.PipCommRemarkExample.Criteria;
import com.bdcor.pip.web.common.domain.PipCommRemarkVO;
import com.bdcor.pip.web.common.service.RemarkService;
import com.bdcor.pip.web.pro.promgt.domain.Lcc;
@Service
@Transactional
public class RemarkServiceImpl implements RemarkService{
   @Autowired
   PipCommRemarkMapper remarkMapper;
   
   @Override
   public Map<Short,List<PipCommRemark>> getRemakListByPk(PipCommRemark remark) {
      PipCommRemarkExample exp=new PipCommRemarkExample();
      Criteria c=exp.createCriteria();
      if(StringUtils.isNotBlank(remark.getPk1())){
         c.andPk1EqualTo(remark.getPk1());
      }
      if(StringUtils.isNotBlank(remark.getPk2())){
         c.andPk2EqualTo(remark.getPk2());
      }
      if(StringUtils.isNotBlank(remark.getPk3())){
         c.andPk3EqualTo(remark.getPk3());
      }
      c.andIsDeleteEqualTo((short)1);
      exp.setOrderByClause(" module_id,id");
      List<PipCommRemark> remarkList=remarkMapper.selectByExample(exp);
      Map<Short,List<PipCommRemark>> remarkByModule=Maps.newLinkedHashMap();
      for(PipCommRemark rem:remarkList){
         rem.setRemark1(rem.getRemark1().replace("\n", "<br/>"));
         Short moduleId=rem.getModuleId();
         if(remarkByModule.get(moduleId)==null){
            List<PipCommRemark> rList=Lists.newArrayList();
            remarkByModule.put(moduleId, rList);
         }
         remarkByModule.get(moduleId).add(rem);
      }
      return remarkByModule;
   }
   
   /**
    * 
    * @param srcList 需要添加备注数量的list
    * @param remarkFieldName  列表中对象备注对应字段名
    * @param paraName   列表中对象主键对应字段名
    */
   public void setRemarkCount(Object srcList,String remarkFieldName,String...paraName){
      List<PipCommRemarkVO> remarkCountVO=remarkMapper.selectCountByModuleId(null);
      List<Object>srcs=(List<Object>)srcList;
      for(Object src:srcs){
         String pk1=null,pk2=null,pk3=null;
         if(paraName.length==1){
            pk1=(String)ReflectionUtils.getFieldValue(src, paraName[0]);
         }else if(paraName.length==2){
            pk2=(String)ReflectionUtils.getFieldValue(src, paraName[1]);
         }else if(paraName.length==3){
            pk3=(String)ReflectionUtils.getFieldValue(src, paraName[2]);
         }
         for(PipCommRemarkVO remarkCount:remarkCountVO){
            if(paraName.length==1){
               if(remarkCount.getPk1()!=null&&remarkCount.getPk1().equals(pk1)){
                  ReflectionUtils.setFieldValue(src, remarkFieldName, remarkCount.getCount().toString());
                  break;
               }
            }else if(paraName.length==2){
               if(remarkCount.getPk1()!=null&&remarkCount.getPk1().equals(pk1)&&remarkCount.getPk2()!=null&&remarkCount.getPk2().equals(pk2)){
                  ReflectionUtils.setFieldValue(src, remarkFieldName, remarkCount.getCount().toString());
                  break;
               }
            }else if(paraName.length==3){
               if(remarkCount.getPk1()!=null&&remarkCount.getPk1().equals(pk1)
                     &&remarkCount.getPk2()!=null&&remarkCount.getPk2().equals(pk2)
                     &&remarkCount.getPk3()!=null&&remarkCount.getPk3().equals(pk3)){
                  ReflectionUtils.setFieldValue(src, remarkFieldName, remarkCount.getCount().toString());
                  break;
               }
            }
         }
      }
   }
   public static void test(Object srcList){
      
   }
   public static void main(String[]args){
      Lcc lcc=new Lcc();
      lcc.setLccCode("222");
      System.out.println(ReflectionUtils.getFieldValue(lcc, "lccCode"));
   }

}
