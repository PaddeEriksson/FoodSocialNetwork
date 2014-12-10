package com.FoodSocialNetwork.app;


import java.util.HashSet;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import com.FoodSocialNetwork.app.responce.DefaultResponseConverter;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


@Configuration
public class ConfigurationBeans {
	
    @Bean(name = "conversionService")
    public ConversionService getConversionService() {
        ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
        bean.setConverters(getConverters());
        bean.afterPropertiesSet();
        ConversionService object = bean.getObject();
        return object;
    }

    private Set<Converter> getConverters() {
        Set<Converter> converters = new HashSet<Converter>();

        converters.add(new DefaultResponseConverter());

        return converters;
    }
	
	@Bean
	public DataSource dataSource()
	{
		MysqlDataSource myds = new MysqlDataSource();
		
		myds.setUrl("jdbc:mysql://localhost:3306/test");
		myds.setUser("root");
		myds.setPassword("abc123");
		
		return myds;
	}
	
	
	@Bean
	public JdbcOperations jdbcOperations()
	{
		JdbcTemplate returnValue = new JdbcTemplate(dataSource());
		if(returnValue == null)
		{
			System.out.println("ERROR AS HELLL AGAIN");
		}
		return returnValue;
	}
	

}
