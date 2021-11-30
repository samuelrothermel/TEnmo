package com.techelevator.tenmo.model;

// MODEL
// This class represents the User object in the application.
// It defines the properties of the User object and supplies
// the view of these properties.

public class User {

	private Integer id;
	private String username;

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return id + " - " + username;
	}
}
