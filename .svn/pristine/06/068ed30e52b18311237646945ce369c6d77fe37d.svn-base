package com.bdcor.pip.web.common.tagsupport;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.bdcor.pip.dict.domain.DictCommDTO;
import com.bdcor.pip.dict.utils.DictUtils;
/**
 * 从缓存中由dicttype和code获取对应value
 * 前台jsp使用 <%@ taglib uri="http://com.bdcor.pip.web.common.tagsupport" prefix="dict" %>
 * @author rp
 *
 */
public class WriterDictTag  extends TagSupport{
	private String code;
	private String dictType;

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getDictType() {
		return dictType;
	}
	public void setDictType(String dictType) {
		this.dictType = dictType;
	}
	public int doStartTag() {
		String value;
		StringBuffer sb=new StringBuffer();
		value=sb.toString();
		JspWriter out = this.pageContext.getOut();
		StringBuffer scriptTag = new StringBuffer();
		scriptTag.append(getDictValue());
		scriptTag.append("\n"+value);
		try {
			out.write(scriptTag.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 1;
	}
	
	public String getDictValue(){
		List<DictCommDTO> dtoList=DictUtils.getDatasByType(dictType);
		for(DictCommDTO dto:dtoList){
			if(code.equals(dto.getvCode())){
				return dto.getvName();
			}
		}
		return "";
	}
		
	public int doAfterBody() throws JspException{
		return SKIP_BODY;
	}
	public int doEndTag() throws JspException{
		return EVAL_PAGE;
	}
}
