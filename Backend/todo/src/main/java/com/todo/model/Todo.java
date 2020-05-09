package com.todo.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "todo")
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;

	public Todo() {
		super();
	}

	public Todo(int id, String title, User user) {
		super();
		this.id = id;
		this.title = title;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getUserEmail() {
		return user;
	}

	public void setUserEmail(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", title=" + title + ", user=" + user + "]";
	}

}