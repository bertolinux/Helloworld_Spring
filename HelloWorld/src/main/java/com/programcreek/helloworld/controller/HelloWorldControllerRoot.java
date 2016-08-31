package com.programcreek.helloworld.controller;

import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HelloWorldControllerRoot {
    protected Session session;
    @SuppressWarnings("rawtypes")
	Class entityClass;
    String title;
    String path;
    String rootJsp;
    
    public HelloWorldControllerRoot() {
		this.title = "Helloworld";
    }
	
    @RequestMapping("*")
    public ModelAndView root() {
	    ModelAndView mv = new ModelAndView("root");
		mv.addObject("name", title);
		mv.addObject("title", Constant.capitalize(title));
		return mv;
    }    
}