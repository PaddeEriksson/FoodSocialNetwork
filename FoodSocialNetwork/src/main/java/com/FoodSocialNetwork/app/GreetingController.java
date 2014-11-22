package com.FoodSocialNetwork.app;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FoodSocialNetwork.app.database.DatabaseHandler;


@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Responce greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	try {
			new DatabaseHandler().createName(name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return new Responce(0, "Succes");    }
    
    @RequestMapping("/insert")
    public Responce insert(@RequestParam(value="name", defaultValue="patrik") String name) {
    	try {
			new DatabaseHandler().createName(name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return new Responce(0, "Succes");
    	
    }
    
    @RequestMapping("SelectAll")
    public String[] selectAll()
    {
    	try {
			return new DatabaseHandler().getNames();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
}