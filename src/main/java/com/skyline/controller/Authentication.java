package com.skyline.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skyline.model.ResponseModel;
import com.skyline.service.UserService;
import com.skyline.util.SecurityUtil;

import net.sf.json.JSONObject;

@Controller
public class Authentication {
	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	@RequestMapping(value = "/security/auth")
	public String showLoginPage(HttpServletRequest request) {
		return "index";
	}
	
	@RequestMapping(value = "/security/register.do", produces = "text/html;charset=UTF-8", method = {RequestMethod.POST})
	@ResponseBody
	public String register(
			HttpServletResponse response,
			@RequestParam("name") String username,
			@RequestParam("mobile") String mobile,
			@RequestParam("password") String password,
			@RequestParam("confirm") String confirm,
			HttpSession session
			){
		if(username ==null || mobile == null || password == null || confirm == null ||
				!SecurityUtil.checkUsername(username) || !SecurityUtil.checkPhoneNum(mobile) || 
				!password.equals(confirm)) {
			return JSONObject.fromObject(new ResponseModel(ResponseModel.Status.register_failed_securityCheck)).toString();
		}
		
		UserService.RegisterStatus registerStatus = userService.register(username, mobile, bcryptEncoder.encode(password), null);
		ResponseModel responseModel = null;
		switch (registerStatus) {
		case success:
			responseModel = new ResponseModel(ResponseModel.Status.register_success);
			break;
		case usernameAlreadyExist:
			responseModel = new ResponseModel(ResponseModel.Status.register_failed_u_e);
			break;
		case mobilephoneNumberAlreadyExist:
			responseModel = new ResponseModel(ResponseModel.Status.register_failed_m_e);
			break;
		case unknowError:
		default:
			responseModel = new ResponseModel(ResponseModel.Status.register_failed_unknown_error);
		}
		return JSONObject.fromObject(responseModel).toString();
	}
	
	@RequestMapping(value = "/security/check_loginStatus.do", produces = "text/html;charset=UTF-8", method = {RequestMethod.POST})
	@ResponseBody
	public String checkLoginStatus() {
		ResponseModel responseModel = new ResponseModel(ResponseModel.Status.status_logged);
		return JSONObject.fromObject(responseModel).toString();
	}
}
