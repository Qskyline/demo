package com.skyline.springsecurity.ajax.config;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import com.skyline.model.ResponseModel;
import com.skyline.service.UserService;
import com.skyline.util.HttpUtil;

public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
	@Autowired
	UserService userService;	
	@Override
	public void onAuthenticationFailure(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse response, AuthenticationException paramAuthenticationException)
			throws IOException, ServletException {
		ResponseModel result = new ResponseModel(ResponseModel.Status.login_auth_failed);
		if(paramAuthenticationException instanceof LockedException) {
			result = new ResponseModel(ResponseModel.Status.login_user_locked);
		} else if(paramAuthenticationException instanceof SessionAuthenticationException) {
			result = new ResponseModel(ResponseModel.Status.login_user_nickout_error);
		} else if(paramAuthenticationException instanceof MyAuthenticationException) {
			result = new ResponseModel(ResponseModel.Status.login_security_sqlInjection);
		}
        HttpUtil.writeToResponse(response, result);
	}
}
