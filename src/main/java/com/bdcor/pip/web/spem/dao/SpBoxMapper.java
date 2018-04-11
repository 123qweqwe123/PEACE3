package com.bdcor.pip.web.spem.dao;


import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.spem.domain.SpBoxVo;
import com.bdcor.pip.web.spem.filter.SpBoxFilter;
import com.bdcor.pip.web.spem.filter.TubeFilter;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;

@MyBatisRepository
public interface SpBoxMapper {
	
	 /**
     * 按项目统计记录
     */   
    int countByProjectId(String projectId);
    
    int updateWayBillNo(SpBoxVo record);
    
    int removeWayBillNo(String waybill_no);
    
    int update_status(String waybill_no);
  
    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(SpBoxVo record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(SpBoxVo record);

    /**
     * 根据条件查询记录集
     */
    List<SpBoxVo> selectByExample(@Param("id")String id,@Param("lcc_code")String lcc_code,@Param("projectId")String projectId);  

    /**
     * 根据 运单号 获取 盒子 无分页
     */
    List<SpBoxVo> spboxListByWaybillNo(@Param("waybill_no")String waybill_no); 
    
    
    /**
     * 根据 运单号 获取 盒子 分页
     */
    List<SpBoxVo> spboxListByWaybillNoByfilter(SpBoxFilter filter);   
    /**
     * 盒子查询 带分页
     */
    List<SpBoxVo>  spboxListByfilter(SpBoxFilter filter);  
    
    /**
     * 根据 运单号 获取 管子
     */
    List<Map> microListBywaybill_no(TubeFilter filter); 
    /**
     * 根据主键查询记录
     */
    SpBoxVo selectByPrimaryKey(String id); 

  

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(SpBoxVo record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(SpBoxVo record); 
    
     /**
      * 获取 盒子的所有管子
      * @param filter
      * @return
      */
	List microList(TubeFilter filter);
}