package com.elastic.sample.spring.apis;

import java.io.IOException;

import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;

public class UpdateDocAPI {
	
	private RestHighLevelClient client;
	
	@Autowired
    public UpdateDocAPI(RestHighLevelClient client) {
        this.client = client;

    }
	
	 
    public UpdateDocAPI() {

    }
    
    
    public UpdateResponse updateDocRequest(final String index,final String jsonStr, final String id)
    {
    	try 
    	{
    		final UpdateRequest request = new UpdateRequest(index, id).doc(jsonStr, XContentType.JSON);
    		return client.update(request, RequestOptions.DEFAULT);
       
        } catch (final IOException e) 
    	{
            throw new RuntimeException(e);
        }
    }
    
    
    public UpdateResponse upsertDocRequest(final String index,final String jsonStr, final String id)
    {
    	try 
    	{

    		final UpdateRequest request = new UpdateRequest(index, id).doc(jsonStr, XContentType.JSON).upsert(jsonStr, XContentType.JSON);
    		return client.update(request, RequestOptions.DEFAULT);
       
        } catch (final IOException e) 
    	{
            throw new RuntimeException(e);
        }
    }
    

}
