package tech.sosa.ingweb.application.actor.service;

import static org.junit.Assert.*;

import org.junit.Test;

import tech.sosa.ingweb.domain.actor.Actor;
import tech.sosa.ingweb.domain.actor.ActorDoesNotExistException;
import tech.sosa.ingweb.domain.actor.ActorFullName;
import tech.sosa.ingweb.domain.actor.ActorId;
import tech.sosa.ingweb.domain.actor.ActorRepository;
import tech.sosa.ingweb.infrastructure.persistence.actor.ActorRepositoryStub;

public class UpdateActorTest {

	@Test
	public void shouldAnExistingActorBeUpdated() {
		
		ActorRepository repository = ActorRepositoryStub.with(
			new Actor(
				new ActorId(1L),
				new ActorFullName("Elija Wood") // Oops, misspelled
			)
		);
		
		String newFullName = "Elijah Wood";
		
		UpdateActorRequest request = new UpdateActorRequest(1L, newFullName);
		
		UpdateActor updateActor = new UpdateActor(repository);
		
		updateActor.execute(request);
		
		Actor updatedActor = repository.ofId(new ActorId(request.id));
		
		assertEquals(new ActorFullName(newFullName), updatedActor.fullName());
	}
	
	@Test(expected = ActorDoesNotExistException.class)
	public void shouldANonExistingActorNotBeUpdatedAndAnExceptionBeThrown() {
		ActorRepository repository = ActorRepositoryStub.empty();
		
		UpdateActorRequest request = new UpdateActorRequest(1L, "My new unused Full Name");
		
		UpdateActor updateActor = new UpdateActor(repository);
		
		updateActor.execute(request);
	}

}
