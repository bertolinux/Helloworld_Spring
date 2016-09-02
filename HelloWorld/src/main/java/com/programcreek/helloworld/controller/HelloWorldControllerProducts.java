package com.programcreek.helloworld.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Transaction;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(Constant.PRODUCTS_CONTROLLER_PATH)
public class HelloWorldControllerProducts extends HelloWorldControllerBase {
    
    public HelloWorldControllerProducts() {
    	super(Products.class, "products", Constant.PRODUCTS_CONTROLLER_PATH, "products");
    }

    @RequestMapping("/")
    public ModelAndView root() {
	    ModelAndView mv = new ModelAndView(rootJsp);
		mv.addObject("name", title);
		mv.addObject("controller_url", path);
		mv.addObject("title", title);
		return mv;
    }
    
	@RequestMapping(value = "/search", produces = "application/json")
	public @ResponseBody List<HashMap<String, String>> search(
			@RequestParam(value = "starts_with", required = true, defaultValue = "") String startsWith
	) {
		@SuppressWarnings("unchecked")
		List<Products> items = session.
			createCriteria(Products.class).	
			add( Restrictions.like("name", "%"+startsWith+"%")).
		    addOrder( Property.forName("name").asc() ).
			list();
		
		List<HashMap<String, String>> m = new ArrayList<HashMap<String, String>>();
		for (int i=0; i < items.size(); i++) {
			String key = new Integer(items.get(i).getId()).toString();
			String value = items.get(i).getName();
			HashMap <String,String> item = new HashMap<String,String>();
			item.put("id", key);
			item.put("value", value);
			m.add(item);
		}
		return m;
	}    
	
	@RequestMapping("/insert")
	public @ResponseBody String insert(@RequestParam(value = "n", required = true, defaultValue = "") String n) {
		
		String return_insert;
		Transaction transaction = session.beginTransaction();
		try {
			for (int i = 0; i < new Integer(n); i++) {
				Products product = new Products();
				product.setName("Name " + randomString(n));				
				session.save(product);  
			}
			transaction.commit();
            return_insert = new String("OK");
		} catch (Exception e) {
			// TODO Auto-generated catch block 
			transaction.rollback();
            e.printStackTrace();
            return_insert = new String("KO");
		}		
		return return_insert;
	}	
}