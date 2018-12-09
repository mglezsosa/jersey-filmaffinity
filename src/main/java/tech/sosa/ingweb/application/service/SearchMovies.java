package tech.sosa.ingweb.application.service;

import java.util.Collection;
import java.util.stream.Collectors;

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
		Collection<Movie> movies = movieRepository.all();
		
		if (request.partialTitle != null) {
			movies = movies.stream()
					.filter(e -> e.title().toString().toLowerCase().contains(request.partialTitle))
					.collect(Collectors.toList());
		}
		if (request.genre != null) {
			movies = movies.stream()
					.filter(e -> e.genre().equals(new Genre(request.genre)))
					.collect(Collectors.toList());
		}
		if (request.year != -1) {
			movies = movies.stream()
					.filter(e -> e.year().equals(new Year(request.year)))
					.collect(Collectors.toList());
		}
		
		return movies;
	}
}
