package com.skyline.model;

import java.util.Hashtable;

public class ResponseModel {
	private String errMsg;
	private int statusCode;
	private Object data;
	
	private static Hashtable<Status, String> statusMsgMap;
	private static Hashtable<Status, Integer> codeMsgMap;
	
	public static enum Status{
		register_success, register_failed_u_e, register_failed_m_e, register_failed_unknown_error, register_failed_securityCheck,
		login_success, login_user_already_logged, login_auth_failed, login_user_locked, login_user_nickout_error, login_security_sqlInjection,
		status_logged, status_nologged, status_session_timeout, status_session_single_user_restriction, status_access_deny,
		logout_success
	}	
	
	static {
		statusMsgMap = new Hashtable<>();
		statusMsgMap.put(Status.register_success, "Register Success.");
		statusMsgMap.put(Status.register_failed_u_e, "The user name is already exit.");
		statusMsgMap.put(Status.register_failed_m_e, "The mobilephone number is already registered.");		
		statusMsgMap.put(Status.register_failed_unknown_error, "Unknow Error.");
		statusMsgMap.put(Status.register_failed_securityCheck, "Security Check failed.");
		statusMsgMap.put(Status.login_success, "Login Success.");
		statusMsgMap.put(Status.login_user_already_logged, "The user has logged somewhere else. Cause the single-user restriction, the previous user has been nick out, and the current user will login.");
		statusMsgMap.put(Status.login_auth_failed, "Authentication Failed.");
		statusMsgMap.put(Status.login_user_locked, "The current user has been locked.");
		statusMsgMap.put(Status.login_user_nickout_error, "The user current status is abnormal. Login Forbidden.");
		statusMsgMap.put(Status.login_security_sqlInjection, "Do you want injection? To be a good person. Go away please!");
		statusMsgMap.put(Status.status_logged, "The current user is already logged.");
		statusMsgMap.put(Status.status_nologged, "The current user has not been logged.");
		statusMsgMap.put(Status.status_session_timeout, "The current session time out. Please login again.");
		statusMsgMap.put(Status.status_session_single_user_restriction, "The current user has been nicked out.");
		statusMsgMap.put(Status.status_access_deny, "Access Deny.");		
		statusMsgMap.put(Status.logout_success, "Logout Success.");
		
		
		codeMsgMap = new Hashtable<>();
		codeMsgMap.put(Status.register_success, 0);
		codeMsgMap.put(Status.register_failed_u_e, 1);
		codeMsgMap.put(Status.register_failed_m_e, 2);
		codeMsgMap.put(Status.register_failed_unknown_error, 3);
		codeMsgMap.put(Status.register_failed_securityCheck, 4);
		codeMsgMap.put(Status.login_success, 100);
		codeMsgMap.put(Status.login_user_already_logged, 101);
		codeMsgMap.put(Status.login_auth_failed, 102);
		codeMsgMap.put(Status.login_user_locked, 103);
		codeMsgMap.put(Status.login_user_nickout_error, 104);
		codeMsgMap.put(Status.login_security_sqlInjection, 105);
		codeMsgMap.put(Status.status_logged, 200);
		codeMsgMap.put(Status.status_nologged, 201);
		codeMsgMap.put(Status.status_session_timeout, 202);
		codeMsgMap.put(Status.status_session_single_user_restriction, 203);
		codeMsgMap.put(Status.status_access_deny, 204);
		codeMsgMap.put(Status.logout_success, 300);
	}
	
	public ResponseModel(Object data) {
		this.statusCode = codeMsgMap.get(Status.status_logged);
		this.errMsg = statusMsgMap.get(Status.status_logged);
		this.data = data;
	}
	
	public ResponseModel(Status status) {
		this.statusCode = codeMsgMap.get(status);
		this.errMsg = statusMsgMap.get(status);
		this.data = null;
	}
	
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
