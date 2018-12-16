package tech.sosa.ingweb.application.director.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

import tech.sosa.ingweb.domain.director.Director;
import tech.sosa.ingweb.domain.director.DirectorFullName;
import tech.sosa.ingweb.domain.director.DirectorId;
import tech.sosa.ingweb.domain.director.DirectorRepository;
import tech.sosa.ingweb.infrastructure.persistence.director.DirectorRepositoryStub;

public class SearchDirectorsTest {

	@Test
	public void shouldAllDirectorsBeListedIfRequestIsEmpty() {
		
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
		
		Collection<Director> actualDirectors = new SearchDirectors(repository).execute(new SearchDirectorsRequest());
		
		assertEquals(Arrays.asList(
				aDirector,
				anotherDirector), actualDirectors);
	}
	
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

}
