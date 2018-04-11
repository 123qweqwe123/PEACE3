package com.bdcor.pip.web.spem.controller;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.core.utils.StringUtils;
import com.bdcor.pip.web.spem.domain.BillEventVo;
import com.bdcor.pip.web.spem.domain.IceBoxRegVo;
import com.bdcor.pip.web.spem.domain.SpBoxVo;
import com.bdcor.pip.web.spem.domain.WayBillVo;
import com.bdcor.pip.web.spem.filter.SpBoxFilter;
import com.bdcor.pip.web.spem.filter.TubeFilter;
import com.bdcor.pip.web.spem.filter.WayBillFilter;
import com.bdcor.pip.web.spem.service.BillEventService;
import com.bdcor.pip.web.spem.service.IceBoxRegService;
import com.bdcor.pip.web.spem.service.SpBoxService;
import com.bdcor.pip.web.spem.service.ViewExcel;
import com.bdcor.pip.web.spem.service.WayBillVoService;
import com.bdcor.pip.web.spem.utils.SysCodeToNameUtil;

@Controller
@RequestMapping("spem/waybill") 
public class WayBillController {
	
	
	private static final String SpBoxFilter = null;
	@Autowired
	private IceBoxRegService iceBoxRegService; 
	@Autowired
	private SpBoxService   spBoxService; 
	@Autowired
	private WayBillVoService   wayBillVoService; 
		
	@Autowired
	private BillEventService  billEventService; 
	
