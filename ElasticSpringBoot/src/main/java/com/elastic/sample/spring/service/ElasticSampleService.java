package com.elastic.sample.spring.service;

import org.elasticsearch.action.index.IndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elastic.sample.spring.apis.CreateDocAPI;
import com.elastic.sample.spring.document.MoviesDocument;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ElasticSampleService {
	
	private ObjectMapper objectMapper;
	
	@Autowired
	CreateDocAPI idxAPI;
	
    @Autowired
    public ElasticSampleService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        
        
    }
    
    public String CreateMoviesDocument(MoviesDocument document, String index) throws Exception {
       return idxAPI.syncIndexRequest(index, objectMapper.writeValueAsString(document)).getResult().name();
    }


}
