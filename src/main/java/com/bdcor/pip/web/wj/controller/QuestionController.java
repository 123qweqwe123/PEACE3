package com.bdcor.pip.web.wj.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bdcor.pip.client.tools.Result;
import com.bdcor.pip.client.tools.ResultBean;
import com.bdcor.pip.client.vo.paper.Paper;
import com.bdcor.pip.client.vo.paper.PaperService;
import com.bdcor.pip.core.utils.Securitys;

@Controller
@RequestMapping("question")
public class QuestionController {

	
	
	@RequestMapping(value = "save")
	public void save(HttpServletRequest request) {

//		User u = (User) request.getSession().getAttribute("loginUser");
		String loginName =Securitys.getUser().getLoginName();
		String chinaPeacePid = request.getParameter("pId").trim();
		String paperId = request.getParameter("paperid").trim();
		String   patientId=  request.getParameter("patientId");
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ResultBean resultBean = new ResultBean(request);
		Date nowDate = new Date();
		Result r = resultBean.getResult();
		r.setPatientID(chinaPeacePid);
//		r.setOperaterID(String.valueOf(u.getId()));
		r.setOperaterID(Securitys.getUser().getId() );
//		r.setOperaterName(u.getName());
		r.setOperaterName(Securitys.getUser().getName());
		r.setUQSBeginTime(request.getParameter("UQSBeginTime"));
		r.setUQSIsHold(request.getParameter("UQSIsHold"));
		r.setUQSRemark(request.getParameter("UQSRemark"));
		String thisBeginTime = request.getParameter("thisBeginTime");

		// 保存所有的患者问卷数据 ResultBean

	}
	
	@RequestMapping(value = "page")
	public String page(HttpServletRequest request, Model model) {

		  String ctxPath = request.getSession().getServletContext().getRealPath("/") 
	        		+ File.separator + "WEB-INF"
	        		+ File.separator + "static"+ File.separator +"questionxmltemplete"+ File.separator; 
		
		// 加载所有的xml问卷
		PaperService.getInstance().initPaper(ctxPath);
		List<Paper> papers = PaperService.papers;
	
		String   patientId=  request.getParameter("patientId");
		Paper paper = null;
		//获得医生所使用的问卷编号     也可以是问卷文件名称
		String   pagerId=  request.getParameter("pagerId");
		//先默认设置一个值
		if(pagerId==null || "".equals(pagerId)){
			pagerId = "004001";
		}
		
		// 问卷id号
		for (Paper p : papers) {
			if (pagerId.equals(p.getPaperId())) {
				paper = p;
			}

		}
		model.addAttribute("patientId", "11200522");
		model.addAttribute("paper", paper);
		return "question/list";
		
	}

}
