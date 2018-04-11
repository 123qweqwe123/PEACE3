package com.bdcor.pip.web.pro.promgt.service.patientExcelToDb;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import com.bdcor.pip.core.utils.StringUtils;
import com.bdcor.pip.web.data.dao.PipCommPatientHistoryMapper;
import com.bdcor.pip.web.data.dao.PipCommPatientInputMapper;
import com.bdcor.pip.web.data.domain.PipCommPatientHistory;
import com.bdcor.pip.web.data.domain.PipCommPatientHistoryExample;
import com.bdcor.pip.web.data.domain.PipCommPatientInput;
import com.bdcor.pip.web.pro.promgt.service.patientExcelResolving.ExcelDataTransToObjWithPOI;
import com.bdcor.pip.web.pro.promgt.service.patientExcelResolving.exception.ExcelDataException;
import com.bdcor.pip.web.pro.promgt.service.patientExcelResolving.obj.ExcelDataObj;
import com.bdcor.pip.web.pro.promgt.service.patientExcelResolving.obj.ExcelPatientObj;
import com.bdcor.pip.web.pro.promgt.service.patientExcelResolving.obj.ExcelPatientOfIdCordObj;
import com.bdcor.pip.web.pro.promgt.service.patientExcelResolving.obj.ExcelPatientOfOtherObj;
import com.bdcor.pip.web.pro.promgt.service.patientExcelTemplate.obj.AreaObj;

@Component
public class PatientExcelToDb{

	private static Logger log = LoggerFactory.getLogger(PatientExcelToDb.class);
	
	@Autowired
	PipCommPatientHistoryMapper pipCommPatientHistoryMapper;
	
	@Autowired
	PipCommPatientInputMapper pipCommPatientInputMapper;
	

	
	
	
	
	public void toDb(){
		log.info("开始受访者excel文件入库================================================");

		List<PipCommPatientHistory> importHistorys = notInToDbExcleFile();
		log.info("待导入的文件个数："+importHistorys.size());
		
		for(PipCommPatientHistory history:importHistorys){
			File importfile = new File(history.getFilePath(),history.getFileName());  
		
			log.info("开始导入文件："+importfile.getPath());
        	//excel数据读取
        	ExcelDataObj dataObj = null;
			try {
				dataObj = new ExcelDataTransToObjWithPOI(importfile).getExcelData();
			} catch (Exception e) {
				e.printStackTrace();
			}
        	
			if(dataObj==null){
				return;
			}
			
        	AreaObj areaObj = dataObj.getArea();
        	List<ExcelPatientObj> patientObjs = dataObj.getObjs();
        	for(ExcelPatientObj pObj:patientObjs){
        		PipCommPatientInput pInput = newPatient(areaObj,history,pObj);
        		
        		
        		if(pObj instanceof ExcelPatientOfIdCordObj){
        			ExcelPatientOfIdCordObj idCard = (ExcelPatientOfIdCordObj)pObj;
        			//证件号
        			pInput.setCredentialsType(null);
        			//性别
					String ns = idCard.getIdNumber().substring(idCard.getIdNumber().length() - 2 , idCard.getIdNumber().length() - 1);
					if ( idCard.getIdNumber().length() == 15 ){
						ns = idCard.getIdNumber().substring(14, 15);
					}
					Integer n = Integer.parseInt(ns);
					if ( n % 2 == 0 ){
						pInput.setSex("2");
					}else{
						pInput.setSex("1");
					}
					//生日
					String dtxt = idCard.getIdNumber().substring(6,14);
					if ( idCard.getIdNumber().length() == 15){
						dtxt = "19" + idCard.getIdNumber().substring(6,12);
					}
					try{
						SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
						Date dd = sdf.parse(dtxt);
						pInput.setBirthday(dd);
					}catch(Exception ex){}
        		
        		}else if(pObj instanceof ExcelPatientOfOtherObj){
        			ExcelPatientOfOtherObj other = (ExcelPatientOfOtherObj)pObj;
        			//证件号
        			pInput.setCredentialsType(certType(other.getCertType()));
        			//性别
        			if(StringUtils.isNotBlank(other.getSex())){
        				if (other.getSex().equals("男")){
    						pInput.setSex("1");
    					}else if (other.getSex().equals("女")){
    						pInput.setSex("2");
    					}
        			}
            		//生日
//        			pInput.setBirthday(birthday)
        		}
        		this.pipCommPatientInputMapper.insert(pInput);
        	}//excel中的一行数据
        	
        	
        	log.info("完成导入文件："+importfile.getPath());
			
        	PipCommPatientHistoryExample hExample = new PipCommPatientHistoryExample();
        	hExample.createCriteria().andFileNameEqualTo(importfile.getName());
        	List<PipCommPatientHistory> updateHisList = this.pipCommPatientHistoryMapper.selectByExample(hExample);
        	for(PipCommPatientHistory updateHis:updateHisList){
        		updateHis.setInsertToDbSuc(new Short((short) 1));
        		updateHis.setRowNum(patientObjs.size());
        		this.pipCommPatientHistoryMapper.updateByPrimaryKey(updateHis);
        	}
        	
		}//文件处理完
		log.info("完成受访者excel文件入库================================================");
	}
	
	
	
