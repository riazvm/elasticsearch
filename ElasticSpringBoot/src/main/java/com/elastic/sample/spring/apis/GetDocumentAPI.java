package com.elastic.sample.spring.apis;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;

public class GetDocumentAPI {
	
	private RestHighLevelClient client;
	
	@Autowired
    public GetDocumentAPI(RestHighLevelClient client) {
        this.client = client;

    }
	
    public GetDocumentAPI() {

    }

    public GetResponse getByID(final String index,final String id)
    {
    	try 
    	{
    		
    		final GetRequest request = new GetRequest(index, id);

            return client.get(request, RequestOptions.DEFAULT);
        } catch (final IOException e) 
    	{
            throw new RuntimeException(e);
        }
    }

}

