package tech.sosa.ingweb.application.movie.service;

import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SearchMoviesRequest {

	@QueryParam("title_portion")
	public String partialTitle;
	
	@QueryParam("genre")
	public String genre;
	
	@QueryParam("year")
	public Integer year;
	
	public SearchMoviesRequest(String partialTitle, String genre, Integer year) {
		this.partialTitle = partialTitle;
		this.genre = genre;
		this.year = year;
	}

	public SearchMoviesRequest() {
	}

	public String getPartialTitle() {
		return partialTitle;
	}

	public void setPartialTitle(String partialTitle) {
		this.partialTitle = partialTitle;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public boolean isEmpty() {
		if (
			(partialTitle == null || partialTitle == "") &&
			(genre == null || genre == "") &&
			year == null
		) {
			return true;
		}
		return false;
	}
	
}
