package tech.sosa.ingweb.infrastructure.persistence.actor;

import java.util.stream.Stream;

import tech.sosa.ingweb.domain.actor.Actor;
import tech.sosa.ingweb.domain.actor.ActorFullName;
import tech.sosa.ingweb.domain.actor.ActorId;
import tech.sosa.ingweb.domain.actor.ActorRepository;

public class ActorRepositoryStub {

	public static ActorRepository empty() {
		return new InMemoryActorRepository();
	}

	public static ActorRepository with(Actor...actors) {
		ActorRepository repository = empty();
		
		Stream.of(actors).forEach(repository::add);
		
		return repository;
	}
	
	public static ActorRepository withDummyData() {
		return with(
			new Actor(new ActorId(1L), new ActorFullName("A1")),
			new Actor(new ActorId(2L), new ActorFullName("A2")),
			new Actor(new ActorId(3L), new ActorFullName("A3")),
			new Actor(new ActorId(4L), new ActorFullName("A4"))
		);
	}
	
	public static ActorRepository withDummyAndSearchable(Actor...actors) {
		ActorRepository repository = withDummyData();
		
		Stream.of(actors).forEach(repository::add);
		
		return repository;
	}

}
