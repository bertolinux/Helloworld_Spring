package com.programcreek.helloworld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(Constant.PRODUCTS_CONTROLLER_PATH)
public class HelloWorldControllerProducts extends HelloWorldControllerBase {
	
	String message = "Welcome to Spring MVC!";
    
    public HelloWorldControllerProducts() {
    	super(Products.class, "products", Constant.PRODUCTS_CONTROLLER_PATH,"products");
    }	
}