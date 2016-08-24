package com.programcreek.helloworld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(Constant.USERS_CONTROLLER_PATH)
public class HelloWorldControllerUsers extends HelloWorldControllerBase {
	
	String message = "Welcome to Spring MVC!";
    
    public HelloWorldControllerUsers() {
    	super(Users.class, "users", Constant.USERS_CONTROLLER_PATH,"users");
    }	
}