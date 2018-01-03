package com.skyline.springsecurity.ajax.config;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.web.RedirectStrategy;

import com.skyline.model.ResponseModel;
import com.skyline.util.HttpUtil;

public class MyRedirectStrategy implements RedirectStrategy{
	@Override
	public void sendRedirect(HttpServletRequest request, HttpServletResponse response,
			String paramString) throws IOException {
		HttpUtil.writeToResponse(response, new ResponseModel(ResponseModel.Status.status_session_timeout));
	}
}