	@RequestMapping(value="forCreate",method = RequestMethod.POST) 
	public  ModelAndView   forCreateWayBill(HttpServletRequest req){
		ModelAndView model = new ModelAndView("spem/createWayBill"); 
		try {
			model.addObject("showboxdata", req.getParameter("showboxdata"));
			//对冻存盒列表进行排序
			String boxdata=req.getParameter("boxdata");
			String[]boxdatas= boxdata.split(",");
			Arrays.sort(boxdatas);
			StringBuffer sb=new StringBuffer();
			for(String data:boxdatas){
				sb.append(data).append(",");
			}
			model.addObject("boxdata", sb.toString());
			//req.setAttribute("boxdata", req.getParameter("boxdata"));
			model.addObject("remark", req.getParameter("remark"));
			model.addObject("boxType", req.getParameter("boxType"));
			model.addObject("lcc_code", req.getParameter("lcc_code"));
			
			String projectId =  Securitys.getCurrentProject();
			int i = spBoxService.countByProjectId(projectId);
			NumberFormat nf = NumberFormat.getInstance();
	        //设置是否使用分组
	        nf.setGroupingUsed(false);
	        //设置最大整数位数
	        nf.setMaximumIntegerDigits(6);
	        //设置最小整数位数   
	        nf.setMinimumIntegerDigits(6);
	       
	        String id= projectId+nf.format(i+1);
			
			model.addObject("id",id);
			
		} catch (Exception e) {
			e.printStackTrace(); 
		} 
		
		return model;    
	}  
	
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "createWayBill" )
	@ResponseBody
	public ModelAndView  CreateWayBill( WayBillVo vo ){
		//重定向防止重复提交
		ModelAndView model = new ModelAndView("redirect:/spem/waybill/createWayBillSuccess"); 
		    vo.setApplyDate(new Date());
		    vo.setApplyer(Securitys.getUser().getId());
		    //vo.setLccCode(Securitys.getUser().getLccCode());  运单是 运送哪个单位的 冻存盒 不是 用户的单位
		    vo.setProjectId(Securitys.getCurrentProject());
		    vo.setCreateBy(Securitys.getUser().getId() );
		    vo.setCreateDate(new Date());
		    int i = wayBillVoService.createWayBill(vo);
			
			return model;
	}  
	
	
	@RequestMapping(value = "createWayBillSuccess" )
	@ResponseBody
	public ModelAndView  createWayBillSuccess( WayBillVo vo ){
		ModelAndView model = new ModelAndView("spem/createWayBillSuccess"); 
		return model;
	} 
	
	
	/**
	 *  中心 处理 运单
	 * @return
	 */
	@RequestMapping(value="forQueryBill") 
	public  ModelAndView   forQueryBill(){
		ModelAndView model = new ModelAndView("spem/spemList"); 
		
		return model;    
	}     
	
	/**
	 * 协作单位处理 运单
	 * @return
	 */
	@RequestMapping(value="forQueryBill_lcc") 
	public  ModelAndView   forQueryBill_lcc(){
		ModelAndView model = new ModelAndView("spem/spemList_lcc"); 
		
		return model;    
	}   
	
	
	/**
	 * 中心 查询 运单
 	 * @param filter
	 * @return
	 */
	@RequestMapping(value="list",produces="application/json")
	public  @ResponseBody JqgridResponse   list(WayBillFilter filter){   
		
		List<WayBillVo> data = wayBillVoService.list(filter);  
		JqgridResponse<WayBillVo> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(data);  
		
		return response;  
	}  
	
	
	/**
	 * lcc  查询 运单
 	 * @param filter
	 * @return
	 */
	@RequestMapping(value="list_lcc",produces="application/json")
	public  @ResponseBody JqgridResponse   list_lcc(WayBillFilter filter){   
		
		List<WayBillVo> data = wayBillVoService.list_lcc(filter);  
		JqgridResponse<WayBillVo> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(data);  
		
		return response;  
	}    
	/**
	 * 运单  状态  操作
	 * @return
	 */
	@RequestMapping(value="processBill") 
	public  ResponseEntity   processBill(String current_event_code,String waybill_no){
		
		int i = wayBillVoService.processBill(current_event_code, waybill_no);
		
		if(i>0)
		{
			Map<String, Object> res = new HashMap<String, Object>();
	        
	        res.put("success", true);
	        
	        return new ResponseEntity(res, HttpStatus.OK);  
		}
		return null;
	}   
	       
	
	/**
	 *  运单 明细  查看
	 * @return
	 */
	@RequestMapping(value="bill_detail") 
	public  ModelAndView   Bill_Detail(String waybill_no){
		ModelAndView model = new ModelAndView("spem/wayBillView"); 
		WayBillVo vo = wayBillVoService.selectByPrimaryKey(waybill_no);
		model.addObject("vo", vo);
		return model;    
	}     
	/**
	 * 冻存盒列表 带分页
	 * @param fi
	 * @return
	 */
	@RequestMapping(value="bill_detail_boxList",produces="application/json")
	public  @ResponseBody JqgridResponse   bill_detail_boxList(SpBoxFilter fi){  
		
		List<SpBoxVo> bl = spBoxService.spboxListByWaybillNoByfilter(fi); 
	    JqgridResponse<SpBoxVo> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(bl);  
		
		return response;  
	}    
	/**
	 * 冻存 管  列表 带分页
	 * @param fi
	 * @return
	 */
	@RequestMapping(value="bill_detail_tubeList",produces="application/json")
	public  @ResponseBody JqgridResponse   bill_detail_tubeList(TubeFilter fi){  
		List<Map> tubeList = spBoxService.microListBywaybill_no(fi);
	    JqgridResponse<Map> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(tubeList);  
		
		return response;  
	}             
	/**
	 * 运送单 事件 列表
	 * @param fi
	 * @return
	 */
	@RequestMapping(value="bill_detail_evList",produces="application/json")
	public  @ResponseBody JqgridResponse   bill_detail_evList(String waybill_no){  
		
		List<BillEventVo> data = billEventService.selectByExample(waybill_no);
	    JqgridResponse<BillEventVo> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(data);  
		
		return response;   
	}       
	
	
	@RequestMapping(value="openmodal",method = RequestMethod.GET)
	public  ModelAndView   openForm(Model model,String id){ 
		  ModelAndView mav = new ModelAndView("spem/iceboxRegForm"); 
		  IceBoxRegVo ibr = null;
	       if (!StringUtils.isBlank(id)) {
	           ibr = iceBoxRegService.getById(id);
	           
	           ibr.setProjectName(SysCodeToNameUtil.getProjectNameByCode(ibr.getProjectid() ) );
	           
	           ibr.setLcc_name(SysCodeToNameUtil.getLccNameByCode(ibr.getLccid()));
	        }else{ 
		       ibr = new IceBoxRegVo();
		       ibr.setProjectid(Securitys.getCurrentProject() );
		       ibr.setProjectName(Securitys.getUser().getCurrent_projectName());
		       ibr.setLccid(Securitys.getUser().getLccCode());
		       ibr.setLcc_name(Securitys.getUser().getLccName());
		   }  
	       mav.addObject("ibr", ibr); 
		  return mav;
    }     
	

	@RequestMapping(value="delete",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Boolean delete(@RequestParam(value="id",required=true)String id){
		try{
			wayBillVoService.delete(id);
			return true;
		}catch(Exception e){ 
			return false;
		}   
	}
	
	
	@RequestMapping(value="selectBox",method = RequestMethod.GET)
	public  ModelAndView   selectBox(String id,String spemType){   
		  ModelAndView mav = new ModelAndView("spem/selectBoxForm");  
		  List<?> data = spBoxService.selectByExample(id,spemType);  
		  mav.addObject("option", data) ;
		  
		 
		  return mav;   
    }   
	
	
	@RequestMapping(value="microList",produces="application/json")
	public  @ResponseBody JqgridResponse   microList(TubeFilter filter){  
		
		List<Map> data = spBoxService.microList(filter);  
		JqgridResponse<Map> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(data);  
		
		return response;  
	}   
	@SuppressWarnings("unchecked")
	@RequestMapping("export2excel")  
	public ModelAndView viewExcel(WayBillVo vo) {  
	        Map model = new HashMap(); 
	        String boxCodes=vo.getBoxCodes();
	        List<Map> datas=new ArrayList<Map>();
	        if(boxCodes!=null&&"1".equals(vo.getType())){
	        	String[] codes=boxCodes.split(",");
	        	for(String code:codes){
	        		TubeFilter t= new TubeFilter();
	        		t.setBoxId(code.substring(0,code.length()-1));
	        		t.setBoxType(code.substring(code.length()-1));
	        		t.setRows(100000);//设置10万,如果数据量太大考虑分页了
	        		List<Map> data = spBoxService.microList(t);
	        		datas.addAll(data);
	        	}
	        }else if("2".equals(vo.getType())){
	        	TubeFilter t= new TubeFilter();
        		t.setWaybillno(vo.getWaybill_no());
        		t.setRows(100000);//设置10万,如果数据量太大考虑分页了
	        	List<Map> data = spBoxService.microListBywaybill_no(t);
        		datas.addAll(data);
	        }
	        model.put("list",datas);         
	        return new ModelAndView(new ViewExcel(), model);  
	 }  
} 
