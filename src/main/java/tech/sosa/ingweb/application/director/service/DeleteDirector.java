package tech.sosa.ingweb.application.director.service;

import tech.sosa.ingweb.domain.director.Director;
import tech.sosa.ingweb.domain.director.DirectorDoesNotExistException;
import tech.sosa.ingweb.domain.director.DirectorId;
import tech.sosa.ingweb.domain.director.DirectorRepository;

public class DeleteDirector {

	private DirectorRepository repository;

	public DeleteDirector(DirectorRepository repository) {
		this.repository = repository;
	}
	
	public void execute(DeleteDirectorRequest request) {
		Director requestedDirector = repository.ofId(new DirectorId(request.id));
		if (requestedDirector == null) {
			throw new DirectorDoesNotExistException();
		}
		repository.delete(requestedDirector);
	}
	
}
