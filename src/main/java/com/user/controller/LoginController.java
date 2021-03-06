package com.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.user.model.User;
import com.user.security.CookieUtil;
import com.user.security.JwtUtil;
import com.user.services.LoginService;

import net.sf.json.JSONObject;

@Controller
public class LoginController {
	private static final String jwtTokenCookieName = "JWT-TOKEN";
	private static final String signingKey = "signingKey";

	@Value("${host}")
	private String host;

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/json/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody User login(@RequestBody String userJson) {

		JSONObject jsonObject = JSONObject.fromObject(userJson);
		User user = new User();
		user = loginService.checkLoginCredentials(jsonObject.getString("username"), jsonObject.getString("password"));
		return user;
	}

	private static final Map<String, String> credentials = new HashMap<>();

	@RequestMapping("/")
	public String home() {
		return "redirect:/login";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(HttpServletResponse httpServletResponse, String username, String password, String redirect,
			Model model) {
		if (username == null && password == null) {
			model.addAttribute("error", "Invalid username or password!");
			return "login";
		} else {
			User user = loginService.checkLoginCredentials(username, password);

			if (user == null) {
				model.addAttribute("error", "Invalid username or password!");
				return "login";
			}

			String token = JwtUtil.generateToken(signingKey, username);
			CookieUtil.create(httpServletResponse, jwtTokenCookieName, token, false, -1, host, user.getFirstName(),
					user.getLastName());

			return "redirect:" + redirect;
		}
	}

}
