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
@RequestMapping(Constant.USERS_CONTROLLER_PATH)
public class HelloWorldControllerUsers extends HelloWorldControllerBase {
    
    public HelloWorldControllerUsers() {
    	super(Users.class, "users", Constant.USERS_CONTROLLER_PATH, "users");
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
	public @ResponseBody List<HashMap<String, String>> actions(
			@RequestParam(value = "string", required = true, defaultValue = "") String string
	) {
		
		@SuppressWarnings("unchecked")
		List<Users> items = session.
			createCriteria(Users.class).	
			add( Restrictions.like("name", "%"+string+"%")).
		    addOrder( Property.forName("name").asc() ).
			list();
		
		List<HashMap<String, String>> listMap = new ArrayList<HashMap<String, String>>();
		for (int i=0; i < items.size(); i++) {
			
			String key = new Integer(items.get(i).getId()).toString();
			String value = items.get(i).getName();
			
			HashMap <String,String> item = new HashMap<String,String>();
			item.put("id", key);
			item.put("value", value);
			
			listMap.add(item);
		}
		return listMap;
	}  	
	
	@RequestMapping(value = "/insert", produces = "application/json")
	public @ResponseBody String insertNew(@RequestParam(value = "string", required = true, defaultValue = "") String string) {
		
		String return_insert;
		Transaction transaction = session.beginTransaction();;
		try {
			Users user = new Users();				
			user.setName(string);				
			session.save(user);  
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