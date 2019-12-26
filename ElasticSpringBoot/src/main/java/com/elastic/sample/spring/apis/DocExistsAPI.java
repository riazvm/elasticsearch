package com.elastic.sample.spring.apis;

import java.io.IOException;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.springframework.beans.factory.annotation.Autowired;

public class DocExistsAPI {
	
	private RestHighLevelClient client;
	
	@Autowired
    public DocExistsAPI(RestHighLevelClient client) {
        this.client = client;

    }
	
	 
    public DocExistsAPI() {

    }

    public boolean existsByID(final String index, final String id)
    {
    	try 
    	{
    		 final GetRequest request = new GetRequest(index, id)
                     .fetchSourceContext(new FetchSourceContext(false))
                     .storedFields("_none_");

             return client.exists(request, RequestOptions.DEFAULT);
        } catch (final IOException e) 
    	{
            throw new RuntimeException(e);
        }
    }

}
