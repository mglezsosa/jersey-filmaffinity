package tech.sosa.ingweb.application.movie.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import tech.sosa.ingweb.domain.movie.Genre;
import tech.sosa.ingweb.domain.movie.Movie;
import tech.sosa.ingweb.domain.movie.MovieId;
import tech.sosa.ingweb.domain.movie.MovieRepository;
import tech.sosa.ingweb.domain.movie.MovieTitle;
import tech.sosa.ingweb.domain.movie.Year;
import tech.sosa.ingweb.infrastructure.persistence.movie.MovieRepositoryStub;

public class SearchMoviesTest {
	
	@Test
	public void shouldAllMoviesBeListedIfRequestIsEmpty() {
		
		Movie searchableMovie1 = new Movie(
				new MovieId(10L),
				new MovieTitle("Lord of the rings: The Two Towers"),
				new Genre("Fantasy"),
				new Year(2002)
			);
		
		Movie searchableMovie2 = new Movie(
				new MovieId(14L),
				new MovieTitle("The Lord of the Rings: The Fellowship of the Ring"),
				new Genre("Fantasy"),
				new Year(2001)
			);
		
		Movie searchableMovie3 = new Movie(
				new MovieId(15L),
				new MovieTitle("The Lord of the Rings: The Return of the King"),
				new Genre("Fantasy"),
				new Year(2003)
			);
		
		MovieRepository repository = MovieRepositoryStub.with(
				searchableMovie1, 
				searchableMovie2, 
				searchableMovie3);
		
		SearchMoviesRequest request = new SearchMoviesRequest();
		
		Collection<Movie> allMovies = new SearchMovies(repository).execute(request);
		
		
		assertEquals(Arrays.asList(searchableMovie1, 
				searchableMovie2, 
				searchableMovie3), allMovies);
	}

	@Test
	public void shouldAnExistingMovieBeFoundByFullTitle() {
		
		Movie searchableMovie = new Movie(
				new MovieId(10L),
				new MovieTitle("Lord of the rings: The Two Towers"),
				new Genre("Fantasy"),
				new Year(2002)
			);
		
		MovieRepository repository = MovieRepositoryStub.withDummyAndSearchable(searchableMovie);
		
		Collection<Movie> expectedMovies = Arrays.asList(searchableMovie);
		
		SearchMoviesRequest request = new SearchMoviesRequest(
				"Lord of the rings: The Two Towers",
				null,
				null
			);
		
		SearchMovies searchMovies = new SearchMovies(repository);
		Collection<Movie> actualMovies = searchMovies.execute(request);
		
		assertEquals(expectedMovies, actualMovies);
	}
	
	@Test
	public void shouldAnExistingMovieBeFoundByPartialTitle() {
		
		Movie searchableMovie = new Movie(
				new MovieId(10L),
				new MovieTitle("Lord of the rings: The Two Towers"),
				new Genre("Fantasy"),
				new Year(2002)
			);
		
		MovieRepository repository = MovieRepositoryStub.withDummyAndSearchable(searchableMovie);
		Collection<Movie> expectedMovies = new ArrayList<>();
		expectedMovies.add(searchableMovie);
		
		SearchMoviesRequest request = new SearchMoviesRequest(
				"lord",
				null,
				null
			);
		
		SearchMovies searchMovies = new SearchMovies(repository);
		Collection<Movie> actualMovies = searchMovies.execute(request);
		
		assertEquals(expectedMovies, actualMovies);
	}
	
	@Test
	public void shouldAnExistingMovieBeFoundByGenre() {
		
		Movie searchableMovie = new Movie(
				new MovieId(11L),
				new MovieTitle("Overlord"),
				new Genre("Action"),
				new Year(2018)
			);
		
		MovieRepository repository = MovieRepositoryStub.withDummyAndSearchable(searchableMovie);
		Collection<Movie> expectedMovies = Arrays.asList(searchableMovie);
		
		SearchMoviesRequest request = new SearchMoviesRequest(
				null,
				"Action",
				null
			);
		
		SearchMovies searchMovies = new SearchMovies(repository);
		Collection<Movie> actualMovies = searchMovies.execute(request);
		
		assertEquals(expectedMovies, actualMovies);
	}
	
