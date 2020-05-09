package com.todo.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.todo.model.User;

@Repository
public class UserRepository {
	
	@Value("${spring.datasource.url}")
	private String url;
	
	@Value("${spring.datasource.username}")
	private String userName;
	
	@Value("${spring.datasource.password}")
	private String pass;
	

	public List<User> getAllUser() {
		ArrayList<User> userList = new ArrayList<>();
		
		Connection con;
		try {
			con = DriverManager.getConnection(url,userName, pass);
			CallableStatement stmt = con.prepareCall("{call selectUser()}");
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
		
			while(rs.next()) {
				int id = rs.getInt("id");
				String  fullName = rs.getString("fullName");
				String email = rs.getString("email");
				String pass = rs.getString("password");
				String authority = rs.getString("authority");
				Date createOn  = rs.getDate("createOn");
				boolean isActive = rs.getBoolean("isActive");
				
				User user = new User(id, fullName, email,pass, authority, createOn,isActive);
				userList.add(user);
			}
				
			stmt.closeOnCompletion();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userList;
	}

}
