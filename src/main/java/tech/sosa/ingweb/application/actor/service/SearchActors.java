package tech.sosa.ingweb.application.actor.service;

import java.util.Collection;

import tech.sosa.ingweb.domain.actor.Actor;
import tech.sosa.ingweb.domain.actor.ActorRepository;

public class SearchActors {

	private ActorRepository repository;

	public SearchActors(ActorRepository repository) {
		this.repository = repository;
	}
	
	public Collection<Actor> execute(SearchActorsRequest request) {
		
		if (request.isEmpty()) return repository.all();
		
		return repository.ofSpecs(request.partialName);
	}

}
