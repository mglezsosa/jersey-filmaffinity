package tech.sosa.ingweb.application.actor.service;

import tech.sosa.ingweb.domain.director.Director;
import tech.sosa.ingweb.domain.director.DirectorDoesNotExistException;
import tech.sosa.ingweb.domain.director.DirectorId;
import tech.sosa.ingweb.domain.director.DirectorRepository;
import tech.sosa.ingweb.domain.movie.Movie;
import tech.sosa.ingweb.domain.movie.MovieDoesNotExistException;
import tech.sosa.ingweb.domain.movie.MovieId;
import tech.sosa.ingweb.domain.movie.MovieRepository;

public class AssignDirector {

	private MovieRepository movieRepository;
	private DirectorRepository directorRepository;
	
	public AssignDirector(MovieRepository movieRepository, DirectorRepository directorRepository) {
		this.movieRepository = movieRepository;
		this.directorRepository = directorRepository;
	}

	public void execute(AssignDirectorRequest request) {
		Movie movieToUpdate = movieRepository.ofId(new MovieId(request.movieId));
		if (movieToUpdate == null) {
			throw new MovieDoesNotExistException();
		}
		
		Director directorToAssign = directorRepository.ofId(new DirectorId(request.directorId));
		if (directorToAssign == null) {
			throw new DirectorDoesNotExistException();
		}
		
		movieToUpdate.assignDirector(directorToAssign);
		movieRepository.update(movieToUpdate);
	}
	
}
