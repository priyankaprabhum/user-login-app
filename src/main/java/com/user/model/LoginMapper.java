package com.user.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class LoginMapper implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {

		User user = new User();
		user.setUserName(rs.getString("user_name"));
		user.setPassword(rs.getString("user_password"));
		return user;
	}
	
	public User getLoginInformation(String userName, String password){
	
		User user = new User();
		String sql = "";
		return user;
	}
}
