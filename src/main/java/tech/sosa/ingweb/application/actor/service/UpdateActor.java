package tech.sosa.ingweb.application.actor.service;

import tech.sosa.ingweb.domain.actor.Actor;
import tech.sosa.ingweb.domain.actor.ActorDoesNotExistException;
import tech.sosa.ingweb.domain.actor.ActorFullName;
import tech.sosa.ingweb.domain.actor.ActorId;
import tech.sosa.ingweb.domain.actor.ActorRepository;

public class UpdateActor {
	
	private ActorRepository repository;

	public UpdateActor(ActorRepository repository) {
		this.repository = repository;
	}

	public void execute(UpdateActorRequest request) {
		Actor requestedActor = repository.ofId(new ActorId(request.id));
		if (requestedActor == null) {
			throw new ActorDoesNotExistException();
		}
		repository.update(new Actor(new ActorId(request.id), new ActorFullName(request.fullName)));
	}
	
}
