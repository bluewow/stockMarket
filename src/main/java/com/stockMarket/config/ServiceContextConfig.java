package com.stockMarket.config;

import java.io.IOException;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan(basePackages= {"com.stockMarket.service","com.stockMarket.dao"})
public class ServiceContextConfig {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Bean
	public DriverManagerDataSource dataSource() {
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		// mysql
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://dev.notepubs.com:3306/stock?autoReconnect=true&useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=Asi");
		dataSource.setUsername("stock");
		dataSource.setPassword("11111");
		
		// oracle
//		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
//		dataSource.setUrl("jdbc:oracle:thin:@192.168.0.3:1521/xepdb1");
//		dataSource.setUsername("ACORN");
//		dataSource.setPassword("newlec");
		
		return dataSource;
	}
	
	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() throws IOException {
		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		factory.setDataSource(dataSource());
		factory.setMapperLocations(
				applicationContext.getResources("classpath:com/stockMarket/dao/mybatis/mapper/*.xml"));
		
		return factory;
	}
	
	@Bean
	public SqlSessionTemplate sqlSession() throws Exception {
		SqlSessionTemplate sqlSession = 
				new SqlSessionTemplate(sqlSessionFactory().getObject());
				
		return sqlSession;
	}
}
