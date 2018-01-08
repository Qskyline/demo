package com.skyline.springsecurity.ajax.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.skyline.util.SecurityUtil;

public class MyAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
	protected MyAuthenticationFilter() {
		super(new AntPathRequestMatcher("/security/login.do", "POST"));
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse arg1) throws AuthenticationException {
		String username = request.getParameter("name");
		String password = request.getParameter("password");
		if(!SecurityUtil.checkUsername(username)) {
			throw new MyAuthenticationException("Username format check failed!");
		}
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
		return this.getAuthenticationManager().authenticate(authRequest);
	}
}
