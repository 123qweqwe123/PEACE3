/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.data.constant 
 */

package com.bdcor.pip.data.constant;
/**  
 * description:  这个主要是设置导出时候的基础数据
 * @author yangfeng 创建时间：2016年1月12日         
 */
public class ExcelDataConstant
{
	/**
	 * 短信
	 */
	public final static String [] HEAD_MSG =  new String[]{"LCCID","单位名称","PID","患者姓名",//"身份证",
			"手机号","所属组别","短信内容","是否需要回复","预设发送时间","发送时间","是否发送","发送状态","回执信息","发送次数"
			//,"是否删除","创建时间","创建人"
	};
	public final static String [] DATA_MSG =  new String[]{"LCCID","LCCNAME","PATIENTID","PATIENTNAME",//"IDNUMBER",
		    "MOBILE","BGROUP","MSGNAME","ISNEEDREPLY","PRESENDTIME","REALSENDTIME","STATE","SENDRESULT","SENDREASON","SENDCOUNT"
			//,"ISDELETE","CREATETIME","CREATORNAME"
	};
	
	/**
	 * 血样列表导出 表头 
	 */
	public final static String [] HEAD_BLOODLIST =  new String[]{"盒号","血样编码","PID","患者姓名","LCCID","医院名称","问卷提交时间","随访类型","样本状态"};
	/**
	 * 血样列表导出 对应的数据的name
	 */
	public final static Object [] DATA_BLOODLIST = new String[]{"BOX_CODE","ANSWER","PATIENT_ID","PATIENT_NAME","LCC_CODE","LCC_NAME","END_TIME","UQS_VERSION","STATE"};
	
	/**
	 * 事件列表导出 表头 
	 */
	public final static String [] HEADERARRAY_EVENT =  new String[]{"LCCID","医院名称","PID","事件ID","姓名","事件分类","上报事件时间","入院日期","出院日期","住院医院","是否死亡事件","是否需收集文件","门诊类型"};
	/**
	 * 事件列表导出 对应的数据的name
	 */
	public final static Object [] DATANAMEARRAY_EVENT = new Object[]{"LCC_CODE","LCC_NAME","PATIENT_ID","EVENT_CODE","PATIENT_NAME","EVENT_TYPE","EVENT_DATE","IN_HOS_DATE","OUT_HOS_DATE","HOS_NAME","IS_DEATH","FILECOUNT","BELONGTYPE"};
	/**
	 * 采血器具包使用报表 表头
	 */
	public final static String [] HEAD_BLOOD =  new String[]{"LCC编码","库房名称","随访人数","死亡人数","发放","未使用","已使用","报损","过期"};
	/**
	 * 采血器具包使用报表 数据name
	 */
	public final static String [] DATA_BLOOD =  new String[]{"LCC_CODE","STOCK_NAME","SFRS","SWRS","FF","WSY","YSY","BS","GQ"};
	/**
	 * 事件进度报表 表头
	 */
	public final static String [] HEAD_EVENT =  new String[]{"LCCID","LCC名称","事件数","住院次数"};
	/**
	 * 事件进度报表 数据name
	 */
	public final static String [] DATA_EVENT =  new String[]{"LCC_CODE","LCC_NAME","SJZS","ZYZS"};
	
	/**
	 * 事件进度明细报表 表头
	 */
	public final static String [] HEAD_DETAIL_EVENT =  new String[]{"LCCID","医院名称","患者姓名","PID","事件ID","事件名称","入院时间","出院时间","住院医院名称"};
	/**
	 * 事件进度明细报表 数据name
	 */
	public final static String [] DATA_DETAIL_EVENT =  new String[]{"LCC_CODE","LCC_NAME","PATIENT_NAME","PATIENT_ID","EVENT_CODE","EVENT_NAME","IN_HOS_DATE","OUT_HOS_DATE","HOS_NAME"};
	/**
	 * 血检报表 表头
	 */
	public final static String [] HEAD_UQSANSWERQNBLOOD =  new String[]{"LCCID","医院名称","本院采血","外院采血","已录入","未录入"};
	/**
	 * 血检报表 数据name
	 */
	public final static String [] DATA_UQSANSWERQNBLOOD =  new String[]{"LCC_CODE","LCC_NAME","BYCYCOUNT","FBYSYCOUNT","YLRCOUNT","WLR"};
	/**
	 * 问卷完成情况报表 表头
	 */
	public final static String [] HEAD_UQSANSWERQNLOG =  new String[]{"LCCID","医院名称","应随访人数","死亡人数","首次随访","6月随访(干预)","6月随访(非干预)","6月随访(电话随访)","末次随访(干预末次)","末次随访(非干预末次)"};
	/**
	 * 问卷完成情况报表 数据name
	 */
	public final static String [] DATA_UQSANSWERQNLOG =  new String[]{"LCC_CODE","LCC_NAME","YSFRS","SWRS","SCSF","GY","FGY","UQSMOBILE","GYMC","FGYMC"};
	/**
	 * 未回复短信查询表头
	 */
	public final static String [] HEAD_MSGNOREPLY =  new String[]{"LCCID","PID","患者姓名","身份证","手机","所属组别","短信发送内容","发送时间"};
	/**
	 * 未回复短信查询 数据name
	 */
	public final static String [] DATA_MSGNOREPLY =  new String[]{"LCCID","PATIENTID","PATIENTNAME","IDNUMBER","MOBILE","BGROUP","MSGNAME","SENDTIME"};
	/**
	 * 回复短信查询表头
	 */
	public final static String [] HEAD_MSGREPLY =  new String[]{"LCCID","PID","患者姓名","身份证","手机","是否糖尿病","所属组别","短信发送内容","回复时间"};
	/**
	 * 回复短信查询 数据name
	 */
	public final static String [] DATA_MSGREPLY =  new String[]{"LCCID","PATIENTID","PATIENTNAME","IDNUMBER","MOBILE","ISDIABETES","BGROUP","MSGNAME","REPLYTIME"};

