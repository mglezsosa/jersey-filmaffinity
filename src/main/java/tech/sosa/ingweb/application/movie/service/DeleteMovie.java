package tech.sosa.ingweb.application.movie.service;

import tech.sosa.ingweb.domain.movie.Movie;
import tech.sosa.ingweb.domain.movie.MovieDoesNotExistException;
import tech.sosa.ingweb.domain.movie.MovieId;
import tech.sosa.ingweb.domain.movie.MovieRepository;

public class DeleteMovie {

	private MovieRepository movieRepository;

	public DeleteMovie(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}
	
	public void execute(DeleteMovieRequest request) {
		
		Movie requestedMovie = movieRepository.ofId(new MovieId(request.id));
		if (requestedMovie == null) {
			throw new MovieDoesNotExistException();
		}
		
		movieRepository.delete(requestedMovie);
	}
	
}
