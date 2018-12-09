package tech.sosa.ingweb.application.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tech.sosa.ingweb.domain.movie.Genre;
import tech.sosa.ingweb.domain.movie.Movie;
import tech.sosa.ingweb.domain.movie.MovieDoesNotExistException;
import tech.sosa.ingweb.domain.movie.MovieId;
import tech.sosa.ingweb.domain.movie.MovieRepository;
import tech.sosa.ingweb.domain.movie.MovieTitle;
import tech.sosa.ingweb.domain.movie.Year;
import tech.sosa.ingweb.infrastructure.persistence.MovieRepositoryStub;

public class UpdateMovieTest {

	private MovieRepository movieRepository;
	
	@Before
	public void setUp() {
		movieRepository = MovieRepositoryStub.with(new Movie(
					new MovieId(1L),
					new MovieTitle("M1"),
					new Genre("Drama"),
					new Year(2016)
				));
	}
	
	@Test
	public void shouldAnExistingMovieBeUpdated() {
		
		UpdateMovieRequest request = new UpdateMovieRequest(
					1L,
					"Movie1",
					"Thriller",
					2015
				);
		
		UpdateMovie updateMovie = new UpdateMovie(movieRepository);
		updateMovie.execute(request);
		
		Movie updatedMovie = movieRepository.ofId(new MovieId(request.id));
		
		assertEquals(new Genre("Thriller"), updatedMovie.genre());
		assertEquals(new MovieTitle("Movie1"), updatedMovie.title());
		assertEquals(new Year(2015), updatedMovie.year());
	}
	
	@Test(expected = MovieDoesNotExistException.class)
	public void shouldANonExistingMovieNotBeUpdatedAndAnExceptionBeThrown() {
		
		UpdateMovieRequest request = new UpdateMovieRequest(
				2L,
				"Movie1",
				"Thriller",
				2015
			);
		
		UpdateMovie updateMovie = new UpdateMovie(movieRepository);
		updateMovie.execute(request);
	}

}