	/**
	 * 随机问卷完成统计表表头
	 */
	public final static String [] HEAD_UQSCOMPLETETAB =  new String[]{"LCCID","医院名称","PID","患者姓名","问卷状态","随机延迟时间(天)"};
	/**
	 * 随机问卷完成统计表 数据name
	 */
	public final static String [] DATA_UQSCOMPLETETAB =  new String[]{"LCCCODE","LCCNAME","PATIENTID","PATIENTNAME","STATE","DAYS"};

        /**
         * 血检报表 表头
         */
        public final static String [] HEAD_UQSANSWERQNBLOOD_2 =  new String[]{"LCCID","医院名称","已随访数","本院采血","外院采血","未采血数","已录入","未录入"};
        /**
         * 血检报表 数据name
         */
        public final static String [] DATA_UQSANSWERQNBLOOD_2 =  new String[]{"LCC_CODE","LCC_NAME","YSFS","BYCX","WYCX","WCX","YLR","WLR"};

        /**
         * 血检报表明细 表头
         */
        public final static String [] HEAD_UQSANSWERQNBLOOD_6 =  new String[]{"LCCID","医院名称","PID","患者姓名","门诊类型","采血地点","问卷完成时间","相距天数"};
        /**
         * 血检报表 数据name
         */
        public final static String [] DATA_UQSANSWERQNBLOOD_6 =  new String[]{"LCC_CODE","LCC_NAME","PATIENT_ID","PATIENT_NAME","TYPE","PLACE","END_TIME","DAYS"};

	/**
	 * 血检报表明细 表头
	 */
	public final static String [] HEAD_MSGNOREPLY_WATCH =  new String[]{"LCCID","PID","患者姓名","手机","所属组别","最近连续未回复时间","历史致电次数"};
	/**
	 * 血检报表 数据name
	 */
	public final static String [] DATA_UMSGNOREPLY_WATCH =  new String[]{"LCC_CODE","PATIENT_ID","PATIENT_NAME","MOBILE","BELONG_GROUP","SENDTIME_REAL","CON"};//"LCC_NAME",

	public final static String[] HEAD_PLANDATE_INFO = new String[]{"随访名称","LCCID","医院名称","PID","患者姓名","身份证","手机","第一联系人手机","理想时间","预约时间","相距天数","完成情况"};

	public final static String[] HEAD_CHAT_PLANDATE_INFO = new String[]{"随访名称","LCCID","医院名称","PID","患者姓名","身份证","手机","第一联系人手机","所属组别","理想时间","预约时间","相距天数","完成情况"};

	public final static String[] DATA_PLANDATE_INFO = new String[]{"VIEWNAME","LCC_CODE","LCC_NAME","PATIENT_ID","PATIENT_NAME","ID_NUMBER","MOBILE","LINK_MAN1_MOBILE","LXSJ","PLANDATE_EXPORT","XJTS","WCQK2"};

	public final static String[] DATA_CHAT_PLANDATE_INFO = new String[]{"VIEWNAME","LCC_CODE","LCC_NAME","PATIENT_ID","PATIENT_NAME","ID_NUMBER","MOBILE","LINK_MAN1_MOBILE","GROUPNAME","LXSJ","PLANDATE_EXPORT","XJTS","WCQK2"};

	public final static String[] HEAD_PATIENT_LIST = new String[]{"LCCID", "PID","患者姓名","身份证","手机","是否参加手机短信","是否糖尿病","首次随访","6月随访","12月随访","18月随访", "末次随访", "是否可答6月问卷", "用户名", "完成时间"};

	public final static String[] DATA_PATIENT_LIST = new String[]{"LCCCODE","ID","PATIENTNAME","IDNUMBER","MOBILE","ISJOINMSG","IS_DIABETES","UQS001","UQS002","UQS003","UQS004","UQS005","IS_6STATE","ACCOUNTNAME","FINISHTIME"};

}

