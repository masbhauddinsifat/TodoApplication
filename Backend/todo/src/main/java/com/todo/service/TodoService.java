//package com.todo.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.todo.model.Todo;
//import com.todo.repository.TodoRepository;
//
//@Service
//public class TodoService {
//
//	@Autowired
//	private TodoRepository todoRepo;
//
//	public List<Todo> getAllTodo() {
//		return todoRepo.findAll();
//
//	}
//
//	public Todo getTodo(int id) {
//		return todoRepo.findById(id).orElse(null);
//	}
//
//	public Todo addTodo(Todo todo) {
//		return todoRepo.save(todo);
//	}
//
//	public Todo updateTodo(Todo todo) {
//		return todoRepo.save(todo);
//	}
//
//	public String deleteTodo(int id) {
//		try {
//			todoRepo.deleteById(id);
//			return "delete successfull";
//		} catch (Exception e) {
//			return "" + e;
//		}
//
//	}
//
//}
