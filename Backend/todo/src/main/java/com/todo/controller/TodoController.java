package com.todo.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo.model.Todo;
import com.todo.service.TodoService;

@RestController
public class TodoController {

	@Autowired
	private TodoService todoService;

	@GetMapping("/todo")
	public List<Todo> getAllTodo() {
		return todoService.getAllTodo();
	}

	@GetMapping("/todo/{id}")
	public Todo getTodo(@PathVariable int id) {
		return todoService.getTodo(id);
	}

	@PostMapping(value = "/todo")
	public String addTodo(@NotBlank @NotNull @Valid @RequestBody Todo todo) {
		return todoService.addTodo(todo);
	}

	@PutMapping("/todo/{id}")
	public String updateTodo(@RequestBody Todo todo) {
		return todoService.updateTodo(todo);
	}

	@DeleteMapping("/todo/{id}")
	public String deleteTodo(@PathVariable int id) {
		return todoService.deleteTodo(id);
	}
}
