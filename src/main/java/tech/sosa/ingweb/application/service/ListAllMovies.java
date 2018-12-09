package tech.sosa.ingweb.application.service;

import java.util.Collection;

import tech.sosa.ingweb.domain.movie.Movie;
import tech.sosa.ingweb.domain.movie.MovieRepository;

public class ListAllMovies {
	
	protected MovieRepository movieRepository;

	public ListAllMovies(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	public Collection<Movie> execute() {
		return movieRepository.all();
	}

}
