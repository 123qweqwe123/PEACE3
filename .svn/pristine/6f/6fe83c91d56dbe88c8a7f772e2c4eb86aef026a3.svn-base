package com.bdcor.pip.web.fee.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.core.exception.ServiceException;
import com.bdcor.pip.core.utils.GenerateKey;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.fee.dao.FeeManagerDao;
import com.bdcor.pip.web.fee.domain.FeeCategoryStatisVo;
import com.bdcor.pip.web.fee.domain.FeeDepartStatisVo;
import com.bdcor.pip.web.fee.domain.FeeRegister;
import com.bdcor.pip.web.fee.domain.ProjectCost;
import com.bdcor.pip.web.fee.filter.FeeFilter;
import com.bdcor.pip.web.fee.filter.FeeRegFilter;
import com.bdcor.pip.web.fee.service.FeeManagerService;
import com.bdcor.pip.web.sys.rbac.service.UserService;

@Service
@Transactional
public class FeeManagerServiceImpl  implements FeeManagerService{
	
	@Autowired
	FeeManagerDao feeManagerDao;  
	
	@Autowired
	UserService userService; 

	@Override
	public Map getFeeList(FeeFilter filter ) {     
		Map total_sum; 
		try {
			
			
			
			//按照配置的数据权限 过滤 数据   
			String sql = userService.getUserDataLimitSQL(Securitys.getUserId());   
			if(StringUtils.isBlank(filter.getLcc_code()))
			{   
				filter.setLcc_code("("+Securitys.getUser().getLccCode()+")"); 
			}else if(sql.indexOf(filter.getLcc_code())>-1){
				filter.setLcc_code("("+filter.getLcc_code()+")"); 
			}else if(sql.indexOf(filter.getLcc_code())==-1){
				filter.setLcc_code("('-100')"); 
			}   
			
			if (StringUtils.isBlank(filter.getProject_id())) {
				filter.setProject_id ( Securitys.getUser().getCurrent_projectId());
			} 
			
			List<ProjectCost> fee      =  getFeeManagerDao().getFeeList(filter);
			total_sum                  =  getFeeManagerDao().getLccFee(filter); 
			if(null==total_sum)
				total_sum = new HashMap(); 
			
			int area_total = 0;        
			
			for(ProjectCost pc  : fee){
				int unit_cost =  new Integer(pc.getType_sum()).intValue();//单位消耗
				int pn = new Integer(   pc.getPat_sum()  ).intValue();    //人数
				int type_cost  = unit_cost * pn;
				area_total += type_cost;
				pc.setLcc_fee_total( type_cost +"" );
			}
			total_sum.put("list", fee);
			return total_sum;
		} catch (Exception e) {
			throw new ServiceException("操作失败", e);
		}  
	}    
	
	@Override
	public List<FeeRegister> getFeeRegList(FeeRegFilter filter) {
		try {
			filter.setProjectName(Securitys.getCurrentProject());
			List<FeeRegister> fee = feeManagerDao.getFeeRegList(filter);
			
			return fee;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("操作失败", e);
		}   
	}   
	
	@Override
	public int save(FeeRegister fr) {
		
		try {
			if(null==fr.getId() || "".equals(fr.getId()))
			{
			  String feeId = GenerateKey.getKey(GenerateKey.PREFIX_FEEREG);
			  fr.setId(feeId);
			  
			  if(null==fr.getReg_date())
			     fr.setReg_date(new Date()); 
			  
			  fr.setProjectId( Securitys.getCurrentProject() );
			  fr.setProjectName(Securitys.getUser().getCurrent_projectName() );
			  fr.setRegistrant(Securitys.getUserId());
			 
			 }    
			
			  Calendar cl = Calendar.getInstance(); // 撤分 费用发生日期 便于统计
			  cl.setTime(fr.getReg_date());
			  fr.setTally_year(cl.get(cl.YEAR)+"");
			  fr.setTally_month(cl.get(cl.MONTH )+1 +""); 
			return feeManagerDao.save(fr);
			
		} catch (Exception e) {
			throw new ServiceException("操作失败", e);
		}  
	}     

	@Override
	public int update(FeeRegister fr) {
		
		try {
			return  feeManagerDao.update(fr);
		} catch (Exception e) { 
			throw new ServiceException("操作失败", e);
		}
	}

	@Override
	public int delete(String id) {
		
		return feeManagerDao.delete(id); 
	}  

	
	
