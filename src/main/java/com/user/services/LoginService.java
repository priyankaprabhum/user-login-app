package com.user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.model.LoginMapper;
import com.user.model.User;

@Service
public class LoginService {

	@Autowired
	public LoginMapper loginMapper;
	
	public User checkLoginCredentials(String userName, String password){
		
		User user = new User();
		user = loginMapper.getLoginInformation(userName, password);
		return user;
	}
	
}
