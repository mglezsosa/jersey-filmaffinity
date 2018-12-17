package tech.sosa.ingweb.application.actor.service;

import tech.sosa.ingweb.domain.actor.Actor;
import tech.sosa.ingweb.domain.actor.ActorDoesNotExistException;
import tech.sosa.ingweb.domain.actor.ActorId;
import tech.sosa.ingweb.domain.movie.Movie;
import tech.sosa.ingweb.domain.movie.MovieDoesNotExistException;
import tech.sosa.ingweb.domain.movie.MovieId;
import tech.sosa.ingweb.domain.movie.MovieRepository;

public class ListMovieActorById {

	private MovieRepository movieRepository;
	
	public ListMovieActorById(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	public Actor execute(ListMovieActorByIdRequest request) {
		
		Movie requestedMovie = movieRepository.ofId(new MovieId(request.movieId));
		if (requestedMovie == null) {
			throw new MovieDoesNotExistException();
		}
		
		return findActor(request, requestedMovie);
	}

	private Actor findActor(ListMovieActorByIdRequest request, Movie requestedMovie) {
		return requestedMovie.getActors().stream()
				.filter(a -> a.id().equals(new ActorId(request.actorId)))
				.findFirst()
				
				.orElseThrow(ActorDoesNotExistException::new);
	}
	
}
