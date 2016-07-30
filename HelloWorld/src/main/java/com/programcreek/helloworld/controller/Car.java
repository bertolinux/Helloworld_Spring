package com.programcreek.helloworld.controller;

public class Car implements Vehicle {
	private String name;
	
	public Car(String name) {
		this.name = name;
	}
	
	public String start() {
		return "This is a " + name + " Car";
	}
	
}
