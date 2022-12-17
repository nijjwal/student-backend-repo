package com.nijjwal.dto;

public class CustomStudentResponse {

	private String firstName;

	public CustomStudentResponse() {

	}

	public CustomStudentResponse(String firstName) {
		super();
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

}
