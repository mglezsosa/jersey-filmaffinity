package tech.sosa.ingweb.application.movie.service;

import static org.junit.Assert.*;


import org.junit.Test;

import tech.sosa.ingweb.application.actor.service.UnassignDirector;
import tech.sosa.ingweb.application.actor.service.UnassignDirectorRequest;
import tech.sosa.ingweb.domain.director.Director;
import tech.sosa.ingweb.domain.director.DirectorId;
import tech.sosa.ingweb.domain.director.DirectorRepository;
import tech.sosa.ingweb.domain.movie.Movie;
import tech.sosa.ingweb.domain.movie.MovieId;
import tech.sosa.ingweb.domain.movie.MovieRepository;
import tech.sosa.ingweb.infrastructure.persistence.director.DirectorRepositoryStub;
import tech.sosa.ingweb.infrastructure.persistence.movie.MovieRepositoryStub;

public class UnassignDirectorTest {

	@Test
	public void shouldAnExistingDirectorBeAssignedToAnExistingMovie() {
		
		DirectorRepository directorRepository = DirectorRepositoryStub.withDummyData();
		Director aDirector = directorRepository.ofId(new DirectorId(1L));
		MovieRepository movieRepository = MovieRepositoryStub.withDummyData();
		Movie aMovie = movieRepository.ofId(new MovieId(1L));
		aMovie.assignDirector(aDirector);
		
		UnassignDirectorRequest request = new UnassignDirectorRequest(1L, 1L);
		
		new UnassignDirector(movieRepository, directorRepository).execute(request);
	
		Movie updatedMovie = movieRepository.ofId(new MovieId(1L));
		
		assertNull(updatedMovie.getDirector());
	}

}
