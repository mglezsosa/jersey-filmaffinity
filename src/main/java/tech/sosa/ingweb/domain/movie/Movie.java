package tech.sosa.ingweb.domain.movie;

import java.util.Date;

public class Movie {
	
	protected MovieId id;
	protected MovieTitle title;
	protected Genre genre;
	protected Date year;
	
	public Movie(MovieId id, MovieTitle title, Genre genre, Date year) {
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.year = year;
	}
	
	public MovieId id() {
		return new MovieId(id.value());
	}
	
	public MovieTitle title() {
		return new MovieTitle(title.toString());
	}
	
	public Genre genre() {
		return new Genre(genre.toString());
	}
	
	public Date year() {
		return new Date(year.getTime());
	}
	
}
