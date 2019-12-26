package com.elastic.sample.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
public class MicroMeterConfig implements WebMvcConfigurer{
	
	  @Override
	  public void addViewControllers(ViewControllerRegistry registry) {
	    //well, that's just for the compatibility with the toolbox...
	    //do not pay too much attention to it
	    registry.addViewController("/prometheus").setViewName("forward:/actuator/prometheus");
	  }
	  

}
