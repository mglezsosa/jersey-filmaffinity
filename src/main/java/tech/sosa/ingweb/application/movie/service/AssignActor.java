package tech.sosa.ingweb.application.movie.service;

import tech.sosa.ingweb.domain.actor.Actor;
import tech.sosa.ingweb.domain.actor.ActorDoesNotExistException;
import tech.sosa.ingweb.domain.actor.ActorId;
import tech.sosa.ingweb.domain.actor.ActorRepository;
import tech.sosa.ingweb.domain.movie.Movie;
import tech.sosa.ingweb.domain.movie.MovieDoesNotExistException;
import tech.sosa.ingweb.domain.movie.MovieId;
import tech.sosa.ingweb.domain.movie.MovieRepository;

public class AssignActor {

	private MovieRepository movieRepository;
	private ActorRepository actorRepository;
	
	public AssignActor(MovieRepository movieRepository, ActorRepository actorRepository) {
		this.movieRepository = movieRepository;
		this.actorRepository = actorRepository;
	}

	public void execute(AssignActorRequest request) {
		Movie movieToUpdate = movieRepository.ofId(new MovieId(request.movieId));
		if (movieToUpdate == null) {
			throw new MovieDoesNotExistException();
		}
		
		Actor actorToAssign = actorRepository.ofId(new ActorId(request.actorId));
		if (actorToAssign == null) {
			throw new ActorDoesNotExistException();
		}
		
		movieToUpdate.assignActor(actorToAssign);
		movieRepository.update(movieToUpdate);
	}
	
}
