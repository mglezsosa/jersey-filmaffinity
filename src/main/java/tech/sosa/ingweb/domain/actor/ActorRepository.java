package tech.sosa.ingweb.domain.actor;

import java.util.Collection;

public interface ActorRepository {

	public ActorId nextIdentity();

	public void add(Actor anActor);

	public void update(Actor anActor);

	public void delete(Actor anActor);

	public Collection<Actor> all();

	public Actor ofId(ActorId expectedActorId);
	
}
