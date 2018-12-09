package tech.sosa.ingweb.infrastructure.persistence.director;

import tech.sosa.ingweb.domain.director.DirectorRepository;

public class DirectorRepositoryStub {

	public static DirectorRepository empty() {
		return new InMemoryDirectorRepository();
	}
	
}
