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
				new MovieTitle("Movie1"),
				new Genre("Drama"),
				new Date(2018)
			);
		
		Movie anotherMovie = new Movie(
				new MovieId(2L),
				new MovieTitle("Movie2"),
				new Genre("Thriller"),
				new Date(2018)
			);
		
		Movie yetAnotherMovie = new Movie(
				new MovieId(3L),
				new MovieTitle("Movie3"),
				new Genre("Musical"),
				new Date(2017)
			);
		
		movieRepository.add(aMovie);
		movieRepository.add(anotherMovie);
		movieRepository.add(yetAnotherMovie);
		
		return movieRepository;
	}
	
	public static MovieRepository with(Movie... someMovies) {
		
		MovieRepository movieRepository = new InMemoryMovieRepository();
		
		Stream.of(someMovies).forEach(movieRepository::add);
		
		return movieRepository;
	}
	
}
