package com.example.springsocial.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.springsocial.filters.TokenCheckFilter;

@Configuration
public class CustomConfig {
	@Bean
	public FilterRegistrationBean<OncePerRequestFilter> loggingFilter(){
	    FilterRegistrationBean<OncePerRequestFilter> registrationBean 
	      = new FilterRegistrationBean<>();
	    registrationBean.setFilter(new TokenCheckFilter());
	    registrationBean.addUrlPatterns("/business/useToken");
	    return registrationBean;    
	}

}
