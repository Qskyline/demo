package com.skyline.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.skyline.controller.Authentication;

@Service
public class LogService {
	public Logger getLog(Class<?> c) {
		return LoggerFactory.getLogger(Authentication.class);
	}
	
	public void dbErrorLog(Logger log, Exception e) {
		log.error("db save error!");
		log.error(e.toString());
	}
}
