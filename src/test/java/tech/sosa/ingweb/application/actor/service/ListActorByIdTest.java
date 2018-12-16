package tech.sosa.ingweb.application.actor.service;

import static org.junit.Assert.*;

import org.junit.Test;

import tech.sosa.ingweb.domain.actor.Actor;
import tech.sosa.ingweb.domain.actor.ActorDoesNotExistException;
import tech.sosa.ingweb.domain.actor.ActorFullName;
import tech.sosa.ingweb.domain.actor.ActorId;
import tech.sosa.ingweb.domain.actor.ActorRepository;
import tech.sosa.ingweb.infrastructure.persistence.actor.ActorRepositoryStub;

public class ListActorByIdTest {

	@Test
	public void shouldAnExistingDirectorBeFoundById() {
		
		Actor requestedActor = new Actor(new ActorId(1L), new ActorFullName("Elijah Wood"));
		
		ActorRepository repository = ActorRepositoryStub.with(requestedActor);
		
		ListActorByIdRequest request = new ListActorByIdRequest(1L);
		
		ListActorById listActorById = new ListActorById(repository);
		
		Actor actualActor = listActorById.execute(request);
		
		assertEquals(requestedActor, actualActor);
	}
	
	@Test(expected = ActorDoesNotExistException.class)
	public void shouldANonExistingActorNotBeFoundAndAnExceptionBeThrown() {
		
		ActorRepository repository = ActorRepositoryStub.with(
			new Actor(new ActorId(1L), new ActorFullName("No idea who is this")),
			new Actor(new ActorId(2L), new ActorFullName("No idea who is this"))
		);
		
		ListActorByIdRequest request = new ListActorByIdRequest(3L);
		
		ListActorById listActorById = new ListActorById(repository);
		
		listActorById.execute(request);
	}

}
