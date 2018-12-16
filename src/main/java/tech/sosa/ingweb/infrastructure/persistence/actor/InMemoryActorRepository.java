package tech.sosa.ingweb.infrastructure.persistence.actor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import tech.sosa.ingweb.domain.actor.Actor;
import tech.sosa.ingweb.domain.actor.ActorId;
import tech.sosa.ingweb.domain.actor.ActorRepository;

public class InMemoryActorRepository implements ActorRepository {
	
	private Map<ActorId, Actor> actors;

	public InMemoryActorRepository() {
		this.actors = new HashMap<>();
	}

	@Override
	public ActorId nextIdentity() {
		Long newIdentity = Long.valueOf(actors.size() + 1);
		return new ActorId(newIdentity);
	}

	@Override
	public void add(Actor anActor) {
		actors.put(anActor.id(), anActor);
	}

	@Override
	public void update(Actor anActor) {
		actors.put(anActor.id(), anActor);
	}

	@Override
	public void delete(Actor anActor) {
		actors.remove(anActor.id());
	}

	@Override
	public Collection<Actor> all() {
		return new ArrayList<Actor>(actors.values());
	}

	@Override
	public Actor ofId(ActorId expectedActorId) {
		return actors.get(expectedActorId);
	}

}
