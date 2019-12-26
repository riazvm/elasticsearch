package com.elastic.sample.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


import io.jaegertracing.Configuration;


@SpringBootApplication
public class ElasticSampleApplication {

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.build();
	}


	public static void main(String[] args) {
		SpringApplication.run(ElasticSampleApplication.class, args);
	}

}