	/**
	 * 未处理的excel
	 * @return
	 */
	private List<PipCommPatientHistory> notInToDbExcleFile(){
		
		PipCommPatientHistoryExample example = new PipCommPatientHistoryExample();
		example.createCriteria()
		.andInsertToDbSucEqualTo((short)0)
		.andCheckedSucEqualTo((short)1);
		List<PipCommPatientHistory> patientsHistory = pipCommPatientHistoryMapper.selectByExample(example);
		return patientsHistory;
		
		
	}
	
	/**
	 * 证件号编码
	 * @param typeName
	 * @return
	 */
	private String certType(String typeName){
		String type = "";
		if ( typeName.equals("居民户口簿")){
			type = "02";
		}else if ( typeName.equals("护照")){
			type = "03";
		}else if ( typeName.equals("军官证")){
			type = "04";
		}else if ( typeName.equals("驾驶证")){
			type = "05";
		}else if ( typeName.equals("港涣居民来往内地通行证")){
			type = "06";
		}else if ( typeName.equals("台湾居民来往内地通行证")){
			type = "07";
		}else if ( typeName.equals("其他法定有效证件")){
			type = "99";
		}
		return type;
	}
	
	/**
	 * 新对象
	 * @param areaObj
	 * @param history
	 * @return
	 */
	private PipCommPatientInput newPatient(AreaObj areaObj,PipCommPatientHistory history,ExcelPatientObj pObj){
		PipCommPatientInput po = new PipCommPatientInput();
		po.setProjectId(history.getProjectId());
		Date cd = new Date();
		po.setCreateDate(cd);
		Date cd2 = new Date();
		cd2.setTime(cd.getTime() + 24L * 3600 * 1000 );
		po.setUpdateDate(cd2);
		po.setIsRemoved(new Short("0"));
		
		po.setAddress(areaObj.toString());
		
		po.setNowProvenceCode(areaObj.getProvinceId());
		po.setNowCityCode(areaObj.getCityId());
		po.setNowCountyCode(areaObj.getDistrictId());
		po.setNowTownCode(areaObj.getTownId());
		po.setNowVillageCode(areaObj.getVillageId());
		
		po.setPatientName(pObj.getPatientName());
		po.setHelpCode(this.nameToFirstLeter(pObj.getPatientName()));
		po.setIdNumber(pObj.getIdNumber());
		po.setPhone(pObj.getPhone());
		po.setMobile(pObj.getMobile());
		po.setLccCode(pObj.getLccCode());
		
		po.setIsConform((short)2);
		po.setIsConsent((short)2);
		
		//设置id
		List<Map<String,Object>> keys = this.pipCommPatientInputMapper.getEntityId();
		if ( keys != null && keys.size() == 1 ){
			po.setId(((BigDecimal)keys.get(0).get("KEY")).toString());
		}else{
			po.setId("1");
		}
		return po;
	}
	
	private String nameToFirstLeter(String name){
		if(StringUtils.isBlank(name)){
			return "";
		}
		
		HanyuPinyinOutputFormat format= new HanyuPinyinOutputFormat();

		format.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);
		format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);

		StringBuffer sb = new StringBuffer();
		try{
			for(char c:name.toCharArray()){
				String[] pinyinArray = null;
				pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c, format);
				sb.append(pinyinArray[0].toUpperCase().charAt(0));
			}
		}catch(BadHanyuPinyinOutputFormatCombination e){
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	
}
