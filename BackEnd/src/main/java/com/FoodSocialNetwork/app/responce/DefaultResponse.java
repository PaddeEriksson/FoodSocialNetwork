package com.FoodSocialNetwork.app.responce;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Component;



/**
 * THe default response contains a boolean with that tells if the login was success full and a error with the error if any
 * 
 * @author Toshiba
 *
 */
public class DefaultResponse {
	private boolean success;
	private String error;
	
	public DefaultResponse()
	{
		
	}
	
	public DefaultResponse(String json)
	{
		JSONObject s;
		try {
			s = new JSONObject(json);
			this.setError(s.getString("name"));

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
}
