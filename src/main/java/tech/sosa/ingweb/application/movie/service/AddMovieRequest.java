package tech.sosa.ingweb.application.movie.service;

public class AddMovieRequest {

	public String title;
	public String genre;
	public int year;
	
	public AddMovieRequest(String title, String genre, int year) {
		this.title = title;
		this.genre = genre;
		this.year = year;
	}

	public AddMovieRequest() {
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
		return "AddMovieRequest [title=" + title + ", genre=" + genre + ", year=" + year + "]";
	}
	
}
