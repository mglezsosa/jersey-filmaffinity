package tech.sosa.ingweb.application.service;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import tech.sosa.ingweb.domain.movie.Genre;
import tech.sosa.ingweb.domain.movie.Movie;
import tech.sosa.ingweb.domain.movie.MovieId;
import tech.sosa.ingweb.domain.movie.MovieRepository;
import tech.sosa.ingweb.domain.movie.MovieTitle;
import tech.sosa.ingweb.domain.movie.Year;
import tech.sosa.ingweb.infrastructure.persistence.MovieRepositoryStub;

public class DeleteMovieTest {
	
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
	public void shouldAnExistentMovieBeDeleted() {
		
		DeleteMovieRequest request = new DeleteMovieRequest(
					1L
				);
		
		DeleteMovie deleteMovie = new DeleteMovie(movieRepository);
		deleteMovie.execute(request);
		
		Collection<Movie> remainingMovies = movieRepository.all();
		
		assertTrue(remainingMovies.isEmpty());
	}

}
