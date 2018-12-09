package tech.sosa.ingweb.domain.movie;

public class Movie {
	
	protected MovieId id;
	protected MovieTitle title;
	protected Genre genre;
	protected Year year;
	
	public Movie(MovieId id, MovieTitle title, Genre genre, Year year) {
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
	
	public Year year() {
		return new Year(year.value());
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", genre=" + genre + ", year=" + year + "]";
	}
	
}
