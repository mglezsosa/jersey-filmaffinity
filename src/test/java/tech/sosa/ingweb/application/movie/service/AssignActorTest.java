package tech.sosa.ingweb.application.movie.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tech.sosa.ingweb.domain.actor.Actor;
import tech.sosa.ingweb.domain.actor.ActorId;
import tech.sosa.ingweb.domain.actor.ActorRepository;
import tech.sosa.ingweb.domain.movie.Movie;
import tech.sosa.ingweb.domain.movie.MovieId;
import tech.sosa.ingweb.domain.movie.MovieRepository;
import tech.sosa.ingweb.infrastructure.persistence.actor.ActorRepositoryStub;
import tech.sosa.ingweb.infrastructure.persistence.movie.MovieRepositoryStub;

public class AssignActorTest {

	@Test
	public void shouldAnExistingDirectorBeAssignedToAnExistingMovie() {
		
		MovieRepository movieRepository = MovieRepositoryStub.withDummyData();
		ActorRepository actorRepository = ActorRepositoryStub.withDummyData();
		
		AssignActorRequest request = new AssignActorRequest(1L, 1L);
		
		new AssignActor(movieRepository, actorRepository).execute(request);
		
		Actor expectedActor = actorRepository.ofId(new ActorId(1L));
		Movie updatedMovie = movieRepository.ofId(new MovieId(1L));
		
		assertTrue(updatedMovie.getActors().contains(expectedActor));
	}

}
