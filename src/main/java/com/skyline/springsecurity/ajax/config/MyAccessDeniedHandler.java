package com.skyline.springsecurity.ajax.config;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import com.skyline.model.ResponseModel;
import com.skyline.util.NetworkUtil;

public class MyAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException arg2)
			throws IOException, ServletException {
		NetworkUtil.writeToResponse(response, new ResponseModel(ResponseModel.Status.status_access_deny));
	}
}
