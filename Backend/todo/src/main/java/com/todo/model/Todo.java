package com.todo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "todo")
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private boolean isComplete;
	@Transient
	private Date CreatedOn;
	private String userEmail;
	

	public Todo() {
		super();
	}

	public Todo(int id, String title, boolean isComplete, Date createdOn, String userEmail) {
		super();
		this.id = id;
		this.title = title;
		this.isComplete = isComplete;
		CreatedOn = createdOn;
		this.userEmail = userEmail;
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

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	public Date getCreatedOn() {
		return CreatedOn;
	}

	public void setCreatedOn(Date createdOn) {
		CreatedOn = createdOn;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", title=" + title + ", userEmail=" + userEmail + ", isComplete=" + isComplete
				+ ", CreatedOn=" + CreatedOn + "]";
	}

	
}