package com.elastic.sample.spring.document;


import java.util.Date;
import java.util.List;


public class MoviesDocument {

    private String movieId;
    private String title;
    private List<String> genres;
    private String year;
    
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getGenres() {
		return genres;
	}
	public void setGenres(List<String> genres) {
		this.genres = genres;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
   
    

}