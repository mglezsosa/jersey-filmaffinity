package tech.sosa.ingweb.application.movie.service;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

import tech.sosa.ingweb.domain.director.Director;
import tech.sosa.ingweb.domain.director.DirectorId;
import tech.sosa.ingweb.domain.director.DirectorRepository;
import tech.sosa.ingweb.domain.movie.Genre;
import tech.sosa.ingweb.domain.movie.Movie;
import tech.sosa.ingweb.domain.movie.MovieId;
import tech.sosa.ingweb.domain.movie.MovieRepository;
import tech.sosa.ingweb.domain.movie.MovieTitle;
import tech.sosa.ingweb.domain.movie.Year;
import tech.sosa.ingweb.infrastructure.persistence.director.DirectorRepositoryStub;
import tech.sosa.ingweb.infrastructure.persistence.movie.MovieRepositoryStub;

public class ListDirectorMovieByIdTest {

	@Test
	public void shouldAMovieThatBelongsToADirectorBeListed() {
		
		Movie expectedMovie = new Movie(new MovieId(100L), new MovieTitle("M100"), new Genre("G1"), new Year(2010));
		
		MovieRepository movieRepository = MovieRepositoryStub.withDummyAndSearchable(expectedMovie);
		DirectorRepository directorRepository = DirectorRepositoryStub.withDummyData();
		
		Director aDirector = directorRepository.ofId(new DirectorId(1L));
		Collection<Movie> expectedMovies = movieRepository.all();
		
		expectedMovies.stream().map(m -> {
			m.assignDirector(aDirector);
			return m;
		}).forEach(movieRepository::update);
		
		ListDirectorMovieByIdRequest request = new ListDirectorMovieByIdRequest(1L, 100L);
		Movie actualMovie = new ListDirectorMovieById(movieRepository, directorRepository).execute(request);
		
		assertEquals(expectedMovie, actualMovie);
	}

}
