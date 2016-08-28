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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
    
    private void initHibernate() {
		Configuration cfg = new Configuration().configure().addAnnotatedClass(entityClass);
		SessionFactory sessionFactory = cfg
				.buildSessionFactory(new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build());
		session = sessionFactory.openSession(); 	
    }
    
    @SuppressWarnings("rawtypes")
	public HelloWorldControllerRoot(Class entityClass, String title, String path, String rootJsp) {
    	this.entityClass = entityClass;
    	this.title = title;
    	this.path = path;
    	this.rootJsp = rootJsp;
    	initHibernate();
    }
    
	public HelloWorldControllerRoot() {
		this.title = "Helloworld";
    }
	
    @RequestMapping("*")
    public ModelAndView root() {
    	System.out.println("ciao sono su root!");
	    ModelAndView mv = new ModelAndView("root");
		mv.addObject("name", title);
//		mv.addObject("controller_url", path);
		mv.addObject("title", title);
		return mv;
    }    
    
    private String hibernateToJsonString(List<searchRecord> items) {		
    	
		JSONArray jsonarr = new JSONArray();
		for (int i=0; i < items.size(); i++) {
			JSONObject temp = new JSONObject();
			temp.put("name", ((searchRecord) items.get(i)).getName());
			jsonarr.put(temp);
		}
    	JSONObject json = new JSONObject();
    	json.put("items", jsonarr);
    	return json.toString();
    }
	
	@RequestMapping(value = "/search", produces = "application/json")
	public @ResponseBody String search(
			@RequestParam(value = "starts_with", required = true, defaultValue = "") String startsWith
	) {
		@SuppressWarnings("unchecked")
		List<searchRecord> items = session.
				createCriteria(entityClass).
				add( Restrictions.like("name", "%"+startsWith+"%")).
			    addOrder( Property.forName("name").asc() ).
				list();

		return hibernateToJsonString(items);
	}    
	
	@RequestMapping("/insert")
	public @ResponseBody String insert(@RequestParam(value = "n", required = true, defaultValue = "") String n) {
		
		String return_insert;
		// Hibernate insert into users table
		String capitalizeTitle = Character.toUpperCase(title.charAt(0)) + title.substring(1);
		try {
			Transaction transaction = session.beginTransaction();
			for (int i = 0; i < new Integer(n); i++) {
				long random = Math.round(Math.random()*(new Integer(n)));
				String random_string = new Long(random).toString();
				Object object = entityClass.newInstance();
				((searchRecord) object).setName(capitalizeTitle + " " + random_string);
				session.save(object);  
			}
			transaction.commit();
            return_insert = new String("OK");
		} catch (Exception e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
            return_insert = new String("KO");
		}		
		return return_insert;
	}	
}