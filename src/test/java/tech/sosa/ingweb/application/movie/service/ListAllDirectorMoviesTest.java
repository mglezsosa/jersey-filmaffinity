package tech.sosa.ingweb.application.movie.service;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

import tech.sosa.ingweb.domain.director.Director;
import tech.sosa.ingweb.domain.director.DirectorId;
import tech.sosa.ingweb.domain.director.DirectorRepository;
import tech.sosa.ingweb.domain.movie.Movie;
import tech.sosa.ingweb.domain.movie.MovieRepository;
import tech.sosa.ingweb.infrastructure.persistence.director.DirectorRepositoryStub;
import tech.sosa.ingweb.infrastructure.persistence.movie.MovieRepositoryStub;

public class ListAllDirectorMoviesTest {

	@Test
	public void shouldAllMoviesOfADirectorBeListed() {
		
		MovieRepository movieRepository = MovieRepositoryStub.withDummyData();
		DirectorRepository directorRepository = DirectorRepositoryStub.withDummyData();
		
		Director aDirector = directorRepository.ofId(new DirectorId(1L));
		Collection<Movie> expectedMovies = movieRepository.all();
		
		expectedMovies.stream().map(m -> {
			m.assignDirector(aDirector);
			return m;
		}).forEach(movieRepository::update);
		
		ListDirectorMoviesRequest request = new ListDirectorMoviesRequest(1L);
		Collection<Movie> actualMovies = new ListDirectorMovies(movieRepository, directorRepository).execute(request);
		
		assertEquals(expectedMovies, actualMovies);
	}

}
