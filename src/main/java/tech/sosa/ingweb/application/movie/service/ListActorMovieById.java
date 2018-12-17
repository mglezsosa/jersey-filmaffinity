package tech.sosa.ingweb.application.movie.service;

import tech.sosa.ingweb.domain.actor.Actor;
import tech.sosa.ingweb.domain.actor.ActorDoesNotExistException;
import tech.sosa.ingweb.domain.actor.ActorId;
import tech.sosa.ingweb.domain.actor.ActorRepository;
import tech.sosa.ingweb.domain.movie.Movie;
import tech.sosa.ingweb.domain.movie.MovieDoesNotExistException;
import tech.sosa.ingweb.domain.movie.MovieId;
import tech.sosa.ingweb.domain.movie.MovieRepository;

public class ListActorMovieById {

	private MovieRepository movieRepository;
	private ActorRepository actorRepository;
	
	public ListActorMovieById(MovieRepository movieRepository, ActorRepository actorRepository) {
		this.movieRepository = movieRepository;
		this.actorRepository = actorRepository;
	}

	public Movie execute(ListActorMovieByIdRequest request) {
		
		Actor requestedActor = actorRepository.ofId(new ActorId(request.actorId));
		if (requestedActor == null) {
			throw new ActorDoesNotExistException();
		}
		
		return findMovie(request, requestedActor);
	}

	private Movie findMovie(ListActorMovieByIdRequest request, Actor requestedActor) {
		Movie movie = movieRepository.ofActor(requestedActor).stream()
		.filter(m -> m.id().equals(new MovieId(request.movieId)))
		.findFirst()
		
		.orElseThrow(MovieDoesNotExistException::new);
		
		return movie;
	}
	
}
