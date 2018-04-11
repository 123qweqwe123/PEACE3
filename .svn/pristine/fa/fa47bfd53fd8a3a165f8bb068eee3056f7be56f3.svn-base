package com.bdcor.pip.web.spem.dao;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.spem.domain.BillEventVo;
import com.bdcor.pip.web.spem.domain.BillEventVoKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@MyBatisRepository
public interface BillEventMapper {
    /**
     * 根据条件查询记录总数
     */
   /* int countByExample(Criteria example);*/

    /**
     * 根据条件删除记录
     */
    /*int deleteByExample(Criteria example);*/

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(BillEventVoKey key);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(BillEventVo record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(BillEventVo record);

    /**
     * 根据条件查询记录集
     */
    List<BillEventVo> selectByExample(String waybill_no);  

    /**
     * 根据主键查询记录
     */
    BillEventVo selectByPrimaryKey(BillEventVoKey key);  

    /**
     * 根据条件更新属性不为空的记录
     */
   /* int updateByExampleSelective(@Param("record") BillEventVo record, @Param("example") Criteria example);*/

    /**
     * 根据条件更新记录
     */
    /*int updateByExample(@Param("record") BillEventVo record, @Param("example") Criteria example);*/

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(BillEventVo record); 

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(BillEventVo record);
}