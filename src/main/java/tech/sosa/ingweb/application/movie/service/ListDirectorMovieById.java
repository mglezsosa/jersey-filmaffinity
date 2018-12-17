package tech.sosa.ingweb.application.movie.service;

import tech.sosa.ingweb.domain.director.Director;
import tech.sosa.ingweb.domain.director.DirectorDoesNotExistException;
import tech.sosa.ingweb.domain.director.DirectorId;
import tech.sosa.ingweb.domain.director.DirectorRepository;
import tech.sosa.ingweb.domain.movie.Movie;
import tech.sosa.ingweb.domain.movie.MovieDoesNotExistException;
import tech.sosa.ingweb.domain.movie.MovieId;
import tech.sosa.ingweb.domain.movie.MovieRepository;

public class ListDirectorMovieById {

	private MovieRepository movieRepository;
	private DirectorRepository directorRepository;
	
	public ListDirectorMovieById(MovieRepository movieRepository, DirectorRepository directorRepository) {
		this.movieRepository = movieRepository;
		this.directorRepository = directorRepository;
	}

	public Movie execute(ListDirectorMovieByIdRequest request) {
		Director requestedDirector = directorRepository.ofId(new DirectorId(request.directorId));
		if (requestedDirector == null) {
			throw new DirectorDoesNotExistException();
		}
		
		return findMovie(request, requestedDirector);
	}

	private Movie findMovie(ListDirectorMovieByIdRequest request, Director requestedDirector) {
		Movie movie = movieRepository.ofDirector(requestedDirector).stream()
		.filter(m -> m.id().equals(new MovieId(request.movieId)))
		.findFirst()
		
		.orElseThrow(MovieDoesNotExistException::new);
		
		return movie;
	}
	
	
}
