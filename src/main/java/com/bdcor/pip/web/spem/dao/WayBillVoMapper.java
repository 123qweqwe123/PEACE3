package com.bdcor.pip.web.spem.dao;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.spem.domain.WayBillVo;
import com.bdcor.pip.web.spem.filter.WayBillFilter;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface WayBillVoMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(WayBillFilter example);
    
    
    List<WayBillVo> list_lcc(WayBillFilter example);
    /**
     * 根据 当前状态获取下一个事件
     * @param current_event_code
     * @return
     */
    Map getNextEventCode(String current_event_code); 

    /**
     * 根据条件删除记录
     */
    int deleteByExample(WayBillFilter example);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String waybill_no);
    
    /**
     * 根据主键删除记录 假删除
     */
    int delete(String waybill_no);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(WayBillVo record);

    /**
     * 保存属性不为空的记录
     */
    int createWayBill(WayBillVo record);

    /**
     * 根据条件查询记录集
     */
    List<WayBillVo> list(WayBillFilter example);

    /**
     * 根据主键查询记录
     */
    WayBillVo selectByPrimaryKey(String waybill_no);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("record") WayBillVo record, @Param("example") WayBillFilter example);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("record") WayBillVo record, @Param("example") WayBillFilter example);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(WayBillVo record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(WayBillVo record);
}