package com.programcreek.helloworld.controller;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.json.JSONArray;
import org.json.JSONObject;
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
    
    @SuppressWarnings("unchecked")
	protected String hibernateToJsonString(List<Object> items) {	
    	JSONArray jsonarr = new JSONArray();
		for (int i=0; i < items.size(); i++) {
			JSONObject temp = new JSONObject();
	    	for (Field field : entityClass.getDeclaredFields()) {
    			String name = field.getName();
    			String capitalizeName = Constant.capitalize(name);
    	        Method getNameMethod;
				try {
					getNameMethod = entityClass.getMethod("get"+capitalizeName);
					String value = (String) getNameMethod.invoke(items.get(i)).toString();
					temp.put(name, value);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			jsonarr.put(temp);
		}
    	JSONObject json = new JSONObject();
    	json.put("items", jsonarr);
    	return json.toString();
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