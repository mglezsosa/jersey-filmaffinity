package tech.sosa.ingweb.application.movie.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MovieDTO {

	public long id;
	public String title;
	public String genre;
	public int year;
	
	public MovieDTO(long id, String title, String genre, int year) {
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.year = year;
	}
	
}
