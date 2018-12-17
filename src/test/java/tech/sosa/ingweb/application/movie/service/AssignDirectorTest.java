package tech.sosa.ingweb.application.movie.service;

import static org.junit.Assert.*;

import org.junit.Test;

import tech.sosa.ingweb.application.actor.service.AssignDirector;
import tech.sosa.ingweb.application.actor.service.AssignDirectorRequest;
import tech.sosa.ingweb.domain.director.Director;
import tech.sosa.ingweb.domain.director.DirectorId;
import tech.sosa.ingweb.domain.director.DirectorRepository;
import tech.sosa.ingweb.domain.movie.Movie;
import tech.sosa.ingweb.domain.movie.MovieId;
import tech.sosa.ingweb.domain.movie.MovieRepository;
import tech.sosa.ingweb.infrastructure.persistence.director.DirectorRepositoryStub;
import tech.sosa.ingweb.infrastructure.persistence.movie.MovieRepositoryStub;

public class AssignDirectorTest {

	@Test
	public void shouldAnExistingDirectorBeAssignedToAnExistingMovie() {
		
		MovieRepository movieRepository = MovieRepositoryStub.withDummyData();
		DirectorRepository directorRepository = DirectorRepositoryStub.withDummyData();
		
		AssignDirectorRequest request = new AssignDirectorRequest(1L, 1L);
		
		new AssignDirector(movieRepository, directorRepository).execute(request);
		
		Director expectedDirector = directorRepository.ofId(new DirectorId(1L));
		Movie updatedMovie = movieRepository.ofId(new MovieId(1L));
		
		assertEquals(expectedDirector.id(), updatedMovie.getDirector().id());
	}

}
