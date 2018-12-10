package tech.sosa.ingweb.application.movie.service;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

import tech.sosa.ingweb.domain.movie.Genre;
import tech.sosa.ingweb.domain.movie.Movie;
import tech.sosa.ingweb.domain.movie.MovieDoesNotExistException;
import tech.sosa.ingweb.domain.movie.MovieId;
import tech.sosa.ingweb.domain.movie.MovieRepository;
import tech.sosa.ingweb.domain.movie.MovieTitle;
import tech.sosa.ingweb.domain.movie.Year;
import tech.sosa.ingweb.infrastructure.persistence.movie.MovieRepositoryStub;

public class DeleteMovieTest {

	@Test
	public void shouldAnExistentMovieBeDeleted() {
		
		MovieRepository movieRepository = MovieRepositoryStub.with(new Movie(
				new MovieId(1L),
				new MovieTitle("M1"),
				new Genre("Drama"),
				new Year(2016)
			));
		
		DeleteMovieRequest request = new DeleteMovieRequest(
					1L
				);
		
		DeleteMovie deleteMovie = new DeleteMovie(movieRepository);
		deleteMovie.execute(request);
		
		Collection<Movie> remainingMovies = movieRepository.all();
		
		assertTrue(remainingMovies.isEmpty());
	}
	
	@Test(expected = MovieDoesNotExistException.class)
	public void shouldANonExistentMovieNotBeDeletedAndAnExceptionBeThrown() {
		
		MovieRepository repository = MovieRepositoryStub.empty();
		
		DeleteMovieRequest request = new DeleteMovieRequest(
				1L
			);
	
		DeleteMovie deleteMovie = new DeleteMovie(repository);
		deleteMovie.execute(request);
	}

}
