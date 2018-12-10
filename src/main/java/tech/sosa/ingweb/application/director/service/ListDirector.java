package tech.sosa.ingweb.application.director.service;

import tech.sosa.ingweb.domain.director.Director;
import tech.sosa.ingweb.domain.director.DirectorDoesNotExistException;
import tech.sosa.ingweb.domain.director.DirectorId;
import tech.sosa.ingweb.domain.director.DirectorRepository;

public class ListDirector {
	
	private DirectorRepository directorRepository;

	public ListDirector(DirectorRepository directorRepository) {
		this.directorRepository = directorRepository;
	}
	
	public Director execute(ListDirectorRequest request) {
		Director requestedDirector = directorRepository.ofId(
					new DirectorId(request.id)
				);
		if (requestedDirector == null) {
			throw new DirectorDoesNotExistException();
		}
		return requestedDirector;
	}
	
}
