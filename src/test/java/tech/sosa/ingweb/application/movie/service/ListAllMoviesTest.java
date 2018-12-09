package tech.sosa.ingweb.application.movie.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import tech.sosa.ingweb.application.movie.service.ListAllMovies;
import tech.sosa.ingweb.domain.movie.Genre;
import tech.sosa.ingweb.domain.movie.Movie;
import tech.sosa.ingweb.domain.movie.MovieId;
import tech.sosa.ingweb.domain.movie.MovieRepository;
import tech.sosa.ingweb.domain.movie.MovieTitle;
import tech.sosa.ingweb.domain.movie.Year;
import tech.sosa.ingweb.infrastructure.persistence.movie.InMemoryMovieRepository;

public class ListAllMoviesTest {

	private MovieRepository movieRepository;
	
	@Before
	public void setUp() {
		movieRepository = new InMemoryMovieRepository();
	}

	@Test
	public void shouldListAllZeroMovies() {
		ListAllMovies listMoviesUseCase = new ListAllMovies(movieRepository);
		Collection<Movie> allMovies = listMoviesUseCase.execute();
		assertEquals(new ArrayList<Movie>(), allMovies);
	}
	
	@Test
	public void shouldListAllSeveralMovies() {
		
		Movie aMovie = new Movie(
				new MovieId(1L),
				new MovieTitle("Movie1"),
				new Genre("Drama"),
				new Year(2018)
			);
		
		Movie anotherMovie = new Movie(
				new MovieId(2L),
				new MovieTitle("Movie2"),
				new Genre("Thriller"),
				new Year(2018)
			);
		
		movieRepository.add(aMovie);
		movieRepository.add(anotherMovie);
		
		ListAllMovies listMoviesUseCase = new ListAllMovies(movieRepository);
		Collection<Movie> allMovies = listMoviesUseCase.execute();
		
		Collection<Movie> listOfMovies = new ArrayList<Movie>();
		listOfMovies.add(aMovie);
		listOfMovies.add(anotherMovie);
		
		assertEquals((Collection<Movie>) listOfMovies,(Collection<Movie>) allMovies);
	}

}
