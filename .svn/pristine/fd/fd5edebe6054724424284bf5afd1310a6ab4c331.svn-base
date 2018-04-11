package com.bdcor.pip.web.common.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bdcor.pip.web.common.service.CommonJdbcService;

@Service
public class CommonJdbcServiceImpl implements CommonJdbcService {

	@Autowired
	DataSource dataSource;
	
	@Override
	//@Cacheable(value = "sysCache",key="#params['table']+#params['wherekey']")  
	public List<Map<String,String>> selectJsonForAutocomplete(Map<String,Object> params) {
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		Connection conn = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			String[] columns = (String[]) params.get("columns");
			
			StringBuffer sql = new StringBuffer();
			sql.append("select ");
			for ( String col:columns ){
				sql.append(col);
				sql.append(",");
			}
			sql.append(" '' from ");
			sql.append(params.get("table"));
			sql.append(" where 1 = 1 ");
			if ( params.get("where") != null  ){
				List<Map<String,String>> ws = (List<Map<String,String>>) params.get("where");
				for ( Map<String,String> map : ws ){
					sql.append(" and ");
					sql.append(map.get("column"));
					sql.append(" ");
					sql.append(map.get("operate"));
					sql.append(" '");
					sql.append(map.get("value"));
					sql.append("'");
				}
			}
			if ( columns[0] != null ){
				sql.append(" order by ");
				sql.append(columns[0]);
			}
			conn = this.dataSource.getConnection();
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while(rs.next()){
				Map<String,String> map = new HashMap<String,String>();
				for ( String col : columns ){
					map.put(col, rs.getString(col));
				}
				list.add(map);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(rs!=null){
					rs.close();
				}
				if(ps!=null){
					ps.close();
				}
				if(conn!=null){
					conn.close();
				}
			}catch(SQLException e){
			}
		}
		//JsonMapper jm = JsonMapper.nonDefaultMapper();
		return list;
	}
	
	

}
