package com.skyline.springsecurity.ajax.config;

import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

public class MyJdbcTokenRepositoryImpl extends JdbcTokenRepositoryImpl {
	public static final String DEF_REMOVE_SERIES_TOKENS_SQL = "delete from persistent_logins where series = ?";
	
	private String removeSeriesTokensSql = DEF_REMOVE_SERIES_TOKENS_SQL;
	
	public void removeSeriesTokens(String series) {
		getJdbcTemplate().update(removeSeriesTokensSql, series);
	}
}
