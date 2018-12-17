package tech.sosa.ingweb.application.movie.service;

import java.util.Collection;

import tech.sosa.ingweb.domain.actor.Actor;
import tech.sosa.ingweb.domain.actor.ActorDoesNotExistException;
import tech.sosa.ingweb.domain.actor.ActorId;
import tech.sosa.ingweb.domain.actor.ActorRepository;
import tech.sosa.ingweb.domain.movie.Movie;
import tech.sosa.ingweb.domain.movie.MovieRepository;

public class ListActorMovies {

	private MovieRepository movieRepository;
	private ActorRepository actorRepository;
	
	public ListActorMovies(MovieRepository movieRepository, ActorRepository actorRepository) {
		this.movieRepository = movieRepository;
		this.actorRepository = actorRepository;
	}

	public Collection<Movie> execute(ListActorMoviesRequest request) {
		Actor requestedActor = actorRepository.ofId(new ActorId(request.actorId));
		if (requestedActor == null) {
			throw new ActorDoesNotExistException();
		}
		
		return movieRepository.ofActor(requestedActor);
	}
	
}
