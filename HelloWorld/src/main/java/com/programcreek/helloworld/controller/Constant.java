package com.programcreek.helloworld.controller;

public class Constant {
	public static final String USERS_CONTROLLER_PATH = "Users";
	public static final String PRODUCTS_CONTROLLER_PATH = "Products";
	public static final String SALES_CONTROLLER_PATH = "Sales";
	
	public static String capitalize(String in) {
		return Character.toUpperCase(in.charAt(0)) + in.substring(1);
	}
}
