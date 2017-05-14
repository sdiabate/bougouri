package com.bougouri.timetable.business;

public class DataSource {
	private String driver;
	private String dialect;
	private String url;
	private String user;
	private String password;
	private String schemaStartegy;
	private String showSql;
	
	public final String getDriver() {
		return driver;
	}
	
	public final void setDriver(final String driver) {
		this.driver = driver;
	}
	
	public final String getDialect() {
		return dialect;
	}
	
	public final void setDialect(final String dialect) {
		this.dialect = dialect;
	}
	
	public final String getUrl() {
		return url;
	}
	
	public final void setUrl(final String url) {
		this.url = url;
	}
	
	public final String getUser() {
		return user;
	}
	
	public final void setUser(final String user) {
		this.user = user;
	}
	
	public final String getPassword() {
		return password;
	}
	
	public final void setPassword(final String password) {
		this.password = password;
	}
	
	public final String getSchemaStartegy() {
		return schemaStartegy;
	}
	
	public final void setSchemaStartegy(final String schemaStartegy) {
		this.schemaStartegy = schemaStartegy;
	}
	
	public final String getShowSql() {
		return showSql;
	}
	
	public final void setShowSql(final String showSql) {
		this.showSql = showSql;
	}

}
