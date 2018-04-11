package com.bdcor.pip.web.data.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.data.domain.NewsLcc;
import com.bdcor.pip.web.data.domain.NewsLccExample;

@MyBatisRepository
public interface NewsLccMapper {
    int countByExample(NewsLccExample example);

    int deleteByExample(NewsLccExample example);

    int insert(NewsLcc record);

    int insertSelective(NewsLcc record);

    List<NewsLcc> selectByExample(NewsLccExample example);

    int updateByExampleSelective(@Param("record") NewsLcc record, @Param("example") NewsLccExample example);

    int updateByExample(@Param("record") NewsLcc record, @Param("example") NewsLccExample example);
}