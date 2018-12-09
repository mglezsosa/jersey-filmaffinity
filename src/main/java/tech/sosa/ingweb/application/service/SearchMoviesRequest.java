package tech.sosa.ingweb.application.service;

public class SearchMoviesRequest {

	public String partialTitle;
	public String genre;
	public int year;
	
	public SearchMoviesRequest(String partialTitle, String genre, int year) {
		this.partialTitle = partialTitle;
		this.genre = genre;
		this.year = year;
	}
	
}
