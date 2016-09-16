package com.programcreek.helloworld.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
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
@RequestMapping(Constant.SALES_CONTROLLER_PATH)
public class HelloWorldControllerSales extends HelloWorldControllerBase {
    
    public HelloWorldControllerSales() {
    	super(Sales.class, "sales", Constant.SALES_CONTROLLER_PATH, "sales");
    }
    
    @RequestMapping("/")
    public ModelAndView root() {
	    ModelAndView mv = new ModelAndView(rootJsp);
		mv.addObject("name", title);
		mv.addObject("controller_url", path);
		mv.addObject("title", title);
		
		List<Users> users = (new HelloWorldControllerUsers()).getList("");
		
		JSONArray a = new JSONArray();
		a.put(new JSONObject().put("id", "-").put("name", "-"));
		for (int i=0; i < users.size(); i++) {
			JSONObject o = new JSONObject();
			o.put("id", users.get(i).getId());
			o.put("name", users.get(i).getName());
			a.put(o);
		}
		mv.addObject("userStoreJSON", a.toString());

		List<Products> products = (new HelloWorldControllerProducts()).getList("");
		JSONArray a1 = new JSONArray();
		a1.put(new JSONObject().put("id", "-").put("name", "-"));
		for (int i=0; i < products.size(); i++) {
			JSONObject o = new JSONObject();
			o.put("id", products.get(i).getId());
			o.put("name", products.get(i).getName());
			a1.put(o);
		}
		mv.addObject("productStoreJSON", a1.toString());
		
		return mv;
    }
    
	@RequestMapping(value = "/search", produces = "application/json")
	public @ResponseBody List<HashMap<String, String>> search(			
			@RequestParam(value = "user", required = true, defaultValue = "") String user, 
			@RequestParam(value = "product", required = true, defaultValue = "") String product, 
			@RequestParam(value = "string", required = true, defaultValue = "") String Value) {

		session.clear();
		Criteria c = session.createCriteria(Sales.class);
		if (user.compareTo("-") != 0)
			c = c.add(Restrictions.eq("user.id", new Integer(user).intValue()));
		if (product.compareTo("-") != 0)
			c = c.add(Restrictions.eq("product.id", new Integer(product).intValue()));
		if (Value != "")
			c = c.add(Restrictions.like("value", "%"+Value+"%"));
		
		@SuppressWarnings("unchecked")
		List<Sales> items = c.
		    addOrder( Property.forName("value").asc() ).
		    setCacheable(false).
			list();
		
		List<HashMap<String, String>> m = new ArrayList<HashMap<String, String>>();
		for (int i=0; i < items.size(); i++) {
			String key = new Integer(items.get(i).getId()).toString();
			HashMap <String,String> item = new HashMap<String,String>();
			item.put("id", key);
			item.put("value", items.get(i).getValue());
			item.put("user", items.get(i).getUser().getName());
			item.put("product", items.get(i).getProduct().getName());
			item.put("date", items.get(i).getDate().toString());
			m.add(item);
		}
		return m;
	};    
	
	@RequestMapping(value = "/save", produces = "application/json")
	public @ResponseBody String save(@RequestParam(value = "string", required = true, defaultValue = "") String string) {
		
		String return_insert;
		Transaction transaction = session.beginTransaction();
		try {
			JSONArray data = new JSONArray(string); 
			System.out.println(data.length());
			for (int i=0; i < data.length(); i++) {
				JSONObject obj = (JSONObject) data.get(i);
				Sales sale = null;
				try {
					sale = (Sales) session.get(Sales.class,new Integer(obj.getString("id")));
					sale.setValue(obj.getString("value"));
				} catch(Exception e) {
					Users user = (Users) session.get(Users.class,obj.getInt("iduser"));
					Products product = (Products) session.get(Products.class,obj.getInt("idproduct"));
					sale = new Sales();
					sale.setUser(user);
					sale.setProduct(product);
					sale.setValue(obj.getString("value"));
				} 				
				session.save(sale);  
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
				Sales sale = (Sales) session.get(Sales.class,new Integer(data[i]));
				session.delete(sale);  
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