package com.programcreek.helloworld.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;

@Controller
public abstract class HelloWorldControllerBase {
    protected Session session;
    @SuppressWarnings("rawtypes")
	Class entityClass;
    String title;
    String path;
    String rootJsp;
    
    private void initHibernate() {
		Configuration cfg = new Configuration().configure().addAnnotatedClass(entityClass);
		SessionFactory sessionFactory = cfg
				.buildSessionFactory(new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build());
		session = sessionFactory.openSession(); 	
    }
    
    @SuppressWarnings("rawtypes")
	public HelloWorldControllerBase(Class entityClass, String title, String path, String rootJsp) {
    	this.entityClass = entityClass;
    	this.title = Constant.capitalize(title);
    	this.path = path;
    	this.rootJsp = rootJsp;
    	initHibernate();
    }    
    
    protected String randomString(String n) {
		double random = Math.random();
		double randomNormalized = random * (new Double(n));
		int randomNormalizedInt = new Double(randomNormalized).intValue();
		return new Integer(randomNormalizedInt).toString();
    }
    
    protected int randomInt(String n) {
		double random = Math.random();
		double randomNormalized = random * (new Double(n));
		int randomNormalizedInt = new Double(randomNormalized).intValue();
		return new Integer(randomNormalizedInt).intValue();
    }    
}