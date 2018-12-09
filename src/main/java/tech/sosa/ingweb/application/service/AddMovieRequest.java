package tech.sosa.ingweb.application.service;

public class AddMovieRequest {

	public String title;
	public String genre;
	public int year;
	
	public AddMovieRequest(String title, String genre, int year) {
		this.title = title;
		this.genre = genre;
		this.year = year;
	}
	
}
