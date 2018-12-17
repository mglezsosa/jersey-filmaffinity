package tech.sosa.ingweb.application.movie.service;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;

import tech.sosa.ingweb.domain.actor.Actor;
import tech.sosa.ingweb.domain.actor.ActorId;
import tech.sosa.ingweb.domain.actor.ActorRepository;
import tech.sosa.ingweb.domain.movie.Movie;
import tech.sosa.ingweb.domain.movie.MovieRepository;
import tech.sosa.ingweb.infrastructure.persistence.actor.ActorRepositoryStub;
import tech.sosa.ingweb.infrastructure.persistence.movie.MovieRepositoryStub;

public class ListAllActorMoviesTest {

	@Test
	public void shouldAllMoviesOfAnActorBeListed() {
		
		MovieRepository movieRepository = MovieRepositoryStub.withDummyData();
		ActorRepository actorRepository = ActorRepositoryStub.withDummyData();
		
		Actor anActor = actorRepository.ofId(new ActorId(1L));
		Collection<Movie> expectedMovies = movieRepository.all();
		
		expectedMovies.stream().map(m -> {
			m.assignActor(anActor);
			return m;
		}).forEach(movieRepository::update);;
		
		ListActorMoviesRequest request = new ListActorMoviesRequest(1L);
		Collection<Movie> actualMovies = new ListActorMovies(movieRepository, actorRepository).execute(request);
		
		assertEquals(expectedMovies, actualMovies);
	}

}
