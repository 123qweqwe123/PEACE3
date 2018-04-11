package com.bdcor.pip.web.fee.dao;


import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.fee.domain.NCDepartVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@MyBatisRepository
public interface NCDepartVoMapper {
   /* *//**
     * 根据条件查询记录总数
     *//*
    int countByExample(Criteria example);

    *//**
     * 根据条件删除记录
     *//*
    int deleteByExample(Criteria example);*/

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String deptCode);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(NCDepartVo record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(NCDepartVo record);

    /**
     * 根据条件查询记录集
     *  List<NCDepartVo> selectByExample(Criteria example);
     */
   
    List<NCDepartVo> getAllNCDepart();

    /**
     * 根据主键查询记录
     */
    NCDepartVo selectByPrimaryKey(String deptCode);

    /**
     * 根据条件更新属性不为空的记录
     */
   
    /**
     * 根据条件更新记录
     * 
     *  int updateByExampleSelective(@Param("record") NCDepartVo record, @Param("example") Criteria example);
        int updateByExample(@Param("record") NCDepartVo record, @Param("example") Criteria example);

     */
   
    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(NCDepartVo record); 

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(NCDepartVo record);
}