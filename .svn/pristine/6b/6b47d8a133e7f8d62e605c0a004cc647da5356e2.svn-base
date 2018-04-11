package com.bdcor.pip.web.pro.promgt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.pro.promgt.domain.LinkMan;
import com.bdcor.pip.web.pro.promgt.filter.LinkManFilter;

@MyBatisRepository
public interface LinkManDao {

	List<LinkMan> getAllLinkMans(LinkManFilter filter);

	LinkMan getLinkManByCode(@Param("linkManCode") String linkManCode);

	void updateLinkMan(LinkMan linkMan);

	void addLinkMan(LinkMan linkMan);

	void delete(@Param("linkManCode") String linkManCode);

}
