package com.skyline.springsecurity.ajax.config;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import com.skyline.model.ResponseModel;
import com.skyline.util.NetworkUtil;

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {	
	@Autowired
	private MySessionRegistryImpl mySessionRegistryImpl;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		ResponseModel result = new ResponseModel(ResponseModel.Status.login_success);
		
		List<SessionInformation> sessions = mySessionRegistryImpl.getAllSessions(authentication.getPrincipal(), false);
		String currentSessionId = request.getSession(false).getId();
		
		if(sessions.size() > 1) {
			result = new ResponseModel(ResponseModel.Status.login_user_already_logged);
			for (SessionInformation sessionInfo : sessions) {
				if(!sessionInfo.getSessionId().equals(currentSessionId)) {
					((MySessionInformation)sessionInfo).setKicked();
					((MySessionInformation)sessionInfo).expireNow();
					mySessionRegistryImpl.updateSessionInformation(sessionInfo);	
				}
			}
		}
		
		NetworkUtil.writeToResponse(response, result);
	}
}
