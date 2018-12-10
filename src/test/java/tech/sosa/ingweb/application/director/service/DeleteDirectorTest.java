package tech.sosa.ingweb.application.director.service;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

import tech.sosa.ingweb.domain.director.Director;
import tech.sosa.ingweb.domain.director.DirectorDoesNotExistException;
import tech.sosa.ingweb.domain.director.DirectorFullName;
import tech.sosa.ingweb.domain.director.DirectorId;
import tech.sosa.ingweb.domain.director.DirectorRepository;
import tech.sosa.ingweb.infrastructure.persistence.director.DirectorRepositoryStub;

public class DeleteDirectorTest {

	@Test
	public void shouldAnExistentDirectorBeDeleted() {
		
		DirectorRepository repository = DirectorRepositoryStub.with(
					new Director(
						new DirectorId(1L),
						new DirectorFullName("Peter Jackson")
					)
				);
		
		DeleteDirectorRequest request = new DeleteDirectorRequest(
				1L
			);
	
		DeleteDirector deleteDirector = new DeleteDirector(repository);
		deleteDirector.execute(request);
		
		Collection<Director> remainingDirectors = repository.all();
		
		assertTrue(remainingDirectors.isEmpty());
	}
	
	@Test(expected = DirectorDoesNotExistException.class)
	public void shouldANonExistentDirectorNotBeDeletedAndAnExceptionBeThrown() {
		
		DirectorRepository repository = DirectorRepositoryStub.empty();
		
		DeleteDirectorRequest request = new DeleteDirectorRequest(
				1L
			);
	
		DeleteDirector deleteDirector = new DeleteDirector(repository);
		deleteDirector.execute(request);
	}

}
