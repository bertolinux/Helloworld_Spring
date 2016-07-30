package com.programcreek.helloworld.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller 
public class HelloWorldController {
	String message = "Welcome to Spring MVC!";
    private Configuration cfg;
    private static SessionFactory sessionFactory;
    private Session session;
    
    public HelloWorldController() {

    }
    
	@RequestMapping("/hello")
	public ModelAndView showMessage(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		ModelAndView mv = new ModelAndView("helloworld"); 
		mv.addObject("message", message);   
		mv.addObject("name", name);
		return mv;
	}
	
	@RequestMapping("/hello1")
	public ModelAndView showMessage1(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {

    	try {
	    	cfg = new Configuration().configure().addAnnotatedClass(Users.class);
	    	sessionFactory = cfg.buildSessionFactory();
	    	session = sessionFactory.openSession();
	    	System.out.println("Tutto ok1");
    	} catch (Exception e) {
    		e.printStackTrace();
    	}		
		
		ModelAndView mv = new ModelAndView("helloworld");
		mv.addObject("name", name);
		
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
		
		// Hibernate insert into users table
//		try {
//			Transaction transaction = session.beginTransaction();
//			for (int i = 0; i < 100; i++)
//				session.save(new Users(i,"new " + i)); 
//			transaction.commit();
//            session.close();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block 
//			session.close();
//			e.printStackTrace();
//		}
		// End of Hibernate
		
		List<Users> users = session.createCriteria(Users.class).list();
		
		mv.addObject("nuovo", users.get(0).getName());
		mv.addObject("users", users);
		
		mv.addObject("message", obj.getMessage());
		return mv;
	}	
}