package com.programcreek.helloworld.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Property;
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
@RequestMapping(Constant.USERS_CONTROLLER_PATH)
public class HelloWorldControllerUsers {
	
	String message = "Welcome to Spring MVC!";
    private Configuration cfg;
    private static SessionFactory sessionFactory;
    private Session session;
    
    public HelloWorldControllerUsers() {
	
    }
	 
	@RequestMapping("/")
	public ModelAndView showMessage1(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		
		ModelAndView mv = new ModelAndView("users");
		mv.addObject("name", name);
		mv.addObject("controller_url", Constant.USERS_CONTROLLER_PATH);
		
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
		
		mv.addObject("message", obj.getMessage());
		mv.addObject("title", "users");
		return mv;
	}	
	
	@RequestMapping("/search")
	public ModelAndView search_users(
			@RequestParam(value = "search_string", required = true, defaultValue = "") String startsWith) {

		ModelAndView mv = new ModelAndView("search_users");
    	try {
	    	cfg = new Configuration().configure().addAnnotatedClass(Users.class);
	    	sessionFactory = cfg.buildSessionFactory(new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build());
	    	session = sessionFactory.openSession();
	    	System.out.println("Tutto ok1");
    	} catch (Exception e) {
    		e.printStackTrace();
    	}		
		@SuppressWarnings("unchecked")
		List<Users> usersFound = session.
				createCriteria(Users.class).
				add( Restrictions.like("name", "%"+startsWith+"%")).
			    addOrder( Property.forName("name").asc() ).
				list();
		
		// List to JSON conversion using JAVA
		JSONObject usersFoundJson = new JSONObject();
		JSONArray usersFoundJsonArray = new JSONArray();
		for (int i=0; i < usersFound.size(); i++) {
			JSONObject user = new JSONObject();
			user.put("name", usersFound.get(i).getName());
			usersFoundJsonArray.put(user);
		}
		usersFoundJson.put("usersFound", usersFoundJsonArray);
		
		/* List to JSON conversion using JSP	
		<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
		<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
		<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
		{"usersFound": [
			 <c:forEach var="user" items="${usersFound}">
				{ "name": "${user.getName()}"},
			</c:forEach>{}
		]}*/
		
		session.close();
		mv.addObject("controller_url", Constant.USERS_CONTROLLER_PATH);
		mv.addObject("usersFoundJson", usersFoundJson.toString()); 
		return mv;
	}
	
	@RequestMapping("/insert")
	public ModelAndView insert_users(
			@RequestParam(value = "n_users", required = true, defaultValue = "") String n_users) {
    	try {
	    	System.out.println("Fase 1");
	    	cfg = new Configuration().configure().addAnnotatedClass(Users.class);
	    	System.out.println("Fase 2");
	    	sessionFactory = cfg.buildSessionFactory(new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build());
	    	System.out.println("Fase 3");
	    	session = sessionFactory.openSession();
	    	System.out.println("Fase 4");
    	} catch (Exception e) {
    		e.printStackTrace();
	    	System.out.println("Fase ex");
    	}
		ModelAndView mv = new ModelAndView("insert_users");
		
		String return_insert;
		// Hibernate insert into users table
		try {
			Transaction transaction = session.beginTransaction();
			for (int i = 0; i < new Integer(n_users); i++) {
				long random = Math.round(Math.random()*(new Integer(n_users)));
				String random_string = new Long(random).toString();
				session.save(new Users("User " + random_string)); 
			}
			transaction.commit();
            return_insert = new String("OK");
		} catch (Exception e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
            return_insert = new String("KO");
		} finally {
			session.close();
			
		}
		// End of Hibernate
		
		mv.addObject("return_insert", return_insert);
		mv.addObject("controller_url", Constant.USERS_CONTROLLER_PATH);
		return mv;
	}		
	
}