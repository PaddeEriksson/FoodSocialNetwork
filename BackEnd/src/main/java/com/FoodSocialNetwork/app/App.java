package com.FoodSocialNetwork.app;


import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */

@ComponentScan
@EnableAutoConfiguration
public class App 
{
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}