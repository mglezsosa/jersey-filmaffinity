package tech.sosa.ingweb.application.service;

import java.util.Date;

public class AddMovieRequest {

	public String title;
	public String genre;
	public Date year;
	
	public AddMovieRequest(String title, String genre, Date year) {
		this.title = title;
		this.genre = genre;
		this.year = year;
	}
	
}
