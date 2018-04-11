package com.bdcor.pip.web.msg.domain;


public class PatientGroupVo {
	
	private String patientId;
	
	private int sex;//性别 1:男 2:女
	
	private int age_grade;//年龄阶段 1:55以下 2:55-64 3:65及以上
	
	private int medicalHis_MI;//有无心肌梗死病史 1:有 2:无
	
	private int edu_level;//教育水平 1:初中及以下 2:初中到高中 3:高中及以上
	
	private int medicalInsurance;//医保类型 1:城镇居民/职工医疗保险/公费医疗 2:农村合作医疗 3:完全自费
	
	private int belong_group;//1:实验组 2:对照组
	
	private int is_diabetes;//是否糖尿病 1:是 2:否
	
	private String groupStr;//01：非糖尿病实验组 02:非糖尿病对照组 11:糖尿病实验组 12:糖尿病对照组
	
	private static final int sex1_weight = 1;
	
	private static final int sex2_weight = 1;
	
	private static final int age_grade1_weight = 1;
	
	private static final int age_grade2_weight = 1;
	
	private static final int age_grade3_weight = 1;
	
	private static final int medicalHis_MI1_weight = 1;
	
	private static final int medicalHis_MI2_weight = 1;
	
	private static final int edu_level1_weight = 1;
	
	private static final int edu_level2_weight = 1;
	
	private static final int edu_level3_weight = 1;
	
	private static final int medicalInsurance1_weight = 1;
	
	private static final int medicalInsurance2_weight = 1;
	
	private static final int medicalInsurance3_weight = 1;
	
	private double group1_weight = 1;
	
	
	
	private  Double oldsum_g1_sex1;
	private  Double oldsum_g1_sex2;
	
	private  Double oldsum_g1_age1;
	private  Double oldsum_g1_age2;
	private  Double oldsum_g1_age3;
	
	private  Double oldsum_g1_mi1;
	private  Double oldsum_g1_mi2;
	
	private  Double oldsum_g1_edu1;
	private  Double oldsum_g1_edu2;
	private  Double oldsum_g1_edu3;
	
	private  Double oldsum_g1_medInsure1;
	private  Double oldsum_g1_medInsure2;
	private  Double oldsum_g1_medInsure3;
	
	
	
	private  Double oldsum_g2_sex1;
	private  Double oldsum_g2_sex2;
	
	private  Double oldsum_g2_age1;
	private  Double oldsum_g2_age2;
	private  Double oldsum_g2_age3;
	
	private  Double oldsum_g2_mi1;
	private  Double oldsum_g2_mi2;
	
	private  Double oldsum_g2_edu1;
	private  Double oldsum_g2_edu2;
	private  Double oldsum_g2_edu3;
	
	private  Double oldsum_g2_medInsure1;
	private  Double oldsum_g2_medInsure2;
	private  Double oldsum_g2_medInsure3;
	
	
	
	
	
	
	public static Double oldsum_t1_g1_sex1;
	public static Double oldsum_t1_g1_sex2;
	
	public static Double oldsum_t1_g1_age1;
	public static Double oldsum_t1_g1_age2;
	public static Double oldsum_t1_g1_age3;
	
	public static Double oldsum_t1_g1_mi1;
	public static Double oldsum_t1_g1_mi2;
	
	public static Double oldsum_t1_g1_edu1;
	public static Double oldsum_t1_g1_edu2;
	public static Double oldsum_t1_g1_edu3;
	
	public static Double oldsum_t1_g1_medInsure1;
	public static Double oldsum_t1_g1_medInsure2;
	public static Double oldsum_t1_g1_medInsure3;
	
	
	public static Double oldsum_t1_g2_sex1;
	public static Double oldsum_t1_g2_sex2;
	
	public static Double oldsum_t1_g2_age1;
	public static Double oldsum_t1_g2_age2;
	public static Double oldsum_t1_g2_age3;
	
