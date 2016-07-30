package com.programcreek.helloworld.controller;

public class HelloWorld {
	private String message;
	private Vehicle vehicle;

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message + " Vehicle: " + vehicle.start();
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
}
