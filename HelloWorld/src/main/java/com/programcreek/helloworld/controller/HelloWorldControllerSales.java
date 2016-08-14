package com.programcreek.helloworld.controller;

import java.sql.Wrapper;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.boot.MetadataSources;
import org.hibernate.ogm.OgmSessionFactory;
import org.hibernate.ogm.boot.OgmSessionFactoryBuilder;
import org.hibernate.ogm.cfg.OgmConfiguration;
import org.hibernate.ogm.cfg.OgmProperties;
import org.hibernate.ogm.datastore.infinispan.Infinispan;
import org.hibernate.service.ServiceRegistry;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(Constant.SALES_CONTROLLER_PATH)
public class HelloWorldControllerSales {
	
	String message = "Welcome to Spring MVC!";
    private Configuration cfg;
    private static SessionFactory sessionFactory;
    private Session session;
    private ServiceRegistry serviceRegistry;
    
    public HelloWorldControllerSales() {
    }
	 
	@RequestMapping("/")
	public ModelAndView showMessage1(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		
		ModelAndView mv = new ModelAndView("sales");
		mv.addObject("name", name);
		mv.addObject("controller_url", Constant.SALES_CONTROLLER_PATH);

		mv.addObject("title", "sales");
		return mv;
	}	
	
	@SuppressWarnings("deprecation")
	@RequestMapping("/search")
	public ModelAndView search_users(
			@RequestParam(value = "search_string", required = true, defaultValue = "") String startsWith) {

		ModelAndView mv = new ModelAndView("search_sales");
    	try {

    		StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
			    .applySetting( OgmProperties.ENABLED, true )
			    //assuming you are using JTA in a non container environment
			    .applySetting( AvailableSettings.TRANSACTION_COORDINATOR_STRATEGY, "jta" )
			    //assuming JBoss TransactionManager in standalone mode
			    .applySetting( AvailableSettings.JTA_PLATFORM, "JBossTS" )
			    //assuming Infinispan as the backend, using the default settings
			    .applySetting( OgmProperties.DATASTORE_PROVIDER, Infinispan.DATASTORE_PROVIDER_NAME )
			    .build();

			//build the SessionFactory
			OgmSessionFactory sessionFactory = new MetadataSources( registry )
			    .addAnnotatedClass( Sales.class )
			    .buildMetadata()
			    .getSessionFactoryBuilder()
			    .unwrap( OgmSessionFactoryBuilder.class )
			    .build();	    			
	    			
    			session = sessionFactory.openSession();
	    	System.out.println("Tutto ok1");
    	} catch (Exception e) {
    		e.printStackTrace();
    	}		

//		@SuppressWarnings("unchecked")
//		List<Sales> search_salesFound = session.
//				createCriteria(Sales.class).
//				add( Restrictions.like("user", "%"+startsWith+"%")).
//			    addOrder( Property.forName("user").asc() ).
//				list();
//		
//		// List to JSON conversion using JAVA
//		JSONObject salesFoundJson = new JSONObject();
//		JSONArray salesFoundJsonArray = new JSONArray();
//		for (int i=0; i < search_salesFound.size(); i++) {
//			JSONObject sale = new JSONObject();
//			sale.put("user", search_salesFound.get(i).getUser());
//			salesFoundJsonArray.put(sale);
//		}
//		salesFoundJson.put("salesFound", salesFoundJsonArray);
//		

		@SuppressWarnings("unchecked")
		List<Sales> search_salesFound = session.
			createCriteria(Sales.class).
//			add( Restrictions.like("user", "%"+startsWith+"%")).
//			addOrder( Property.forName("user").asc() ).
			list();

// List to JSON conversion using JAVA
JSONObject salesFoundJson = new JSONObject();
JSONArray salesFoundJsonArray = new JSONArray();
for (int i=0; i < 1; i++) {
	JSONObject sale = new JSONObject();
	sale.put("sale", search_salesFound.size());
	salesFoundJsonArray.put(sale);
}
salesFoundJson.put("salesFound", salesFoundJsonArray);
		
		
		session.close();
		mv.addObject("controller_url", Constant.SALES_CONTROLLER_PATH);
		mv.addObject("salesFoundJson", salesFoundJson.toString()); 
		return mv;
	}
	
	@RequestMapping("/insert")
	public ModelAndView insert_users(
			@RequestParam(value = "n_users", required = true, defaultValue = "") String n_products) {
    	try {
	    	System.out.println("Fase 1");
	    	cfg = new Configuration().configure("hibernate.cfg-mongodb.xml").addAnnotatedClass(Products.class);
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
		mv.addObject("controller_url", Constant.PRODUCTS_CONTROLLER_PATH);
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