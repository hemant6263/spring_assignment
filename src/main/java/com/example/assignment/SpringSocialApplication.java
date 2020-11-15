package com.example.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.example.assignment.config.AppProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class SpringSocialApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SpringSocialApplication.class, args);
	}
}
