package tech.sosa.ingweb.application.movie.service;

import java.util.Collection;

import tech.sosa.ingweb.domain.director.Director;
import tech.sosa.ingweb.domain.director.DirectorDoesNotExistException;
import tech.sosa.ingweb.domain.director.DirectorId;
import tech.sosa.ingweb.domain.director.DirectorRepository;
import tech.sosa.ingweb.domain.movie.Movie;
import tech.sosa.ingweb.domain.movie.MovieRepository;

public class ListDirectorMovies {

	private MovieRepository movieRepository;
	private DirectorRepository directorRepository;
	
	public ListDirectorMovies(MovieRepository movieRepository, DirectorRepository directorRepository) {
		super();
		this.movieRepository = movieRepository;
		this.directorRepository = directorRepository;
	}

	public Collection<Movie> execute(ListDirectorMoviesRequest request) {
		Director requestedDirector = directorRepository.ofId(new DirectorId(request.directorId));
		if (requestedDirector == null) {
			throw new DirectorDoesNotExistException();
		}
		
		return movieRepository.ofDirector(requestedDirector);
	}
	
	

}
