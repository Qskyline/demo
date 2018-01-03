package com.skyline.springsecurity.ajax.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;

import com.skyline.model.ResponseModel;
import com.skyline.util.HttpUtil;

public class MyUserKickFilter implements Filter {
	@Autowired
	PersistentTokenBasedRememberMeServices myRememberMeService;
	private final SessionRegistry sessionRegistry;	
	private List<LogoutHandler> handlers;
	
	public MyUserKickFilter(LogoutHandler[] handlers, SessionRegistry sessionRegistry) {
		this.handlers = Arrays.asList(handlers);
		this.sessionRegistry = sessionRegistry;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chian)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		HttpSession session = request.getSession(false);		
		if(session != null) {
			MySessionInformation info = (MySessionInformation)this.sessionRegistry.getSessionInformation(session.getId());
			if(info != null &&info.isKicked()) {
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				for (LogoutHandler logoutHandler : handlers) {
					logoutHandler.logout(request, response, auth);
				}
				HttpUtil.writeToResponse(response, new ResponseModel(ResponseModel.Status.status_session_single_user_restriction));
				return ;
			}
		}
		
		chian.doFilter(req, res);		
	}
}
