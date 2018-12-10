package tech.sosa.ingweb.application.director.service;

import tech.sosa.ingweb.domain.director.Director;
import tech.sosa.ingweb.domain.director.DirectorDoesNotExistException;
import tech.sosa.ingweb.domain.director.DirectorFullName;
import tech.sosa.ingweb.domain.director.DirectorId;
import tech.sosa.ingweb.domain.director.DirectorRepository;

public class UpdateDirector {

	private DirectorRepository repository;

	public UpdateDirector(DirectorRepository repository) {
		this.repository = repository;
	}
	
	public void execute(UpdateDirectorRequest request) {
		Director requestedDirector = repository.ofId(new DirectorId(request.id));
		if (requestedDirector == null) {
			throw new DirectorDoesNotExistException();
		}
		repository.update(new Director(
					new DirectorId(request.id),
					new DirectorFullName(request.fullName)
				));
	}
	
}
