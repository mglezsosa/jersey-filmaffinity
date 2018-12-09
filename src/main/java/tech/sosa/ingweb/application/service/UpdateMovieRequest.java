package tech.sosa.ingweb.application.service;

public class UpdateMovieRequest {
	
	public long id;
	public String title;
	public String genre;
	public int year;
	
	public UpdateMovieRequest(long id, String title, String genre, int year) {
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.year = year;
	}
	
}
