package com.todo.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.todo.model.Todo;

@Repository
public class TodoRepository {

	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.username}")
	private String userName;

	@Value("${spring.datasource.password}")
	private String password;

	public List<Todo> getAllTodo() {
		ArrayList<Todo> todoList = new ArrayList<>();

		try {
			Connection con = DriverManager.getConnection(url, userName, password);

			CallableStatement stmt = con.prepareCall("{call selectTodo}");

			stmt.execute();
			ResultSet resultSet = stmt.getResultSet();

			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String title = resultSet.getString(2);
				String userEmail = resultSet.getString(3);

				Todo todo = new Todo(id, title, userEmail);
				todoList.add(todo);
			}

			resultSet.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return todoList;
	}

	public String addTodo(Todo todo) {
		try {
			Connection con = DriverManager.getConnection(url, userName, password);

			CallableStatement stmt = con.prepareCall("{call createTodo(?,?)}");
			stmt.setString(1, todo.getTitle());
			stmt.setString(2, todo.getUserEmail());
			stmt.execute();

			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "Success";
	}

	public String updateTodo(Todo todo) {
		try {
			Connection con = DriverManager.getConnection(url, userName, password);

			CallableStatement stmt = con.prepareCall("{call updateTodo(?,?)}");
			stmt.setInt(1, todo.getId());
			stmt.setString(2, todo.getTitle());
			stmt.execute();

			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "Success";
	}

	public String deleteTodo(int id) {
		try {
			Connection con = DriverManager.getConnection(url, userName, password);

			CallableStatement stmt = con.prepareCall("{call deleteTodo(?)}");
			stmt.setInt(1, id);
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Success";

	}

}
