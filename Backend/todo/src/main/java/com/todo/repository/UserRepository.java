package com.todo.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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
			con = DriverManager.getConnection(url, userName, pass);
			CallableStatement stmt = con.prepareCall("{call selectAllUser()}");
			stmt.execute();
			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				int id = rs.getInt("id");
				String fullName = rs.getString("fullName");
				String email = rs.getString("email");
				String pass = rs.getString("password");
				String authority = rs.getString("authority");
				Date createOn = rs.getDate("createOn");
				boolean isActive = rs.getBoolean("isActive");

				User user = new User(id, fullName, email, pass, authority, createOn, isActive);
				userList.add(user);
			}

			stmt.closeOnCompletion();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userList;
	}

	public User getSingleUser(String email) {
		User user = null;
		try {
			Connection con = DriverManager.getConnection(url, userName, pass);

			CallableStatement stmt = con.prepareCall("{call selectSingleUser(?)}");
			stmt.setString(1, email);
			stmt.execute();

			ResultSet resultSet = stmt.getResultSet();
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String fullName = resultSet.getString(2);
				String userEmail = resultSet.getString(3);
				String password = resultSet.getString(4);
				String authority = resultSet.getString(5);
				Date createdOn = resultSet.getDate(6);
				boolean isActive = resultSet.getBoolean(7);

				user = new User(id, fullName, userEmail, password, authority, createdOn, isActive);
			}
			resultSet.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public String postUser(User user) {
		try {
			Connection con = DriverManager.getConnection(url, userName, pass);
			CallableStatement stmt = con.prepareCall("{call createUser(?,?,?,?,?)}");

			stmt.setString(1, user.getFullName());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getAuthority());
			stmt.setBoolean(5, user.isActive());

			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Success";
	}

	public String updateUser(User user) {
		try {
			Connection con = DriverManager.getConnection(url, userName, pass);

			CallableStatement stmt = con.prepareCall("{call updateUser(?,?,?)}");

			stmt.setString(1, user.getEmail());
			stmt.setString(2, user.getFullName());
			stmt.setString(3, user.getPassword());

			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Success";
	}

	public String deleteUser(User user) {
		try {
			Connection con = DriverManager.getConnection(url, userName, pass);
			CallableStatement stmt = con.prepareCall("{call deleteUser(?)}");

			stmt.setString(1, user.getEmail());
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Success";
	}

}
