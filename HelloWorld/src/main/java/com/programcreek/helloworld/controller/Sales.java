package com.programcreek.helloworld.controller;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("serial")
@Entity
@Table(name = "sales")
public class Sales  implements Serializable {
	@Id	
	@GeneratedValue
	@Column(name = "id")
    private int id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "iduser", referencedColumnName="id")
	private Users user;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "idproduct", referencedColumnName="id")
	private Products product;
	
	@Column(name = "date")
    private Timestamp date;
	@Column(name = "value")
    private String value;
	
	public Sales() {
	}

	public Sales(Users user, Products product, String value) {
		this.user = user;
		this.product = product;
		this.value = value;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}
}
