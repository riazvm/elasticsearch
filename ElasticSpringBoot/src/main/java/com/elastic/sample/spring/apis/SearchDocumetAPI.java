package com.elastic.sample.spring.apis;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.ClearScrollRequest;
import org.elasticsearch.action.search.ClearScrollResponse;
import org.elasticsearch.action.search.MultiSearchRequest;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

public class SearchDocumetAPI {

	
private RestHighLevelClient client;
	
	@Autowired
    public SearchDocumetAPI(RestHighLevelClient client) {
        this.client = client;

    }
	
    public SearchDocumetAPI() {

    }
     // Search All Indicies
    
    public SearchResponse searchAllIndicies(final String index,final String id)
    {
    	try 
    	{
    	    SearchRequest searchRequest = new SearchRequest(); 
    	   // SearchRequest searchRequest = new SearchRequest(index);
    	    //sourceBuilder.query(QueryBuilders.termQuery("user", "kimchy"));
    	    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder(); 
    	    searchSourceBuilder.query(QueryBuilders.matchAllQuery()); 
    	    searchRequest.source(searchSourceBuilder);
    	    return client.search(searchRequest, RequestOptions.DEFAULT);
    	    
        } catch (final Exception e) 
    	{
            throw new RuntimeException(e);
        }
    }
    // Search Specific Indicies
    public SearchResponse searchByIndicies(final String index,final String id)
    {
    	try 
    	{
    	    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder(); 
    	    sourceBuilder.query(QueryBuilders.termQuery("genre", "action")); 
    	    // result index to start default is 0
    	    sourceBuilder.from(0); 
    	    //size of result set
    	    sourceBuilder.size(5); 
    	    sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC)); 
    	    sourceBuilder.sort(new FieldSortBuilder("id").order(SortOrder.ASC));  
    	    sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS)); 
    	    
    	    
    	    //Returns documents that match a provided text, number, date or boolean value. The provided text is analyzed before matching.//
    	   /*
    	    QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", "Interstellar")
                    .fuzziness(Fuzziness.AUTO)
                    .prefixLength(3)
                    .maxExpansions(10);
    	    sourceBuilder.query(matchQueryBuilder);
    	       */
    	    
    	    
    	    // Source Filtering, by default everything is returned
    	   /* sourceBuilder.fetchSource(false);
    	    String[] includeFields = new String[] {"title", "genre"};
    	    String[] excludeFields = new String[] {"year"};
    	    sourceBuilder.fetchSource(includeFields, excludeFields);
			*/
    	    
    	    
    	    SearchRequest searchRequest = new SearchRequest(index);
    	    searchRequest.source(sourceBuilder);
    	 
    	    return client.search(searchRequest, RequestOptions.DEFAULT);
    	    
        } catch (final Exception e) 
    	{
            throw new RuntimeException(e);
        }
    }
    
    //The Scroll API can be used to retrieve a large number of results from a search request.

    
    public SearchResponse scrollAPI(final String index,final String id)
    {
    	try 
    	{
    		final Scroll scroll = new Scroll(TimeValue.timeValueMinutes(1L));
	    	SearchRequest searchRequest = new SearchRequest(index);
	    	searchRequest.scroll(scroll);
	    	SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
	    	searchSourceBuilder.query(QueryBuilders.matchQuery("title", "Interstellar"));
	    	searchRequest.source(searchSourceBuilder);
	
	    	SearchResponse SearchResponse = client.search(searchRequest, RequestOptions.DEFAULT); 
	    	
	    	
	    	/*
	    	 String scrollId = searchResponse.getScrollId();
	    	SearchHit[] searchHits = searchResponse.getHits().getHits();
	
	    	while (searchHits != null && searchHits.length > 0) { 
	    	    
	    	    SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId); 
	    	    scrollRequest.scroll(scroll);
	    	    searchResponse = client.scroll(scrollRequest, RequestOptions.DEFAULT);
	    	    scrollId = searchResponse.getScrollId();
	    	    searchHits = searchResponse.getHits().getHits();
	    	}
	
	    	ClearScrollRequest clearScrollRequest = new ClearScrollRequest(); 
	    	clearScrollRequest.addScrollId(scrollId);
	    	ClearScrollResponse clearScrollResponse = client.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);
	    	boolean succeeded = clearScrollResponse.isSucceeded();
    	 	*/
    	    return SearchResponse;
    	    
        } catch (final Exception e) 
    	{
            throw new RuntimeException(e);
        }
    }
    
    // Multiple queries executed in parallel
    
    public MultiSearchResponse mutiSearchAPI(final String index,final String id)
    {
    	try 
    	{
    		MultiSearchRequest request = new MultiSearchRequest();    
    	    SearchRequest firstSearchRequest = new SearchRequest();   
    	    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    	    searchSourceBuilder.query(QueryBuilders.matchQuery("title", "Interstellar"));
    	    firstSearchRequest.source(searchSourceBuilder);
    	    request.add(firstSearchRequest);                          
    	    SearchRequest secondSearchRequest = new SearchRequest();  
    	    searchSourceBuilder = new SearchSourceBuilder();
    	    searchSourceBuilder.query(QueryBuilders.matchQuery("title", "Dead Presidents"));
    	    secondSearchRequest.source(searchSourceBuilder);
    	    request.add(secondSearchRequest);
    	    return client.msearch(request, RequestOptions.DEFAULT);
    	    
        } catch (final Exception e) 
    	{
            throw new RuntimeException(e);
        }
    }
    


}
