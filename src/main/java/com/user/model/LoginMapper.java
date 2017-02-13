package com.user.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class LoginMapper implements RowMapper<User>{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {

		User user = new User();
		user.setUserId(rs.getLong("user_id"));
		user.setUserName(rs.getString("user_name"));
		user.setFirstName(rs.getString("first_name"));
		user.setLastName(rs.getString("last_name"));
		user.setEmail(rs.getString("user_email"));
		user.setUserRole(rs.getString("user_role"));
		//user.setPassword(rs.getString("user_password"));
		return user;
	}
	
	public User getLoginInformation(String userName, String password){
	
		User user = new User();
		String sql = "select user_password from loginadm01.t_user where user_name=?";
		String encryptedPassword = jdbcTemplate.queryForObject(sql, new Object[]{userName}, String.class);		
		
		String decryptPasswordSql = "select(? = crypt( ? , ? )) as matched";
		boolean isPasswordCorrect = jdbcTemplate.queryForObject(decryptPasswordSql, new Object[]{ encryptedPassword, password, 
				encryptedPassword }, boolean.class);
		
		if (isPasswordCorrect){
			
			String userInfoSql = "select user_id, user_name, first_name, last_name, user_email, user_role from loginadm01.t_user "
					+ "where user_name=?";
			user = jdbcTemplate.queryForObject(userInfoSql, new Object[] { userName }, new LoginMapper());
			return user;
		}else
		{
			return null;
		}
			
	}
}
