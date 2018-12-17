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

public class UnassignActorTest {

	@Test
	public void shouldAnExistingDirectorBeAssignedToAnExistingMovie() {
		
		ActorRepository actorRepository = ActorRepositoryStub.withDummyData();
		Actor anActor = actorRepository.ofId(new ActorId(1L));
		MovieRepository movieRepository = MovieRepositoryStub.withDummyData();
		Movie aMovie = movieRepository.ofId(new MovieId(1L));
		aMovie.assignActor(anActor);
		
		UnassignActorRequest request = new UnassignActorRequest(1L, 1L);
		
		new UnassignActor(movieRepository, actorRepository).execute(request);
	
		Movie updatedMovie = movieRepository.ofId(new MovieId(1L));
		
		assertTrue(updatedMovie.getActors().isEmpty());
	}

}
