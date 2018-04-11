package com.bdcor.pip.web.common.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdcor.pip.web.data.dao.PipCommLccMsgMapper;
import com.bdcor.pip.web.data.domain.PipCommLccMsg;
import com.bdcor.pip.web.data.domain.PipCommLccMsgExample;
import com.bdcor.pip.web.pro.promgt.dao.LccDao;
import com.bdcor.pip.web.pro.promgt.domain.Lcc;
import com.bdcor.pip.web.pro.promgt.filter.LccFilter;
import com.bdcor.pip.web.pro.promgt.service.LccService;

@Controller
@RequestMapping("websocket") 
public class WebSocketController {

	@Autowired
	private LccService lccService;
	
	@Autowired
	private LccDao lccDao;
	
	@Autowired
	private PipCommLccMsgMapper pipCommLccMsgDao;
	
	@RequestMapping(value="interactive",method = RequestMethod.GET)
	public  String   interactive(Model model){
		return "interactive/index";
	} 
	
	@RequestMapping(value="getLcc",produces="application/json")
	public  @ResponseBody List getLcc(@RequestParam("projectId")String projectId,
			@RequestParam("lccCode")String lccCode){
		return this.lccDao.getAllValidLccs( lccCode , projectId );
	}
	
	@RequestMapping(value="getLccMsgNum",produces="application/json")
	public  @ResponseBody List getLccMsgNum(@RequestParam("projectId")String projectId,
			@RequestParam("toLccCode")String toLccCode){
		return this.pipCommLccMsgDao.getLccMsgNum(toLccCode,projectId); 
	}
	
	@RequestMapping(value="getLccMsg",produces="application/json")
	public  @ResponseBody List getLccMsg(@RequestParam("projectId")String projectId,
			@RequestParam("toLcc")String toLcc,
			@RequestParam("fromLcc")String fromLcc){
		PipCommLccMsgExample example = new PipCommLccMsgExample();
		example.createCriteria()
			.andIsReadEqualTo("2")
			.andProjectIdEqualTo(projectId)
			.andFromLccCodeEqualTo(fromLcc)
			.andToLccCodeEqualTo(toLcc);
			
		List<PipCommLccMsg> list = this.pipCommLccMsgDao.selectByExample(example); 
		Date start = null;
		for (PipCommLccMsg msg : list ){
			if ( start == null ){
				start = msg.getCreateDate();
			}
			msg.setIsRead("1");
			this.pipCommLccMsgDao.updateByPrimaryKey(msg);
			if ( start.getTime() > msg.getCreateDate().getTime() ){
				start = msg.getCreateDate();
			}
		}
		if ( start == null ){
			start = new Date();
		}
		/*PipCommLccMsgExample e2 = new PipCommLccMsgExample();
		e2.createCriteria().andFromLccCodeEqualTo(toLcc)
			.andToLccCodeEqualTo(fromLcc)
			.andCreateDateGreaterThanOrEqualTo(start);
		List<PipCommLccMsg> list2 = this.pipCommLccMsgDao.selectByExample(e2); 
		list.addAll(list2);*/
		ComparatorMsg comparator = new ComparatorMsg();
		Collections.sort(list, comparator);
		return list; 
	}
	
	@RequestMapping(value="send")
	public void send(@RequestParam("projectId")String projectId,
			@RequestParam("fromLcc")String fromLcc,
			@RequestParam("toLcc")String toLcc,
			@RequestParam("msg")String msg){
		PipCommLccMsg m = new PipCommLccMsg();
		m.setCreateDate(new Date());
		m.setFromLccCode(fromLcc);
		m.setIsRead("2");
		m.setMessage(msg);
		m.setProjectId(projectId);
		m.setToLccCode(toLcc);
		this.pipCommLccMsgDao.insert(m);
	}
	
	 class ComparatorMsg implements Comparator{

		 public int compare(Object arg0, Object arg1) {
			 PipCommLccMsg msg0 = (PipCommLccMsg) arg0;
			 PipCommLccMsg msg1 = (PipCommLccMsg) arg1;
			 return msg0.getCreateDate().compareTo(msg1.getCreateDate());
		 }  
	}
	
}
