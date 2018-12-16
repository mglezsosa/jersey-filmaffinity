package tech.sosa.ingweb.application.director.service;

import static org.junit.Assert.*;

import org.junit.Test;

import tech.sosa.ingweb.domain.director.Director;
import tech.sosa.ingweb.domain.director.DirectorDoesNotExistException;
import tech.sosa.ingweb.domain.director.DirectorFullName;
import tech.sosa.ingweb.domain.director.DirectorId;
import tech.sosa.ingweb.domain.director.DirectorRepository;
import tech.sosa.ingweb.infrastructure.persistence.director.DirectorRepositoryStub;

public class ListDirectorByIdTest {

	@Test
	public void shouldAnExistingDirectorBeFoundById() {
		
		Director expectedDirector = new Director(
					new DirectorId(1L),
					new DirectorFullName("Peter Jackson")
				);
		
		DirectorRepository directorRepository = DirectorRepositoryStub.with(
					new Director(
								new DirectorId(2L),
								new DirectorFullName("No idea who is this")
							),
					expectedDirector
				);
		
		ListDirectorRequest request = new ListDirectorRequest(
					1L
				);
		ListDirector listDirector = new ListDirector(directorRepository);
		Director actualDirector = listDirector.execute(request);
		
		assertEquals(expectedDirector.id(), actualDirector.id());
	}
	
	@Test(expected = DirectorDoesNotExistException.class)
	public void shouldANonExistingDirectorNotBeFoundAndAnExceptionBeThrown() {
		
		DirectorRepository directorRepository = DirectorRepositoryStub.with(
				new Director(
						new DirectorId(1L),
						new DirectorFullName("No idea who is this")
					),
				new Director(
						new DirectorId(2L),
						new DirectorFullName("No idea who is this")
					)
			);
		
		ListDirectorRequest request = new ListDirectorRequest(3L);
		
		ListDirector listDirector = new ListDirector(directorRepository);
		listDirector.execute(request);
	}

}
