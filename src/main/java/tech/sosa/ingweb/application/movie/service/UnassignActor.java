package tech.sosa.ingweb.application.movie.service;

import tech.sosa.ingweb.domain.actor.Actor;
import tech.sosa.ingweb.domain.actor.ActorDoesNotExistException;
import tech.sosa.ingweb.domain.actor.ActorId;
import tech.sosa.ingweb.domain.actor.ActorRepository;
import tech.sosa.ingweb.domain.movie.Movie;
import tech.sosa.ingweb.domain.movie.MovieDoesNotExistException;
import tech.sosa.ingweb.domain.movie.MovieId;
import tech.sosa.ingweb.domain.movie.MovieRepository;

public class UnassignActor {

	private MovieRepository movieRepository;
	private ActorRepository actorRepository;
	
	public UnassignActor(MovieRepository movieRepository, ActorRepository actorRepository) {
		this.movieRepository = movieRepository;
		this.actorRepository = actorRepository;
	}

	public void execute(UnassignActorRequest request) {
		Movie movieToUpdate = movieRepository.ofId(new MovieId(request.movieId));
		if (movieToUpdate == null) {
			throw new MovieDoesNotExistException();
		}
		
		Actor actorToUnassign = actorRepository.ofId(new ActorId(request.actorId));
		if (actorToUnassign == null) {
			throw new ActorDoesNotExistException();
		}
		
		movieToUpdate.unassignActor(actorToUnassign);
		movieRepository.update(movieToUpdate);
	}
	
}