	@Test
	public void shouldAnExistingMovieBeFoundByYear() {
		
		Movie searchableMovie = new Movie(
				new MovieId(12L),
				new MovieTitle("Lord of the flies"),
				new Genre("Drama"),
				new Year(1963)
			);
		
		MovieRepository repository = MovieRepositoryStub.withDummyAndSearchable(searchableMovie);
		Collection<Movie> expectedMovies = new ArrayList<>();
		expectedMovies.add(searchableMovie);
		
		SearchMoviesRequest request = new SearchMoviesRequest(
				null,
				null,
				1963
			);
		
		SearchMovies searchMovies = new SearchMovies(repository);
		Collection<Movie> actualMovies = searchMovies.execute(request);
		
		assertEquals(expectedMovies, actualMovies);
	}
	
	@Test
	public void shouldSeveralExistingMoviesBeFoundByPartialTitleAndGenre() {
		
		Movie searchableMovie1 = new Movie(
				new MovieId(12L),
				new MovieTitle("Lord of the flies"),
				new Genre("Drama"),
				new Year(1963)
			);
		Movie searchableMovie2 = new Movie(
				new MovieId(11L),
				new MovieTitle("Overlord"),
				new Genre("Action"),
				new Year(2018)
			);
		
		Movie searchableMovie3 = new Movie(
				new MovieId(10L),
				new MovieTitle("Lord of the rings: The Two Towers"),
				new Genre("Fantasy"),
				new Year(2002)
			);
		
		Movie searchableMovie4 = new Movie(
				new MovieId(14L),
				new MovieTitle("The Lord of the Rings: The Fellowship of the Ring"),
				new Genre("Fantasy"),
				new Year(2001)
			);
		
		Movie searchableMovie5 = new Movie(
				new MovieId(15L),
				new MovieTitle("The Lord of the Rings: The Return of the King"),
				new Genre("Fantasy"),
				new Year(2003)
			);
		
		MovieRepository repository = MovieRepositoryStub.withDummyAndSearchable(
				searchableMovie1,
				searchableMovie2,
				searchableMovie3,
				searchableMovie4,
				searchableMovie5
				);
		
		Collection<Movie> expectedMovies = new ArrayList<>();
		expectedMovies.add(searchableMovie3);
		expectedMovies.add(searchableMovie4);
		expectedMovies.add(searchableMovie5);
		
		SearchMoviesRequest request = new SearchMoviesRequest(
				"lord",
				"Fantasy",
				null
			);
		
		SearchMovies searchMovies = new SearchMovies(repository);
		Collection<Movie> actualMovies = searchMovies.execute(request);
		
		assertEquals(expectedMovies, actualMovies);
	}
	
	@Test
	public void shouldSeveralExistingMoviesBetweenOtherSimilarOnesBeFoundByTerms() {
		
		Movie searchableMovie1 = new Movie(
				new MovieId(12L),
				new MovieTitle("Lord of the flies"),
				new Genre("Drama"),
				new Year(1963)
			);
		Movie searchableMovie2 = new Movie(
				new MovieId(11L),
				new MovieTitle("Overlord"),
				new Genre("Action"),
				new Year(2018)
			);
		
		Movie searchableMovie3 = new Movie(
				new MovieId(20L),
				new MovieTitle("A weird german action movie with lord in its title"),
				new Genre("Action"),
				new Year(2018)
			);
		
		Movie searchableMovie4 = new Movie(
				new MovieId(21L),
				new MovieTitle("An action movie without the partial title you're searching for"),
				new Genre("Action"),
				new Year(2018)
			);
		
		Movie searchableMovie5 = new Movie(
				new MovieId(14L),
				new MovieTitle("Yeah, this action movie has lord in its title, but it's not of 2018"),
				new Genre("Action"),
				new Year(2001)
			);
		
		MovieRepository repository = MovieRepositoryStub.withDummyAndSearchable(
				searchableMovie1,
				searchableMovie2,
				searchableMovie3,
				searchableMovie4,
				searchableMovie5
				);
		
		Collection<Movie> expectedMovies = new ArrayList<>();
		expectedMovies.add(searchableMovie2);
		expectedMovies.add(searchableMovie3);
		
		SearchMoviesRequest request = new SearchMoviesRequest(
				"lord",
				"Action",
				2018
			);
		
		SearchMovies searchMovies = new SearchMovies(repository);
		Collection<Movie> actualMovies = searchMovies.execute(request);
		
		assertTrue(CollectionUtils.isEqualCollection(expectedMovies, actualMovies));
	}

}
