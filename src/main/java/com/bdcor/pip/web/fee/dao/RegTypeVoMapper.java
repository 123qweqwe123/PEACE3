package com.bdcor.pip.web.fee.dao;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.fee.domain.RegTypeVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@MyBatisRepository
public interface RegTypeVoMapper {
   /* *//**
     * 根据条件查询记录总数
     *//*
    int countByExample(Criteria example);

    *//**
     * 根据条件删除记录
     *//*
    int deleteByExample(Criteria example);
    *//**
     * 根据条件查询记录集
     *//*
    List<RegTypeVo> selectByExample(Criteria example);
    *//**
     * 根据条件更新属性不为空的记录
     *//*
    int updateByExampleSelective(@Param("record") RegTypeVo record, @Param("example") Criteria example);

    *//**
     * 根据条件更新记录
     *//*
    int updateByExample(@Param("record") RegTypeVo record, @Param("example") Criteria example);*/

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String typeCode);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(RegTypeVo record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(RegTypeVo record);

    
    
    List<RegTypeVo> getAll();

    /**
     * 根据主键查询记录
     */
    RegTypeVo selectByPrimaryKey(String typeCode);

    

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(RegTypeVo record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(RegTypeVo record);
}