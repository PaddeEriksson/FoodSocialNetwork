package com.FoodSocialNetwork.app;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.FoodSocialNetwork.app.database.DatabaseHandler;
import com.FoodSocialNetwork.app.database.User;
import com.FoodSocialNetwork.app.database.DAO.UserDAO;
import com.FoodSocialNetwork.app.responce.DefaultResponse;



@RestController
public class GreetingController {

	@Resource
	private UserDAO dao;
	
//    private static final String template = "Hello, %s!";
//    private final AtomicLong counter = new AtomicLong();
//
//    @RequestMapping("/greeting")
//    public Responce greeting(@RequestParam(value="name", defaultValue="World") String name) {
//    	try {
//			//new DatabaseHandler().createName(name);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	
//    	return new Responce(0, "Succes");    }
//    
//    @RequestMapping("/insert")
//    public Responce insert(@RequestParam(value="name", defaultValue="patrik") String name){
//    	try {
//			//new DatabaseHandler().createName(name);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	
//    	int x = 100;
//    	String[] s = new String[x];
//    	ArrayList<String> a = new ArrayList<String>();
//    	
//    	
//    	
//    	for(int i = 0; i < s.length; i++)
//    	{
//    		
//    	}
//    	
//    	return new Responce(0, "Succes");
//    	
//    }
//    
		
    @RequestMapping("/test")
    public DefaultResponse selectAll(@RequestParam(value = "ingridient") String ds)
    {
    	boolean resp = dao.createUser(new User());
    	DefaultResponse dr = new DefaultResponse();
    	dr.setSuccess(resp);
    	if(!resp)
    	{
    		dr.setError("Error in creating User");
    	}
    	
    	
    	return dr;
    }
    
    @Autowired
    ServletContext context;
    
    
    @RequestMapping(value = "/test2",
            method = RequestMethod.GET,
            headers="Accept=image/jpeg, image/jpg, image/png, image/gif")
    public byte[] testphoto() throws IOException {
    	
        InputStream is = this.getClass().getResourceAsStream("/test.png"); 

        // Prepare buffered image.
        BufferedImage img = ImageIO.read(is);

        // Create a byte array output stream.
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        // Write to output stream
        ImageIO.write(img, "png", bao);

        return bao.toByteArray();
        
    }

}