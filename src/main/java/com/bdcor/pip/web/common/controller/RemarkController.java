/**
 * 
 */
package com.bdcor.pip.web.common.controller;

import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.common.dao.PipCommRemarkMapper;
import com.bdcor.pip.web.common.domain.PipCommRemark;
import com.bdcor.pip.web.common.service.RemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author rp
 *
 */
@Controller
@RequestMapping("remark")
public class RemarkController {
   
   @Autowired
   PipCommRemarkMapper remarkDao;
   @Autowired
   RemarkService remarkService;
   
   /**
    * 获取备注列表
    */
   @RequestMapping("openmodalRemarkList")
   public String getRemarkList(PipCommRemark remark,Model model){
//      PipCommRemarkExample example=new PipCommRemarkExample();
//      example.createCriteria().andPk1EqualTo(remark.getPk1()).
//         andPk2EqualTo(remark.getPk2()).
//         andPk3EqualTo(remark.getPk3()).
//         andModuleIdEqualTo(remark.getModuleId()).
//         andIsDeleteEqualTo((short)1);
//      List<PipCommRemark> remarkList=remarkDao.selectByExample(example);
      Map<Short,List<PipCommRemark>> remarkByModule=remarkService.getRemakListByPk(remark);
      //remarkService.getRemakListByPk(remark);
      model.addAttribute("remarkModuleMap", remarkByModule);
      model.addAttribute("remark", remark);
      return "common/remark";
   }
   /**
    * 删除备注
    * @param remark
    * @return
    */
   @RequestMapping(value="delete")
   @ResponseBody
   public ResponseEntity<?> deleteRemark(PipCommRemark remark){
       Map<String,Object> res = new HashMap<String,Object>();
       try{
          remark.setIsDelete((short)2);//删除
          remarkDao.updateByPrimaryKeySelective(remark);
          res.put("success", true);
       }catch(Exception e){
          res.put("success", false);
       }
       return new ResponseEntity(res,HttpStatus.OK);
   }
   /**
    * 添加备注
    * @param remark
    * @return
    */
   @RequestMapping(value="add")
   @ResponseBody
   public ResponseEntity<?> addRemark(PipCommRemark remark){
       Map<String,Object> res = new HashMap<String,Object>();
       try{
//          remark.setId(Integer.parseInt(GenerateKey.getKey(GenerateKey.PREFIX_PIP_COMMON_REMARK)));
          remark.setUpdateTime(new Date());
          remark.setUpdateUserId(Securitys.getUserId());
          remark.setUpdateBy(Securitys.getUserName());
          remarkDao.insertSelective(remark);
          res.put("success", true);
       }catch(Exception e){
          res.put("success", false);
       }
       return new ResponseEntity(res,HttpStatus.OK);
   }

  
}
