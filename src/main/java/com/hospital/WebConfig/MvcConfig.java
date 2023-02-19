package com.hospital.WebConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	
	public void addViewController(ViewControllerRegistry registry) {
		registry.addViewController("/error-403").setViewName("error/error-403");
		registry.addViewController("/error-404").setViewName("404");
		
	}

}
