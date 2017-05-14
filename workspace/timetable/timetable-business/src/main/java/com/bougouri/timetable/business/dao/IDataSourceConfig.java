package com.bougouri.timetable.business.dao;

import java.util.Properties;

import javax.sql.DataSource;

public interface IDataSourceConfig {

	DataSource getDataSource();
	
	Properties getServerConnectionProperties();
}
