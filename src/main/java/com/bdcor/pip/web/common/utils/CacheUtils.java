package com.bdcor.pip.web.common.utils;

/**
 * Description:
 * User: Huang rp
 * Date: 2015/12/24 21:44
 * Version: 1.0
 */

import com.bdcor.pip.data.util.ReflectionUtils;
import com.bdcor.pip.web.common.dao.PipSysParameterMapper;
import com.bdcor.pip.web.common.domain.PipSysParameter;
import com.bdcor.pip.web.common.domain.PipSysParameterExample;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 组装缓存数据
 */
public class CacheUtils {

    /**
     * 系统参数
     */
    public static final String CACHE_SYS_PARAMETER = "parameterCache";

	/*系统参数*/
    /**
     * 标签
     */

    @Autowired
    PipSysParameterMapper sysParameterMapper;// 系统参数

    /**
     * 执行缓存的初始化操作
     */
    public void init() {
        parameterCacheInit();
    }

    /**
     * 刷新特定缓存
     *
     * @param cacheName
     */
    public void refreshCache(String cacheName) {
        if (CACHE_SYS_PARAMETER.equals(cacheName)) {
            parameterCacheInit();
        }
    }


    /**
     * 初始化系统参数缓存
     */
    public void parameterCacheInit() {
        PipSysParameterExample parameterExample = new PipSysParameterExample();
        parameterExample.createCriteria().andIsValidEqualTo((short) 1);
        parameterExample.setOrderByClause("type,sequence");
        List<PipSysParameter> sysParameterList = sysParameterMapper.selectByExample(parameterExample);
        Cache parameterCache = getCacheManager().getCache(CACHE_SYS_PARAMETER);
        Map<String, List<PipSysParameter>> paraMap = Maps.newHashMap();
        for (PipSysParameter para : sysParameterList) {// 缓存的key为paraType
            // value为SysParameter对象
            String paraType = para.getType();
            if (paraMap.get(paraType) == null) {
                List<PipSysParameter> paraList = Lists.newArrayList();
                paraList.add(para);
                paraMap.put(paraType, paraList);
            } else {
                paraMap.get(paraType).add(para);
            }
        }
        for (Map.Entry<String, List<PipSysParameter>> paraList : paraMap
            .entrySet()) {
            parameterCache.put(new Element(paraList.getKey(), paraList
                .getValue()));
        }
    }

    public static CacheManager getCacheManager() {
        return CacheManager.getInstance();
    }

    /**
     * 得到某个缓存使用次数
     *
     * @param cacheName
     */
    public static void getStatistics(String cacheName) {
        getCacheManager().getCache(cacheName).getStatistics().getInMemoryHits();// 换取缓存命中次数
    }

    /**
     * 获取缓存对象
     *
     * @param cacheName
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getCacheValue(String cacheName, Object key) {
        Validate.notNull(cacheName);
        Validate.notNull(key);
        if (getCacheManager().getCache(cacheName) == null) {
            return null;
        }
        if (getCacheManager().getCache(cacheName).get(key) == null) {
            return null;
        }
        return (T) getCacheManager().getCache(cacheName).get(key)
            .getObjectValue();
    }

    public static <T> Object getCacheValueAttribute(String cacheName,
                                                    Object key, String attrName) {
        Validate.notNull(attrName);
        return ReflectionUtils.getFieldValue(getCacheValue(cacheName, key),
            attrName);
    }

    public static void setCallValue(String cacheName, Object key, Object value) {
        getCacheManager().getCache(cacheName).put(new Element(key, value));
    }


    /**
     * @param cacheName
     * @param type
     * @param code
     * @param value
     * @param <T>
     * @return 通过code获取value 或者通过value获取code
     */
    public static <T> Object getSysParameterCacheValueByTypeAndCode(
        Object type, String code, String value) {
        Validate.notNull(type);
        List<PipSysParameter> parametersByType = getCacheValue(CACHE_SYS_PARAMETER, type);
        for (PipSysParameter parameter : parametersByType) {
            if (code != null && parameter.getCode().equals(code)) {
                return parameter.getValue();
            }
            if (value != null && parameter.getValue().equals(value)) {
                return parameter.getCode();
            }
        }
        return null;
    }
}
