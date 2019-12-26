package com.elastic.sample.spring.apis;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.elastic.sample.spring.config.ElasticsearchConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class CreateDocAPI {

	
	private RestHighLevelClient client;
	
	@Autowired
    public CreateDocAPI(RestHighLevelClient client) {
        this.client = client;

    }
	
    public CreateDocAPI() {

    }

    public IndexResponse syncIndexRequest(final String index,final String jsonStr)
    {
    	try 
    	{
    		
            final IndexRequest request = new IndexRequest(index).source(jsonStr, XContentType.JSON);
            return client.index(request, RequestOptions.DEFAULT);
        } catch (final IOException e) 
    	{
            throw new RuntimeException(e);
        }
    }

  /*  public void asyncIndexRequest(final String index,final String jsonStr) throws InterruptedException
    {
    	final IndexRequest request = new IndexRequest(index).source(jsonStr, XContentType.JSON);
		client.indexAsync(request, RequestOptions.DEFAULT, new ActionListener<>() {
		    @Override
		    public void onResponse(final IndexResponse indexResponse) {
		        // Let's do something!
		    }

		    @Override
		    public void onFailure(final Exception e) {
		        throw new RuntimeException(e);
		    }
		}
      );
    }
*/
}