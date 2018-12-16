package tech.sosa.ingweb.application.actor.service;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

import tech.sosa.ingweb.domain.actor.Actor;
import tech.sosa.ingweb.domain.actor.ActorDoesNotExistException;
import tech.sosa.ingweb.domain.actor.ActorFullName;
import tech.sosa.ingweb.domain.actor.ActorId;
import tech.sosa.ingweb.domain.actor.ActorRepository;
import tech.sosa.ingweb.infrastructure.persistence.actor.ActorRepositoryStub;

public class DeleteActorTest {

	@Test
	public void shouldAnExistentActorBeDeleted() {
		
		ActorRepository actorRepository = ActorRepositoryStub.with(
			new Actor(
				new ActorId(1L),
				new ActorFullName("Elijah Wood")
			)
		);
		
		DeleteActorRequest request = new DeleteActorRequest(1L);
		
		DeleteActor deleteActor = new DeleteActor(actorRepository);
		
		deleteActor.execute(request);
		
		Collection<Actor> remainingActors = actorRepository.all();
		
		assertTrue(remainingActors.isEmpty());
	}

	@Test(expected = ActorDoesNotExistException.class)
	public void shouldANonExistentActorNotBeDeletedAndAnExceptionBeThrown() {
		
		ActorRepository repository = ActorRepositoryStub.empty();
		
		DeleteActorRequest request = new DeleteActorRequest(1L);
		
		DeleteActor deleteActor = new DeleteActor(repository);
		
		deleteActor.execute(request);
	}
}
