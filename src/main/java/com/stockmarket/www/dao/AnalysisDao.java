package com.stockmarket.www.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.stockmarket.www.entity.Analysis;

@Mapper
public interface AnalysisDao {
	public List<Analysis> getlist();
	public Analysis get(String codeNum);
	
	public int insert(Analysis entityList);
	public int insertAll(List<Analysis> entityList);
	public int delete(String codeNum);
}
