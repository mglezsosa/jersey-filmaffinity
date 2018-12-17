package tech.sosa.ingweb.application.movie.service;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

import tech.sosa.ingweb.domain.actor.Actor;
import tech.sosa.ingweb.domain.actor.ActorId;
import tech.sosa.ingweb.domain.actor.ActorRepository;
import tech.sosa.ingweb.domain.movie.Genre;
import tech.sosa.ingweb.domain.movie.Movie;
import tech.sosa.ingweb.domain.movie.MovieId;
import tech.sosa.ingweb.domain.movie.MovieRepository;
import tech.sosa.ingweb.domain.movie.MovieTitle;
import tech.sosa.ingweb.domain.movie.Year;
import tech.sosa.ingweb.infrastructure.persistence.actor.ActorRepositoryStub;
import tech.sosa.ingweb.infrastructure.persistence.movie.MovieRepositoryStub;

public class ListActorMovieByIdTest {

	@Test
	public void shouldAMovieThatBelongsToADirectorBeListed() {
		
		Movie expectedMovie = new Movie(new MovieId(100L), new MovieTitle("M100"), new Genre("G1"), new Year(2010));
		
		MovieRepository movieRepository = MovieRepositoryStub.withDummyAndSearchable(expectedMovie);
		ActorRepository actorRepository = ActorRepositoryStub.withDummyData();
		
		Actor anActor = actorRepository.ofId(new ActorId(1L));
		Collection<Movie> expectedMovies = movieRepository.all();
		
		expectedMovies.stream().map(m -> {
			m.assignActor(anActor);
			return m;
		}).forEach(movieRepository::update);
		
		ListActorMovieByIdRequest request = new ListActorMovieByIdRequest(1L, 100L);
		Movie actualMovie = new ListActorMovieById(movieRepository, actorRepository).execute(request);
		
		assertEquals(expectedMovie, actualMovie);
	}

}
