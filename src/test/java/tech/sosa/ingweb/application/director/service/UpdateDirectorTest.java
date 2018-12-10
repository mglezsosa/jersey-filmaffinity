package tech.sosa.ingweb.application.director.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import tech.sosa.ingweb.domain.director.Director;
import tech.sosa.ingweb.domain.director.DirectorDoesNotExistException;
import tech.sosa.ingweb.domain.director.DirectorFullName;
import tech.sosa.ingweb.domain.director.DirectorId;
import tech.sosa.ingweb.domain.director.DirectorRepository;
import tech.sosa.ingweb.infrastructure.persistence.director.DirectorRepositoryStub;

public class UpdateDirectorTest {

	@Test
	public void shouldAnExistingDirectorBeUpdated() {
		
		DirectorRepository repository = DirectorRepositoryStub.with(
					new Director(
							new DirectorId(1L),
							new DirectorFullName("Qentin Tarantino") // Oops, misspelled
					)
				);
		
		UpdateDirectorRequest request = new UpdateDirectorRequest(
				1L,
				"Quentin Tarantino"
			);
	
		UpdateDirector updateDirector = new UpdateDirector(repository);
		updateDirector.execute(request);
		
		Director updatedDirector = repository.ofId(new DirectorId(request.id));
		
		assertEquals(new DirectorFullName("Quentin Tarantino"), updatedDirector.fullName());
	}
	
	@Test(expected = DirectorDoesNotExistException.class)
	public void shouldANonExistingDirectorNotBeUpdatedAndAnExceptionBeThrown() {
		DirectorRepository repository = DirectorRepositoryStub.empty();
		
		UpdateDirectorRequest request = new UpdateDirectorRequest(
				1L,
				"Quentin Tarantino"
			);
		
		UpdateDirector updateDirector = new UpdateDirector(repository);
		updateDirector.execute(request);
	}

}
