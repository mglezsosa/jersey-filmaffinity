package tech.sosa.ingweb.infrastructure.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import tech.sosa.ingweb.domain.movie.Movie;
import tech.sosa.ingweb.domain.movie.MovieDoesNotExistException;
import tech.sosa.ingweb.domain.movie.MovieId;
import tech.sosa.ingweb.domain.movie.MovieRepository;

public class InMemoryMovieRepository implements MovieRepository {

	public Map<MovieId, Movie> movies;
	
	public InMemoryMovieRepository() {
		movies = new HashMap<MovieId, Movie>();
	}
	
	@Override
	public MovieId nextIdentity() {
		Long newIdentityValue = Long.valueOf(movies.size());
		return new MovieId(newIdentityValue);
	}
	
	@Override
	public void add(Movie aMovie) {
		movies.put(aMovie.id(), aMovie);
	}

	@Override
	public void update(Movie item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Movie item) {
		// TODO Auto-generated method stub
	}

	@Override
	public Collection<Movie> all() {
		return new ArrayList<Movie>(movies.values());
	}

	@Override
	public Movie ofIdOrFail(MovieId movieId) {
		Movie result = movies.get(movieId);
		if (result == null) {
			throw new MovieDoesNotExistException();
		}
		return result;
	}
}
