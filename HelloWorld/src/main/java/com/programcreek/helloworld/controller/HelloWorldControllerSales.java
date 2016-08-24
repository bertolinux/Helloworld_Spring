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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(Constant.SALES_CONTROLLER_PATH)
public class HelloWorldControllerSales {
	
	String message = "Welcome to Spring MVC!";
    private Configuration cfg;
    private static SessionFactory sessionFactory;
    private Session session;
    
    public HelloWorldControllerSales() {
    }
	 
	@RequestMapping("/")
	public ModelAndView showMessage1(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		
		ModelAndView mv = new ModelAndView("products");
		mv.addObject("name", name);
		mv.addObject("controller_url", Constant.SALES_CONTROLLER_PATH);
		
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
		
		mv.addObject("message", obj.getMessage());
		mv.addObject("title", "products");
		return mv;
	}	
	
	@RequestMapping("/search")
	public ModelAndView search_users(
			@RequestParam(value = "search_string", required = true, defaultValue = "") String startsWith) {

		ModelAndView mv = new ModelAndView("search_products");
    	try {
	    	cfg = new Configuration().configure().addAnnotatedClass(Products.class);
	    	sessionFactory = cfg.buildSessionFactory(new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build());
	    	session = sessionFactory.openSession();
	    	System.out.println("Tutto ok1");
    	} catch (Exception e) {
    		e.printStackTrace();
    	}		
		@SuppressWarnings("unchecked")
		List<Products> search_productsFound = session.
				createCriteria(Products.class).
				add( Restrictions.like("name", "%"+startsWith+"%")).
			    addOrder( Property.forName("name").asc() ).
				list();
		
		// List to JSON conversion using JAVA
		JSONObject productsFoundJson = new JSONObject();
		JSONArray productsFoundJsonArray = new JSONArray();
		for (int i=0; i < search_productsFound.size(); i++) {
			JSONObject user = new JSONObject();
			user.put("name", search_productsFound.get(i).getName());
			productsFoundJsonArray.put(user);
		}
		productsFoundJson.put("productsFound", productsFoundJsonArray);
		
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
		mv.addObject("controller_url", Constant.SALES_CONTROLLER_PATH);
		mv.addObject("productsFoundJson", productsFoundJson.toString()); 
		return mv;
	}
	
	@RequestMapping("/insert")
	public ModelAndView insert_users(
			@RequestParam(value = "n_users", required = true, defaultValue = "") String n_products) {
    	try {
	    	System.out.println("Fase 1");
	    	cfg = new Configuration().configure().addAnnotatedClass(Products.class);
	    	System.out.println("Fase 2");
	    	sessionFactory = cfg.buildSessionFactory(new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build());
	    	System.out.println("Fase 3");
	    	session = sessionFactory.openSession();
	    	System.out.println("Fase 4");
    	} catch (Exception e) {
    		e.printStackTrace();
	    	System.out.println("Fase ex");
    	}
		ModelAndView mv = new ModelAndView("insert_products");
		
		String return_insert;
		// Hibernate insert into users table
		try {
			Transaction transaction = session.beginTransaction();
			for (int i = 0; i < new Integer(n_products); i++) {
				long random = Math.round(Math.random()*(new Integer(n_products)));
				String random_string = new Long(random).toString();
				session.save(new Products("Product " + random_string)); 
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
		mv.addObject("controller_url", Constant.SALES_CONTROLLER_PATH);
		return mv;
	}		
	
	@RequestMapping("/a/{seconda}/{terza}/{quarta}")
	public ModelAndView a_b_c_d(
			@RequestParam(value = "n_users", required = true, defaultValue = "") String n_products,
			@PathVariable String seconda,
			@PathVariable String terza,
			@PathVariable String quarta) {
		
		ModelAndView mv = new ModelAndView("abcd");
		mv.addObject("seconda",seconda);
		mv.addObject("terza",terza);
		mv.addObject("quarta",quarta);
		return mv;
	}
	
}