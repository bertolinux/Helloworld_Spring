package com.programcreek.helloworld.controller;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "products")
public class Products  implements Serializable {
	@Id	
	@GeneratedValue
	@Column(name = "id")
    private int id;
	@Column(name = "name")
	private String name;
	
	public Products(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Products(String name) {
		this.name = name;
	}

	public Products() {
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
