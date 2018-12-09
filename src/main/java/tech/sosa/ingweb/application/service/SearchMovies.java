package tech.sosa.ingweb.application.service;

import java.util.Collection;
import java.util.stream.Collectors;

import tech.sosa.ingweb.domain.movie.Movie;
import tech.sosa.ingweb.domain.movie.MovieRepository;

public class SearchMovies {

	private MovieRepository movieRepository;

	public SearchMovies(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}
	
	public Collection<Movie> execute(SearchMoviesRequest request) {
		
		Collection<Movie> allMovies = movieRepository.all();
		
		return allMovies.stream()
				.filter(e -> e.title().toString().toLowerCase().contains(request.partialTitle))
				.collect(Collectors.toList());
	}
}
