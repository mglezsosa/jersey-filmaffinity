package tech.sosa.ingweb.application.actor.service;

import tech.sosa.ingweb.domain.actor.Actor;
import tech.sosa.ingweb.domain.actor.ActorDoesNotExistException;
import tech.sosa.ingweb.domain.actor.ActorId;
import tech.sosa.ingweb.domain.actor.ActorRepository;

public class DeleteActor {

	private ActorRepository repository;

	public DeleteActor(ActorRepository repository) {
		this.repository = repository;
	}

	public void execute(DeleteActorRequest request) {
		Actor requestedActor = repository.ofId(new ActorId(request.id));
		if (requestedActor == null) {
			throw new ActorDoesNotExistException();
		}
		repository.delete(requestedActor);
	}
	
}
