package com.programcreek.helloworld.controller;

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
		mv.addObject("selectAttribute", "value");
		return mv;
    }
    
	@RequestMapping(value = "/search", produces = "application/json")
	public @ResponseBody String search(
			@RequestParam(value = "starts_with", required = true, defaultValue = "") String startsWith
	) {
		@SuppressWarnings("unchecked")
		List<Object> items = session.
			createCriteria(Sales.class).	
			add( Restrictions.like("value", "%"+startsWith+"%")).
		    addOrder( Property.forName("value").asc() ).
			list();

		return hibernateToJsonString(items);
	}    
	
	@RequestMapping("/insert")
	public @ResponseBody String insert(@RequestParam(value = "n", required = true, defaultValue = "") String n) {
		
		String return_insert;
		try {
			Transaction transaction = session.beginTransaction();
			for (int i = 0; i < new Integer(n); i++) {
				Sales sale = new Sales();
				sale.setIduser(randomInt(n));				
				sale.setIdproduct(randomInt(n));				
				sale.setValue(randomString(n));				
				session.save(sale);  
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