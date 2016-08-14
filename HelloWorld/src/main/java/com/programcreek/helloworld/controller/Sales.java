package com.programcreek.helloworld.controller;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sales")
public class Sales {
	@Id	
	@GeneratedValue
	@Column(name = "id")
    private int id;
	@Column(name = "user")
	private String user;
	@Column(name = "product")
	private String product;
	
	public Sales(String user, String product) {
		this.user = user;
		this.product = product;
	}

	public Sales() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}
}
