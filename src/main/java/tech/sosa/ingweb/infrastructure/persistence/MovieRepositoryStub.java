package tech.sosa.ingweb.infrastructure.persistence;

import java.util.Date;
import java.util.stream.Stream;

import tech.sosa.ingweb.domain.movie.Genre;
import tech.sosa.ingweb.domain.movie.Movie;
import tech.sosa.ingweb.domain.movie.MovieId;
import tech.sosa.ingweb.domain.movie.MovieRepository;
import tech.sosa.ingweb.domain.movie.MovieTitle;

public class MovieRepositoryStub {
	
	public static MovieRepository withDummyData() {
		
		MovieRepository movieRepository = new InMemoryMovieRepository();
		
		Movie aMovie = new Movie(
				new MovieId(1L),
				new MovieTitle("M1"),
				new Genre("G1"),
				new Date(2020)
			);
		
		Movie anotherMovie = new Movie(
				new MovieId(2L),
				new MovieTitle("M2"),
				new Genre("G2"),
				new Date(2020)
			);
		
		Movie yetAnotherMovie = new Movie(
				new MovieId(3L),
				new MovieTitle("M3"),
				new Genre("G3"),
				new Date(2020)
			);
		
		Movie evenYetAnotherMovie = new Movie(
				new MovieId(4L),
				new MovieTitle("M4"),
				new Genre("G4"),
				new Date(2020)
			);
		
		movieRepository.add(aMovie);
		movieRepository.add(anotherMovie);
		movieRepository.add(yetAnotherMovie);
		movieRepository.add(evenYetAnotherMovie);
		
		return movieRepository;
	}
	
	public static MovieRepository with(Movie... someMovies) {
		
		MovieRepository movieRepository = new InMemoryMovieRepository();
		
		Stream.of(someMovies).forEach(movieRepository::add);
		
		return movieRepository;
	}

	public static MovieRepository withDummyAndSearchable(Movie searchableMovie) {
		
		MovieRepository movieRepository = withDummyData();
		
		movieRepository.add(searchableMovie);
		
		return movieRepository;
	}
	
}
