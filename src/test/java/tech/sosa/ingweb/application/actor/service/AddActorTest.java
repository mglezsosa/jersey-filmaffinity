package tech.sosa.ingweb.application.actor.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import tech.sosa.ingweb.domain.actor.Actor;
import tech.sosa.ingweb.domain.actor.ActorId;
import tech.sosa.ingweb.domain.actor.ActorRepository;
import tech.sosa.ingweb.infrastructure.persistence.actor.ActorRepositoryStub;

public class AddActorTest {

	@Test
	public void shouldANonExisitingActorBeAdded() {
		
		ActorRepository actorRepository =  ActorRepositoryStub.empty();
		
		AddActorRequest request = new AddActorRequest(
					"Elijah Wood"
				);
		
		ActorId newId = actorRepository.nextIdentity();
		AddActor addDirector = new AddActor(actorRepository);
		
		addDirector.execute(request);
		
		Actor expectedActor = actorRepository.ofId(newId);
		assertEquals(expectedActor.id(), newId);
	}

}
