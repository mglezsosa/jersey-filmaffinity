package tech.sosa.ingweb.application.actor.service;

import static java.util.stream.Collectors.toList;

import java.util.Collection;

import tech.sosa.ingweb.domain.actor.Actor;
import tech.sosa.ingweb.domain.actor.ActorRepository;

public class SearchActors {

	private ActorRepository repository;

	public SearchActors(ActorRepository repository) {
		this.repository = repository;
	}
	
	public Collection<Actor> execute(SearchActorsRequest request) {
		
		Collection<Actor> actors = repository.all();
		
		if (request.isEmpty()) return actors;
		
		actors = filterActors(request, actors);
		
		return actors;
	}

	private Collection<Actor> filterActors(SearchActorsRequest request, Collection<Actor> actors) {
		return actors.stream()
				.filter(a -> a.fullName().toString().toLowerCase().contains(request.partialName.toLowerCase()))
				.collect(toList());
	}

}