	@Override
	public Map getFeeStatisticsByCategory(String date) {
		Date d = null; 
		try {
			   d = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			} catch (Exception e) {
				e.printStackTrace();
			}  
		
		  Calendar c = Calendar.getInstance();
		  if(d!=null)
		      c.setTime(d);
		  
		  List xAxisData = new ArrayList(); 
		  SimpleDateFormat fm = new SimpleDateFormat("yyyy年MM月");
		  
		  Map map = new HashMap();
		  String dateStr =  null;
		 
	      c.add(Calendar.MONTH, -5); 
	      dateStr = c.get(Calendar.YEAR)+""+(c.get(Calendar.MONTH )+1);
	      xAxisData.add(fm.format(c.getTime()));
	      map.put("x1",dateStr);
	      c.add(Calendar.MONTH, 1);
	      dateStr = c.get(Calendar.YEAR)+""+(c.get(Calendar.MONTH )+1);
	      xAxisData.add(fm.format(c.getTime()));
	      map.put("x2",dateStr);
	      c.add(Calendar.MONTH, 1);
	      dateStr = c.get(Calendar.YEAR)+""+(c.get(Calendar.MONTH )+1);
	      xAxisData.add(fm.format(c.getTime()));
	      map.put("x3",dateStr);
	      c.add(Calendar.MONTH, 1);
	      dateStr = c.get(Calendar.YEAR)+""+(c.get(Calendar.MONTH )+1);
	      xAxisData.add(fm.format(c.getTime()));
	      map.put("x4",dateStr);
	      c.add(Calendar.MONTH, 1);
	      dateStr = c.get(Calendar.YEAR)+""+(c.get(Calendar.MONTH )+1);
	      xAxisData.add(fm.format(c.getTime()));
	      map.put("x5",dateStr);
	      c.add(Calendar.MONTH, 1);
	      dateStr = c.get(Calendar.YEAR)+""+(c.get(Calendar.MONTH )+1);
	      xAxisData.add(fm.format(c.getTime()));
	      map.put("x6",dateStr);
	      map.put("projectId", Securitys.getCurrentProject());
	      
	      
	      List<FeeCategoryStatisVo> qdata = feeManagerDao.getFeeLineByCatagory(map);
	      List rdata = new ArrayList();
	      Map map2 = new HashMap();
	      for(FeeCategoryStatisVo v:qdata){
	    	  Map m = new HashMap();
	    	  m.put("name", v.getReg_type());
		      m.put("type", "line"); 
		      List l = new ArrayList();
		      l.add( null==v.getX1()?"-":v.getX1());
		      l.add( null==v.getX2()?"-":v.getX2() );
		      l.add( null==v.getX3()?"-":v.getX3() );
		      l.add( null==v.getX4()?"-":v.getX4() );
		      l.add( null==v.getX5()?"-":v.getX5() );
		      l.add( null==v.getX6()?"-":v.getX6() );
		      m.put("data", l); 
	    	  rdata.add(m);
	      } 
	      map2 = new HashMap();
	      map2.put("series", rdata);
	      map2.put("xAxis", xAxisData);
	    return map2;
	} 

	@Override
	public Map getFeeStatisticsByDepart( String dateTo) {
		Date d = null; 
		try {
			   d = new SimpleDateFormat("yyyy-MM-dd").parse(dateTo);
			} catch (Exception e) {
				e.printStackTrace();
			}  
		
		  Calendar c = Calendar.getInstance();
		  if(d!=null)
		      c.setTime(d);
		  
		  List xAxisData = new ArrayList(); 
		  SimpleDateFormat fm = new SimpleDateFormat("yyyy年MM月");
		  
		  Map map = new HashMap();
		  String dateStr =  null;
		 
	      c.add(Calendar.MONTH, -5); 
	      dateStr = c.get(Calendar.YEAR)+""+(c.get(Calendar.MONTH )+1);
	      xAxisData.add(fm.format(c.getTime()));
	      map.put("x1",dateStr);
	      c.add(Calendar.MONTH, 1);
	      dateStr = c.get(Calendar.YEAR)+""+(c.get(Calendar.MONTH )+1);
	      xAxisData.add(fm.format(c.getTime()));
	      map.put("x2",dateStr);
	      c.add(Calendar.MONTH, 1);
	      dateStr = c.get(Calendar.YEAR)+""+(c.get(Calendar.MONTH )+1);
	      xAxisData.add(fm.format(c.getTime()));
	      map.put("x3",dateStr);
	      c.add(Calendar.MONTH, 1);
	      dateStr = c.get(Calendar.YEAR)+""+(c.get(Calendar.MONTH )+1);
	      xAxisData.add(fm.format(c.getTime()));
	      map.put("x4",dateStr);
	      c.add(Calendar.MONTH, 1);
	      dateStr = c.get(Calendar.YEAR)+""+(c.get(Calendar.MONTH )+1);
	      xAxisData.add(fm.format(c.getTime()));
	      map.put("x5",dateStr);
	      c.add(Calendar.MONTH, 1);
	      dateStr = c.get(Calendar.YEAR)+""+(c.get(Calendar.MONTH )+1);
	      xAxisData.add(fm.format(c.getTime()));
	      map.put("x6",dateStr);
	      map.put("projectId", Securitys.getCurrentProject());
	      
	      
	      List<FeeDepartStatisVo> qdata = feeManagerDao.getFeeLineByDepart(map);
	      List rdata = new ArrayList();
	     
	      for(FeeDepartStatisVo v:qdata){
	    	  Map m = new HashMap();
	    	  m.put("name", v.getReg_depart());
		      m.put("type", "bar"); 
		     
		      
		      
		      List l = new ArrayList();
		      l.add( null==v.getX1()?"-":v.getX1());
		      l.add( null==v.getX2()?"-":v.getX2() );
		      l.add( null==v.getX3()?"-":v.getX3() );
		      l.add( null==v.getX4()?"-":v.getX4() );
		      l.add( null==v.getX5()?"-":v.getX5() );
		      l.add( null==v.getX6()?"-":v.getX6() );
		      m.put("data", l);  
	    	  rdata.add(m); 
	      }   
	      map = new HashMap();
	      map.put("series", rdata);
	      map.put("xAxis", xAxisData);
	    return map;
		
	}    

	public FeeManagerDao getFeeManagerDao() {
		return feeManagerDao;
	}

	public void setFeeManagerDao(FeeManagerDao feeManagerDao) {
		this.feeManagerDao = feeManagerDao;
	}

	@Override
	public FeeRegister getById(String id) {
		
		
		
		return feeManagerDao.getById(id);
	}

	

	
	
 }
