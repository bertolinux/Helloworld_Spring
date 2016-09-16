package com.programcreek.helloworld.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Transaction;
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
@RequestMapping(Constant.USERS_CONTROLLER_PATH)
public class HelloWorldControllerUsers extends HelloWorldControllerBase {
    
    public HelloWorldControllerUsers() {
    	super(Users.class, "users", Constant.USERS_CONTROLLER_PATH, "users");
    }
    
    public List<Users> getList(String string) {
		@SuppressWarnings("unchecked")
		List<Users> items = session.
			createCriteria(Users.class).	
			add( Restrictions.like("name", "%"+string+"%")).
		    addOrder( Property.forName("name").asc() ).
			list();
		return items;
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
			@RequestParam(value = "string", required = true, defaultValue = "") String string
	) {
		List<Users> items = this.getList(string);
		List<HashMap<String, String>> listMap = new ArrayList<HashMap<String, String>>();
		for (int i=0; i < items.size(); i++) {
			
			String key = new Integer(items.get(i).getId()).toString();
			String value = items.get(i).getName();
			
			HashMap <String,String> item = new HashMap<String,String>();
			item.put("id", key);
			item.put("name", value);
			
			listMap.add(item);
		}
		return listMap;
	} 
	
	@RequestMapping(value = "/save", produces = "application/json")
	public @ResponseBody String save(@RequestParam(value = "string", required = true, defaultValue = "") String string) {
		
		String return_insert;
		Transaction transaction = session.beginTransaction();
		try {
			JSONArray data = new JSONArray(string); 
			System.out.println(data.length());
			for (int i=0; i < data.length(); i++) {
				JSONObject obj = (JSONObject) data.get(i);
				Users user = null;
				try {
					user = (Users) session.get(Users.class,new Integer(obj.getString("id")));
					user.setName(obj.getString("name"));
				} catch(Exception e) {
					user = new Users(obj.getString("name"));
				} 
									
				session.save(user);  
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
	
	@RequestMapping(value = "/remove", produces = "application/json")
	public @ResponseBody String remove(@RequestParam(value = "string", required = true, defaultValue = "") String string) {
		
		String return_insert;
		
		Transaction transaction = session.beginTransaction();
		try {
			String[] data = string.split(",");
			for (int i=0; i < data.length; i++) {
				Users user = (Users) session.get(Users.class,new Integer(data[i]));
				session.delete(user);  
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