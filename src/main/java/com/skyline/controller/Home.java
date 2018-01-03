package com.skyline.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skyline.model.ResponseModel;

import net.sf.json.JSONObject;

@Controller
public class Home {
	
	@RequestMapping(value = "/security/getHomeData.do")
	@ResponseBody
	public String getHomeData(HttpSession session) {		
		ResponseModel response = new ResponseModel("Hello World.");		
		return JSONObject.fromObject(response).toString();
	}
}