	public static Double oldsum_t1_g2_mi1;
	public static Double oldsum_t1_g2_mi2;
	
	public static Double oldsum_t1_g2_edu1;
	public static Double oldsum_t1_g2_edu2;
	public static Double oldsum_t1_g2_edu3;
	
	public static Double oldsum_t1_g2_medInsure1;
	public static Double oldsum_t1_g2_medInsure2;
	public static Double oldsum_t1_g2_medInsure3;
	
	
	
	
	
	
	public static Double oldsum_t2_g1_sex1;
	public static Double oldsum_t2_g1_sex2;
	
	public static Double oldsum_t2_g1_age1;
	public static Double oldsum_t2_g1_age2;
	public static Double oldsum_t2_g1_age3;
	
	public static Double oldsum_t2_g1_mi1;
	public static Double oldsum_t2_g1_mi2;
	
	public static Double oldsum_t2_g1_edu1;
	public static Double oldsum_t2_g1_edu2;
	public static Double oldsum_t2_g1_edu3;
	
	public static Double oldsum_t2_g1_medInsure1;
	public static Double oldsum_t2_g1_medInsure2;
	public static Double oldsum_t2_g1_medInsure3;
	
	
	public static Double oldsum_t2_g2_sex1;
	public static Double oldsum_t2_g2_sex2;
	
	public static Double oldsum_t2_g2_age1;
	public static Double oldsum_t2_g2_age2;
	public static Double oldsum_t2_g2_age3;
	
	public static Double oldsum_t2_g2_mi1;
	public static Double oldsum_t2_g2_mi2;
	
	public static Double oldsum_t2_g2_edu1;
	public static Double oldsum_t2_g2_edu2;
	public static Double oldsum_t2_g2_edu3;
	
	public static Double oldsum_t2_g2_medInsure1;
	public static Double oldsum_t2_g2_medInsure2;
	public static Double oldsum_t2_g2_medInsure3;
	
	
	
	
	
	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getAge_grade() {
		return age_grade;
	}

	public void setAge_grade(int age_grade) {
		this.age_grade = age_grade;
	}

	public int getMedicalHis_MI() {
		return medicalHis_MI;
	}

	public void setMedicalHis_MI(int medicalHis_MI) {
		this.medicalHis_MI = medicalHis_MI;
	}

	public int getEdu_level() {
		return edu_level;
	}

	public void setEdu_level(int edu_level) {
		this.edu_level = edu_level;
	}

	public int getMedicalInsurance() {
		return medicalInsurance;
	}

	public void setMedicalInsurance(int medicalInsurance) {
		this.medicalInsurance = medicalInsurance;
	}

	public int getBelong_group() {
		return belong_group;
	}

	public void setBelong_group(int belong_group) {
		this.belong_group = belong_group;
	}

	public static int getSex1Weight() {
		return sex1_weight;
	}

	public static int getSex2Weight() {
		return sex2_weight;
	}

	public static int getAgeGrade1Weight() {
		return age_grade1_weight;
	}

	public static int getAgeGrade2Weight() {
		return age_grade2_weight;
	}

	public static int getAgeGrade3Weight() {
		return age_grade3_weight;
	}

	public static int getMedicalhisMi1Weight() {
		return medicalHis_MI1_weight;
	}

	public static int getMedicalhisMi2Weight() {
		return medicalHis_MI2_weight;
	}

	public static int getEduLevel1Weight() {
		return edu_level1_weight;
	}

	public static int getEduLevel2Weight() {
		return edu_level2_weight;
	}

	public static int getEduLevel3Weight() {
		return edu_level3_weight;
	}

	public static int getMedicalinsurance1Weight() {
		return medicalInsurance1_weight;
	}

	public static int getMedicalinsurance2Weight() {
		return medicalInsurance2_weight;
	}

	public static int getMedicalinsurance3Weight() {
		return medicalInsurance3_weight;
	}

