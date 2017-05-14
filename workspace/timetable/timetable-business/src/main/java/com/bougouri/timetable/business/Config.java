package com.bougouri.timetable.business;

public class Config {

	private DataSource dataSource;
	
	public final DataSource getDataSource() {
		return dataSource;
	}
	
	public final void setDataSource(final DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
