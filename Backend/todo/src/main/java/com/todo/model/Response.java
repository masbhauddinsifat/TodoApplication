package com.todo.model;

public class Response {

	int statusCode;
	String message;

	public Response() {
		super();
	}

	public Response(int statusCode, String message) {
		super();
		this.statusCode = statusCode;
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Responce [statusCode=" + statusCode + ", message=" + message + "]";
	}

}