	public String getGroupStr() {
		return groupStr;
	}

	public void setGroupStr(String groupStr) {
		this.groupStr = groupStr;
	}

	public int getIs_diabetes() {
		return is_diabetes;
	}

	public void setIs_diabetes(int is_diabetes) {
		this.is_diabetes = is_diabetes;
		copyOldSum();
	}

	public double getGroup1_weight() {
		return group1_weight;
	}

	public void setGroup1_weight(double group1_weight) {
		this.group1_weight = group1_weight;
	}

	public Double getOldsum_g1_sex1() {
		return oldsum_g1_sex1;
	}

	public void setOldsum_g1_sex1(Double oldsum_g1_sex1) {
		this.oldsum_g1_sex1 = oldsum_g1_sex1;
	}

	public Double getOldsum_g1_sex2() {
		return oldsum_g1_sex2;
	}

	public void setOldsum_g1_sex2(Double oldsum_g1_sex2) {
		this.oldsum_g1_sex2 = oldsum_g1_sex2;
	}

	public Double getOldsum_g1_age1() {
		return oldsum_g1_age1;
	}

	public void setOldsum_g1_age1(Double oldsum_g1_age1) {
		this.oldsum_g1_age1 = oldsum_g1_age1;
	}

	public Double getOldsum_g1_age2() {
		return oldsum_g1_age2;
	}

	public void setOldsum_g1_age2(Double oldsum_g1_age2) {
		this.oldsum_g1_age2 = oldsum_g1_age2;
	}

	public Double getOldsum_g1_age3() {
		return oldsum_g1_age3;
	}

	public void setOldsum_g1_age3(Double oldsum_g1_age3) {
		this.oldsum_g1_age3 = oldsum_g1_age3;
	}

	public Double getOldsum_g1_mi1() {
		return oldsum_g1_mi1;
	}

	public void setOldsum_g1_mi1(Double oldsum_g1_mi1) {
		this.oldsum_g1_mi1 = oldsum_g1_mi1;
	}

	public Double getOldsum_g1_mi2() {
		return oldsum_g1_mi2;
	}

	public void setOldsum_g1_mi2(Double oldsum_g1_mi2) {
		this.oldsum_g1_mi2 = oldsum_g1_mi2;
	}

	public Double getOldsum_g1_edu1() {
		return oldsum_g1_edu1;
	}

	public void setOldsum_g1_edu1(Double oldsum_g1_edu1) {
		this.oldsum_g1_edu1 = oldsum_g1_edu1;
	}

	public Double getOldsum_g1_edu2() {
		return oldsum_g1_edu2;
	}

	public void setOldsum_g1_edu2(Double oldsum_g1_edu2) {
		this.oldsum_g1_edu2 = oldsum_g1_edu2;
	}

	public Double getOldsum_g1_edu3() {
		return oldsum_g1_edu3;
	}

	public void setOldsum_g1_edu3(Double oldsum_g1_edu3) {
		this.oldsum_g1_edu3 = oldsum_g1_edu3;
	}

	public Double getOldsum_g1_medInsure1() {
		return oldsum_g1_medInsure1;
	}

	public void setOldsum_g1_medInsure1(Double oldsum_g1_medInsure1) {
		this.oldsum_g1_medInsure1 = oldsum_g1_medInsure1;
	}

	public Double getOldsum_g1_medInsure2() {
		return oldsum_g1_medInsure2;
	}

	public void setOldsum_g1_medInsure2(Double oldsum_g1_medInsure2) {
		this.oldsum_g1_medInsure2 = oldsum_g1_medInsure2;
	}

	public Double getOldsum_g1_medInsure3() {
		return oldsum_g1_medInsure3;
	}

	public void setOldsum_g1_medInsure3(Double oldsum_g1_medInsure3) {
		this.oldsum_g1_medInsure3 = oldsum_g1_medInsure3;
	}

