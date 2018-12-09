package tech.sosa.ingweb.application.service;

import java.util.Date;

public class SearchMoviesRequest {

	public String partialTitle;
	public String genre;
	public Date year;
	
	public SearchMoviesRequest(String partialTitle, String genre, Date year) {
		this.partialTitle = partialTitle;
		this.genre = genre;
		this.year = year;
	}
	
}
