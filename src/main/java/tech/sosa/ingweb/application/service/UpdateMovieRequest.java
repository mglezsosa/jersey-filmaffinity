package tech.sosa.ingweb.application.service;

import java.util.Date;

public class UpdateMovieRequest {
	
	public long id;
	public String title;
	public String genre;
	public Date year;
	
	public UpdateMovieRequest(long id, String title, String genre, Date year) {
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.year = year;
	}
	
}
