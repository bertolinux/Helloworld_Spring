package com.programcreek.helloworld.controller;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users {
	@Id
	@Column(name = "id")
    private int id;
	@Column(name = "name")
	private String name;
	
	public Users(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Users() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
