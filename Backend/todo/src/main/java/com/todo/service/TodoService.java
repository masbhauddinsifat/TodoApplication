package com.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.model.Response;
import com.todo.model.Todo;
import com.todo.repository.TodoRepository;

@Service
public class TodoService {

	@Autowired
	private TodoRepository todoRepository;

	public List<Todo> getAllTodo() {
		return todoRepository.getAllTodo();

	}

	public Todo getSingleTodo(int id) {
		return todoRepository.getSingleTodo(id);
	}

	public Response addTodo(Todo todo) {
		return todoRepository.addTodo(todo);
	}

	public Response updateTodo(Todo todo) {
		return todoRepository.updateTodo(todo);
	}

	public Response deleteTodo(int id) {
		return todoRepository.deleteTodo(id);
	}

	

}
