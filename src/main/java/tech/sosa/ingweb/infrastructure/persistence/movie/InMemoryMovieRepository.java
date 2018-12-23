package tech.sosa.ingweb.infrastructure.persistence.movie;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import tech.sosa.ingweb.domain.actor.Actor;
import tech.sosa.ingweb.domain.director.Director;
import tech.sosa.ingweb.domain.movie.Genre;
import tech.sosa.ingweb.domain.movie.Movie;
import tech.sosa.ingweb.domain.movie.MovieId;
import tech.sosa.ingweb.domain.movie.MovieRepository;
import tech.sosa.ingweb.domain.movie.Year;

public class InMemoryMovieRepository implements MovieRepository {

	public Map<MovieId, Movie> movies;
	
	public InMemoryMovieRepository() {
		movies = new HashMap<MovieId, Movie>();
	}

	@Override
	public MovieId nextIdentity() {
		Long newIdentityValue = Long.valueOf(movies.size() + 1);
		return new MovieId(newIdentityValue);
	}
	
	@Override
	public void add(Movie aMovie) {
		movies.put(aMovie.id(), aMovie);
	}

	@Override
	public void update(Movie aMovie) {
		movies.put(aMovie.id(), aMovie);
	}

	@Override
	public void delete(Movie aMovie) {
		movies.remove(aMovie.id());
	}

	@Override
	public Collection<Movie> all() {
		return new ArrayList<Movie>(movies.values());
	}

	@Override
	public Movie ofId(MovieId movieId) {
		return movies.get(movieId);
	}

	@Override
	public Collection<Movie> ofDirector(Director director) {
		return movies.values().stream().filter(m -> m.getDirector().equals(director)).collect(toList());
	}

	@Override
	public Collection<Movie> ofActor(Actor actor) {
		return movies.values().stream().filter(m -> m.getActors().contains(actor)).collect(toList());
	}

	@Override
	public Collection<Movie> ofSpecs(String partialTitle, Genre genre, Year year) {
		Collection<Movie> movies = all();
		
		return filterMovies(partialTitle, genre, year, movies);
	}
	
	private Collection<Movie> filterMovies(String partialTitle, Genre genre, Year year, Collection<Movie> movies) {
		if (partialTitle != null) {
			movies = movies.stream()
					.filter(e -> e.title().toString().toLowerCase().contains(partialTitle.toLowerCase()))
					.collect(Collectors.toList());
		}
		if (genre != null) {
			movies = movies.stream()
					.filter(e -> e.genre().equals(genre))
					.collect(Collectors.toList());
		}
		if (year != null) {
			movies = movies.stream()
					.filter(e -> e.year().equals(year))
					.collect(Collectors.toList());
		}
		return movies;
	}
	
}