	public Double getOldsum_g2_sex1() {
		return oldsum_g2_sex1;
	}

	public void setOldsum_g2_sex1(Double oldsum_g2_sex1) {
		this.oldsum_g2_sex1 = oldsum_g2_sex1;
	}

	public Double getOldsum_g2_sex2() {
		return oldsum_g2_sex2;
	}

	public void setOldsum_g2_sex2(Double oldsum_g2_sex2) {
		this.oldsum_g2_sex2 = oldsum_g2_sex2;
	}

	public Double getOldsum_g2_age1() {
		return oldsum_g2_age1;
	}

	public void setOldsum_g2_age1(Double oldsum_g2_age1) {
		this.oldsum_g2_age1 = oldsum_g2_age1;
	}

	public Double getOldsum_g2_age2() {
		return oldsum_g2_age2;
	}

	public void setOldsum_g2_age2(Double oldsum_g2_age2) {
		this.oldsum_g2_age2 = oldsum_g2_age2;
	}

	public Double getOldsum_g2_age3() {
		return oldsum_g2_age3;
	}

	public void setOldsum_g2_age3(Double oldsum_g2_age3) {
		this.oldsum_g2_age3 = oldsum_g2_age3;
	}

	public Double getOldsum_g2_mi1() {
		return oldsum_g2_mi1;
	}

	public void setOldsum_g2_mi1(Double oldsum_g2_mi1) {
		this.oldsum_g2_mi1 = oldsum_g2_mi1;
	}

	public Double getOldsum_g2_mi2() {
		return oldsum_g2_mi2;
	}

	public void setOldsum_g2_mi2(Double oldsum_g2_mi2) {
		this.oldsum_g2_mi2 = oldsum_g2_mi2;
	}

	public Double getOldsum_g2_edu1() {
		return oldsum_g2_edu1;
	}

	public void setOldsum_g2_edu1(Double oldsum_g2_edu1) {
		this.oldsum_g2_edu1 = oldsum_g2_edu1;
	}

	public Double getOldsum_g2_edu2() {
		return oldsum_g2_edu2;
	}

	public void setOldsum_g2_edu2(Double oldsum_g2_edu2) {
		this.oldsum_g2_edu2 = oldsum_g2_edu2;
	}

	public Double getOldsum_g2_edu3() {
		return oldsum_g2_edu3;
	}

	public void setOldsum_g2_edu3(Double oldsum_g2_edu3) {
		this.oldsum_g2_edu3 = oldsum_g2_edu3;
	}

	public Double getOldsum_g2_medInsure1() {
		return oldsum_g2_medInsure1;
	}

	public void setOldsum_g2_medInsure1(Double oldsum_g2_medInsure1) {
		this.oldsum_g2_medInsure1 = oldsum_g2_medInsure1;
	}

	public Double getOldsum_g2_medInsure2() {
		return oldsum_g2_medInsure2;
	}

	public void setOldsum_g2_medInsure2(Double oldsum_g2_medInsure2) {
		this.oldsum_g2_medInsure2 = oldsum_g2_medInsure2;
	}

	public Double getOldsum_g2_medInsure3() {
		return oldsum_g2_medInsure3;
	}

	public void setOldsum_g2_medInsure3(Double oldsum_g2_medInsure3) {
		this.oldsum_g2_medInsure3 = oldsum_g2_medInsure3;
	}
	
