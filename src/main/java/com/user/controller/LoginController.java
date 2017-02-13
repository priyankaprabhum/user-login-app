package com.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.user.model.User;
import com.user.services.LoginService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "user")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody User login(@RequestBody String userJson) {

		JSONObject jsonObject = JSONObject.fromObject(userJson);
		User user = new User();
		user = loginService.checkLoginCredentials(jsonObject.getString("username"), jsonObject.getString("password"));
		return user;
	}
}
