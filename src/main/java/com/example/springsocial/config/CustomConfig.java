package com.example.springsocial.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.springsocial.service.TokenCheckFilter;

@Configuration
public class CustomConfig {
	@Autowired
	private TokenCheckFilter tockenFilter;
	@Bean
	public FilterRegistrationBean<OncePerRequestFilter> loggingFilter(){
	    FilterRegistrationBean<OncePerRequestFilter> registrationBean 
	      = new FilterRegistrationBean<>();
	    registrationBean.setFilter(tockenFilter);
	    registrationBean.addUrlPatterns("/business/useToken");
	    return registrationBean;    
	}

}
