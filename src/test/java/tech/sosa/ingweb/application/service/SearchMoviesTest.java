package tech.sosa.ingweb.application.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.junit.Test;

import tech.sosa.ingweb.domain.movie.Genre;
import tech.sosa.ingweb.domain.movie.Movie;
import tech.sosa.ingweb.domain.movie.MovieId;
import tech.sosa.ingweb.domain.movie.MovieRepository;
import tech.sosa.ingweb.domain.movie.MovieTitle;
import tech.sosa.ingweb.infrastructure.persistence.MovieRepositoryStub;

public class SearchMoviesTest {

	@Test
	public void shouldAnExistingMovieBeFoundByPartialTitle() {
		
		Movie searchableMovie = new Movie(
				new MovieId(10L),
				new MovieTitle("Lord of the rings: The Two Towers"),
				new Genre("Fantasy"),
				new Date(2002)
			);
		
		MovieRepository movieRepository = MovieRepositoryStub.withDummyAndSearchable(searchableMovie);
		
		SearchMoviesRequest request = new SearchMoviesRequest(
					"lord",
					null,
					null
				);
		
		SearchMovies searchMovies = new SearchMovies(movieRepository);
		Collection<Movie> actualMovies = searchMovies.execute(request);
		
		Collection<Movie> expectedMovies = new ArrayList<>();
		expectedMovies.add(searchableMovie);
		
		assertEquals(expectedMovies, actualMovies);
	}

}
