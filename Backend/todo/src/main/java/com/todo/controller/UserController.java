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

import com.todo.model.User;
import com.todo.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/user")
	public List<User> getAllUser(){
		return userService.getAllUser();
	}
	
	@GetMapping("/user/{email}")
	public User getSingleUser(@PathVariable String email) {
		return userService.getSingleUser(email);
	}
	
	@PostMapping("/user")
	public String postUser(@Valid @NotNull @NotBlank @RequestBody User user) {
		return userService.postUser(user);
	}
	
	@PutMapping("/user/{email}")
	public String updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}
	
	@DeleteMapping("/user/{email}")
	public String deleteUser(@RequestBody User user) {
		return userService.deleteUser(user);
	}
}
