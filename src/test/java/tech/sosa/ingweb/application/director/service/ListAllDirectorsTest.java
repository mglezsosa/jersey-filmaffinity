package tech.sosa.ingweb.application.director.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import tech.sosa.ingweb.domain.director.Director;
import tech.sosa.ingweb.domain.director.DirectorFullName;
import tech.sosa.ingweb.domain.director.DirectorId;
import tech.sosa.ingweb.domain.director.DirectorRepository;
import tech.sosa.ingweb.infrastructure.persistence.director.DirectorRepositoryStub;

public class ListAllDirectorsTest {

	@Test
	public void shouldAllExistingDirectorsBeListed() {
		
		Director aDirector = new Director(
				new DirectorId(1L),
				new DirectorFullName("Peter Jackson")
			);
		
		Director anotherDirector = new Director(
				new DirectorId(2L),
				new DirectorFullName("Quentin Tarantino")
			);
		
		DirectorRepository repository = DirectorRepositoryStub.with(
					aDirector,
					anotherDirector
				);
		
		Collection<Director> expectedDirectors = new ArrayList<>();
		expectedDirectors.add(aDirector);
		expectedDirectors.add(anotherDirector);
		
		ListAllDirectors listAllDirectors = new ListAllDirectors(repository);
		Collection<Director> actualDirectors = listAllDirectors.execute();
		
		assertEquals(expectedDirectors, actualDirectors);
	}
	
}
