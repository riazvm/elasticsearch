package com.elastic.sample.spring.apis;

import java.io.IOException;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;

public class DeleteDocAPI {

private RestHighLevelClient client;
	
	@Autowired
    public DeleteDocAPI(RestHighLevelClient client) {
        this.client = client;

    }
	
    public DeleteDocAPI() {

    }

    public DeleteResponse deleteByID(final String index, final String id)
    {
    	try 
    	{
    		final DeleteRequest request = new DeleteRequest(index, id);
    		return client.delete(request, RequestOptions.DEFAULT);
        } catch (final IOException e) 
    	{
            throw new RuntimeException(e);
        }
    }
}
