package tech.sosa.ingweb.application.service;

import tech.sosa.ingweb.domain.movie.Genre;
import tech.sosa.ingweb.domain.movie.Movie;
import tech.sosa.ingweb.domain.movie.MovieDoesNotExistException;
import tech.sosa.ingweb.domain.movie.MovieId;
import tech.sosa.ingweb.domain.movie.MovieRepository;
import tech.sosa.ingweb.domain.movie.MovieTitle;
import tech.sosa.ingweb.domain.movie.Year;

public class UpdateMovie {
	
	private MovieRepository movieRepository;

	public UpdateMovie(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	public void execute(UpdateMovieRequest request) {
		
		Movie requestedMovie = movieRepository.ofId(new MovieId(request.id));
		if (requestedMovie == null) {
			throw new MovieDoesNotExistException();
		}
		
		movieRepository.update(new Movie(
					new MovieId(request.id),
					new MovieTitle(request.title),
					new Genre(request.genre),
					new Year(request.year)
				));
	}
	
}
