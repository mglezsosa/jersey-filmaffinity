package tech.sosa.ingweb.application.director.service;

import static org.junit.Assert.*;

import org.junit.Test;

import tech.sosa.ingweb.application.director.service.AddDirectorRequest;
import tech.sosa.ingweb.domain.director.Director;
import tech.sosa.ingweb.domain.director.DirectorId;
import tech.sosa.ingweb.domain.director.DirectorRepository;
import tech.sosa.ingweb.infrastructure.persistence.director.DirectorRepositoryStub;

public class AddDirectorTest {

	@Test
	public void shouldANonExisitingDirectorBeAdded() {
		
		DirectorRepository directorRepository =  DirectorRepositoryStub.empty();
		
		AddDirectorRequest request = new AddDirectorRequest(
					"Peter Jackson"
				);
		
		DirectorId newId = directorRepository.nextIdentity();
		AddDirector addDirector = new AddDirector(directorRepository);
		
		addDirector.execute(request);
		
		Director expectedDirector = directorRepository.ofId(newId);
		assertEquals(expectedDirector.id(), newId);
	}

}
