package tech.sosa.ingweb.application.service;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import tech.sosa.ingweb.domain.movie.Genre;
import tech.sosa.ingweb.domain.movie.Movie;
import tech.sosa.ingweb.domain.movie.MovieDoesNotExistException;
import tech.sosa.ingweb.domain.movie.MovieId;
import tech.sosa.ingweb.domain.movie.MovieRepository;
import tech.sosa.ingweb.domain.movie.MovieTitle;
import tech.sosa.ingweb.infrastructure.persistence.MovieRepositoryStub;

public class ListMovieByIdTest {

	private MovieRepository movieRepository;
	
	@Before
	public void setUp() {
		movieRepository = MovieRepositoryStub.with(new Movie(
					new MovieId(1L),
					new MovieTitle("M1"),
					new Genre("Drama"),
					new Date(2016)
				));
	}
	
	@Test
	public void shouldAnExistingMovieBeFoundById() {
		
		ListMovieRequest request = new ListMovieRequest(1L);
		
		ListMovie listMovie = new ListMovie(movieRepository);
		Movie result = listMovie.execute(request);
		
		assertEquals(result.id(), new MovieId(request.id));
	}
	
	@Test(expected = MovieDoesNotExistException.class)
	public void shouldANonExistingMovieNotBeFoundAndAnExceptionBeThrown() {
		
		ListMovieRequest request = new ListMovieRequest(2L);
		
		ListMovie listMovie = new ListMovie(movieRepository);
		listMovie.execute(request);
	}
	
}
