package tech.sosa.ingweb.application.service;

import tech.sosa.ingweb.domain.movie.Genre;
import tech.sosa.ingweb.domain.movie.Movie;
import tech.sosa.ingweb.domain.movie.MovieRepository;
import tech.sosa.ingweb.domain.movie.MovieTitle;

public class AddMovie {
	
	private MovieRepository movieRepository;
	
	public AddMovie(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	public void execute(AddMovieRequest request) {
		movieRepository.add(new Movie(
					movieRepository.nextIdentity(),
					new MovieTitle(request.title),
					new Genre(request.genre),
					request.year
				));
	}
}
