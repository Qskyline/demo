package com.skyline.springsecurity.ajax.config;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.skyline.model.ResponseModel;
import com.skyline.util.HttpUtil;

public class MyAuthEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		HttpUtil.writeToResponse(response, new ResponseModel(ResponseModel.Status.status_nologged));
	}
}
