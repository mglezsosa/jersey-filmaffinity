package tech.sosa.ingweb.application.director.service;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import tech.sosa.ingweb.domain.director.Director;
import tech.sosa.ingweb.domain.director.DirectorFullName;
import tech.sosa.ingweb.domain.director.DirectorId;
import tech.sosa.ingweb.domain.director.DirectorRepository;
import tech.sosa.ingweb.infrastructure.persistence.director.DirectorRepositoryStub;

public class SearchDirectorsTest {

	@Test
	public void shouldSeveralExistingDirectorsBeFoundByPartialName() {
		
		Director aDirector = new Director(
				new DirectorId(10L),
				new DirectorFullName("Peter Jackson")
			);
		
		Director anotherDirector = new Director(
				new DirectorId(11L),
				new DirectorFullName("Peter Weir")
			);
		
		DirectorRepository repository = DirectorRepositoryStub.withDummyAndSearchable(
				aDirector,
				anotherDirector
			);
		
		SearchDirectorsRequest request = new SearchDirectorsRequest(
				"Peter"
			);
		
		SearchDirectors searchDirectors = new SearchDirectors(repository);
		Collection<Director> actualDirectors = searchDirectors.execute(request);
		
		Collection<Director> expectedDirectors = new ArrayList<>();
		expectedDirectors.add(aDirector);
		expectedDirectors.add(anotherDirector);
		
		assertEquals(expectedDirectors, actualDirectors);
	}
	
	@Test(expected = InvalidParameterException.class)
	public void shouldAnExceptionBeThrownWhenQueryingWithEmptyParameters() {
		
		DirectorRepository repository = DirectorRepositoryStub.withDummyAndSearchable();
		SearchDirectorsRequest request = new SearchDirectorsRequest(
					null
				);
		
		SearchDirectors searchDirectors = new SearchDirectors(repository);
		searchDirectors.execute(request);
	}

}
