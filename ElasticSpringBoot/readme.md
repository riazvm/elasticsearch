Download 
https://grouplens.org/datasets/movielens/ml-latest-small.zip

http://files.grouplens.org/datasets/movielens/ml-latest-small.zip

Extract the files

Create an index called movies with the following mapping for year

Use postman method: PUT 
URI: IP:9200/movies  
	{ 
	   "mappings": {
		 "properties" : {
				 "year" :{“type": "date"}
				 }
			 }
	 }


Insert a document into the index movies



