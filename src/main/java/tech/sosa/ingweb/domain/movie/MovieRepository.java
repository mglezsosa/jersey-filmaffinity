package tech.sosa.ingweb.domain.movie;

import java.util.Collection;

public interface MovieRepository {
	
	public MovieId nextIdentity();

	public void add(Movie aMovie);

	public void update(Movie aMovie);

	public void remove(Movie aMovie);

	public Collection<Movie> all();

	public Movie ofIdOrFail(MovieId expectedMovieId);
	
}
