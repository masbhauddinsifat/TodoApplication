package com.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public String addTodo(Todo todo) {
		return todoRepository.addTodo(todo);
	}

	public String updateTodo(Todo todo) {
		return todoRepository.updateTodo(todo);
	}

	public String deleteTodo(int id) {
		return todoRepository.deleteTodo(id);
	}

	

}
