package tech.sosa.ingweb.application.service;

import tech.sosa.ingweb.domain.movie.Movie;
import tech.sosa.ingweb.domain.movie.MovieId;
import tech.sosa.ingweb.domain.movie.MovieRepository;

public class ListMovie {

	private MovieRepository movieRepository;

	public ListMovie(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}
	
	public Movie execute(ListMovieRequest request) {
		return movieRepository.ofIdOrFail(new MovieId(request.id));
	}
	
}
