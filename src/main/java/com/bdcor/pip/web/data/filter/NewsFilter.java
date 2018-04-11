package com.bdcor.pip.web.data.filter;

import com.bdcor.pip.core.persistence.domain.BaseFilter;


public class NewsFilter extends BaseFilter{
	
	private String name;
	
	private String title;

	private String channel;
	
	
	
	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
