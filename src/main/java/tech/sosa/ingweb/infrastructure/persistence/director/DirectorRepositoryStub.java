package tech.sosa.ingweb.infrastructure.persistence.director;

import java.util.stream.Stream;

import tech.sosa.ingweb.domain.director.Director;
import tech.sosa.ingweb.domain.director.DirectorFullName;
import tech.sosa.ingweb.domain.director.DirectorId;
import tech.sosa.ingweb.domain.director.DirectorRepository;

public class DirectorRepositoryStub {

	public static DirectorRepository empty() {
		return new InMemoryDirectorRepository();
	}
	
	public static DirectorRepository with(Director... directors) {
		DirectorRepository repository = empty();
		Stream.of(directors).forEach(repository::add);
		return repository;
	}
	
	public static DirectorRepository withDummyData() {
		
		DirectorRepository repository = new InMemoryDirectorRepository();
		repository.add(new Director(
				new DirectorId(1L),
				new DirectorFullName("D1")
			));
		repository.add(new Director(
				new DirectorId(2L),
				new DirectorFullName("D2")
			));
		repository.add(new Director(
				new DirectorId(3L),
				new DirectorFullName("D3")
			));
		repository.add(new Director(
				new DirectorId(4L),
				new DirectorFullName("D4")
			));
		return repository;
	}

	public static DirectorRepository withDummyAndSearchable(Director... directors) {
		
		DirectorRepository repository = withDummyData();
		Stream.of(directors).forEach(repository::add);
		return repository;
	}
	
}
