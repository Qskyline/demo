package com.skyline.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConfigService{
	 @Value("#{config.mysql_user}")
	 private String user;
	 
	 @Value("#{config.mysql_password}")
	 private String password;	 
	 
	 @Value("#{config.mysql_driverClass}")
	 private String driverClass;	
	 
	 @Value("#{config.mysql_jdbcUrl}")
	 private String jdbcUrl;

	 
	 public String getUser() {
		 return user;
	 }
	 public String getPassword() {
		 return password;
	 }
	 public String getDriverClass() {
		 return driverClass;
	 }
	 public String getJdbcUrl() {
		 return jdbcUrl;
	 }
}
