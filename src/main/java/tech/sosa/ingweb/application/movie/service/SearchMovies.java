package tech.sosa.ingweb.application.movie.service;

import java.util.Collection;

import tech.sosa.ingweb.domain.movie.Genre;
import tech.sosa.ingweb.domain.movie.Movie;
import tech.sosa.ingweb.domain.movie.MovieRepository;
import tech.sosa.ingweb.domain.movie.Year;

public class SearchMovies {

	private MovieRepository movieRepository;

	public SearchMovies(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}
	
	public Collection<Movie> execute(SearchMoviesRequest request) {
		
		// TODO: Specification pattern for flexible queries. Maybe 'and' operator is enough.
		// By the time, this is the provisional naive approach.		
		if (request.isEmpty()) {
			return movieRepository.all();
		}

		Genre genre = null;
		Year year = null;
		
		if (request.genre != null) {
			genre = new Genre(request.genre);
		}
		if (request.year != null) {
			year = new Year(request.year);
		}
		
		return movieRepository.ofSpecs(request.partialTitle, genre, year);
	}
	
}
