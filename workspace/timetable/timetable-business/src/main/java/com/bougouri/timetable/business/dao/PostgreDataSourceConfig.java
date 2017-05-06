package com.bougouri.timetable.business.dao;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@Profile("prod")
public class PostgreDataSourceConfig implements IDataSourceConfig{

	@Override
	public DataSource getDataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://localhost:5432/bougouri");
		dataSource.setUsername("postgres");
		dataSource.setPassword("Dev4guampri");
		return dataSource;
	}

	@Override
	public Properties getServerConnectionProperties() {
		final Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		return properties;
	}

}
