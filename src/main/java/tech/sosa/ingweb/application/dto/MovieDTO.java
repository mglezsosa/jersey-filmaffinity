package tech.sosa.ingweb.application.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MovieDTO {

	public long id;
	public String title;
	public String genre;
	public Date year;
	
	public MovieDTO(long id, String title, String genre, Date year) {
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.year = year;
	}
	
}
