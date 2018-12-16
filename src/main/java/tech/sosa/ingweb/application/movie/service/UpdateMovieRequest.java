package tech.sosa.ingweb.application.movie.service;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UpdateMovieRequest {
	
	public long id;
	
	public String title;
	
	public String genre;
	
	public int year;
	
	public UpdateMovieRequest(Long id, String title, String genre, int year) {
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.year = year;
	}

	public UpdateMovieRequest() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "UpdateMovieRequest [id=" + id + ", title=" + title + ", genre=" + genre + ", year=" + year + "]";
	}
	
}