	public void copyOldSum(){
		if(this.is_diabetes==1){
			this.oldsum_g1_sex1 = oldsum_t1_g1_sex1;
			this.oldsum_g1_sex2 = oldsum_t1_g1_sex2;
			
			this.oldsum_g1_age1 = oldsum_t1_g1_age1;
			this.oldsum_g1_age2 = oldsum_t1_g1_age2;
			this.oldsum_g1_age3 = oldsum_t1_g1_age3;
			
			this.oldsum_g1_mi1 = oldsum_t1_g1_mi1;
			this.oldsum_g1_mi2 = oldsum_t1_g1_mi2;
			
			this.oldsum_g1_edu1 = oldsum_t1_g1_edu1;
			this.oldsum_g1_edu2 = oldsum_t1_g1_edu2;
			this.oldsum_g1_edu3 = oldsum_t1_g1_edu3;
			
			this.oldsum_g1_medInsure1 = oldsum_t1_g1_medInsure1;
			this.oldsum_g1_medInsure2 = oldsum_t1_g1_medInsure2;
			this.oldsum_g1_medInsure3 = oldsum_t1_g1_medInsure3;
			
			
			this.oldsum_g2_sex1 = oldsum_t1_g2_sex1;
			this.oldsum_g2_sex2 = oldsum_t1_g2_sex2;
			
			this.oldsum_g2_age1 = oldsum_t1_g2_age1;
			this.oldsum_g2_age2 = oldsum_t1_g2_age2;
			this.oldsum_g2_age3 = oldsum_t1_g2_age3;
			
			this.oldsum_g2_mi1 = oldsum_t1_g2_mi1;
			this.oldsum_g2_mi2 = oldsum_t1_g2_mi2;
			
			this.oldsum_g2_edu1 = oldsum_t1_g2_edu1;
			this.oldsum_g2_edu2 = oldsum_t1_g2_edu2;
			this.oldsum_g2_edu3 = oldsum_t1_g2_edu3;
			
			this.oldsum_g2_medInsure1 = oldsum_t1_g2_medInsure1;
			this.oldsum_g2_medInsure2 = oldsum_t1_g2_medInsure2;
			this.oldsum_g2_medInsure3 = oldsum_t1_g2_medInsure3;
		}else{
			this.oldsum_g1_sex1 = oldsum_t2_g1_sex1;
			this.oldsum_g1_sex2 = oldsum_t2_g1_sex2;
			
			this.oldsum_g1_age1 = oldsum_t2_g1_age1;
			this.oldsum_g1_age2 = oldsum_t2_g1_age2;
			this.oldsum_g1_age3 = oldsum_t2_g1_age3;
			
			this.oldsum_g1_mi1 = oldsum_t2_g1_mi1;
			this.oldsum_g1_mi2 = oldsum_t2_g1_mi2;
			
			this.oldsum_g1_edu1 = oldsum_t2_g1_edu1;
			this.oldsum_g1_edu2 = oldsum_t2_g1_edu2;
			this.oldsum_g1_edu3 = oldsum_t2_g1_edu3;
			
			this.oldsum_g1_medInsure1 = oldsum_t2_g1_medInsure1;
			this.oldsum_g1_medInsure2 = oldsum_t2_g1_medInsure2;
			this.oldsum_g1_medInsure3 = oldsum_t2_g1_medInsure3;
			
			
			this.oldsum_g2_sex1 = oldsum_t2_g2_sex1;
			this.oldsum_g2_sex2 = oldsum_t2_g2_sex2;
			
			this.oldsum_g2_age1 = oldsum_t2_g2_age1;
			this.oldsum_g2_age2 = oldsum_t2_g2_age2;
			this.oldsum_g2_age3 = oldsum_t2_g2_age3;
			
			this.oldsum_g2_mi1 = oldsum_t2_g2_mi1;
			this.oldsum_g2_mi2 = oldsum_t2_g2_mi2;
			
			this.oldsum_g2_edu1 = oldsum_t2_g2_edu1;
			this.oldsum_g2_edu2 = oldsum_t2_g2_edu2;
			this.oldsum_g2_edu3 = oldsum_t2_g2_edu3;
			
			this.oldsum_g2_medInsure1 = oldsum_t2_g2_medInsure1;
			this.oldsum_g2_medInsure2 = oldsum_t2_g2_medInsure2;
			this.oldsum_g2_medInsure3 = oldsum_t2_g2_medInsure3;
		}
	}
	
	
}
