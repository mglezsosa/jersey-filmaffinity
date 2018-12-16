package tech.sosa.ingweb.application.actor.service;

import tech.sosa.ingweb.domain.actor.Actor;
import tech.sosa.ingweb.domain.actor.ActorFullName;
import tech.sosa.ingweb.domain.actor.ActorRepository;

public class AddActor {

	private ActorRepository repository;

	public AddActor(ActorRepository repository) {
		this.repository = repository;
	}
	
	public void execute(AddActorRequest request) {
		repository.add(
			new Actor(
				repository.nextIdentity(),
				new ActorFullName(request.fullName)
			)
		);
	}
	
}
