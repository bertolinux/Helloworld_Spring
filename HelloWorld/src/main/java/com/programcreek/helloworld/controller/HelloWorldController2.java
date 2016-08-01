package com.programcreek.helloworld.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller 
@RequestMapping("New")
public class HelloWorldController2 {
	String message = "Welcome to Spring MVC!";
    private Configuration cfg;
    private static SessionFactory sessionFactory;
    private Session session;
    
    public HelloWorldController2() {
	
    }
    
	@RequestMapping("/ciao")
	public ModelAndView showMessage(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		ModelAndView mv = new ModelAndView("helloworld"); 
		mv.addObject("message", message);   
		mv.addObject("name", name);
		return mv;
	}	
	
}