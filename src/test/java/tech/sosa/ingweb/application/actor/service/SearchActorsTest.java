package tech.sosa.ingweb.application.actor.service;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

import tech.sosa.ingweb.domain.actor.Actor;
import tech.sosa.ingweb.domain.actor.ActorFullName;
import tech.sosa.ingweb.domain.actor.ActorId;
import tech.sosa.ingweb.domain.actor.ActorRepository;
import tech.sosa.ingweb.infrastructure.persistence.actor.ActorRepositoryStub;

public class SearchActorsTest {

	@Test
	public void shouldAllActorsBeListedIfRequestIsEmpty() {
		Actor anActor = new Actor(new ActorId(1L), new ActorFullName("Robert De Niro"));
		Actor anotherActor = new Actor(new ActorId(2L), new ActorFullName("Sylvester Stallone"));
		Actor yerAnotherActor = new Actor(new ActorId(3L), new ActorFullName("Arnold Schwartzenegger"));

		ActorRepository repository = ActorRepositoryStub.with(
			anActor,
			anotherActor,
			yerAnotherActor
		);
		
		Collection<Actor> allActors = new SearchActors(repository).execute(new SearchActorsRequest());
		
		assertEquals(Arrays.asList(anActor,
			anotherActor,
			yerAnotherActor), allActors);
	}
	
	@Test
	public void shouldAnActorBeFoundByExactName() {
		
		Actor anActor = new Actor(new ActorId(5L), new ActorFullName("Robert De Niro"));
		Actor anotherActor = new Actor(new ActorId(6L), new ActorFullName("Robert Duvall"));
		
		ActorRepository repository = ActorRepositoryStub.withDummyAndSearchable(anActor, anotherActor);
		
		Collection<Actor> expectedActors = Arrays.asList(anActor);
		
		SearchActorsRequest request = new SearchActorsRequest("Robert De Niro");
		
		SearchActors searchActors = new SearchActors(repository);
		
		Collection<Actor> actualActors = searchActors.execute(request);
		
		assertEquals(expectedActors, actualActors);
	}
	
	@Test
	public void shouldSeveralExistingActorsBeFoundByPartialName() {
		
		Actor anActor = new Actor(new ActorId(5L), new ActorFullName("Robert De Niro"));
		Actor anotherActor = new Actor(new ActorId(6L), new ActorFullName("Robert Duvall"));
		
		ActorRepository repository = ActorRepositoryStub.withDummyAndSearchable(anActor, anotherActor);
		
		Collection<Actor> expectedActors = Arrays.asList(anActor, anotherActor);
		
		SearchActorsRequest request = new SearchActorsRequest("robert");
		
		SearchActors searchActors = new SearchActors(repository);
		
		Collection<Actor> actualActors = searchActors.execute(request);
		
		assertEquals(expectedActors, actualActors);
	}

}
