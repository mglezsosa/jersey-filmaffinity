package tech.sosa.ingweb.application.actor.service;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

import tech.sosa.ingweb.domain.actor.Actor;
import tech.sosa.ingweb.domain.actor.ActorFullName;
import tech.sosa.ingweb.domain.actor.ActorId;
import tech.sosa.ingweb.domain.actor.ActorRepository;
import tech.sosa.ingweb.domain.movie.Movie;
import tech.sosa.ingweb.domain.movie.MovieId;
import tech.sosa.ingweb.domain.movie.MovieRepository;
import tech.sosa.ingweb.infrastructure.persistence.actor.ActorRepositoryStub;
import tech.sosa.ingweb.infrastructure.persistence.movie.MovieRepositoryStub;

public class ListMovieActorByIdTest {

	@Test
	public void shouldAnActorThatBelongsToAMovieBeListed() {
		
		Actor expectedActor = new Actor(new ActorId(10L), new ActorFullName("A10"));
		
		MovieRepository movieRepository = MovieRepositoryStub.withDummyData();
		ActorRepository actorRepository = ActorRepositoryStub.withDummyAndSearchable(expectedActor);
		
		Movie aMovie = movieRepository.ofId(new MovieId(1L));
		Collection<Actor> actors = actorRepository.all();
		
		actors.stream().forEach(a -> aMovie.assignActor(a));
		movieRepository.update(aMovie);
		
		ListMovieActorByIdRequest request = new ListMovieActorByIdRequest(1L, 10L);
		Actor actualActor = new ListMovieActorById(movieRepository).execute(request);

		assertEquals(expectedActor, actualActor);
	}

}
