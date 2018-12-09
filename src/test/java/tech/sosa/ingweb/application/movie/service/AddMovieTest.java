package tech.sosa.ingweb.application.movie.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tech.sosa.ingweb.application.movie.service.AddMovie;
import tech.sosa.ingweb.application.movie.service.AddMovieRequest;
import tech.sosa.ingweb.domain.movie.MovieId;
import tech.sosa.ingweb.domain.movie.MovieRepository;
import tech.sosa.ingweb.infrastructure.persistence.movie.InMemoryMovieRepository;

public class AddMovieTest {
	
	private MovieRepository movieRepository;
	
	@Before
	public void setUp() {
		movieRepository = new InMemoryMovieRepository();
	}

	@Test
	public void shouldAMovieBeAdded() {
		
		AddMovieRequest request = new AddMovieRequest(
					"Movie1",
					"Thriller",
					2016
				);
		
		MovieId expectedMovieId = movieRepository.nextIdentity();
		
		AddMovie addMovie = new AddMovie(movieRepository);
		addMovie.execute(request);
		
		assertEquals(movieRepository.ofId(expectedMovieId).id(), expectedMovieId);
	}

}
