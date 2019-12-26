package com.elastic.sample.spring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.elastic.sample.spring.document.MoviesDocument;
import com.elastic.sample.spring.service.ElasticSampleService;


@RestController
public class ElasticSampleController {

	 private ElasticSampleService service;

    @Autowired
    public ElasticSampleController(ElasticSampleService service) {

        this.service = service;
    }

	
	@PostMapping("/createmovies")
    public String createmovies(@Valid @RequestBody MoviesDocument movies) throws Exception {
    	 //Product tmpProduct= productRepo.save(product);
		String index="movies";
		System.out.println(movies.getMovieId());
    	return service.CreateMoviesDocument(movies, index);
        
    }
	 @RequestMapping("/failelastic")
	    public String failaccount() throws InterruptedException {
		 throw new RuntimeException("No Such Method");
	 }
	
    @RequestMapping("/health")
    public String getHealth() throws InterruptedException {
        return "Ok";
    }

}
