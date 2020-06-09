package com.todo.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.todo.model.Response;
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

			CallableStatement stmt = con.prepareCall("{call selectAllTodo}");

			stmt.execute();
			ResultSet resultSet = stmt.getResultSet();

			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String title = resultSet.getString(2);
				boolean isComplete = resultSet.getBoolean(3);
				Date createdOn = resultSet.getDate(4);
				String userEmail = resultSet.getString(5);

				Todo todo = new Todo(id, title, isComplete, createdOn, userEmail);
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

	public Todo getSingleTodo(int id) {
		Todo todo = null;
		
		try {
			Connection con = DriverManager.getConnection(url, userName, password);
			
			CallableStatement stmt = con.prepareCall("{call selectSingleTodo(?)}");
			stmt.setInt(1, id);
			
			stmt.execute();
			
			ResultSet resultSet = stmt.getResultSet();
			
			while(resultSet.next()) {
				int userId = resultSet.getInt(1);
				String title = resultSet.getString(2);
				boolean isComplete = resultSet.getBoolean(3);
				Date createdOn = resultSet.getDate(4);
				String userEmail = resultSet.getString(5);

				todo = new Todo(userId, title, isComplete, createdOn, userEmail);
			}
			
			resultSet.close();
			stmt.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return todo;
	}

	
	public Response addTodo(Todo todo) {
		try {
			Connection con = DriverManager.getConnection(url, userName, password);

			CallableStatement stmt = con.prepareCall("{call createTodo(?,?,?)}");
			stmt.setString(1, todo.getTitle());
			stmt.setBoolean(2, todo.isComplete());
			stmt.setString(3, todo.getUserEmail());
			stmt.execute();

			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new Response(HttpServletResponse.SC_CREATED, "CREATED");
	}

	public Response updateTodo(Todo todo) {
		try {
			Connection con = DriverManager.getConnection(url, userName, password);

			CallableStatement stmt = con.prepareCall("{call updateTodo(?,?,?)}");
			stmt.setInt(1, todo.getId());
			stmt.setString(2, todo.getTitle());
			stmt.setBoolean(3, todo.isComplete());
			
			stmt.execute();

			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new Response(HttpServletResponse.SC_OK, "OK");
	}

	public Response deleteTodo(int id) {
		try {
			Connection con = DriverManager.getConnection(url, userName, password);

			CallableStatement stmt = con.prepareCall("{call deleteTodo(?)}");
			stmt.setInt(1, id);
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new Response(HttpServletResponse.SC_OK, "OK");

	}
}
