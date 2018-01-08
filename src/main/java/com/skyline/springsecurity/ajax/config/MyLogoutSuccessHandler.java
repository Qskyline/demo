package com.skyline.springsecurity.ajax.config;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import com.skyline.model.ResponseModel;
import com.skyline.util.HttpUtil;

public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
	@Override
	public void onLogoutSuccess(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse response, Authentication paramAuthentication)
			throws IOException, ServletException {
		HttpUtil.writeToResponse(response, new ResponseModel(ResponseModel.Status.logout_success));	
	}
}
