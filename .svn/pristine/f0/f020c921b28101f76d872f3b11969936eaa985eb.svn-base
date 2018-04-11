package com.bdcor.pip.data.util;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;

import java.io.IOException;

/**
 * Mybatis配置文件出现问题Spring配置此类替换SqlSessionFactoryBean 打印问题日志
 * Created by zhang.rw on 16-7-12.
 */
public class FixedSqlSessionFactory extends SqlSessionFactoryBean {
    @Override
    protected SqlSessionFactory buildSqlSessionFactory() throws IOException {
        try {
            return super.buildSqlSessionFactory();
        }catch (Exception e){
            // 输出异常信息
            e.printStackTrace();
        }finally {
            ErrorContext.instance().reset();
        }
        return null;
    }
}
