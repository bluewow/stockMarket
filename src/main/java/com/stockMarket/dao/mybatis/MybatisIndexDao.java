package com.stockMarket.dao.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stockMarket.dao.IndexDao;
import com.stockMarket.entity.TestEntity;

@Repository
public class MybatisIndexDao implements IndexDao {

	private SqlSession sqlSession;
	private IndexDao mapperDao;
	
	@Autowired
	public MybatisIndexDao(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
		this.mapperDao = sqlSession.getMapper(IndexDao.class);
	}
	
	@Override
	public TestEntity get() {
		return mapperDao.get();
	}

}
