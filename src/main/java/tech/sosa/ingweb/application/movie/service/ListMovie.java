package tech.sosa.ingweb.application.movie.service;

import tech.sosa.ingweb.domain.movie.Movie;
import tech.sosa.ingweb.domain.movie.MovieDoesNotExistException;
import tech.sosa.ingweb.domain.movie.MovieId;
import tech.sosa.ingweb.domain.movie.MovieRepository;

public class ListMovie {

	private MovieRepository movieRepository;

	public ListMovie(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}
	
	public Movie execute(ListMovieRequest request) {
		
		Movie requestedMovie = movieRepository.ofId(new MovieId(request.id));
		if (requestedMovie == null) {
			throw new MovieDoesNotExistException();
		}
		
		return movieRepository.ofId(requestedMovie.id());
	}
	
}
