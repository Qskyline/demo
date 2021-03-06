package com.skyline.springsecurity.ajax.config;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionInformation;
import com.skyline.service.UserService;

public class MyAuthenticationSuccessListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

	@Autowired
	UserService userService;
	@Autowired
	private MySessionRegistryImpl mySessionRegistryImpl;
	
	@Override
	public void onApplicationEvent(InteractiveAuthenticationSuccessEvent paramE) {
		Authentication auth = paramE.getAuthentication();
		String userName = auth.getName();
		if(!userService.updateSuccessUserLoginlog(userName)) {
			//登录日志记录出错,强制下线
			List<SessionInformation> sessions = mySessionRegistryImpl.getAllSessions(auth.getPrincipal(), false);
			for (SessionInformation sessionInfo : sessions) {
				sessionInfo.expireNow();
				mySessionRegistryImpl.updateSessionInformation(sessionInfo);
			}
		}
	}
}
