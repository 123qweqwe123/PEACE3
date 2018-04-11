package com.bdcor.pip.web.spem.dao;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.spem.domain.IceBoxRegVo;
import com.bdcor.pip.web.spem.domain.SpemTypeVo;
import com.bdcor.pip.web.spem.filter.IceBoxRegFilter;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface IceBoxRegVoMapper { 
	
	
   

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_SPECIMEN_ICEBOX
     *
     * @mbggenerated
     */
    int delete(@Param("id")String id,@Param("projectid") String projectid);

   

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_SPECIMEN_ICEBOX
     *
     * @mbggenerated
     */
    int save(IceBoxRegVo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_SPECIMEN_ICEBOX
     *
     * @mbggenerated
     */
    List<IceBoxRegVo> list(@Param("lccid") String lccid,@Param("projectid") String projectid);

    
    
    
    
    List<IceBoxRegVo> qlist(IceBoxRegFilter fi);    

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_SPECIMEN_ICEBOX
     *
     * @mbggenerated
     */
    IceBoxRegVo getById(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PIP_SPECIMEN_ICEBOX
     *
     * @mbggenerated
     */
    int update( IceBoxRegVo record);
    
     
    
    /****************冰箱存放样本类型***********************************/
    
    List<SpemTypeVo> spemType_list(SpemTypeVo fi);      
    
    int save_spemType(SpemTypeVo record);
    
    int delete_spemType(SpemTypeVo record);



	List<Map<String, String>> getFrigdeList(Map<String, Object> map);

	List<Map<String,Object>> getExportFrigdeList(Map<String, Object> paramMap);

   
}