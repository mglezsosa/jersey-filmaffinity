package tech.sosa.ingweb.application.movie.service;

public class SearchMoviesRequest {

	public String partialTitle;
	public String genre;
	public Integer year;
	
	public SearchMoviesRequest(String partialTitle, String genre, Integer year) {
		this.partialTitle = partialTitle;
		this.genre = genre;
		this.year = year;
	}
	
}
