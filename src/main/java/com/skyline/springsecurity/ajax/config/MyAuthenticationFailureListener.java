package com.skyline.springsecurity.ajax.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.Authentication;
import com.skyline.service.UserService;

public class MyAuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

	@Autowired
	UserService userService;
	
	@Override
	public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent paramE) {
		Authentication auth = paramE.getAuthentication();
		String userName = auth.getName();
		userService.updateFailedUserLoginlog(userName);
	}
}
