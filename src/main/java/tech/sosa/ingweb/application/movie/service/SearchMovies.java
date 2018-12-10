package tech.sosa.ingweb.application.movie.service;

import java.security.InvalidParameterException;
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
		checkRequestIsNotEmpty(request);
		
		Collection<Movie> movies = movieRepository.all();
		
		movies = filterMovies(request, movies);
		
		return movies;
	}

	private Collection<Movie> filterMovies(SearchMoviesRequest request, Collection<Movie> movies) {
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
		if (request.year != null) {
			movies = movies.stream()
					.filter(e -> e.year().equals(new Year(request.year)))
					.collect(Collectors.toList());
		}
		return movies;
	}

	private void checkRequestIsNotEmpty(SearchMoviesRequest request) {
		if (request.partialTitle == null &&
			request.genre        == null &&
			request.year         == null) {
			throw new InvalidParameterException("At least one parameter must be setted.");
		}
	}
}
