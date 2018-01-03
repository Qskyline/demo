package com.skyline.springsecurity.ajax.config;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;

public class MyRememberMeFilter extends RememberMeAuthenticationFilter{
	@Autowired
	private MySessionRegistryImpl mySessionRegistryImpl;
	
	public MyRememberMeFilter(AuthenticationManager authenticationManager, RememberMeServices rememberMeServices) {
		super(authenticationManager, rememberMeServices);
	}
	
	protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) {
		List<SessionInformation> sessions = mySessionRegistryImpl.getAllSessions(authResult.getPrincipal(), false);
		String currentSessionId = request.getSession(false).getId();
		if(sessions.size() >= 1) {
			for (SessionInformation sessionInfo : sessions) {
				if(!sessionInfo.getSessionId().equals(currentSessionId)) {
					((MySessionInformation)sessionInfo).setKicked();
					((MySessionInformation)sessionInfo).expireNow();
					mySessionRegistryImpl.updateSessionInformation(sessionInfo);
				}
			}
		}
	}
}
