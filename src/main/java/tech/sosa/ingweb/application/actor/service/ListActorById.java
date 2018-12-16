package tech.sosa.ingweb.application.actor.service;

import tech.sosa.ingweb.domain.actor.Actor;
import tech.sosa.ingweb.domain.actor.ActorDoesNotExistException;
import tech.sosa.ingweb.domain.actor.ActorId;
import tech.sosa.ingweb.domain.actor.ActorRepository;

public class ListActorById {

	private ActorRepository repository;

	public ListActorById(ActorRepository repository) {
		this.repository = repository;
	}

	public Actor execute(ListActorByIdRequest request) {
		Actor requestedActor = repository.ofId(new ActorId(request.id));
		if (requestedActor == null) {
			throw new ActorDoesNotExistException();
		}
		return requestedActor;
	}
}
