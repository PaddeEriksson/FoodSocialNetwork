package com.FoodSocialNetwork.app.responce;

import org.springframework.core.convert.converter.Converter;

public class DefaultResponseConverter implements Converter<String, DefaultResponse>{

	@Override
	public DefaultResponse convert(String arg0) {
		DefaultResponse dr = new DefaultResponse("");
		dr.setError(arg0);
		return dr;
	}

	
}